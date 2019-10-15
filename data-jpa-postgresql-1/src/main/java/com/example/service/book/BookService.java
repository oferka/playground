package com.example.service.book;

import com.example.model.book.Book;
import com.example.model.book.Book.Format;

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

    Iterable<Book> findItemsByLanguageName(String name);

    Book createItem(@Valid Book item);

    Optional<Book> findItemById(Long itemId);

    void deleteItemById(Long itemId);

    void updateItem(@Valid Book item);
}
