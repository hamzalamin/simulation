package com.wora.smartbank2.entities.dtos;

import com.wora.entities.enums.Civility;

import java.time.LocalDate;

public record RequestDto(
        String projectName,
        String userType,
        Double loanAmount,
        Double loanDuration,
        Double monthlyPayment,
        String email,
        String phone,
        Civility civility,
        String fName,
        String lName,
        String cin,
        LocalDate birthDate,
        LocalDate employmentStartDate,
        Boolean hasCredits
) {
}
