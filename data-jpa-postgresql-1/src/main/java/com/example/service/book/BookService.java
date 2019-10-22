package com.example.service.book;

import com.example.model.book.Book;
import com.example.model.book.Format;
import com.example.model.language.Language.Code;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

public interface BookService {

    Iterable<Book> findAllItems();

    Iterable<Book> findItemsByTitle(String title);

    Iterable<Book> findItemsByDatePublished(LocalDate datePublished);

    Iterable<Book> findItemsByNumberOfPages(Integer numberOfPages);

    Iterable<Book> findItemsByFamilyFriendly(Boolean familyFriendly);

    Iterable<Book> findItemsByIsbn(String isbn);

    Iterable<Book> findItemsByFormat(Format format);

    Iterable<Book> findItemsByLanguageId(Long languageId);

    Iterable<Book> findItemsByLanguageName(String name);

    Iterable<Book> findItemsByLanguageCode(Code code);

    Iterable<Book> findItemsByKeywordsId(Long keywordId);

    Iterable<Book> findItemsByKeywordsText(String keywordText);

    Iterable<Book> findItemsByKeywordsDateDefined(LocalDate keywordText);

    Iterable<Book> findItemsByKeywordsLanguageId(Long keywordLanguageId);

    Iterable<Book> findItemsByKeywordsLanguageName(String keywordLanguageName);

    Iterable<Book> findItemsByKeywordsLanguageCode(Code keywordLanguageCode);

    Book createItem(@Valid Book item);

    Optional<Book> findItemById(Long itemId);

    void updateItem(@Valid Book item);
}
