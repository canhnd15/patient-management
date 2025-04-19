package com.davidnguyen.billingservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class BillingAccountReq {
    private UUID patientId;
    private String title;
    private String email;
}
