package org.controllers;


        import java.io.IOException;
        import java.net.URL;
        import java.sql.SQLException;
        import java.util.List;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.ListCell;
        import javafx.scene.control.ListView;
        import org.entities.Tickets;
        import org.entities.seeats;
        import org.services.serviceseat;
        import org.services.seviceticket;

public class AfficherSeats {
    private final serviceseat ss = new serviceseat();
    @FXML
    private ResourceBundle resources;
    @FXML
    private Button delete;
    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private ListView<seeats> listSeats;

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fakhribooking.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'AfficherSeats.fxml'.";
        assert listSeats != null : "fx:id=\"listSeats\" was not injected: check your FXML file 'AfficherSeats.fxml'.";
        List<seeats> seat;
        try {
            // Récupère la liste des hôtels depuis le service
            seat = ss.selectAll();

            // Crée une cellule personnalisée pour la ListView
            listSeats.setCellFactory(param -> new ListCell<seeats>() {
                @Override
                protected void updateItem(seeats seat, boolean empty) {
                    super.updateItem(seat, empty);

                    if (empty || seat == null) {
                        // Si la cellule est vide ou l'objet Commande est null, ne rien afficher
                        setText(null);
                    } else {
                        // Affiche les valeurs des attributs de la commande
                        setText(seat.getId() + " - " +
                                seat.getSeat());
                    }
                }
            });
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Ajoute les Commandes à la ListView
        listSeats.getItems().addAll(seat);

        // Ajoute un gestionnaire d'événements pour gérer les clics sur les éléments de la liste
        listSeats.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                seeats selectedItem = listSeats.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    switchToUpdatePage(selectedItem);
                }
            }
        });
    }
    @FXML
    void delete(ActionEvent event) {
        seeats selectedSeat = listSeats.getSelectionModel().getSelectedItem();
        if (selectedSeat != null) {
            try {
                // Delete the selected seat from the database
                ss.deleteOne(selectedSeat);
                // Remove the selected seat from the ListView
                listSeats.getItems().remove(selectedSeat);
            } catch (SQLException e) {
                // Handle SQL exception
                e.printStackTrace();
            }
        } else {
            // No seat selected, show a warning message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a seat to delete.");
            alert.showAndWait();
        }
    }


    private void switchToUpdatePage(seeats seat) {}

}
