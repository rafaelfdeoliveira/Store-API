package com.rafael.purchaseapi.controller;

import com.rafael.purchaseapi.dto.PurchaseDTO;
import com.rafael.purchaseapi.service.CommunicateService;
import com.rafael.purchaseapi.service.PurchaseService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController implements CommunicateService {
    private final PurchaseService purchaseService;

    @Retry(name = "listPurchases", fallbackMethod = "fallback")
    @CircuitBreaker(name = "listPurchases", fallbackMethod = "fallback")
    @RateLimiter(name = "listPurchases", fallbackMethod = "fallback")
    @GetMapping
    public Page<PurchaseDTO> listPurchases(@RequestBody(required = false) List<String> cpfList, Pageable pageable) {
        return purchaseService.listAll(cpfList, pageable);
    }

    @Retry(name = "registerPurchases", fallbackMethod = "fallback")
    @CircuitBreaker(name = "registerPurchases", fallbackMethod = "fallback")
    @RateLimiter(name = "registerPurchases", fallbackMethod = "fallback")
    @PostMapping
    public PurchaseDTO registerPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.registerPurchase(purchaseDTO);
    }

    public Page<PurchaseDTO> fallback(@RequestBody(required = false) List<String> cpfList, Pageable pageable, Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public PurchaseDTO fallback(@RequestBody PurchaseDTO purchaseDTO, Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
