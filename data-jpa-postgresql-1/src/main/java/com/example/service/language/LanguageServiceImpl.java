package com.example.service.language;

import com.example.model.language.Language;
import com.example.model.language.Language.Code;
import com.example.repository.language.LanguageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Service
@Validated
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Language> findAllItems() {
        return repository.findAll();
    }

    @Override
    public Iterable<Language> findItemsByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Iterable<Language> findItemsByCode(Code code) {
        return repository.findByCode(code);
    }

    @Override
    public Language createItem(@Valid Language item) {
        return repository.save(item);
    }

    @Override
    public Optional<Language> findItemById(Long itemId) {
        return repository.findById(itemId);
    }

    @Override
    public void updateItem(@Valid Language item) {
        Optional<Language> existingItem = findItemById(item.getId());
        if(existingItem.isPresent()) {
            repository.save(item);
        }
    }
}
