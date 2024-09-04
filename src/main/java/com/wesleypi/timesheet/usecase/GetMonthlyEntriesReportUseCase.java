package com.wesleypi.timesheet.usecase;

import com.wesleypi.timesheet.controller.dto.response.DayResponse;
import com.wesleypi.timesheet.controller.dto.response.MonthlyReportPaginatedResponse;
import com.wesleypi.timesheet.controller.dto.response.Pagination;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.entity.EntryEntity;
import com.wesleypi.timesheet.persistence.repository.EntryRepository;
import com.wesleypi.timesheet.persistence.repository.HolidayRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.wesleypi.timesheet.helper.DateUtil.isClosedMonth;
import static com.wesleypi.timesheet.helper.DateUtil.isWeekend;
import static com.wesleypi.timesheet.helper.mapper.TimePeriodMapper.TIME_PERIOD_MAPPER;


@Service
@RequiredArgsConstructor
public class GetMonthlyEntriesReportUseCase {

    private final EntryRepository entryRepository;
    private final UserRepository userRepository;

    public MonthlyReportPaginatedResponse execute(final Integer year, final Month month, final String userId, final Pagination pagination) {

        final var firstDayMonth = LocalDate.of(year, month, 1);
        final var lastDayMonth = LocalDate.of(year, month, firstDayMonth.lengthOfMonth());

        final var user = userRepository.findByExternalId(userId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado."));

        final var pageableRequest = PageRequest.of(pagination.getPage(), pagination.getPageSize(), Sort.by("date").ascending());
        final var pagedEntries = entryRepository.findByUserIdAndDateBetween(user.getId(), firstDayMonth, lastDayMonth, pageableRequest);

        if (pagedEntries.getContent().isEmpty()) {
            //TODO: send to a queue
            throw new ResourceNotFoundException("A lista para o mês está atualmente vazia. tente novamente.");
        }

        var total = pagedEntries.getContent().stream().mapToLong(EntryEntity::getTotal).sum();

        final var days = pagedEntries.getContent().stream()
                .map(entry -> DayResponse.builder()
                        .date(entry.getDate().toString())
                        .businessDay(!entry.getIsHoliday() || !isWeekend(entry.getDate()))
                        .total(entry.getTotal().toString())
                        .period(TIME_PERIOD_MAPPER.toResponses(entry.getPeriods()))
                        .build())
                .toList();

        final var entriesPageable = pagedEntries.getPageable();

        return MonthlyReportPaginatedResponse.builder()
                .closed(isClosedMonth(month, year))
                .total(String.valueOf(total))
                .balance(user.getHoursBank())
                .month(firstDayMonth.getMonthValue())
                .year(firstDayMonth.getYear())
                .days(days)
                .pagination(
                        Pagination.builder()
                                .page(entriesPageable.getPageNumber())
                                .pageSize(entriesPageable.getPageSize())
                                .total(pagedEntries.getTotalElements())
                                .build())
                .build();
    }



}
