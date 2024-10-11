package com.wora.smartbank2.services.impl;

import com.wora.smartbank2.entities.dtos.ValidationDto;
import com.wora.smartbank2.services.ICalculValidationService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculValidationService implements ICalculValidationService {

    private final static Double TAX_RATE = 12.0;
    private final static Double MONTHLY_RATE = TAX_RATE / 12.0 / 100.0;


    @Override
    public ValidationDto calculate(ValidationDto validationDto) {
        Double calculatedMonthlyPayment = (validationDto.amount() * MONTHLY_RATE) / (1 - Math.pow(1 + MONTHLY_RATE, -validationDto.duration()));
        return new ValidationDto(validationDto, calculatedMonthlyPayment);
    }

}
