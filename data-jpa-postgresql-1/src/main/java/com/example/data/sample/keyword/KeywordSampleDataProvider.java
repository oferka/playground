package com.example.data.sample.keyword;

import com.example.model.keyword.Keyword;
import com.example.model.language.Language;

import java.util.List;

public interface KeywordSampleDataProvider {

    List<Keyword> getSampleItems(List<Language> loadedLanguages);

    Keyword getSampleItem();
}
