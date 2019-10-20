package com.example.service.keyword;

import com.example.model.keyword.Keyword;
import com.example.repository.keyword.KeywordRepository;
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
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository repository;

    @Autowired
    public KeywordServiceImpl(KeywordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Keyword> findAllItems() {
        return repository.findAll();
    }

    @Override
    public Iterable<Keyword> findItemsByText(String text) {
        return repository.findByText(text);
    }

    @Override
    public Iterable<Keyword> findItemsByDateDefined(LocalDate dateDefined) {
        return repository.findByDateDefined(dateDefined);
    }

    @Override
    public Iterable<Keyword> findItemsByLanguageName(String name) {
        return repository.findByLanguageName(name);
    }

    @Override
    public Keyword createItem(@Valid Keyword item) {
        return repository.save(item);
    }

    @Override
    public Optional<Keyword> findItemById(Long itemId) {
        return repository.findById(itemId);
    }

    @Override
    public void updateItem(@Valid Keyword item) {
        Optional<Keyword> existingItem = findItemById(item.getId());
        if(existingItem.isPresent()) {
            repository.save(item);
        }
    }
}
