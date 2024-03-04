package org.entities;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class Example {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC36fb2c4543f3327b7db8290bcc7d4783";
    public static final String AUTH_TOKEN = "7fc5461bf881123ab1005d25d9917312";

    public void Whatsapp() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String msg = "la demande de sponsoring a éte envoyer avec succés";
        Message message = Message.creator(

                new com.twilio.type.PhoneNumber("whatsapp:+21658427165"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                msg
        ).create();

        System.out.println(message.getSid());
    }
}