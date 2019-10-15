package com.example.data.sample.keyword;

import com.example.model.keyword.Keyword;
import com.example.model.language.Language;

import java.util.List;

public interface KeywordSampleDataLoader {

    KeywordSampleDataProvider getKeywordSampleDataProvider();

    List<Keyword> load(List<Language> loadedLanguages);

    void clean();
}
