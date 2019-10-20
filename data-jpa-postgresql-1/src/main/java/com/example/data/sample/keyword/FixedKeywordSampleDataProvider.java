package com.example.data.sample.keyword;

import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Profile("keyword_fixed_sample_data_provider")
@Service
public class FixedKeywordSampleDataProvider extends KeywordSampleDataProvider {

    @Override
    public List<Keyword> getSampleItems() {
        List<Language> loadedLanguages = getDependencies().get(Language.class);
        return asList(
                Keyword.builder()
                        .text("Programing")
                        .dateDefined(LocalDate.of(2001, 3, 2))
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .build(),
                Keyword.builder()
                        .text("Operations")
                        .dateDefined(LocalDate.of(2011, 8, 22))
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .build(),
                Keyword.builder()
                        .text("DevOps")
                        .dateDefined(LocalDate.of(2013, 5, 27))
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .build(),
                Keyword.builder()
                        .text("HR")
                        .dateDefined(LocalDate.of(2011, 10, 14))
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .build(),
                Keyword.builder()
                        .text("Budget")
                        .dateDefined(LocalDate.of(2003, 9, 8))
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .build()
        );
    }
}
