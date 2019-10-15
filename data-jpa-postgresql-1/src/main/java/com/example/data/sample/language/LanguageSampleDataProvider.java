package com.example.data.sample.language;

import com.example.model.language.Language;

import java.util.List;

public interface LanguageSampleDataProvider {

    List<Language> getSampleItems();

    Language getSampleItem();
}
