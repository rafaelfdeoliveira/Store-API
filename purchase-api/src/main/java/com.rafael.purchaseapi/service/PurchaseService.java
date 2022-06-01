package com.rafael.purchaseapi.service;

import com.rafael.purchaseapi.dto.PurchaseDTO;
import com.rafael.purchaseapi.model.Purchase;
import com.rafael.purchaseapi.repository.PurchaseRepository;
import com.rafael.purchaseapi.repository.specification.PurchaseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public Page<PurchaseDTO> listAll(List<String> cpfList, Pageable pageable) {
        if (cpfList == null) return purchaseRepository.findAll(pageable).map(PurchaseDTO::convert);
        return purchaseRepository
                .findAll(PurchaseSpecification.filterByCpfs(cpfList), pageable)
                .map(PurchaseDTO::convert);
    }

    public PurchaseDTO registerPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseRepository.save(Purchase.convert(purchaseDTO));
        return PurchaseDTO.convert(purchase);
    }
}
