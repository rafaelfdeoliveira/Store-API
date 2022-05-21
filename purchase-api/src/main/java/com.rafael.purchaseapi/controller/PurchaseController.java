package com.rafael.purchaseapi.controller;

import com.rafael.purchaseapi.dto.PurchaseDTO;
import com.rafael.purchaseapi.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping
    public Page<PurchaseDTO> listPurchases(@RequestBody(required = false) List<String> cpfList, Pageable pageable) {
        return purchaseService.listAll(cpfList, pageable);
    }

    @PostMapping
    public PurchaseDTO registerPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.registerPurchase(purchaseDTO);
    }
}
