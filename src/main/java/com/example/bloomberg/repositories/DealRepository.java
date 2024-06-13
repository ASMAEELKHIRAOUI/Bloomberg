package com.example.bloomberg.repositories;

import com.example.bloomberg.domain.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

    Optional<Deal> getDealByAmountAndToCurrencyIsoCodeAndFromCurrencyIsoCodeAndTimestamp(Double amount, String toCurrencyIsoCode, String fromCurrencyIsoCode, LocalDateTime timestamp);

}
