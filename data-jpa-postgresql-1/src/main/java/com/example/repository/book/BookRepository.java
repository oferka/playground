package com.example.repository.book;

import com.example.model.book.Book;
import com.example.model.book.Book.Format;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByDatePublished(LocalDate datePublished);

    List<Book> findByNumberOfPages(Integer numberOfPages);

    List<Book> findByFamilyFriendly(Boolean familyFriendly);

    List<Book> findByIsbn(String isbn);

    List<Book> findByFormat(Format format);

    List<Book> findByLanguageName(String name);
}
