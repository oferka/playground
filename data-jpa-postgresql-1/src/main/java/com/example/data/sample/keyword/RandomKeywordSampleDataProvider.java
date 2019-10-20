package com.example.data.sample.keyword;

import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.data.sample.keyword.RandomKeywordSampleDataProviderUtils.generateRandomKeyword;

@Profile({"keyword_random_sample_data_provider", "default"})
@Service
public class RandomKeywordSampleDataProvider extends KeywordSampleDataProvider {

    @Override
    public List<Keyword> getSampleItems() {
        List<Keyword> keywords = new ArrayList<>();
        for(int i=0; i<120; i++) {
            keywords.add(generateRandomKeyword(getDependencies().get(Language.class)));
        }
        return keywords;
    }
}
