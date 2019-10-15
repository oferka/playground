package com.example.controller.api.rest.language;

import com.example.model.language.Language;
import com.example.model.language.Language.Code;
import com.example.service.language.LanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@RestController
@RequestMapping(path = LanguageController.LANGUAGES_PATH)
public class LanguageController {

    static final String LANGUAGES_PATH = "languages";
    static final String FIND_BY_NAME_PATH = "findByName";
    static final String FIND_BY_CODE_PATH = "findByCode";
    static final String LANGUAGE_ID_PARAM_NAME = "languageId";
    static final String LANGUAGE_NAME_PARAM_NAME = "name";
    static final String LANGUAGE_CODE_PARAM_NAME = "code";
    static final String INVALID_REQUEST_ARGUMENT_REASON = "Request data validation error. The provided data violates the validation constraints defined for a Language";

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping(path=EMPTY)
    public ResponseEntity<Iterable<Language>> findAllItems() {
        return ResponseEntity.ok(languageService.findAllItems());
    }

    @GetMapping(path="/{" + LANGUAGE_ID_PARAM_NAME + "}")
    public ResponseEntity<Language> findItemById(@PathVariable Long languageId) {
        Optional<Language> item = languageService.findItemById(languageId);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path="/" + FIND_BY_NAME_PATH)
    public ResponseEntity<Iterable<Language>> findItemsByName(@RequestParam(name = LANGUAGE_NAME_PARAM_NAME) String name) {
        return ResponseEntity.ok(languageService.findItemsByName(name));
    }

    @GetMapping(path="/" + FIND_BY_CODE_PATH)
    public ResponseEntity<Iterable<Language>> findItemsByCode(@RequestParam(name = LANGUAGE_CODE_PARAM_NAME) Code code) {
        return ResponseEntity.ok(languageService.findItemsByCode(code));
    }

    @PostMapping(path=EMPTY)
    public ResponseEntity<URI> createItem(@RequestBody @Valid Language item) {
        Language savedItem = languageService.createItem(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedItem.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path="/{" + LANGUAGE_ID_PARAM_NAME + "}")
    public ResponseEntity<Void> updateItem(@RequestBody @Valid Language item, @PathVariable Long languageId) {
        Optional<Language> savedItem = languageService.findItemById(languageId);
        if(!savedItem.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        item.setId(savedItem.get().getId());
        languageService.updateItem(item);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path="/{" + LANGUAGE_ID_PARAM_NAME + "}")
    public ResponseEntity<Void> deleteItem(@PathVariable(name = LANGUAGE_ID_PARAM_NAME) Long languageId) {
        languageService.deleteItemById(languageId);
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
