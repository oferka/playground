package com.example.data.sample.language;

import com.example.model.language.Language;
import com.github.javafaker.Faker;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class RandomLanguageSampleDataProviderUtils {

    public static Language generateRandomLanguage() {
        return Language.builder()
                .name(generateRandomLanguageName())
                .code(generateRandomLanguageCode())
                .build();
    }

    private static String generateRandomLanguageName() {
        return new Faker().country().name();
    }

    private static Language.Code generateRandomLanguageCode() {
        Language.Code[] codes = Language.Code.values();
        return codes[nextInt(0, codes.length)];
    }
}
