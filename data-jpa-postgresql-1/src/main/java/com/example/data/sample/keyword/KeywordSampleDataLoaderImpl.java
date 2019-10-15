package com.example.data.sample.keyword;

import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import com.example.repository.keyword.KeywordRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Data
public class KeywordSampleDataLoaderImpl implements KeywordSampleDataLoader {

    private KeywordSampleDataProvider keywordSampleDataProvider;

    private KeywordRepository keywordRepository;

    public KeywordSampleDataLoaderImpl(KeywordSampleDataProvider keywordSampleDataProvider, KeywordRepository keywordRepository) {
        this.keywordSampleDataProvider = keywordSampleDataProvider;
        this.keywordRepository = keywordRepository;
    }

    @Override
    public List<Keyword> load(List<Language> loadedLanguages) {
        log.info("Sample data load - keywords load - started");
        Map<Class, List> dependencies = new HashMap<>();
        dependencies.put(Language.class, loadedLanguages);
        keywordSampleDataProvider.setDependencies(dependencies);
        List<Keyword> itemsToBeLoaded = keywordSampleDataProvider.getSampleItems();
        List<Keyword> loadedItems = keywordRepository.saveAll(itemsToBeLoaded);
        log.info("Sample data load - keywords load - loaded {} keywords", loadedItems.size());
        log.info("Sample data load - keywords load - completed");
        return loadedItems;
    }

    @Override
    public void clean() {
        log.info("Sample data clean - keywords clean - started");
        keywordRepository.deleteAll();
        log.info("Sample data clean - keywords clean - completed");
    }
}
