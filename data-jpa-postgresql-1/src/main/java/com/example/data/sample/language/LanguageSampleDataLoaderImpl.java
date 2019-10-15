package com.example.data.sample.language;

import com.example.model.language.Language;
import com.example.repository.language.LanguageRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class LanguageSampleDataLoaderImpl implements LanguageSampleDataLoader {

    private LanguageSampleDataProvider languageSampleDataProvider;
    private LanguageRepository languageRepository;

    public LanguageSampleDataLoaderImpl(LanguageSampleDataProvider languageSampleDataProvider, LanguageRepository languageRepository) {
        this.languageSampleDataProvider = languageSampleDataProvider;
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> load() {
        log.info("Sample data load - languages load - started");
        List<Language> itemsToBeLoaded = languageSampleDataProvider.getSampleItems();
        List<Language> loadedItems = languageRepository.saveAll(itemsToBeLoaded);
        log.info("Sample data load - languages load - loaded {} languages", loadedItems.size());
        log.info("Sample data load - languages load - completed");
        return loadedItems;
    }

    @Override
    public void clean() {
        log.info("Sample data clean - languages clean - started");
        languageRepository.deleteAll();
        log.info("Sample data clean - languages clean - completed");
    }
}
