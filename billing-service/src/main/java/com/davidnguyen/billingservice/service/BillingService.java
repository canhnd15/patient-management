package com.davidnguyen.billingservice.service;

import com.davidnguyen.billingservice.BillingMapper;
import com.davidnguyen.billingservice.dto.BillingAccountReq;
import com.davidnguyen.billingservice.entity.Billing;
import com.davidnguyen.billingservice.repository.BillingRepository;
import org.springframework.stereotype.Service;

@Service
public class BillingService {
    private final BillingRepository billingRepository;

    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    public Billing createBillingAccount(BillingAccountReq req) {
        Billing billing = BillingMapper.toEntity(req);
        return billingRepository.save(billing);
    }
}
