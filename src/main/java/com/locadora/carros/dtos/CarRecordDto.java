package com.locadora.carros.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarRecordDto(@NotBlank String name, @NotNull int year, @NotBlank String color, @NotNull Boolean available, @NotNull BigDecimal dailyValue) {

}
