package com.wesleypi.timesheet.usecase.timesheet;

import com.wesleypi.timesheet.persistence.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FillMonthlyEntriesUseCase {
    private final EntryRepository entryRepository;

    void execute(LocalDate date) {
        final var month = date.getMonth();



        //TODO: complete
    }
}
