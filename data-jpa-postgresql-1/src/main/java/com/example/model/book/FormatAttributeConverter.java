package com.example.model.book;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FormatAttributeConverter implements AttributeConverter<Format, String> {

    @Override
    public String convertToDatabaseColumn(Format format) {
        return format.getName();
    }

    @Override
    public Format convertToEntityAttribute(String s) {
        return Format.forValue(s);
    }
}
