package com.davidnguyen.billingservice;

import com.davidnguyen.billingservice.dto.BillingAccountReq;
import com.davidnguyen.billingservice.entity.Billing;

import java.time.LocalDate;
import java.util.UUID;

public class BillingMapper {
    public static Billing toEntity(BillingAccountReq req) {
        Billing billing = new Billing();
        billing.setId(UUID.randomUUID());
        billing.setPatientId(req.getPatientId());
        billing.setTitle(req.getTitle());
        billing.setEmail(req.getEmail());
        billing.setCreatedDate(LocalDate.now());
        billing.setExpireDate(LocalDate.now().plusDays(5));
        billing.setStatus("STARTED");
        billing.setDiscount(0);
        billing.setTotalAmount(1000);

        return billing;
    }
}
