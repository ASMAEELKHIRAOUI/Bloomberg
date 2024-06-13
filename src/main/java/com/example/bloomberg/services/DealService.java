package com.example.bloomberg.services;

import com.example.bloomberg.domain.Deal;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DealService {

    Deal save(Deal deal);

    Optional<Deal> search(Deal deal);
}
