package com.example.service.keyword;

import com.example.model.keyword.Keyword;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

public interface KeywordService {

    Iterable<Keyword> findAllItems();

    Iterable<Keyword> findItemsByText(String text);

    Iterable<Keyword> findItemsByDateDefined(LocalDate dateDefined);

    Iterable<Keyword> findItemsByLanguageName(String name);

    Keyword createItem(@Valid Keyword item);

    Optional<Keyword> findItemById(Long itemId);

    void deleteItemById(Long itemId);

    void updateItem(@Valid Keyword item);
}
