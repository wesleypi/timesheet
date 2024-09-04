package com.wesleypi.timesheet.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    public static Boolean isClosedMonth(final Month month, final Integer year) {
        final var actualDate = LocalDate.now();
        return month != actualDate.getMonth() || year != actualDate.getYear();
    }


    public static Boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
