package com.example.service.book;

import com.example.model.book.Book;
import com.example.model.book.Book.Format;
import com.example.model.language.Language.Code;
import com.example.repository.book.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@Validated
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Book> findAllItems() {
        return repository.findAll();
    }

    @Override
    public Iterable<Book> findItemsByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public Iterable<Book> findItemsByDatePublished(LocalDate datePublished) {
        return repository.findByDatePublished(datePublished);
    }

    @Override
    public Iterable<Book> findItemsByNumberOfPages(Integer numberOfPages) {
        return repository.findByNumberOfPages(numberOfPages);
    }

    @Override
    public Iterable<Book> findItemsByFamilyFriendly(Boolean familyFriendly) {
        return repository.findByFamilyFriendly(familyFriendly);
    }

    @Override
    public Iterable<Book> findItemsByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    @Override
    public Iterable<Book> findItemsByFormat(Format format) {
        return repository.findByFormat(format);
    }

    @Override
    public Iterable<Book> findItemsByLanguageId(Long languageId) {
        return repository.findByLanguageId(languageId);
    }

    @Override
    public Iterable<Book> findItemsByLanguageName(String name) {
        return repository.findByLanguageName(name);
    }

    @Override
    public Iterable<Book> findItemsByLanguageCode(Code code) {
        return repository.findByLanguageCode(code);
    }

    @Override
    public Iterable<Book> findItemsByKeywordsId(Long keywordId) {
        return repository.findAllByKeywordsId(keywordId);
    }

    @Override
    public Iterable<Book> findItemsByKeywordsText(String keywordText) {
        return repository.findAllByKeywordsText(keywordText);
    }

    @Override
    public Iterable<Book> findItemsByKeywordsDateDefined(LocalDate keywordDateDefined) {
        return repository.findAllByKeywordsDateDefined(keywordDateDefined);
    }

    @Override
    public Iterable<Book> findItemsByKeywordsLanguageId(Long keywordLanguageId) {
        return repository.findAllByKeywordsLanguageId(keywordLanguageId);
    }

    @Override
    public Iterable<Book> findItemsByKeywordsLanguageName(String keywordLanguageName) {
        return repository.findAllByKeywordsLanguageName(keywordLanguageName);
    }

    @Override
    public Iterable<Book> findItemsByKeywordsLanguageCode(Code keywordLanguageCode) {
        return repository.findAllByKeywordsLanguageCode(keywordLanguageCode);
    }

    @Override
    public Book createItem(@Valid Book item) {
        return repository.save(item);
    }

    @Override
    public Optional<Book> findItemById(Long itemId) {
        return repository.findById(itemId);
    }

    @Override
    public void updateItem(@Valid Book item) {
        Optional<Book> existingItem = findItemById(item.getId());
        if(existingItem.isPresent()) {
            repository.save(item);
        }
    }
}
