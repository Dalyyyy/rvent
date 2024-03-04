package org.services;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordResetService {
    SmsService smsService = new SmsService();
    public void initiatePasswordReset(String phoneNumber) throws Exception {
        String resetToken = generateSecureToken();
        String resetLink = "https://yourapp.com/reset-password?token=" + resetToken;

        // Store resetToken in your database associated with the user's account

        // Send SMS to the user with reset instructions
        sendPasswordResetSMS(phoneNumber, resetLink);
    }

    private void sendPasswordResetSMS(String phoneNumber, String resetLink) {
        // Example using a hypothetical SMS service
        String message = "To reset your password, please follow this link: " + resetLink;
        try {
            // Assuming you have a configured SMS service client
            smsService.sendSMS(phoneNumber, message);
            System.out.println("Password reset SMS sent successfully to " + phoneNumber);
        } catch (Exception e) {
            System.err.println("Failed to send password reset SMS: " + e.getMessage());
            // Handle the error appropriately
        }
    }

    private String generateSecureToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24]; // 192 bits
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}

