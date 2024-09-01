package com.urbanchic.service.impl;


import com.urbanchic.dto.otp.EmailOtpRequestDto;
import com.urbanchic.entity.Otp;
import com.urbanchic.event.NonVerifiedExpiredOtpDeletionEvent;
import com.urbanchic.exception.EmailNotSentException;
import com.urbanchic.repository.OtpRepository;
import com.urbanchic.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final OtpRepository otpRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public String sendOtpEmail(EmailOtpRequestDto emailOtpRequestDto) {
            String otpNumber = generateOtp();
        String otpEmailText = "Dear " + emailOtpRequestDto.getUserName()
                + ", Your One-Time Password(OTP) for verifying your account is : " + otpNumber
                + ". This code is valid for 30 seconds"
                + " Please do not share the OTP."
                +" Thank you, Team Urbanchic";
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(emailOtpRequestDto.getEmail());
            simpleMailMessage.setSubject("Urbanchic Account Verification OTP");
            simpleMailMessage.setText(otpEmailText);
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            throw new EmailNotSentException("Email sent failed. Please try again");
        }
        Otp newOtp = Otp.builder()
                .otpNumber(otpNumber)
                .emailOrNumber(emailOtpRequestDto.getEmail())
                .createdDate(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusSeconds(31))
                .build();
        Otp savedOtp = otpRepository.save(newOtp);
        eventPublisher.publishEvent(new NonVerifiedExpiredOtpDeletionEvent(this));
        return  "Otp has been sent on email : "+savedOtp.getEmailOrNumber();
    }


    private String generateOtp(){
        return  new DecimalFormat("0000").format(new Random().nextInt(9999));
    }


//    @Override
//    public void sendPurchaseOrderMail(PurchasedOrderDto purchasedOrderEmailDto) {
//        try {
//            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage,true);
//            String htmlBody = getPurchaseOrderMailBody(purchasedOrderEmailDto);
//            helper.setTo(purchasedOrderEmailDto.getEmail());
//            helper.setSubject("Urbanchic: Purchase Order Mail");
//            helper.setText(htmlBody,true);
//            javaMailSender.send(mimeMailMessage);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    private String getPurchaseOrderMailBody(PurchasedOrderDto purchasedOrderDto){
//        // Initialize a StringBuilder to accumulate HTML content
//        StringBuilder htmlContent = new StringBuilder();
//
//        // Start building the HTML structure
//        htmlContent.append("<!DOCTYPE html>")
//                .append("<html>")
//                .append("<head>")
//                .append("<title>Patagonia Email</title>")
//                .append("<style>")
//                .append("body { font-family: Cambria, Cochin, Georgia, Times, \"Times New Roman\", serif; font-size: 16px; line-height: 1.5; margin: 0; padding: 0; }")
//                .append("a { color: #007bff; text-decoration: none; }")
//                .append(".container { width: 479px; margin: 0 auto; padding: 20px; }")
//                .append(".header { background-color: #c2f0d4; padding: 20px; border-radius: 5px 5px 0px 0px; }")
//                .append(".header img { width: 50%; max-width: 300px; display: flex; justify-content: center; align-items: center; margin: auto; }")
//                .append(".body { background-color: #f2f2f2; padding: 20px; }")
//                .append(".footer { background-color: #224f34; padding: 20px; color: whitesmoke; text-align: center; border-radius: 0px 0px 5px 5px; }")
//                .append("table { border-spacing: 5px 5px; width: 100%; }")
//                .append("td, th { text-align: center; padding: 8px; }")
//                .append(".main-heading { font-size: 20px; text-align: center; }")
//                .append(".main-heading p { margin: 0; font-weight: bold; }")
//                .append("</style>")
//                .append("</head>")
//                .append("<body>")
//                .append("<div class=\"container\">")
//                .append("<div class=\"header\">")
//                .append("<img src=\"https://i.ibb.co/jTvWfL1/urbanchic-logo.png\" alt=\"urbanchic-logo\" />")
//                .append("</div>")
//                .append("<div class=\"body\">")
//                .append("<div class=\"main-heading\">")
//                .append("<p>!! Hello ").append(purchasedOrderDto.getBuyerName()).append("</p>")
//                .append("<p>Thank you for shopping</p>")
//                .append("<p>at Urban-Chic!</p>")
//                .append("</div>")
//                .append("<p>Your order is being processed. As soon as it ships, we'll send you a Shipping confirmation email with tracking information.</p>")
//                .append("<h3>Your order</h3>")
//                .append("<table>")
//                .append("<tr><th>Item</th><th>Quantity</th><th>Price</th></tr>");
//
//        // Iterate through the list of products to populate the table rows
//        for (OrderedProduct product : purchasedOrderDto.getOrderedProductList()) {
//            htmlContent.append("<tr>")
////                    .append("<td>").append(product.getProductName()).append("</td>") // Product Name
//                    .append("<td>").append(product.getProductQuantity()).append("</td>") // Quantity
//                    .append("<td>").append(product.getProductPrice()).append("</td>") // Price
//                    .append("</tr>");
//        }
//
//        // Calculate total price
//        double productPrice = purchasedOrderDto
//                .getOrderedProductList()
//                .stream()
//                .mapToDouble(product -> product.getProductPrice().doubleValue() * product.getProductQuantity())
//                .sum();
//        double gstAmount = productPrice*0.18;
//        double totalPrice = productPrice+gstAmount;
//        //add gst row
//        htmlContent.append("<tr><td>18% GST</td><td></td><td>$").append(gstAmount).append("</td></tr>");
//        // Add total row
//        htmlContent.append("<tr><td>Total</td><td></td><td>$").append(totalPrice).append("</td></tr>");
//
//        // Finish building the HTML structure
//        htmlContent.append("</table>")
//                .append("<p>If any query please contact us at +91-6425869474 or queris@urbanchic.com</p>")
//                .append("</div>")
//                .append("<div class=\"footer\">")
//                .append("<p>&copy; 2024 Urbanchic, Inc. All rights reserved.</p>")
//                .append("</div>")
//                .append("</div>")
//                .append("</body>")
//                .append("</html>");
//
//        return htmlContent.toString();
//    }
}
