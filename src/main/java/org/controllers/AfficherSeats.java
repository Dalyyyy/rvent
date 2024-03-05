package org.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
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
    @FXML
    private Button ajouterr;
    @FXML
    private Button modifier;
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
    private Button email ;

    @FXML
    private ListView<seeats> listSeats;

    @FXML
    void email(ActionEvent event) { // Prompt the user to enter their email
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Email Address");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter your email address:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(emailAddress -> {
            try {
                // Convert listSeats to PDF
                File pdfFile = convertListToPDF(listSeats.getItems());

                // Send email with PDF attachment
                sendEmailWithAttachment(emailAddress, pdfFile);

                // Show success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Email Sent");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Email sent successfully!");
                successAlert.showAndWait();
            } catch (IOException | MessagingException e) {
                e.printStackTrace();
                // Show error message
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Email Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to send email. Please try again.");
                errorAlert.showAndWait();
            }
        });
    }

    private File convertListToPDF(List<seeats> list) throws IOException {
        // Create a PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Write list items to PDF
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, 700);
            for (seeats item : list) {
                contentStream.showText(item.toString()); // Customize this according to your seeats class
                contentStream.newLine();
            }
            contentStream.endText();
        }

        // Save PDF to a file
        File pdfFile = new File("listSeats.pdf");
        document.save(pdfFile);
        document.close();

        return pdfFile;
    }

    private void sendEmailWithAttachment(String to, File attachment) throws MessagingException, IOException {
        // Email configuration
        String from = "fakrihammemi@gmail.com";
        String host = "smtp.gmail.com";
        String username = "fakrihammemi@gmail.com";
        String password = "20928338A";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create a Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("List of Seats");

        // Create a multipart message
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Please find attached the list of seats.");

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(attachment);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        // Send the message
        Transport.send(message);
    }

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
    void ajouterr(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fakhri ticket.fxml"));
            ajouterr.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void modifier(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierTicket.fxml"));
            modifier.getScene().setRoot(root);
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

            // Configure the TableView to display seat information without the ID
            listSeats.setCellFactory(param -> new ListCell<seeats>() {
                @Override
                protected void updateItem(seeats seat, boolean empty) {
                    super.updateItem(seat, empty);

                    if (empty || seat == null) {
                        // Si la cellule est vide ou l'objet Commande est null, ne rien afficher
                        setText(null);
                    } else {
                        // Affiche seulement les informations des sièges sans l'ID
                        setText(seat.getSeat());
                    }
                }
            });

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
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

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
