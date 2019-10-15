package com.example.repository.keyword;

import com.example.model.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    List<Keyword> findByText(String text);

    List<Keyword> findByDateDefined(LocalDate dateDefined);

    List<Keyword> findByLanguageName(String name);
}
