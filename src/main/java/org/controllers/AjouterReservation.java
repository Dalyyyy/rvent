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
        import javafx.scene.control.Alert;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.TextField;
       import org.entities.Reservation;
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

            Reservation reservation = new Reservation(id, nomRes, nomEvent, dateRes, time);
            ServiceReservation sr = new ServiceReservation();
            sr.insertOne(reservation);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText(" C bon merci !");
            alert.showAndWait();
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