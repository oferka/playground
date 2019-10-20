package com.example.data.sample;

import java.util.List;
import java.util.Map;

public interface SampleDataProvider<T> {

    Map<Class, List> getDependencies();

    void setDependencies(Map<Class, List> dependencies);

    List<T> getSampleItems();
}
