package com.wora.smartbank2.entities.dtos;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Embeddable
public record ValidationDto(
        @NotNull @Positive Double amount,
        @NotNull @Positive Double duration,
        @NotNull @Positive Double monthly
) {
    public ValidationDto(ValidationDto validationDto, Double monthly) {
        this(validationDto.amount(), validationDto.duration(), monthly);
    }
}
