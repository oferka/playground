package com.example.repository.book;

import com.example.model.book.Book;
import com.example.model.book.Format;
import com.example.model.language.Language.Code;
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

    List<Book> findByLanguageId(Long languageId);

    List<Book> findByLanguageName(String languageName);

    List<Book> findByLanguageCode(Code languageCode);

    List<Book> findAllByKeywordsId(Long keywordId);

    List<Book> findAllByKeywordsText(String keywordText);

    List<Book> findAllByKeywordsDateDefined(LocalDate keywordDateDefined);

    List<Book> findAllByKeywordsLanguageId(Long keywordLanguageId);

    List<Book> findAllByKeywordsLanguageName(String keywordLanguageName);

    List<Book> findAllByKeywordsLanguageCode(Code keywordLanguageCode);
}
