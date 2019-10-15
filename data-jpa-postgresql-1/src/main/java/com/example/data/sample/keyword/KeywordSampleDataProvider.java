package com.example.data.sample.keyword;

import com.example.data.sample.SampleDataProvider;
import com.example.model.keyword.Keyword;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class KeywordSampleDataProvider implements SampleDataProvider<Keyword> {

    private Map<Class, List> dependencies;
}
