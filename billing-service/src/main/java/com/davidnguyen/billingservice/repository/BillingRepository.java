package com.davidnguyen.billingservice.repository;

import com.davidnguyen.billingservice.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingRepository extends JpaRepository<Billing, UUID> {
}
