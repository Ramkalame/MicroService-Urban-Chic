package com.urbanchic.service.impl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.urbanchic.dto.otp.SmsOtpRequestDto;
import com.urbanchic.dto.otp.VerifyOtpDto;
import com.urbanchic.entity.Otp;
import com.urbanchic.event.NonVerifiedExpiredOtpDeletionEvent;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.exception.ExpiredOtpException;
import com.urbanchic.exception.IncorrectOtpException;
import com.urbanchic.exception.SmsNotSentException;
import com.urbanchic.repository.OtpRepository;
import com.urbanchic.service.SmsService;
import com.urbanchic.config.TwillioConfig;
import com.urbanchic.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private  final TwillioConfig twillioConfig;
    private final OtpRepository otpRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public String sendOtpSms(SmsOtpRequestDto smsOtpRequestDto) {
        String otpNumber = generateOtp();
        String otpMessage = "Dear " + smsOtpRequestDto.getUserName()
                + ", Your One-Time Password(OTP) for verifying your account is : " + otpNumber
                + " This code is valid for 30 seconds"
                + " Please do not share the OTP."
                +" Thank you, Team Urbanchic";
        PhoneNumber to = new PhoneNumber(smsOtpRequestDto.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twillioConfig.getPhoneNumber());
        try {
            Message message = Message.creator(to, from, otpMessage).create();
            log.info("sms has been sent");
        } catch (Exception e) {
            log.info("sms has not been sent");
            log.error(String.valueOf(e));
            throw new SmsNotSentException("Sms sent failed.Please try again");
        }

        Otp newOtp = Otp.builder()
                .emailOrNumber(smsOtpRequestDto.getPhoneNumber())
                .otpNumber(otpNumber)
                .createdDate(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusSeconds(31))
                .build();
        Otp savedOtp = otpRepository.save(newOtp);
        eventPublisher.publishEvent(new NonVerifiedExpiredOtpDeletionEvent(this));
        return "Otp has been sent on number "+savedOtp.getEmailOrNumber();
    }

    private String generateOtp(){
        return  new DecimalFormat("0000").format(new Random().nextInt(9999));
    }


    @Override
    public boolean verifyOtp(VerifyOtpDto verifyOtpDto) {
        Otp otp = otpRepository.findByOtpNumber(verifyOtpDto.getOtpNumber()).orElseThrow(() ->
                new EntityNotFoundException("Incorrect OTP"));
        if (verifyOtpDto.getEmailOrNumber().equals(otp.getEmailOrNumber())){
           if (LocalDateTime.now().isBefore(otp.getCreatedDate().plusSeconds(30))){
               otpRepository.delete(otp);
               return true;
            }else {
               otpRepository.delete(otp);
               throw new ExpiredOtpException("Otp is Expired");
           }
        }else {
            throw new IncorrectOtpException("Incorrect OTP");
        }
    }

    @Async
    @EventListener
    private void nonVerifiedAndExpiredOtpDeletionListener(NonVerifiedExpiredOtpDeletionEvent event){
        List<Otp> expiredOtpList = otpRepository.findAllByCreatedDateBefore(
                LocalDateTime.now().minusSeconds(31));
        otpRepository.deleteAll(expiredOtpList);
    }

//    @Override
//    public void sendPurchaseOrderSms(PurchasedOrderDto purchasedOrderDto) {
//
//        StringBuilder orderConfirmationMessage = new StringBuilder();
//        orderConfirmationMessage.append("Dear "+purchasedOrderDto.getBuyerName()+"\n\n");
//        orderConfirmationMessage.append("Thank you for your shopping with Urbanchic! \n");
//        orderConfirmationMessage.append("Your order #"+purchasedOrderDto.getOrderId()+" has been received.\n");
//        orderConfirmationMessage.append("Order Details :\n");
//
//        for (OrderedProduct product: purchasedOrderDto.getOrderedProductList()){
////            orderConfirmationMessage.append("- "+product.getProductName()+" : "+product.getProductQuantity()+", ₹"+product.getProductPrice()+"\n");
//        }
//        double productPrice = purchasedOrderDto
//                .getOrderedProductList()
//                .stream()
//                .mapToDouble(product -> product.getProductPrice().doubleValue() * product.getProductQuantity())
//                .sum();
//        double gstAmount = productPrice*0.18;
//        double totalPrice = productPrice+gstAmount;
//
//        orderConfirmationMessage.append("- Subtotal = "+productPrice+"\n");
//        orderConfirmationMessage.append("- Shipping cost + GST "+gstAmount+"\n");
//        orderConfirmationMessage.append("- Total = "+totalPrice+"\n\n");
//        orderConfirmationMessage.append("- Payment Mode: ONLINE, TransactionId: "+1245789475681l+"\n\n");
//        orderConfirmationMessage.append("------Team Urbanchic------");
//
//        PhoneNumber to = new PhoneNumber(purchasedOrderDto.getMobileNumber());
//        PhoneNumber from = new PhoneNumber(twillioConfig.getPhoneNumber());
//        try {
//            Message message = Message.creator(to, from, orderConfirmationMessage.toString()).create();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SmsNotSentException("Sms Not Sent.");
//        }
//    }









}
