package com.rafael.purchaseapi.repository;

import com.rafael.purchaseapi.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {

}
