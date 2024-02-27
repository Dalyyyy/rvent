package org.controllers;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    void goToConfirmation(ActionEvent event) {
        try {
            LocalDate dateRes = dateRES.getValue();
            String time1 = time.getText();
           // get.totprix= totalPrice.getText();
            int adultTickets = Integer.parseInt(adultcombo.getText());
            int childTickets = Integer.parseInt(childcombo.getText());
            int seniorTickets = Integer.parseInt(seniorcombo.getText());
            int id = Integer.parseInt(idd.getText());

            java.util.Date utilDate = java.sql.Date.valueOf(dateRes);

            java.sql.Time sqlTime = java.sql.Time.valueOf(time1);

            Tickets t = new Tickets(id, 520, childTickets, seniorTickets, adultTickets,utilDate,sqlTime);
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