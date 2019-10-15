package com.example.service.language;

import com.example.model.language.Language;
import com.example.model.language.Language.Code;

import javax.validation.Valid;
import java.util.Optional;

public interface LanguageService {

    Iterable<Language> findAllItems();

    Iterable<Language> findItemsByName(String name);

    Iterable<Language> findItemsByCode(Code code);

    Language createItem(@Valid Language item);

    Optional<Language> findItemById(Long itemId);

    void deleteItemById(Long itemId);

    void updateItem(@Valid Language item);
}
