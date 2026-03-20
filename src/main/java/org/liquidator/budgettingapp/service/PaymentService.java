package org.liquidator.budgettingapp.service;

import io.grpc.stub.StreamObserver;
import org.liquidator.budgettingapp.payment.StorePaymentRequest;
import org.liquidator.budgettingapp.payment.StorePaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.liquidator.budgettingapp.payment.PaymentServiceGrpc;

@Service
public class PaymentService extends PaymentServiceGrpc.PaymentServiceImplBase {
    private final Logger LOG = LoggerFactory.getLogger(PaymentService.class);

    @Override
    public void storePayment(StorePaymentRequest storePaymentRequest,
                             StreamObserver<StorePaymentResponse> streamObserver) {
        String requestId = storePaymentRequest.getId();
        LOG.info("Received request from client to store payment: {}", requestId);

        var storePaymentResponse = StorePaymentResponse
                .newBuilder()
                .setId(requestId)
                .setMessage("Request: " + requestId +
                        "\nStored amount: " + storePaymentRequest.getAmount() +
                        "\nMessage: Successfully stored payment")
                .build();

        streamObserver.onNext(storePaymentResponse);
        streamObserver.onCompleted();
    }
}
