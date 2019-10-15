package com.example.data.sample.language;

import com.example.model.language.Language;

import java.util.List;

public interface LanguageSampleDataLoader {

    LanguageSampleDataProvider getLanguageSampleDataProvider();

    List<Language> load();

    void clean();
}
