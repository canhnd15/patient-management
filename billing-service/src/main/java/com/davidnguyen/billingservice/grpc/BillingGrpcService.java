package com.davidnguyen.billingservice.grpc;

import billing.*;
import com.davidnguyen.billingservice.dto.BillingAccountReq;
import com.davidnguyen.billingservice.entity.Billing;
import com.davidnguyen.billingservice.service.BillingService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
    private final BillingService billingService;

    public BillingGrpcService(BillingService billingService) {
        this.billingService = billingService;
    }

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {

        log.info("createBillingAccount request: {}", billingRequest.toString());

        BillingAccountReq req = BillingAccountReq.builder()
                .patientId(UUID.fromString(billingRequest.getPatientId()))
                .title(billingRequest.getName())
                .email(billingRequest.getEmail())
                .build();
        Billing savedBill = billingService.createBillingAccount(req);

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId(String.valueOf(savedBill.getId()))
                .setStatus(savedBill.getStatus())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getBillingByAccountId(AccountInfoRequest accountInfoRequest,
                                      StreamObserver<BillingByAccountResponse> responseObserver) {

        String code = accountInfoRequest.getAccountCode();
        int amount = (int) accountInfoRequest.getAmount();

        if ("ADX134".equals(code)) {
            BillingByAccountResponse response = BillingByAccountResponse.newBuilder()
                    .setAccountId("123")
                    .setAccountCode("ADX134")
                    .setAccountEmail("test@example.com")
                    .setBalance(amount)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
