package com.example.bloomberg.dto;

import com.example.bloomberg.domain.Deal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record DealDTO(

    @NotNull(message = "ID cannot be null")
    Long id,

    @NotNull(message = "From currency ISO code cannot be null")
    String fromCurrencyIsoCode,

    @NotNull(message = "To currency ISO code cannot be null")
    String toCurrencyIsoCode,

    @NotNull(message = "Timestamp cannot be null")
    LocalDateTime timestamp,

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    Double amount

) {
    public static DealDTO fromDeal(Deal deal){
        return new DealDTO(
                deal.getId(),
                deal.getFromCurrencyIsoCode(),
                deal.getToCurrencyIsoCode(),
                deal.getTimestamp(),
                deal.getAmount()
        );
    }

    public Deal toDeal(){
        Deal.DealBuilder orderItemBuilder = new Deal().builder()
                .id(id)
                .fromCurrencyIsoCode(fromCurrencyIsoCode)
                .toCurrencyIsoCode(toCurrencyIsoCode)
                .timestamp(timestamp)
                .amount(amount);
        return orderItemBuilder.build();
    }
}
