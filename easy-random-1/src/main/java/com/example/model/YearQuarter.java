package com.example.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class YearQuarter {

    private LocalDate startDate;
    private LocalDate endDate;

    public YearQuarter(LocalDate startDate) {
        this.startDate = startDate;
        autoAdjustEndDate();
    }

    private void autoAdjustEndDate() {
        endDate = startDate.plusMonths(3L);
    }
}
