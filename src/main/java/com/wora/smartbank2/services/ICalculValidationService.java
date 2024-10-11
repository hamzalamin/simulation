package com.wora.smartbank2.services;

import com.wora.smartbank2.entities.dtos.ValidationDto;

public interface ICalculValidationService {
        ValidationDto calculate(ValidationDto validationDto);
}
