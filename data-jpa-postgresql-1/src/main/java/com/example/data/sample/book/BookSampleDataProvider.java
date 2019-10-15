package com.example.data.sample.book;

import com.example.data.sample.SampleDataProvider;
import com.example.model.book.Book;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class BookSampleDataProvider implements SampleDataProvider<Book> {

    private Map<Class, List> dependencies;
}
