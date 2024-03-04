package org.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.entities.Reservation;
import org.entities.Tickets;
import org.services.seviceticket;


public class ModifierTicket {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adultcombo;

    @FXML
    private Button afficher;

    @FXML
    private CheckBox checkbox;

    @FXML
    private TextField childcombo;

    @FXML
    private Button confirmationBtn;

    @FXML
    private DatePicker dateRES;

    @FXML
    private Button delete;

    @FXML
    private Text evntname;

    @FXML
    private TextField idd;

    @FXML
    private Button modifier;
    @FXML
    private Button back;
    @FXML
    private Button seatsBtn;

    @FXML
    private TextField seniorcombo;

    @FXML
    private TextField time;

    @FXML
    private Text totalPrice;

    @FXML
    void adultcombo(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fakhri ticket.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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
    void delete(ActionEvent event) {

    }

    @FXML
    void goSelectSeats(ActionEvent event) {

    }

    @FXML
    void goToConfirmation(ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {
        try {
            LocalDate dateRes = dateRES.getValue();
            String time1 = time.getText();
            int adultTickets = Integer.parseInt(adultcombo.getText());
            int childTickets = Integer.parseInt(childcombo.getText());
            int seniorTickets = Integer.parseInt(seniorcombo.getText());
            int id = +1;

            java.util.Date utilDate = java.sql.Date.valueOf(dateRes);
            java.sql.Time sqlTime = java.sql.Time.valueOf(time1);

            Tickets t = new Tickets(id, 520, childTickets, seniorTickets, adultTickets, utilDate, sqlTime);
            seviceticket sr = new seviceticket();
            sr.updateOne(t); // Assuming you have an update method in your service to update a ticket
            showSuccessNotification("Modification réussie");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur SQL");
            alert.setContentText("Erreur lors de la modification du ticket: " + e.getMessage());
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de format de numéro");
            alert.setContentText("Veuillez saisir des nombres valides pour les champs de billets.");
            alert.showAndWait();
        }
    }



    void showSuccessNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    void updateTotal(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert adultcombo != null : "fx:id=\"adultcombo\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert afficher != null : "fx:id=\"afficher\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert checkbox != null : "fx:id=\"checkbox\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert childcombo != null : "fx:id=\"childcombo\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert confirmationBtn != null : "fx:id=\"confirmationBtn\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert dateRES != null : "fx:id=\"dateRES\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert evntname != null : "fx:id=\"evntname\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert idd != null : "fx:id=\"idd\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert modifier != null : "fx:id=\"modifier\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert seatsBtn != null : "fx:id=\"seatsBtn\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert seniorcombo != null : "fx:id=\"seniorcombo\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'ModifierTicket.fxml'.";
        assert totalPrice != null : "fx:id=\"totalPrice\" was not injected: check your FXML file 'ModifierTicket.fxml'.";

    }

}
