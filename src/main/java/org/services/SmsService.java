package org.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsService {
    public static final String ACCOUNT_SID = "AC27fc4784fff140f94b9652128479d820";
    public static final String AUTH_TOKEN = "d5b2e159063437f934b01adf18904739";

    public SmsService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSMS(String toPhoneNumber, String message) {
        Message.creator(
                        new PhoneNumber("+216"+toPhoneNumber), // To number
                        new PhoneNumber("14243534264"), // From number
                        message)
                .create();
    }


}
