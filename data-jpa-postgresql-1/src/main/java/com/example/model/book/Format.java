package com.example.model.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Format {
    AUDIO_BOOK("Audio Book"),
    ELECTRONIC_BOOK("Electronic Book"),
    GRAPHIC_NOVEL("Graphic Novel"),
    HARD_COVER ("Hard Cover"),
    PAPER_BACK("Paper Back");

    @Getter
    private String name;

    public static Format forValue(String value) {
        Format result = null;
        Format[] formats = Format.values();
        for(Format format : formats) {
            if(format.getName().equals(value)) {
                result = format;
                break;
            }
        }
        return result;
    }
}
