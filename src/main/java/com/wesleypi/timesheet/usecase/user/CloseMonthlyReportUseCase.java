package com.wesleypi.timesheet.usecase.user;

import com.wesleypi.timesheet.controller.dto.response.CalendarResponse;
import com.wesleypi.timesheet.controller.dto.response.PatchUserMonthlyReportResponse;
import com.wesleypi.timesheet.controller.dto.response.PayResponse;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.entity.EntryEntity;
import com.wesleypi.timesheet.persistence.repository.EntryRepository;
import com.wesleypi.timesheet.persistence.repository.PaymentRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CloseMonthlyReportUseCase {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final EntryRepository entryRepository;

    public PatchUserMonthlyReportResponse execute(final String externalId, final Integer year, final Integer month) {
        final var foundUser =  userRepository.findByExternalId(externalId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado."));

        final var foundPayment =  paymentRepository.findByUserIdAndMonthAndYear(foundUser.getId(), month, year);

        if (foundPayment.isPresent()) {
            final var payment = foundPayment.get();

            return PatchUserMonthlyReportResponse.builder()
                    .name(foundUser.getName())
                    .team(foundUser.getTeam())
                    .bankHours(foundUser.getHoursBank() != 0L)
                    .balanceBankHours(Duration.ofMillis(payment.getCurrentHoursBank()).toHoursPart())
                    .calendar(CalendarResponse.builder()
                            .month(payment.getMonth().toString())
                            .year(payment.getYear().toString())
                            .total(payment.getTotalHours().toString())
                            .build())
                    .pay(PayResponse.builder()
                            .date(payment.getDate().toString())
                            .hour(payment.getHourValue())
                            .total(payment.getTotalValue().toString())
                            .build())
                    .build();
        }

        final var monthFirstDay = LocalDate.of(year, month, 1);
        final var monthLastDay = LocalDate.of(year, month, monthFirstDay.lengthOfMonth());

        final var foundEntries = entryRepository.findByUserIdAndDateBetween(foundUser.getId(), monthFirstDay, monthLastDay);

        if (foundEntries.isEmpty()) {
            throw new ResourceNotFoundException("Não há lançamentos para o ano:"+ year +", mês: "+month);
        }

        final var hasBankHours = foundUser.getHoursBank() != 0L;

        final var hoursBank =  hasBankHours ? getHoursBank(foundEntries) : foundUser.getHoursBank() ;



        // HoursBank.save()

        return null;
    }

    private static long getHoursBank(List<EntryEntity> foundEntries) {
        return foundEntries.stream()
                .mapToLong(entry -> entry.getTotal() - Duration.ofHours(8).toMillis())
                .sum();
    }



}
