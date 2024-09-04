package com.wesleypi.timesheet.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeHelper {

	public static final ZoneId AMERICA_SP_ZONE_ID = ZoneId.of("America/Sao_Paulo");

	public static LocalDateTime localDateTimeAmericaSp() {
		return LocalDateTime.now().atZone(AMERICA_SP_ZONE_ID).toLocalDateTime();
	}

	public static LocalDate localDateAmericaSp() {
		return LocalDateTime.now().atZone(AMERICA_SP_ZONE_ID).toLocalDate();
	}
}
