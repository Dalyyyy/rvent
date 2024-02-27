package org.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.entities.seeats;
import org.services.serviceseat;
public class AddSeats {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button A1;

    @FXML
    private Button A2;

    @FXML
    private Button A3;

    @FXML
    private Button A4;

    @FXML
    private Button A5;

    @FXML
    private Button A6;

    @FXML
    private Button A7;

    @FXML
    private Button A8;

    @FXML
    private Button B1;

    @FXML
    private Button B2;

    @FXML
    private Button B3;

    @FXML
    private Button B4;

    @FXML
    private Button B5;

    @FXML
    private Button B6;

    @FXML
    private Button B7;

    @FXML
    private Button B8;

    @FXML
    private Button C1;

    @FXML
    private Button C2;

    @FXML
    private Button C3;

    @FXML
    private Button C4;

    @FXML
    private Button C5;

    @FXML
    private Button C6;

    @FXML
    private Button C7;

    @FXML
    private Button C8;

    @FXML
    private TextField Sid;

    @FXML
    private Button backBtn;

    @FXML
    private Button cancel1;

    @FXML
    private Button confirmationBtn1;

    @FXML
    private TextField idid;
    @FXML
    private Button back ;
    @FXML
    private Label selectedSeats;

    @FXML
    private Button showw;




    @FXML
    void cancel(ActionEvent event) {

        idid.clear();
        Sid.clear();
       // childcombo.clear();
        //seniorcombo.clear();
       // time.clear();
        //totalPrice.setText("");
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
    void show(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherSeats.fxml"));
            showw.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void Confirmer(ActionEvent event) {
        try {
            int id = Integer.parseInt(idid.getText());
            String siddd = Sid.getText();
            seeats seat= new seeats(id,siddd);
            serviceseat sr = new serviceseat();
            sr.insertOne(seat);
            String total=Sid.getText();
            selectedSeats.setText(total);
        } catch (NumberFormatException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NUMBER FORMAT EXCEPTION");
            alert.show();

        }
    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert A1 != null : "fx:id=\"A1\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert A2 != null : "fx:id=\"A2\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert A3 != null : "fx:id=\"A3\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert A4 != null : "fx:id=\"A4\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert A5 != null : "fx:id=\"A5\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert A6 != null : "fx:id=\"A6\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert A7 != null : "fx:id=\"A7\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert A8 != null : "fx:id=\"A8\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert B1 != null : "fx:id=\"B1\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert B2 != null : "fx:id=\"B2\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert B3 != null : "fx:id=\"B3\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert B4 != null : "fx:id=\"B4\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert B5 != null : "fx:id=\"B5\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert B6 != null : "fx:id=\"B6\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert B7 != null : "fx:id=\"B7\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert B8 != null : "fx:id=\"B8\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert C1 != null : "fx:id=\"C1\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert C2 != null : "fx:id=\"C2\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert C3 != null : "fx:id=\"C3\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert C4 != null : "fx:id=\"C4\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert C5 != null : "fx:id=\"C5\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert C6 != null : "fx:id=\"C6\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert C7 != null : "fx:id=\"C7\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert C8 != null : "fx:id=\"C8\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert Sid != null : "fx:id=\"Sid\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert confirmationBtn1 != null : "fx:id=\"confirmationBtn1\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert idid != null : "fx:id=\"idid\" was not injected: check your FXML file 'fakhribooking.fxml'.";
        assert selectedSeats != null : "fx:id=\"selectedSeats\" was not injected: check your FXML file 'fakhribooking.fxml'.";

    }}


