package com.rafael.purchaseapi.service;

import com.rafael.purchaseapi.dto.PurchaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("eureka-client-purchases")
public interface CommunicateService {

    @GetMapping
    Page<PurchaseDTO> listPurchases(@RequestBody(required = false) List<String> cpfList, Pageable pageable);

    @PostMapping
    PurchaseDTO registerPurchase(@RequestBody PurchaseDTO purchaseDTO);

}
