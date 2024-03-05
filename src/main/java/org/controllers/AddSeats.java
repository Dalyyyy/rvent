package org.controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import com.github.sarxos.webcam.Webcam;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventHandler;
import javafx.util.Duration;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.scene.control.*;
import org.util.RventDB;
import org.util.RventDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

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
import org.entities.Tickets;
import org.util.RventDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
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
    private ImageView webcamView;
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
    private Button back;
    @FXML
    private Label selectedSeats;

    @FXML
    private Button showw;


    private List<String> selectedSeatIds = new ArrayList<>();

    private Connection getConnection;

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
            // Prompt the user to perform an action
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Human Verification");
            alert.setHeaderText(null);
            alert.setContentText("Please click on the button below to confirm that you're human.");
            ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(confirmButtonType, ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait();

            // Verify user action
            if (result.isPresent() && result.get() == confirmButtonType) {
                // Schedule the capture of image after 4 seconds
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Platform.runLater(() -> {
                            try {
                                // Capture an image from the webcam
                                Webcam webcam = discoverWebcam();
                                if (webcam != null) {
                                    webcam.open();
                                    BufferedImage bufferedImage = webcam.getImage();
                                    webcam.close();

                                    // Convert BufferedImage to JavaFX Image
                                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                    try {
                                        ImageIO.write(bufferedImage, "png", outputStream);
                                        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                                        Image image = new Image(inputStream);
                                        webcamView.setImage(image);

                                        // Proceed with seat confirmation
                                        int id = 0;
                                        String siddd = Sid.getText();
                                        seeats seat = new seeats(id, siddd);
                                        serviceseat sr = new serviceseat();
                                        sr.insertOne(seat);
                                        String total = Sid.getText();
                                        selectedSeats.setText(total);

                                        // Show confirmation message
                                        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                                        confirmationAlert.setTitle("Confirmation");
                                        confirmationAlert.setHeaderText(null);
                                        confirmationAlert.setContentText("Confirmation faite!");
                                        confirmationAlert.showAndWait();
                                    } catch (IOException e) {
                                        showAlert("Error", "Failed to convert image.");
                                    }
                                } else {
                                    showAlert("Error", "No webcam available.");
                                }
                            } catch (AWTException | SQLException e) {
                                showAlert("Error", "An error occurred while confirming: " + e.getMessage());
                            }
                        });
                    }
                }));
                timeline.setCycleCount(1);
                timeline.play();
            } else {
                // User did not confirm, display an error message
                showAlert("Error", "Confirmation not done. Please confirm that you're human.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "An error occurred while confirming: " + e.getMessage());
        }
    }

    private Webcam discoverWebcam() throws AWTException {
        Webcam webcam = Webcam.getDefault(); // Recherche de la webcam par défaut
        if (webcam == null) {
            throw new AWTException("No webcam available.");
        }
        return webcam;}



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void goBack(ActionEvent event) {

    }


    @FXML
    void initialize() throws SQLException {
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

        // Add event handlers to all seat buttons
        A1.setOnAction(event -> handleSeatButtonClick(A1));
        A2.setOnAction(event -> handleSeatButtonClick(A2));
        A3.setOnAction(event -> handleSeatButtonClick(A3));
        A4.setOnAction(event -> handleSeatButtonClick(A4));
        A5.setOnAction(event -> handleSeatButtonClick(A5));
        A6.setOnAction(event -> handleSeatButtonClick(A6));
        A7.setOnAction(event -> handleSeatButtonClick(A7));
        A8.setOnAction(event -> handleSeatButtonClick(A8));
        B1.setOnAction(event -> handleSeatButtonClick(B1));
        B2.setOnAction(event -> handleSeatButtonClick(B2));
        B3.setOnAction(event -> handleSeatButtonClick(B3));
        B4.setOnAction(event -> handleSeatButtonClick(B4));
        B5.setOnAction(event -> handleSeatButtonClick(B5));
        B6.setOnAction(event -> handleSeatButtonClick(B6));
        B7.setOnAction(event -> handleSeatButtonClick(B7));
        B8.setOnAction(event -> handleSeatButtonClick(B8));
        C1.setOnAction(event -> handleSeatButtonClick(C1));
        C2.setOnAction(event -> handleSeatButtonClick(C2));
        C3.setOnAction(event -> handleSeatButtonClick(C3));
        C4.setOnAction(event -> handleSeatButtonClick(C4));
        C5.setOnAction(event -> handleSeatButtonClick(C5));
        C6.setOnAction(event -> handleSeatButtonClick(C6));
        C7.setOnAction(event -> handleSeatButtonClick(C7));
        C8.setOnAction(event -> handleSeatButtonClick(C8));
        List<String> chosenSeatNames = getChosenSeatNamesFromDatabase();

        // Iterate through all seat buttons and update their colors based on their chosen status
        for (Button button : Arrays.asList(A1, A2, A3, A4, A5, A6, A7, A8, B1, B2, B3, B4, B5, B6, B7, B8, C1, C2, C3, C4, C5, C6, C7, C8)) {
            String seatId = button.getId();
            // Check if any of the seat IDs associated with this button are chosen
            boolean isChosen = false;
            for (String chosenSeatName : chosenSeatNames) {
                // Split the chosenSeatName string into individual seat IDs
                String[] seatIds = chosenSeatName.split(", ");
                if (Arrays.asList(seatIds).contains(seatId)) {
                    isChosen = true;
                    break;
                }
            }
            // Update the button color based on whether it's chosen or not
            if (isChosen) {
                button.setStyle("-fx-background-color: red");
            } else {
                button.setStyle("-fx-background-color: green");
            }

            // Add event handler for each button to handle seat selection
            button.setOnAction(event -> handleSeatButtonClick(button));
        }
    }


    private List<String> getChosenSeatNamesFromDatabase() {
        List<String> chosenSeatNames = new ArrayList<>();
        try (Connection connection = RventDB.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT `seat_name` FROM `seats`")) {
            while (resultSet.next()) {
                chosenSeatNames.add(resultSet.getString("seat_name"));
            }
        } catch (SQLException e) {
            // Handle any SQL exception
            e.printStackTrace(); // You might want to log the exception instead of printing it
        }
        return chosenSeatNames;
    }


    private void showAlert(Alert.AlertType alertType, String databaseError, String s) {
    }

    private void handleSeatButtonClick(Button button) {
        // Change the color of the button to green
        button.setStyle("-fx-background-color: white");

        // Get the ID of the selected seat
        String seatId = button.getId();

        // Append the selected seat ID to the list of selected seats
        if (!selectedSeatIds.contains(seatId)) {
            selectedSeatIds.add(seatId);
        } else {
            selectedSeatIds.remove(seatId);
        }

        // Update the fields with the selected seat IDs
        updateFields();
    }

    private void updateFields() {
        // Update the selectedSeats label with the IDs of the selected seats
        StringBuilder seatsTextBuilder = new StringBuilder();
        for (String seatId : selectedSeatIds) {
            seatsTextBuilder.append(seatId).append(", ");
        }
        if (seatsTextBuilder.length() > 0) {
            seatsTextBuilder.delete(seatsTextBuilder.length() - 2, seatsTextBuilder.length()); // Remove the last comma and space
        }
        selectedSeats.setText(seatsTextBuilder.toString());

        // Update the Sid text field with the IDs of the selected seats
        StringBuilder sidBuilder = new StringBuilder();
        for (String seatId : selectedSeatIds) {
            sidBuilder.append(seatId).append(", ");
        }
        if (sidBuilder.length() > 0) {
            sidBuilder.delete(sidBuilder.length() - 2, sidBuilder.length()); // Remove the last comma and space
        }
        Sid.setText(sidBuilder.toString());
    }


}


