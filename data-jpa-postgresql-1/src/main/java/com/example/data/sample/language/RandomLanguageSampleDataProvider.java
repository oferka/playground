package com.example.data.sample.language;

import com.example.model.language.Language;
import com.example.model.language.Language.Code;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;

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

    public static Language generateRandomLanguage() {
        return Language.builder()
                .name(generateRandomLanguageName())
                .code(generateRandomLanguageCode())
                .build();
    }

    private static String generateRandomLanguageName() {
        return new Faker().country().name();
    }

    public static Code generateRandomLanguageCode() {
        Code[] codes = Code.values();
        return codes[nextInt(0, codes.length)];
    }
}
