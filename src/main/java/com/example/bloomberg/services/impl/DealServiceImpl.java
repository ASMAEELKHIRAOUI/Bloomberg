package com.example.bloomberg.services.impl;

import com.example.bloomberg.domain.Deal;
import com.example.bloomberg.repositories.DealRepository;
import com.example.bloomberg.services.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    @Override
    public Deal save(Deal deal) {
        if (search(deal).isEmpty())return dealRepository.save(deal);
        return null;
    }

    @Override
    public Optional<Deal> search(Deal deal) {
        return dealRepository.getDealByAmountAndToCurrencyIsoCodeAndFromCurrencyIsoCodeAndTimestamp(deal.getAmount(), deal.getToCurrencyIsoCode(), deal.getFromCurrencyIsoCode(), deal.getTimestamp());
    }

}
