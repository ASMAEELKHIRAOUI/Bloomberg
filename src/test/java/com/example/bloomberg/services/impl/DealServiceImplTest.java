package com.example.bloomberg.services.impl;

import com.example.bloomberg.domain.Deal;
import com.example.bloomberg.repositories.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DealServiceImplTest {

    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;

    private Deal deal;

    @BeforeEach
    void setUp() {
        deal = Deal.builder()
                .id(1L)
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("EUR")
                .timestamp(LocalDateTime.now())
                .amount(100.0)
                .build();
    }

    @Test
    void save_NewDeal_ShouldSaveAndReturnDeal() {
        when(dealRepository.getDealByAmountAndToCurrencyIsoCodeAndFromCurrencyIsoCodeAndTimestamp(anyDouble(), anyString(), anyString(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());
        when(dealRepository.save(any(Deal.class))).thenReturn(deal);

        Deal savedDeal = dealService.save(deal);

        assertNotNull(savedDeal);
        assertEquals(deal, savedDeal);
        verify(dealRepository, times(1)).save(deal);
    }

    @Test
    void save_ExistingDeal_ShouldReturnNull() {
        when(dealRepository.getDealByAmountAndToCurrencyIsoCodeAndFromCurrencyIsoCodeAndTimestamp(anyDouble(), anyString(), anyString(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(deal));

        Deal savedDeal = dealService.save(deal);

        assertNull(savedDeal);
        verify(dealRepository, never()).save(any(Deal.class));
    }

    @Test
    void search_DealExists_ShouldReturnDeal() {
        when(dealRepository.getDealByAmountAndToCurrencyIsoCodeAndFromCurrencyIsoCodeAndTimestamp(anyDouble(), anyString(), anyString(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(deal));

        Optional<Deal> foundDeal = dealService.search(deal);

        assertTrue(foundDeal.isPresent());
        assertEquals(deal, foundDeal.get());
    }

    @Test
    void search_DealDoesNotExist_ShouldReturnEmpty() {
        when(dealRepository.getDealByAmountAndToCurrencyIsoCodeAndFromCurrencyIsoCodeAndTimestamp(anyDouble(), anyString(), anyString(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        Optional<Deal> foundDeal = dealService.search(deal);

        assertFalse(foundDeal.isPresent());
    }
}