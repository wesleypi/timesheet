package com.wesleypi.timesheet.controllers.impl;

import com.wesleypi.timesheet.controllers.DayController;
import com.wesleypi.timesheet.models.Entry;
import com.wesleypi.timesheet.models.Period;

import java.util.List;

public class DayControllerImpl implements DayController {

    /*
        - Deve retornar todos os dias do mês // to return all days i must first get them
        - Considerar a lista de feriados para calcular o days.[*].businessDay
        - Considerar como horário padrão (08:00 / 12:00 - 13:00 / 17:00) quando for feriado.
        - O campo days.[*].total deve ser pré-calculado com base nos campos days.[*].period
        - O campo total deve ser pré-calculado com no campo days.[*].total
        - O campo balance deve ser calculado com base no banco de horas do usuário
    */
    @Override
    public List<Entry> getMonthDays(Integer year, Integer month) {
        return List.of();
    }

    @Override
    public Period putDay(Integer year, Integer month, Integer day, Entry entry) {
        return null;
    }
}
