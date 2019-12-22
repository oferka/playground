package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {

    @JsonbProperty("dog-name")
    private String name;

    private int age;

    @JsonbTransient
    private boolean bitable;

    @JsonbProperty(nillable=true)
    private String bestFriend;

    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate birthDate;

    @JsonbNumberFormat("#0.00")
    private BigDecimal price;
}
