package com.urbanchic.service.impl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.urbanchic.dto.PaymentDto;
import com.urbanchic.dto.PaymentOrderResponseDto;
import com.urbanchic.entity.Payment;
import com.urbanchic.entity.enums.PaymentStatus;
import com.urbanchic.repository.PaymentRepository;
import com.urbanchic.service.PaymentService;
import com.urbanchic.util.RazorPayDetails;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentOrderResponseDto createOrder(double amount) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(RazorPayDetails.KEY,RazorPayDetails.KEY_SECRET);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount",amount*100);
            jsonObject.put("currency",RazorPayDetails.CURRENCY);
            Order order = razorpayClient.orders.create(jsonObject);


            PaymentOrderResponseDto paymentOrderResponseDto = PaymentOrderResponseDto.builder()
                    .orderId(order.get("id"))
                    .status(order.get("status"))
                    .currency(order.get("currency"))
                    .entity(order.get("entity"))
                    .amount( order.get("amount"))
                    .build();
            return paymentOrderResponseDto;
        }catch (RazorpayException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Payment addPaymentDetails(PaymentDto paymentDto) {
        Payment newPayment = new Payment();
        newPayment.setPaymentId(paymentDto.getPaymentId());
        newPayment.setPaymentOrderId(paymentDto.getPaymentOrderId());
        newPayment.setPaymentSignature(paymentDto.getPaymentSignature());
        newPayment.setOrderId(paymentDto.getOrderId());
        newPayment.setAmount(paymentDto.getAmount());
        newPayment.setCurrency(paymentDto.getCurrency());
        newPayment.setPaymentStatus(PaymentStatus.SUCCESS.name());
        newPayment.setPaymentMethod("CARD");
        Payment savedPayment = paymentRepository.save(newPayment);
        return savedPayment;
    }
}
