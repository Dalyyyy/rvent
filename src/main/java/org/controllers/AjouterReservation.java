package org.controllers;
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
        import javafx.scene.control.*;
        import org.entities.Reservation;
        import javafx.application.Application;
        import javafx.scene.control.Alert;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.GridPane;
        import javafx.stage.Stage;

        import java.util.Optional;
        import java.util.Random;
        import org.services.ServiceReservation;
public class AjouterReservation {




    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    @FXML
    void Ajouter(ActionEvent event) {
        try {
            int id = Integer.parseInt(tfId_Res.getText());
            String nomEvent = tfNomEvent.getText();
            String nomRes = tfNomRes.getText();
            LocalDate dateRes = tfdate.getValue();
            LocalTime time = LocalTime.parse(tfTime.getText()); // Parse text to LocalTime

            // Generate CAPTCHA code
            String captcha = generateCAPTCHA();

            // Create confirmation dialog with CAPTCHA input
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Confirmation");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter the CAPTCHA code to confirm: " + captcha);
            Optional<String> result = dialog.showAndWait();

            // Verify CAPTCHA code
            if (result.isPresent() && result.get().equals(captcha)) {
                // CAPTCHA code is correct, proceed with reservation insertion
                Reservation reservation = new Reservation(id, nomRes, nomEvent, dateRes, time);
                ServiceReservation sr = new ServiceReservation();
                sr.insertOne(reservation);

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Confirmation done. Reservation added successfully!");
                successAlert.showAndWait();
            } else {
                // CAPTCHA code is incorrect, show error message
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Incorrect CAPTCHA code. Reservation not added.");
                errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL ERROR");
            alert.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NUMBER FORMAT EXCEPTION");
            alert.show();
        } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DATE/TIME FORMAT ERROR");
            alert.setContentText("Please enter the date/time in correct format.");
            alert.show();
        }
    }

    // Method to generate a random CAPTCHA code with letters and numbers
    private String generateCAPTCHA() {
        int length = 4; // Adjust the length of the CAPTCHA code as needed
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }
        return captcha.toString();
    }


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
            tfId_Res.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    @FXML
    void initialize() {
        assert tfId_Res != null : "fx:id=\"tfId_Res\" was not injected: check your FXML file 'AjouterReservation.fxml'.";
        assert tfNomEvent != null : "fx:id=\"tfNomEvent\" was not injected: check your FXML file 'AjouterReservation.fxml'.";
        assert tfNomRes != null : "fx:id=\"tfNomRes\" was not injected: check your FXML file 'AjouterReservation.fxml'.";
        assert tfTime != null : "fx:id=\"tfTime\" was not injected: check your FXML file 'AjouterReservation.fxml'.";
        assert tfdate != null : "fx:id=\"tfdate\" was not injected: check your FXML file 'AjouterReservation.fxml'.";

    }

}