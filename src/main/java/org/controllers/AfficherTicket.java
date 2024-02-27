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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.entities.Tickets;
import org.services.seviceticket;

public class AfficherTicket {
    private final   seviceticket  st = new seviceticket();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private ListView<Tickets> listTicket;

    @FXML
    private Button modifier;

    @FXML
    void Modifier(ActionEvent event) {
        Tickets selectedTicket = listTicket.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            // L'objet Commande est sélectionné, vous pouvez maintenant l'utiliser comme vous le souhaitez
            switchToUpdatePage(selectedTicket);
        } else {
            // Aucune Commande sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une commande à modifier.");
            alert.showAndWait();
            ToModifierListTicket(); ;
        }
    }
    public void ToModifierListTicket() {
       /* try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierTicket.fxml"));
            Parent root = loader.load();
          //  ModifierTicket ModifierController = loader.getController();
            Scene pageScene = new Scene(root);

            Stage stage = (Stage) myText.getScene().getWindow();
            stage.setScene(pageScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    void delete(ActionEvent event) {
        Tickets TicketASup = listTicket.getSelectionModel().getSelectedItem();

        if (TicketASup != null) {
            // Supprimer la commande de la base de données et de la liste affichée
            try {
                st.deleteOne(TicketASup);
                listTicket.getItems().remove(TicketASup);

                // Afficher un message de confirmation
                afficherMessage("Suppression réussie", "La commande a été supprimé avec succès.", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                // Afficher un message d'erreur en cas d'échec de la suppression
                afficherMessage("Erreur", "Erreur lors de la suppression de la commande : " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            // Si aucun hôtel n'est sélectionné, afficher un message d'avertissement
            afficherMessage("Aucune sélection", "Veuillez sélectionner une commande à supprimer.", Alert.AlertType.WARNING);
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
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'AfficherTicket.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'AfficherTicket.fxml'.";
        assert listTicket != null : "fx:id=\"listTicket\" was not injected: check your FXML file 'AfficherTicket.fxml'.";
        assert modifier != null : "fx:id=\"modifier\" was not injected: check your FXML file 'AfficherTicket.fxml'.";
        List<Tickets> tickets;
        try {
            // Récupère la liste des hôtels depuis le service
            tickets = st.selectAll();

            // Crée une cellule personnalisée pour la ListView
            listTicket.setCellFactory(param -> new ListCell<Tickets>() {
                @Override
                protected void updateItem(Tickets tick, boolean empty) {
                    super.updateItem(tick, empty);

                    if (empty || tick == null) {
                        // Si la cellule est vide ou l'objet Commande est null, ne rien afficher
                        setText(null);
                    } else {
                        // Affiche les valeurs des attributs de la commande
                        setText(tick.getId() + " - " +
                                tick.getTotprix() + " - " +
                                tick.getNbrchild() + " - " +
                                tick.getNbrsen() + " - " +
                                tick.getNbradul() + " - " +
                                tick.getDate() + " - " +
                                tick.getTime());
                    }
                }
            });
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Ajoute les Commandes à la ListView
        listTicket.getItems().addAll(tickets);

        // Ajoute un gestionnaire d'événements pour gérer les clics sur les éléments de la liste
        listTicket.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Tickets selectedItem = listTicket.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    switchToUpdatePage(selectedItem);
                }
            }
        });
    }

    private void afficherAlerte(Alert.AlertType type, String titre, String contenu) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
    private void switchToUpdatePage(Tickets Tick) {
/*

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCommandeFXML.fxml"));
            Parent root = loader.load();

            ModifierTicket modifierArticleController = loader.getController();
            modifierArticleController.setTicket(Tick);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            afficherAlerte(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement de la page de modification : " + e.getMessage());
        */}
    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fakhri ticket.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
