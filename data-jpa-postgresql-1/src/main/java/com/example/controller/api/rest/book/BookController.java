package com.example.controller.api.rest.book;

import com.example.model.book.Book;
import com.example.model.book.Format;
import com.example.model.language.Language.Code;
import com.example.service.book.BookService;
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

import static com.example.controller.api.rest.book.BookController.BOOKS_PATH;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@RestController
@RequestMapping(path = BOOKS_PATH)
public class BookController {

    static final String BOOKS_PATH = "books";
    static final String FIND_BY_TITLE_PATH = "findByTitle";
    static final String FIND_BY_DATE_PUBLISHED_PATH = "findByDatePublished";
    static final String FIND_BY_NUMBER_OF_PAGES_PATH = "findByNumberOfPages";
    static final String FIND_BY_FAMILY_FRIENDLY_PATH = "findByFamilyFriendly";
    static final String FIND_BY_ISBN_PATH = "findByIsbn";
    static final String FIND_BY_FORMAT_PATH = "findByFormat";
    static final String FIND_BY_LANGUAGE_ID_PATH = "findByLanguageId";
    static final String FIND_BY_LANGUAGE_NAME_PATH = "findByLanguageName";
    static final String FIND_BY_LANGUAGE_CODE_PATH = "findByLanguageCode";
    static final String FIND_BY_KEYWORDS_ID_PATH = "findByKeywordsId";
    static final String FIND_BY_KEYWORDS_TEXT_PATH = "findByKeywordsText";
    static final String FIND_BY_KEYWORDS_DATE_DEFINED_PATH = "findByKeywordsDateDefined";
    static final String FIND_BY_KEYWORDS_LANGUAGE_ID_PATH = "findByKeywordsLanguageId";
    static final String FIND_BY_KEYWORDS_LANGUAGE_NAME_PATH = "findByKeywordsLanguageName";
    static final String FIND_BY_KEYWORDS_LANGUAGE_CODE_PATH = "findByKeywordsLanguageCode";
    private static final String BOOK_ID_PARAM_NAME = "bookId";
    static final String BOOK_TITLE_PARAM_NAME = "title";
    static final String BOOK_DATE_PUBLISHED_PARAM_NAME = "datePublished";
    static final String BOOK_NUMBER_OF_PAGES_PARAM_NAME = "numberOfPages";
    static final String BOOK_FAMILY_FRIENDLY_PARAM_NAME = "familyFriendly";
    static final String BOOK_ISBN_PARAM_NAME = "isbn";
    static final String BOOK_FORMAT_PARAM_NAME = "format";
    static final String BOOK_LANGUAGE_ID_PARAM_NAME = "id";
    static final String BOOK_LANGUAGE_NAME_PARAM_NAME = "name";
    static final String BOOK_LANGUAGE_CODE_PARAM_NAME = "code";
    static final String BOOK_KEYWORDS_ID_PARAM_NAME = "id";
    static final String BOOK_KEYWORDS_TEXT_PARAM_NAME = "text";
    static final String BOOK_KEYWORDS_DATE_DEFINED_PARAM_NAME = "dateDefined";
    static final String BOOK_KEYWORDS_LANGUAGE_ID_PARAM_NAME = "id";
    static final String BOOK_KEYWORDS_LANGUAGE_NAME_PARAM_NAME = "name";
    static final String BOOK_KEYWORDS_LANGUAGE_CODE_PARAM_NAME = "code";
    static final String INVALID_REQUEST_ARGUMENT_REASON = "Request data validation error. The provided data violates the validation constraints defined for a Book";

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path=EMPTY)
    public ResponseEntity<Iterable<Book>> findAllItems() {
        return ResponseEntity.ok(bookService.findAllItems());
    }

    @GetMapping(path="/{" + BOOK_ID_PARAM_NAME + "}")
    public ResponseEntity<Book> findItemById(@PathVariable Long bookId) {
        Optional<Book> item = bookService.findItemById(bookId);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path="/" + FIND_BY_TITLE_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByTitle(@RequestParam(name = BOOK_TITLE_PARAM_NAME) String title) {
        return ResponseEntity.ok(bookService.findItemsByTitle(title));
    }

    @GetMapping(path="/" + FIND_BY_DATE_PUBLISHED_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByDatePublished(@RequestParam(name = BOOK_DATE_PUBLISHED_PARAM_NAME) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePublished) {
        return ResponseEntity.ok(bookService.findItemsByDatePublished(datePublished));
    }

    @GetMapping(path="/" + FIND_BY_NUMBER_OF_PAGES_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByNumberOfPages(@RequestParam(name = BOOK_NUMBER_OF_PAGES_PARAM_NAME) Integer numberOfPages) {
        return ResponseEntity.ok(bookService.findItemsByNumberOfPages(numberOfPages));
    }

    @GetMapping(path="/" + FIND_BY_FAMILY_FRIENDLY_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByFamilyFriendly(@RequestParam(name = BOOK_FAMILY_FRIENDLY_PARAM_NAME) Boolean familyFriendly) {
        return ResponseEntity.ok(bookService.findItemsByFamilyFriendly(familyFriendly));
    }

    @GetMapping(path="/" + FIND_BY_ISBN_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByIsbn(@RequestParam(name = BOOK_ISBN_PARAM_NAME) String isbn) {
        return ResponseEntity.ok(bookService.findItemsByIsbn(isbn));
    }

    @GetMapping(path="/" + FIND_BY_FORMAT_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByFormat(@RequestParam(name = BOOK_FORMAT_PARAM_NAME) Format format) {
        return ResponseEntity.ok(bookService.findItemsByFormat(format));
    }

    @GetMapping(path="/" + FIND_BY_LANGUAGE_ID_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByLanguageId(@RequestParam(name = BOOK_LANGUAGE_ID_PARAM_NAME) Long id) {
        return ResponseEntity.ok(bookService.findItemsByLanguageId(id));
    }

    @GetMapping(path="/" + FIND_BY_LANGUAGE_NAME_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByLanguageName(@RequestParam(name = BOOK_LANGUAGE_NAME_PARAM_NAME) String name) {
        return ResponseEntity.ok(bookService.findItemsByLanguageName(name));
    }

    @GetMapping(path="/" + FIND_BY_LANGUAGE_CODE_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByLanguageCode(@RequestParam(name = BOOK_LANGUAGE_CODE_PARAM_NAME) Code code) {
        return ResponseEntity.ok(bookService.findItemsByLanguageCode(code));
    }

    @GetMapping(path="/" + FIND_BY_KEYWORDS_ID_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByKeywordsId(@RequestParam(name = BOOK_KEYWORDS_ID_PARAM_NAME) Long id) {
        return ResponseEntity.ok(bookService.findItemsByKeywordsId(id));
    }

    @GetMapping(path="/" + FIND_BY_KEYWORDS_TEXT_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByKeywordsText(@RequestParam(name = BOOK_KEYWORDS_TEXT_PARAM_NAME) String text) {
        return ResponseEntity.ok(bookService.findItemsByKeywordsText(text));
    }

    @GetMapping(path="/" + FIND_BY_KEYWORDS_DATE_DEFINED_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByKeywordsDateDefined(@RequestParam(name = BOOK_KEYWORDS_DATE_DEFINED_PARAM_NAME) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDefined) {
        return ResponseEntity.ok(bookService.findItemsByKeywordsDateDefined(dateDefined));
    }

    @GetMapping(path="/" + FIND_BY_KEYWORDS_LANGUAGE_ID_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByKeywordsLanguageId(@RequestParam(name = BOOK_KEYWORDS_LANGUAGE_ID_PARAM_NAME) Long id) {
        return ResponseEntity.ok(bookService.findItemsByKeywordsLanguageId(id));
    }

    @GetMapping(path="/" + FIND_BY_KEYWORDS_LANGUAGE_NAME_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByKeywordsLanguageName(@RequestParam(name = BOOK_KEYWORDS_LANGUAGE_NAME_PARAM_NAME) String name) {
        return ResponseEntity.ok(bookService.findItemsByKeywordsLanguageName(name));
    }

    @GetMapping(path="/" + FIND_BY_KEYWORDS_LANGUAGE_CODE_PATH)
    public ResponseEntity<Iterable<Book>> findItemsByKeywordsLanguageCode(@RequestParam(name = BOOK_KEYWORDS_LANGUAGE_CODE_PARAM_NAME) Code code) {
        return ResponseEntity.ok(bookService.findItemsByKeywordsLanguageCode(code));
    }

    @PostMapping(path=EMPTY)
    public ResponseEntity<URI> createItem(@RequestBody @Valid Book item) {
        Book savedItem = bookService.createItem(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedItem.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path="/{" + BOOK_ID_PARAM_NAME + "}")
    public ResponseEntity<Void> updateItem(@RequestBody @Valid Book item, @PathVariable Long bookId) {
        Optional<Book> savedItem = bookService.findItemById(bookId);
        if(savedItem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        item.setId(savedItem.get().getId());
        bookService.updateItem(item);
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
