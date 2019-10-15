package com.example.data.sample.language;

import com.example.data.sample.SampleDataProvider;
import com.example.model.language.Language;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class LanguageSampleDataProvider implements SampleDataProvider<Language> {

    private Map<Class, List> dependencies;
}
