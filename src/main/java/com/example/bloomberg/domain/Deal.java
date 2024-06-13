package com.example.bloomberg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Deal {

    @Id
    private Long id;

    @NotNull(message = "Ordering currency iso code must not be null")
    @NotEmpty(message = "Ordering currency iso code must not be empty")
    @Size(max = 255, min = 3, message = "Ordering currency iso code should be between 3 and 255 character")
    private String fromCurrencyIsoCode;

    @NotNull(message = "To currency iso code must not be null")
    @NotEmpty(message = "To currency iso code must not be empty")
    @Size(max = 255, min = 3, message = "To currency iso code should be between 3 and 255 character")
    private String toCurrencyIsoCode;

    private LocalDateTime timestamp;

    @NotNull(message = "Amount must not be null")
    @Max(value = (long) Double.MAX_VALUE, message = "Value to big")
    @Min(value = 0, message = "Amount should be a positive value")
    private Double amount;

}
