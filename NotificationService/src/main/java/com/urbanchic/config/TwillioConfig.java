package com.urbanchic.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TwillioConfig {

    private String accountSid = TwilioConfigProperties.ACCOUNT_SID;
    private String authToken = TwilioConfigProperties.AUTH_TOKEN;
    private String phoneNumber = TwilioConfigProperties.PHONE_NUMBER;

    @PostConstruct
    public void setup() {
        Twilio.init(accountSid, authToken);
    }
}
