package com.rafael.storeapi.controller;

import com.rafael.storeapi.dto.PurchaseDTO;
import com.rafael.storeapi.kafka.SendKafkaMessage;
import com.rafael.storeapi.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/compra")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    public static Map<String, PurchaseDTO> purchases = new HashMap<>();
    private final SendKafkaMessage sendKafkaMessage;

    @GetMapping
    public Page<PurchaseDTO> listPurchases(@RequestBody(required = false) List<String> cpfList, Pageable pageable) {
        return purchaseService.listAll(cpfList, pageable);
    }

    @PostMapping
    public PurchaseDTO registerPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        purchaseDTO.setIdentificador(UUID.randomUUID().toString());
        purchaseDTO.setDate(LocalDateTime.now());
        purchaseDTO.setStatus("RECEBIDA");

        purchases.put(purchaseDTO.getIdentificador(), purchaseDTO);
        sendKafkaMessage.sendMessage(purchaseDTO);

        return purchaseDTO;
    }
}
