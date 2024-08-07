package com.urbanchic.service.impl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.urbanchic.dto.OtpRequestDto;
import com.urbanchic.dto.OtpSmsResponseDto;
import com.urbanchic.exception.SmsNotSentException;
import com.urbanchic.external.Product;
import com.urbanchic.external.PurchasedOrderDto;
import com.urbanchic.service.SmsService;
import com.urbanchic.config.TwillioConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private  final TwillioConfig twillioConfig;

    @Override
    public OtpSmsResponseDto sendSms(OtpRequestDto otpRequestDto) {
        String otp = generateOtp();
        String otpMessage = "Dear " + otpRequestDto.getUserName()
                + ", Your verification OTP : " + otp
                + " for [COMPANY-NAME] Valid for next 2 minutes."
                + " Please do not share the OTP.";
        PhoneNumber to = new PhoneNumber(otpRequestDto.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twillioConfig.getPhoneNumber());
        try {
            Message message = Message.creator(to, from, otpMessage).create();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SmsNotSentException("Sms Not Sent.");
        }

        OtpSmsResponseDto otpSmsResponseDto = OtpSmsResponseDto.builder()
                .otp(otp)
                .from(from.toString())
                .to(otpRequestDto.getPhoneNumber())
                .message(otpMessage)
                .build();
        return otpSmsResponseDto;
    }

    @Override
    public void sendPurchaseOrderSms(PurchasedOrderDto purchasedOrderDto) {

        StringBuilder orderConfirmationMessage = new StringBuilder();
        orderConfirmationMessage.append("Dear "+purchasedOrderDto.getBuyerName()+"\n\n");
        orderConfirmationMessage.append("Thank you for your shopping with Urbanchic! \n");
        orderConfirmationMessage.append("Your order #"+purchasedOrderDto.getOrderId()+" has been received.\n");
        orderConfirmationMessage.append("Order Details :\n");

        for (Product product: purchasedOrderDto.getOrderedProductList()){
            orderConfirmationMessage.append("- "+product.getProductName()+" : "+product.getProductQuantity()+", ₹"+product.getProductPrice()+"\n");
        }
        double productPrice = purchasedOrderDto
                .getOrderedProductList()
                .stream()
                .mapToDouble(product -> product.getProductPrice().doubleValue() * product.getProductQuantity())
                .sum();
        double gstAmount = productPrice*0.18;
        double totalPrice = productPrice+gstAmount;

        orderConfirmationMessage.append("- Subtotal = "+productPrice+"\n");
        orderConfirmationMessage.append("- Shipping cost + GST "+gstAmount+"\n");
        orderConfirmationMessage.append("- Total = "+totalPrice+"\n\n");
        orderConfirmationMessage.append("- Payment Mode: ONLINE, TransactionId: "+1245789475681l+"\n\n");
        orderConfirmationMessage.append("------Team Urbanchic------");

        PhoneNumber to = new PhoneNumber(purchasedOrderDto.getMobileNumber());
        PhoneNumber from = new PhoneNumber(twillioConfig.getPhoneNumber());
        try {
            Message message = Message.creator(to, from, orderConfirmationMessage.toString()).create();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SmsNotSentException("Sms Not Sent.");
        }
    }

    public  String generateOtp(){
        return  new DecimalFormat("0000").format(new Random().nextInt(9999));
    }








}
