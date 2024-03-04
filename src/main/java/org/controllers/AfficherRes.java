package org.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.controllers.DatabaseHelper1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.entities.Reservation;
import org.services.ServiceReservation;
import java.util.*;
import org.entities.Reservation;
import javafx.scene.Node;
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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.layout.GridPane;
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
    private GridPane gridfak;
    @FXML
    private Label userIdLabel;

    @FXML
    private Button telecharger;


    @FXML
    private Button idModifier;
    @FXML
    private Button idAjouter;
    @FXML
    private Button idRetour;
    @FXML
    private Button idSupp;
    private Reservation selectedRes;
    private ServiceReservation reservationService;

    @FXML
    public void telecharger(ActionEvent event) {
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Create a PDPageContentStream object for drawing
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

            // Define the font and font size
            PDFont font = PDType1Font.HELVETICA_BOLD;
            float fontSize = 12;
            float leading = 1.5f * fontSize;

            // Starting y coordinate
            float y = page.getMediaBox().getHeight() - 50;

            // Add column headers to the PDF
            contentStream.setFont(font, fontSize);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, y);
            contentStream.showText("ID");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Full Name");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Event Name");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Time");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Date");
            contentStream.endText();

            // Move the y coordinate to the next line
            y -= leading;

            // Populate the PDF with reservation information from the GridPane
            for (Node node : gridfak.getChildren()) {
                if (node instanceof Label) {
                    Label label = (Label) node;
                    contentStream.beginText();
                    contentStream.setFont(font, fontSize);
                    contentStream.newLineAtOffset(50, y);
                    contentStream.showText(label.getText());
                    contentStream.endText();
                    y -= leading;
                }
            }

            // Close the content stream
            contentStream.close();

            // Save the PDF document
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
            File file = fileChooser.showSaveDialog(telecharger.getScene().getWindow());
            if (file != null) {
                document.save(file);
                document.close();
            }

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "PDF file saved successfully.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save PDF file: " + e.getMessage());
        }
    }




    @FXML
    void modifier(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierReservation.fxml"));
            idModifier.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void OnRefresh(ActionEvent event) {
        gridfak.getChildren().clear();

// Retrieve the reservations from the database
        List<Reservation> reservations = DatabaseHelper1.getReservations();

        int rowIndex = 0; // Initialize the row index

// Add column headers
        gridfak.add(new Label("ID"), 0, rowIndex);
        gridfak.add(new Label("Full Name"), 1, rowIndex);
        gridfak.add(new Label("Event Name"), 2, rowIndex);
        gridfak.add(new Label("Time"), 3, rowIndex);
        gridfak.add(new Label("Date"), 4, rowIndex);

        rowIndex++; // Move to the next row for data

// Populate the grid with reservation information
        for (Reservation reservation : reservations) {
            gridfak.add(new Label(String.valueOf(reservation.getId())), 0, rowIndex);
            gridfak.add(new Label(reservation.getFullName()), 1, rowIndex);
            gridfak.add(new Label(reservation.getEventName()), 2, rowIndex);
            gridfak.add(new Label(reservation.getTime().toString()), 3, rowIndex);
            gridfak.add(new Label(reservation.getDate().toString()), 4, rowIndex);

            rowIndex++; // Move to the next row
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
    private void populateGrid(List<Reservation> reservations) {
        int rowIndex = 0;
        for (Reservation reservation : reservations) {
            gridfak.add(new Label(String.valueOf(reservation.getId())), 0, rowIndex);
            gridfak.add(new Label(reservation.getFullName()), 1, rowIndex);
            gridfak.add(new Label(reservation.getEventName()), 2, rowIndex);
            gridfak.add(new Label(reservation.getTime().toString()), 3, rowIndex);
            gridfak.add(new Label(reservation.getDate().toString()), 4, rowIndex);

            rowIndex++;
        }
    }
    private void clearGrid() {        gridfak.getChildren().clear();


    }

    @FXML
    void initialize() {
        reservationService = new ServiceReservation();
        populateGrid();

    }
    private void populateGrid() {
        try {
            List<Reservation> reservations = reservationService.selectAll();

            clearGrid();

            int rowIndex = 0;
            for (Reservation reservation : reservations) {
                gridfak.add(new Label(String.valueOf(reservation.getId())), 0, rowIndex);
                gridfak.add(new Label(reservation.getFullName()), 1, rowIndex);
                gridfak.add(new Label(reservation.getEventName()), 2, rowIndex);
                gridfak.add(new Label(reservation.getTime().toString()), 3, rowIndex);
                gridfak.add(new Label(reservation.getDate().toString()), 4, rowIndex);

                rowIndex++;
            }

            // Add event handlers to select row on mouse click
            gridfak.getChildren().forEach(node -> {
                node.setOnMouseClicked(e -> {
                    selectedRowIndex = GridPane.getRowIndex(node);
                });
            });
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch reservations: " + e.getMessage());
        }
    }
    private int selectedRowIndex = -1; // Track the selected row index





  @FXML
        void supprimerRes(ActionEvent event) {
      if (selectedRowIndex != -1) {
          // Get reservation details from the selected row
          Node node = getNodeFromGridPane(selectedRowIndex, 0); // Assuming ID is in column 0
          int reservationId = Integer.parseInt(((Label) node).getText());

          try {
              // Delete reservation from the database
              Reservation reservationToDelete = new Reservation(); // Create a new instance
              reservationToDelete.setId(reservationId); // Set the reservation ID
              reservationService.deleteOne(reservationToDelete);

              // Remove reservation row from the GridPane
              gridfak.getChildren().removeIf(n -> GridPane.getRowIndex(n) == selectedRowIndex);

              showAlert(Alert.AlertType.INFORMATION, "Success", "Reservation deleted successfully.");
          } catch (SQLException e) {
              showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete reservation: " + e.getMessage());
          }
      } else {
          showAlert(Alert.AlertType.WARNING, "Warning", "Please select a reservation to delete.");
      }
  }



    private Node getNodeFromGridPane(int row, int column) {
        for (Node node : gridfak.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null;
    }
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    }
    /*private void populateGrid() {
        List<Reservation> reservations;
        try {
            reservations = reservationService.selectAll();

            int rowIndex = 0;
            for (Reservation reservation : reservations) {
                gridfak.add(new Label(String.valueOf(reservation.getId())), 0, rowIndex);
                gridfak.add(new Label(String.valueOf(reservation.getFullName())), 1, rowIndex);
                gridfak.add(new Label(String.valueOf(reservation.getEventName())), 2, rowIndex);
                gridfak.add(new Label(reservation.getTime().toString()), 3, rowIndex);
                gridfak.add(new Label(reservation.getDate().toString()), 4, rowIndex);

                rowIndex++;
            }

            // Add event handlers to select row on mouse click
            gridfak.getChildren().forEach(node -> {
                node.setOnMouseClicked(e -> {
                    selectedRowIndex = GridPane.getRowIndex(node);
                });
            });
        } catch (SQLException e) {
        }
    }*/










   /* void initialize() {
        assert RestoContainer != null : "fx:id=\"RestoContainer\" was not injected: check your FXML file 'AfficherRes.fxml'.";
        assert userIdLabel != null : "fx:id=\"userIdLabel\" was not injected: check your FXML file 'AfficherRes.fxml'.";
    }*/


