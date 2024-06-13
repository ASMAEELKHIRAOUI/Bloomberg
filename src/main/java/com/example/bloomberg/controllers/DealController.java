package com.example.bloomberg.controllers;

import com.example.bloomberg.domain.Deal;
import com.example.bloomberg.dto.DealDTO;
import com.example.bloomberg.handler.response.ResponseMessage;
import com.example.bloomberg.services.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deal")
public class DealController {

    private final DealService dealService;

    @PostMapping
    public ResponseEntity<DealDTO> save(@RequestBody DealDTO dealToSave){
        Deal deal = dealService.save(dealToSave.toDeal());
        if (deal == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.created("Success", DealDTO.fromDeal(deal));
    }
}
