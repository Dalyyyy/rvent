package org.controllers;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.entities.Reservation;
import org.services.ServiceReservation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class AfficherRes {

    @FXML
    private FlowPane RestoContainer;

    @FXML
    private Label userIdLabel;
    @FXML
    private Button idModifier;
    @FXML
    private Button idAjouter;
    @FXML
    private Button idRetour;
    @FXML
    private Button idSupp;
    private Reservation selectedRes;

    private void displayRes(List<Reservation> reservations) {
        for (Reservation res : reservations) {
            VBox card = createRerservationCard(res);
            RestoContainer.getChildren().add(card);
        }
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierReservation.fxml"));
            idModifier.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }}

    @FXML
    public void OnRefresh(ActionEvent event) {
        try {
            ServiceReservation serviceReservation = new ServiceReservation();
            List<Reservation> reservations = serviceReservation.selectAll();
            RestoContainer.getChildren().clear();
            displayRes(reservations);
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }
    }
    @FXML
    void Ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterReservation.fxml"));
            idAjouter.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }}


    private VBox createRerservationCard(Reservation res) {
        VBox card = new VBox();
        card.getStyleClass().add("reservation-card");
        Label idLabel = new Label("id: " + res.getId());
        Label NomResLabel = new Label("fullName: " + res.getFullName());
        Label EventNomlabel = new Label("eventName: " + res.getEventName());
        Label dateLabel = new Label("date:" + res.getDate());
        Label TimeLabel = new Label("time:" + res.getTime());
        // ImageView imageView = new ImageView(new Image("file:" + product.getImage()));
        // Ajoutez ici d'autres labels pour les autres attributs de restaurant
        card.getChildren().addAll(idLabel, NomResLabel, EventNomlabel, dateLabel, TimeLabel);
        card.setOnMouseClicked(event -> openResDetailsPage(res));
        card.setCursor(Cursor.HAND);
        return card;
    }

    private void openResDetailsPage(Reservation res) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AfficherRestoDetails.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //AfficherDetails controller = fxmlLoader.getController();
            //controller.displayResDetails(res);
            stage.setTitle(" Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Retour(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterReservation.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace(); // Gérer les erreurs de chargement du FXML
        }

    }
    @FXML
    void supprimerRes(ActionEvent event) {
        if (selectedRes != null) {
            try {
                ServiceReservation sr = new ServiceReservation();
                sr.deleteOne(selectedRes);
                // Find the VBox corresponding to the selected reservation and remove it
                for (Node node : RestoContainer.getChildren()) {
                    if (node instanceof VBox) {
                        VBox vBox = (VBox) node;
                        Reservation reservation = (Reservation) vBox.getUserData();
                        if (reservation != null && reservation.getId() == selectedRes.getId()) {
                            RestoContainer.getChildren().remove(vBox);
                            break;
                        }
                    }
                }
                // Afficher un message de confirmation
                afficherMessage("Suppression réussie", "Suppression avec succès.", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                // Afficher un message d'erreur en cas d'échec de la suppression
                afficherMessage("Erreur", "Erreur lors de la suppression : " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            afficherMessage("Aucune sélection", "Veuillez sélectionner une réservation à supprimer.", Alert.AlertType.WARNING);
        }
    }





    private void afficherMessage(String titre, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }




    @FXML
    void initialize() {
        assert RestoContainer != null : "fx:id=\"RestoContainer\" was not injected: check your FXML file 'AfficherRes.fxml'.";
        assert userIdLabel != null : "fx:id=\"userIdLabel\" was not injected: check your FXML file 'AfficherRes.fxml'.";
    }

}
