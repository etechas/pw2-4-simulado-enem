package com.example.demo.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Converte Boolean (Java) <-> INT (banco): true = 1, false = 0
@Converter
public class BooleanParaIntConverter implements AttributeConverter<Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Boolean valor) {
        if (valor == null) {
            return null;
        }
        return valor ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer valor) {
        if (valor == null) {
            return null;
        }
        return valor == 1;
    }
}
