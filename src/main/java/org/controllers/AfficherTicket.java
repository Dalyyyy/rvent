package org.controllers;
import org.entities.Tickets;
import javafx.scene.Node;

import java.util.*;

import org.services.seviceticket;
import org.controllers.DatabaseHelper;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.entities.Tickets;
import org.services.seviceticket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.entities.Tickets;

public class AfficherTicket {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private GridPane gridfak;

    @FXML
    private Button modifier;

    @FXML
    private Button show;
    @FXML
    private Button trier1;

    @FXML
    private Button trier2;

    @FXML
    void Modifier(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierTicket.fxml"));
            modifier.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }      }

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fakhri ticket.fxml"));
            modifier.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }    }
    @FXML
    void trierplusancien(ActionEvent event) {
        List<Tickets> tickets = DatabaseHelper.getTickets();
        tickets.sort(Comparator.comparing(Tickets::getDate)); // Assuming getDate() returns a LocalDate
        clearGrid();
        populateGrid(tickets);
    }
    private void populateGrid(List<Tickets> tickets) {
        int rowIndex = 0;
        for (Tickets ticket : tickets) {
            gridfak.add(new Label(String.valueOf(ticket.getTotprix())), 0, rowIndex);
            gridfak.add(new Label(String.valueOf(ticket.getNbrchild())), 1, rowIndex);
            gridfak.add(new Label(String.valueOf(ticket.getNbrsen())), 2, rowIndex);
            gridfak.add(new Label(String.valueOf(ticket.getNbradul())), 3, rowIndex);
            gridfak.add(new Label(ticket.getTime().toString()), 4, rowIndex);
            gridfak.add(new Label(ticket.getDate().toString()), 5, rowIndex);

            rowIndex++;
        }
    }
    private void clearGrid() {        gridfak.getChildren().clear();


    }

    @FXML
    void trierplusrécent(ActionEvent event) {
        List<Tickets> tickets = DatabaseHelper.getTickets();

        // Sort the tickets by date in descending order
        tickets.sort(Comparator.comparing(Tickets::getDate).reversed());

        // Clear the grid
        clearGrid();

        // Populate the grid with the sorted tickets
        populateGrid(tickets);
    }

   // @FXML
   //void delete(ActionEvent event) {

    //}



    //
    @FXML
    void Back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fakhri ticket.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void show(ActionEvent event) {
// Clear existing data in the grid
        gridfak.getChildren().clear();

        // Retrieve the tickets from the database
        List<Tickets> tickets = DatabaseHelper.getTickets();

        int rowIndex = 0; // Initialize the row index

        // Add column headers
       // gridfak.add(new Label("ID"), 0, rowIndex);
        gridfak.add(new Label("Total Price"), 0, rowIndex);
        gridfak.add(new Label("Total Price"), 0, rowIndex);
        gridfak.add(new Label("Child Tickets"), 1, rowIndex);
        gridfak.add(new Label("Senior Tickets"), 2, rowIndex);
        gridfak.add(new Label("Adult Tickets"), 3, rowIndex);
        gridfak.add(new Label("Time"), 4, rowIndex);
        gridfak.add(new Label("Date"), 5, rowIndex);

        rowIndex++; // Move to the next row for data

        // Populate the grid with ticket information
        for (Tickets ticket : tickets) {
            //gridfak.add(new Label(String.valueOf(ticket.getId())), 0, rowIndex);
            gridfak.add(new Label(String.valueOf(ticket.getTotprix())), 0, rowIndex);
            gridfak.add(new Label(String.valueOf(ticket.getNbrchild())), 1, rowIndex);
            gridfak.add(new Label(String.valueOf(ticket.getNbrsen())), 2, rowIndex);
            gridfak.add(new Label(String.valueOf(ticket.getNbradul())), 3, rowIndex);
            gridfak.add(new Label(ticket.getTime().toString()), 4, rowIndex);
            gridfak.add(new Label(ticket.getDate().toString()), 5, rowIndex);

            rowIndex++; // Move to the next row
        }
    }



   /* @FXML
    void initialize() {

    }
}*/
    private seviceticket ticketService;

    @FXML
    void delete(ActionEvent event) {
        if (selectedRowIndex != -1) {
            // Get ticket details from the selected row
            Node node = getNodeFromGridPane(selectedRowIndex, 0); // Assuming ID is in column 0
            int ticketId = Integer.parseInt(((Label) node).getText());

            try {
                // Delete ticket from the database
                Tickets ticketToDelete = new Tickets(); // Create a new instance
                ticketToDelete.setId(ticketId); // Set the ticket ID
                ticketService.deleteOne(ticketToDelete);

                // Remove ticket row from the GridPane
                gridfak.getChildren().removeIf(n -> GridPane.getRowIndex(n) == selectedRowIndex);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Ticket deleted successfully.");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete ticket: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a ticket to delete.");
        }
    }

    // Method to get a node from the GridPane by row and column indices
    private Node getNodeFromGridPane(int row, int column) {
        for (Node node : gridfak.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null;
    }

    // Method to show an alert
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        ticketService = new seviceticket();
        populateGrid();
    }

    // Method to populate the GridPane with ticket information
    private void populateGrid() {
        List<Tickets> tickets;
        try {
            tickets = ticketService.selectAll();

            int rowIndex = 0;
            for (Tickets ticket : tickets) {
                gridfak.add(new Label(String.valueOf(ticket.getTotprix())), 0, rowIndex);
                gridfak.add(new Label(String.valueOf(ticket.getNbrchild())), 1, rowIndex);
                gridfak.add(new Label(String.valueOf(ticket.getNbrsen())), 2, rowIndex);
                gridfak.add(new Label(String.valueOf(ticket.getNbradul())), 3, rowIndex);
                gridfak.add(new Label(ticket.getTime().toString()), 4, rowIndex);
                gridfak.add(new Label(ticket.getDate().toString()), 5, rowIndex);

                rowIndex++;
            }

            // Add event handlers to select row on mouse click
            gridfak.getChildren().forEach(node -> {
                node.setOnMouseClicked(e -> {
                    selectedRowIndex = GridPane.getRowIndex(node);
                });
            });
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch tickets: " + e.getMessage());
        }
    }

    private int selectedRowIndex = -1; // Track the selected row index
}











//@FXML
  /*  void Modifier(ActionEvent event) {
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
   /* public void ToModifierListTicket() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierTicket.fxml"));
            Parent root = loader.load();
          //  ModifierTicket ModifierController = loader.getController();
            Scene pageScene = new Scene(root);

            Stage stage = (Stage) myText.getScene().getWindow();
            stage.setScene(pageScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

   /* @FXML
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
    /*private void switchToUpdatePage(Tickets Tick) {


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
       /**/
  //  @FXML
   /* void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fakhri ticket.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }*/

