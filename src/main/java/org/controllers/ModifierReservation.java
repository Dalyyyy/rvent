package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.entities.Reservation;
import org.services.ServiceReservation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ModifierReservation {

    @FXML
    private TextField tfId_Res;

    @FXML
    private TextField tfNomEvent;

    @FXML
    private TextField tfNomRes;

    @FXML
    private TextField tfTime;

    @FXML
    private DatePicker tfdate;
    private Reservation resToModify;
    @FXML
    private Button idAfficher;
    private final ServiceReservation res = new ServiceReservation();
    @FXML
    void Annuler(ActionEvent event) {
        tfId_Res.setText(null);
        tfNomEvent.setText(null);
        tfNomRes.setText(null);
        tfdate.setValue(null);
        tfTime.setText(null);
    }
    @FXML
    void Afficher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherRes.fxml"));
            idAfficher.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }}


    @FXML
    void modifier(ActionEvent event) {
        try {
            int id = Integer.parseInt(tfId_Res.getText());
            String nomRes = tfNomRes.getText();
            String nomEvent = tfNomEvent.getText();
            LocalDate date = tfdate.getValue();
            LocalTime time = LocalTime.parse(tfTime.getText());

            // Créer un nouvel objet Reservation avec les valeurs récupérées
            Reservation reservation = new Reservation(id, nomRes, nomEvent, date, time);

            // Appeler la méthode updateOne du service pour effectuer la mise à jour
            res.updateOne(reservation);

            // Afficher une notification de réussite
            showSuccessNotification("Modif réussie");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            throw new RuntimeException("Error executing SQL query", e);
        }
    }



    void showSuccessNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setRestoToModify(Reservation res) {
        this.resToModify = res;


        if (res != null) {
            tfId_Res.setText(String.valueOf(res.getId()));
            tfNomRes.setText(String.valueOf(res.getFullName()));
            tfNomEvent.setText(String.valueOf(res.getEventName()));
            tfTime.setText(String.valueOf(res.getTime()));
            tfdate.setValue(LocalDate.parse(String.valueOf(res.getDate())));



        }

    }}