package com.wesleypi.timesheet.persistence.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesleypi.timesheet.model.TimePeriod;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@Converter
@RequiredArgsConstructor
public class TimePeriodConverter implements AttributeConverter<List<TimePeriod>, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<TimePeriod> timePeriods) {
        try {
            return objectMapper.writeValueAsString(timePeriods);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Erro ao converter lista de itens para JSON.", e);
        }
    }

    @Override
    public List<TimePeriod> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructCollectionType(List.class, TimePeriod.class));
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao converter JSON para lista de itens.", e);
        }
    }
}
