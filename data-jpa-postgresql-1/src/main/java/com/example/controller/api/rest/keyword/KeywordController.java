package com.example.controller.api.rest.keyword;

import com.example.model.keyword.Keyword;
import com.example.service.keyword.KeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@RestController
@RequestMapping(path = KeywordController.KEYWORDS_PATH)
public class KeywordController {

    static final String KEYWORDS_PATH = "keywords";
    static final String FIND_BY_TEXT_PATH = "findByText";
    static final String FIND_BY_DATE_DEFINED_PATH = "findByDateDefined";
    static final String FIND_BY_LANGUAGE_NAME_PATH = "findByLanguageName";
    private static final String KEYWORD_ID_PARAM_NAME = "keywordId";
    static final String KEYWORD_TEXT_PARAM_NAME = "text";
    static final String KEYWORD_DATE_DEFINED_PARAM_NAME = "dateDefined";
    static final String KEYWORD_LANGUAGE_NAME_PARAM_NAME = "name";
    static final String INVALID_REQUEST_ARGUMENT_REASON = "Request data validation error. The provided data violates the validation constraints defined for a Keyword";

    private final KeywordService keywordService;

    @Autowired
    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping(path=EMPTY)
    public ResponseEntity<Iterable<Keyword>> findAllItems() {
        return ResponseEntity.ok(keywordService.findAllItems());
    }

    @GetMapping(path="/{" + KEYWORD_ID_PARAM_NAME + "}")
    public ResponseEntity<Keyword> findItemById(@PathVariable Long keywordId) {
        Optional<Keyword> item = keywordService.findItemById(keywordId);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path="/" + FIND_BY_TEXT_PATH)
    public ResponseEntity<Iterable<Keyword>> findItemsByText(@RequestParam(name = KEYWORD_TEXT_PARAM_NAME) String text) {
        return ResponseEntity.ok(keywordService.findItemsByText(text));
    }

    @GetMapping(path="/" + FIND_BY_DATE_DEFINED_PATH)
    public ResponseEntity<Iterable<Keyword>> findItemsByDateDefined(@RequestParam(name = KEYWORD_DATE_DEFINED_PARAM_NAME) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDefined) {
        return ResponseEntity.ok(keywordService.findItemsByDateDefined(dateDefined));
    }

    @GetMapping(path="/" + FIND_BY_LANGUAGE_NAME_PATH)
    public ResponseEntity<Iterable<Keyword>> findItemsByLanguageName(@RequestParam(name = KEYWORD_LANGUAGE_NAME_PARAM_NAME) String name) {
        return ResponseEntity.ok(keywordService.findItemsByLanguageName(name));
    }

    @PostMapping(path=EMPTY)
    public ResponseEntity<URI> createItem(@RequestBody @Valid Keyword item) {
        Keyword savedItem = keywordService.createItem(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedItem.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path="/{" + KEYWORD_ID_PARAM_NAME + "}")
    public ResponseEntity<Void> updateItem(@RequestBody @Valid Keyword item, @PathVariable Long keywordId) {
        Optional<Keyword> savedItem = keywordService.findItemById(keywordId);
        if(savedItem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        item.setId(savedItem.get().getId());
        keywordService.updateItem(item);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason=INVALID_REQUEST_ARGUMENT_REASON)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
