package com.example;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Validated
@Data
public class Reservation {

    private LocalDate begin;

    private LocalDate end;

    @Valid
    private Customer customer;

    @Positive
    private int room;

    @ConsistentDateParameters
    @ValidReservation
    public Reservation(LocalDate begin, LocalDate end, Customer customer, int room) {
        this.begin = begin;
        this.end = end;
        this.customer = customer;
        this.room = room;
    }
}
