package com.wesleypi.timesheet.helper;

import com.wesleypi.timesheet.exception.ResourceConflictException;
import com.wesleypi.timesheet.model.TimePeriod;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.nonNull;

public class TimeIntervalUtil {

    public static Duration calculateTimePeriods(List<TimePeriod> periods) {
        periods.sort(Comparator.comparing(TimePeriod::getStart));

        TimePeriod previous = null;

        Duration totalDuration = Duration.ZERO;

        for (TimePeriod period : periods) {
            checkOverlappingInterval(previous, period);

            checkIsInvertedInterval(period);

            final var currentPeriodDuration = Duration.between(period.getStart(), period.getEnd());

            totalDuration = totalDuration.plus(currentPeriodDuration);

            previous = period;
        }

        return totalDuration;
    }

    private static void checkOverlappingInterval(TimePeriod previous, TimePeriod period) {
        if (nonNull(previous) && isOverlapping(previous, period)) {
            throw new ResourceConflictException("Intervalo sobreposto encontrado: " +
                    "Início: " + period.getStart() + ", Fim: " + period.getEnd());
        }
    }

    public static void checkIsInvertedInterval(TimePeriod period) {
        if (period.getEnd().isBefore(period.getStart())) {
            throw new ResourceConflictException("O valor final é anterior ao inicial" +
                    "Início: " + period.getStart() + ", Fim: " + period.getEnd());
        }
    }

    public static boolean isOverlapping(TimePeriod previous, TimePeriod period) {
        return previous.getStart().isBefore(period.getEnd()) && previous.getEnd().isAfter(period.getStart());
    }

    public static List<TimePeriod> holidayPeriods() {
        return List.of(
                TimePeriod.builder().start(LocalTime.of(8, 0)).end(LocalTime.of(12, 0)).build(),
                TimePeriod.builder().start(LocalTime.of(13, 0)).end(LocalTime.of(17, 0)).build()
        );
    }
}
