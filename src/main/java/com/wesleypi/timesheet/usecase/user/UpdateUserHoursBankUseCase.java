package com.wesleypi.timesheet.usecase.user;

import com.wesleypi.timesheet.controller.dto.request.user.UpdateUserHoursBankRequest;
import com.wesleypi.timesheet.exception.ResourceNotFoundException;
import com.wesleypi.timesheet.persistence.repository.HoursBankRepository;
import com.wesleypi.timesheet.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.wesleypi.timesheet.helper.mapper.HoursBankMapper.HOURS_BANK_MAPPER;

@Service
@RequiredArgsConstructor
public class UpdateUserHoursBankUseCase {
    private final UserRepository userRepository;
    private final HoursBankRepository hoursBankRepository;

    public void execute(final String externalId, final UpdateUserHoursBankRequest request) {
        final var foundUser = userRepository.findByExternalId(externalId).orElseThrow(() -> new ResourceNotFoundException("usuário não encontrado"));

        final var foundHoursBank = hoursBankRepository.findFirstByUserIdOrderByDateDesc(foundUser.getId()).orElseThrow(() -> new ResourceNotFoundException("usuário não contem banco de horas"));

        final var diffHours = foundHoursBank.getHours() - request.getBalance();

        final var entityToSave = HOURS_BANK_MAPPER.toEntity(request, diffHours);

        hoursBankRepository.save(entityToSave);
    }
}
