package com.davidnguyen.billingservice.grpc;

import billing.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    private final Integer BALANCE = 1_500_0000;

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {

        log.info("createBillingAccount request: {}", billingRequest.toString());

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
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
                    .setBalance(BALANCE - amount)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
