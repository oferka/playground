package com.example.model.randomizers;

import com.example.model.YearQuarter;
import org.jeasy.random.api.Randomizer;

import java.time.LocalDate;
import java.util.Random;

public class YearQuarterRandomizer implements Randomizer<YearQuarter> {

    @Override
    public YearQuarter getRandomValue() {
        return new YearQuarter(LocalDate.ofYearDay(2019, new Random().nextInt(366) + 1));
    }
}
