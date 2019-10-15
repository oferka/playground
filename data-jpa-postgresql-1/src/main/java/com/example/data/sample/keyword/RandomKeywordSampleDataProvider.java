package com.example.data.sample.keyword;

import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Profile({"keyword_random_sample_data_provider", "default"})
@Service
public class RandomKeywordSampleDataProvider implements KeywordSampleDataProvider {

    private List<Language> loadedLanguages;

    @Override
    public List<Keyword> getSampleItems(List<Language> loadedLanguages) {
        this.loadedLanguages = loadedLanguages;
        List<Keyword> keywords = new ArrayList<>();
        for(int i=0; i<120; i++) {
            keywords.add(generateRandomKeyword());
        }
        return keywords;
    }

    @Override
    public Keyword getSampleItem() {
        return generateRandomKeyword();
    }

    private Keyword generateRandomKeyword() {
        return Keyword.builder()
                .text(generateRandomKeywordText())
                .dateDefined(generateRandomKeywordDateDefined())
                .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                .build();
    }

    private static String generateRandomKeywordText() {
        return new Faker().hacker().noun();
    }

    public static LocalDate generateRandomKeywordDateDefined() {
        return LocalDate.now().minusDays(nextInt(0, 10000));
    }
}
