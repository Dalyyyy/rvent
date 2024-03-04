package org.controllers;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.sql.SQLException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;

import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.entities.Tickets;
import org.services.seviceticket;

public class ticket {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField idd;
    @FXML
    private TextField adultcombo;

    @FXML
    private CheckBox checkbox;

    @FXML
    private TextField childcombo;

    @FXML
    private Button confirmationBtn;

    @FXML
    private DatePicker dateRES;

    @FXML
    private Text evntname;

    @FXML
    private Button seatsBtn;

    @FXML
    private TextField seniorcombo;
    @FXML
    private Button delete;
    @FXML
    private TextField time;
    @FXML
    private Button afficher;
    @FXML
    private Text totalPrice;
    @FXML
    private Button modifier;
    @FXML
    private Button affs;

    @FXML
    void adultcombo(ActionEvent event) {

    }

    @FXML
    void goSelectSeats(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fakhribooking.fxml"));
            seatsBtn.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void modifier(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierTicket.fxml"));
            modifier.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void affs(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherSeats.fxml"));
            affs.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void updateTotal(ActionEvent event) {
        int adultPrice = 10; // Define adult ticket price
        int childPrice = 5;  // Define child ticket price
        int seniorPrice = 8; // Define senior ticket price
        int vip = 20; // Define VIP price

        int adultTickets = Integer.parseInt(adultcombo.getText());
        int childTickets = Integer.parseInt(childcombo.getText());
        int seniorTickets = Integer.parseInt(seniorcombo.getText());

        int total = adultTickets * adultPrice + childTickets * childPrice + seniorTickets * seniorPrice;
        if (checkbox.isSelected()) {
            total += vip;
        }
        totalPrice.setText("Total Price: Dinar " + total);
    }



        // Twilio credentials


    @FXML
    void goToConfirmation(ActionEvent event) throws IOException {
        try {
            LocalDate dateRes = dateRES.getValue();
            String time1 = time.getText();
            // int totprix= Integer.parseInt(this.totalPrice.getText());
            int adultTickets = Integer.parseInt(adultcombo.getText());
            int childTickets = Integer.parseInt(childcombo.getText());
            int seniorTickets = Integer.parseInt(seniorcombo.getText());
            int id = +1;

            java.util.Date utilDate = java.sql.Date.valueOf(dateRes);

            java.sql.Time sqlTime = java.sql.Time.valueOf(time1);

            Tickets t = new Tickets(id, 0, childTickets, seniorTickets, adultTickets, utilDate, sqlTime);
            seviceticket sr = new seviceticket();
            sr.insertOne(t);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL ERROR");
            alert.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NUMBER FORMAT EXCEPTION");
            alert.show();
        }
        TextInputDialog phoneNumberDialog = new TextInputDialog();
        phoneNumberDialog.setTitle("Phone Number Confirmation");
        phoneNumberDialog.setHeaderText(null);
        phoneNumberDialog.setContentText("Please enter your phone number:");

        Optional<String> phoneNumberResult = phoneNumberDialog.showAndWait();
        if (phoneNumberResult.isPresent()) {
            String phoneNumber = phoneNumberResult.get();
            if (isValidPhoneNumber(phoneNumber)) {
                String confirmationCode = generateConfirmationCode(); // Generate confirmation code
                boolean confirmationSent = sendConfirmationCode(phoneNumber, confirmationCode);
                if (confirmationSent) {
                    // Prompt user to enter the received confirmation code
                    TextInputDialog confirmationCodeDialog = new TextInputDialog();
                    confirmationCodeDialog.setTitle("Confirmation Code");
                    confirmationCodeDialog.setHeaderText(null);
                    confirmationCodeDialog.setContentText("Please enter the confirmation code sent to your phone:");

                    Optional<String> confirmationCodeResult = confirmationCodeDialog.showAndWait();
                    if (confirmationCodeResult.isPresent()) {
                        String enteredCode = confirmationCodeResult.get();
                        if (enteredCode.equals(confirmationCode)) {
                            // Confirmation code matches, proceed with confirmation
                            // Your confirmation logic here
                            // Display thank you message
                            Alert thankYouAlert = new Alert(Alert.AlertType.INFORMATION);
                            thankYouAlert.setTitle("Thank You");
                            thankYouAlert.setHeaderText(null);
                            thankYouAlert.setContentText("Thank you, dear customer.");
                            thankYouAlert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Invalid Confirmation Code");
                            alert.setHeaderText(null);
                            alert.setContentText("The entered confirmation code is incorrect.");
                            alert.showAndWait();
                        }
                    } else {
                        // User canceled entering the confirmation code
                        // Handle as needed
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Confirmation Code Sending Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to send the confirmation code. Please try again later.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Phone Number");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid phone number.");
                alert.showAndWait();
            }
        } else {
            // User canceled entering the phone number
            // Handle as needed
        }
    }


    private boolean isValidPhoneNumber(String phoneNumber) {
            // Implement phone number validation logic here
            // You can use regular expressions or other validation methods
            return phoneNumber.matches("\\d{8}"); // Example: 10-digit phone number
        }

        private String generateConfirmationCode() {
            Random random = new Random();
            int code = 100000 + random.nextInt(900000); // Generates a random number between 100000 and 999999
            return String.valueOf(code);
        }

    public boolean sendConfirmationCode(String phoneNumber, String confirmationCode) throws IOException {
        try {
             final String ACCOUNT_SID = "AC2e6d3814f509f4df914a32a6dd1433d6";
            final String AUTH_TOKEN = "039f91859dc7b9fde20279f96fec74f5";
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            String messageBody = "Your confirmation code is: " + confirmationCode;

            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("+216"+phoneNumber),
                            new com.twilio.type.PhoneNumber("+18722013339"),
                            messageBody)
                    .create();

            System.out.println(message.getSid());
            if (message.getSid() != null) {
                // Message sent successfully
                return true;
            } else {
                // Failed to send message
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    void delete(ActionEvent event) {

        idd.clear();
        adultcombo.clear();
        childcombo.clear();
        seniorcombo.clear();
        time.clear();
        totalPrice.setText("");


    }

    @FXML
    void afficher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherTicket.fxml"));
            afficher.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }











    @FXML
    void initialize() {
        assert adultcombo != null : "fx:id=\"adultcombo\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert checkbox != null : "fx:id=\"checkbox\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert childcombo != null : "fx:id=\"childcombo\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert confirmationBtn != null : "fx:id=\"confirmationBtn\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert dateRES != null : "fx:id=\"dateRES\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert evntname != null : "fx:id=\"evntname\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert seatsBtn != null : "fx:id=\"seatsBtn\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert seniorcombo != null : "fx:id=\"seniorcombo\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'fakhri ticket.fxml'.";
        assert totalPrice != null : "fx:id=\"totalPrice\" was not injected: check your FXML file 'fakhri ticket.fxml'.";

    }

}