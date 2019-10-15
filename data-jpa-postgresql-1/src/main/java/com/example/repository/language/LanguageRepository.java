package com.example.repository.language;

import com.example.model.language.Language;
import com.example.model.language.Language.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    List<Language> findByName(String name);

    List<Language> findByCode(Code code);
}
