package com.example.data.sample.language;

import com.example.model.language.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.data.sample.language.RandomLanguageSampleDataProviderUtils.generateRandomLanguage;

@Profile({"language_random_sample_data_provider"})
@Service
public class RandomLanguageSampleDataProvider extends LanguageSampleDataProvider {

    @Override
    public List<Language> getSampleItems() {
        List<Language> languages = new ArrayList<>();
        for(int i=0; i<40; i++) {
            languages.add(generateRandomLanguage());
        }
        return languages;
    }

    @Override
    public Language getSampleItem() {
        return generateRandomLanguage();
    }
}
