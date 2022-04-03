package com.rafael.storeapi.service;

import com.rafael.storeapi.dto.PurchaseDTO;
import com.rafael.storeapi.model.Purchase;
import com.rafael.storeapi.repository.PurchaseRepository;
import com.rafael.storeapi.repository.specification.PurchaseSpecification;
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
