package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;

    private LocalDate creationDate;

    private long pages;

    private boolean isPublished;

    private String author;

    private BigDecimal price;
}
