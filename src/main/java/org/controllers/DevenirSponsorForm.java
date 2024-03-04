package org.controllers;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.entities.Example;
import org.entities.Sponsoring;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.services.SponService;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DevenirSponsorForm implements Initializable {

    @FXML
    TableView<Sponsoring> SponTableView;

    @FXML
    private AnchorPane formPane;
    @FXML
    TableColumn<?, ?> nomColumn;

    @FXML
    TableColumn<?, ?> prenomColumn;

    @FXML
    TableColumn<?, ?> etabColumn;

    @FXML
    TableColumn<?, ?> TypeColumn;

    @FXML
    TableColumn<?, ?> domaineColumn;

    @FXML
    TableColumn<?, ?> adresseColumn;

    @FXML
    TableColumn<?, ?> mailColumn;

    @FXML
    TableColumn<?, ?> numColumn;

    @FXML
    TableColumn<?, ?> descriptionColumn;

    @FXML
    TableColumn<Sponsoring,Void> actionsColumn;


    SponService sponService =new SponService();
    ObservableList<Sponsoring> sponsorings;
    {
        try {
            sponsorings = sponService.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private ChoiceBox<String> myChoiceBox;
    private String[] Sélectionner ={"Entreprise","Startup","Organisme","Institution financiére"};

    @FXML
    public VBox display,addsponsor;


    @FXML
    private Text titlefield;

    @FXML
    private Button add_button;

    @FXML
    private Button add_button2;



    @FXML
    private TextField addressField;

    @FXML
    private Button cancel_button;

    @FXML
    private CheckBox consultcheck;

    @FXML
    private CheckBox ITcheck;

    @FXML
    private TextField descField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField etablifield;

    @FXML
    private CheckBox financecheck;

    @FXML
    private CheckBox industricheck;

    @FXML
    private CheckBox marketingcheck;

    @FXML
    private CheckBox mecacheck;



    @FXML
    private TextField nomfield;

    @FXML
    private TextField numeroField;

    @FXML
    private TextField otherfield;

    @FXML
    private TextField prenomfield;

    @FXML
    private TableColumn<?, ?> etatColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(Sélectionner);

        add_button2.setVisible(false);
        add_button2.setManaged(false);
        display.setVisible(false);
        display.setManaged(false);
        addsponsor.setVisible(true);
        addsponsor.setManaged(true);

        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        etabColumn.setCellValueFactory(new PropertyValueFactory<>("nom_etab"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("tetab"));
        domaineColumn.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        numColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        actionsColumn.setCellFactory(createButtonCellFactory());
        SponTableView.setItems(sponsorings);
        System.out.println(sponsorings);
    }
    void fillSubinputs(Sponsoring sponsoring){
        nomfield.setText(String.valueOf(sponsoring.getNom()));
        prenomfield.setText(String.valueOf(sponsoring.getPrenom()));
        //nomField.setText(sponsoring.getNom());
        etablifield.setText(String.valueOf(sponsoring.getNom_etab()));
        //myChoiceBox.setText(String.valueOf(sponsoring.getTetab()));
        //autresField.setText(String.valueOf(sponsoring.getTetab()));
        //adresseField.setText(String.valueOf(sponsoring.getAdresse()));
        //mailField.setText(String.valueOf(sponsoring.getEmail()));
        numeroField.setText(String.valueOf(sponsoring.getNumero()));
        otherfield.setText(String.valueOf(sponsoring.getDescription()));
        // prixField.setText(String.valueOf(abonnement.getPrix()));


    }
    private static DevenirSponsorForm instance =new DevenirSponsorForm();


    public static DevenirSponsorForm getInstance() {
        return instance;
    }
    private int SelectedSub;

    public Callback<TableColumn<Sponsoring, Void>, TableCell<Sponsoring, Void>> createButtonCellFactory() {
        return new Callback<TableColumn<Sponsoring, Void>, TableCell<Sponsoring, Void>>() {
            @Override
            public TableCell<Sponsoring, Void> call(final TableColumn<Sponsoring, Void> param) {
                return new TableCell<Sponsoring, Void>() {

                    final Button deleteButton = createButton("Delete");
                    final Button editButton = createButton("Edit");
                    final SponService SponService = new SponService();

                    {
                        // Set actions for the buttons
                        deleteButton.setOnAction(event -> {
                            Sponsoring sponsoring = getTableView().getItems().get(getIndex());
                            System.out.println("Delete: " + sponsoring.getId());
                            //SponService SponService = new SponService();
                            try {
                                SponService.supprimer(sponsoring.getId());
                                // reload_page();
                                reload();

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }



                            // Add your delete action here
                        });

                        editButton.setOnAction(event -> {
                            Sponsoring sponsoring = getTableView().getItems().get(getIndex());
                            System.out.println("Edit: " + sponsoring.getId());
                            gotoedit();
                            reload();
                            instance.SelectedSub=sponsoring.getId();
                            fillSubinputs(sponsoring);


                            // Add your edit action here
                        });



                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Set buttons as graphic in the cell
                            setGraphic(createButtonPane());
                        }
                    }

                    private Button createButton(String buttonText) {
                        Button button = new Button(buttonText);
                        button.setMinSize(60, 20);
                        return button;
                    }

                    private HBox createButtonPane() {
                        HBox buttonPane = new HBox(5); // spacing between buttons
                        buttonPane.getChildren().addAll(deleteButton, editButton);
                        return buttonPane;
                    }
                };
            }
        };
    }



    @FXML
    public void GOTOADD(){

        display.setVisible(false);
        display.setManaged(false);
        addsponsor.setVisible(true);
        addsponsor.setManaged(true);


    }

    @FXML
    public void GOTODISPLAY(){

        display.setVisible(true);
        display.setManaged(true);
        addsponsor.setVisible(false);
        addsponsor.setManaged(false);



    }

    @FXML
    public void handleAdd() {
        // Récupérer les valeurs des champs
        String nom = nomfield.getText();
        String prenom = prenomfield.getText();
        String nom_etab = etablifield.getText();
        String adresse = addressField.getText();
        int numero = 0;
        try {
            numero = Integer.parseInt(numeroField.getText());
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Veuillez entrer un numéro valide.");
            return; // Arrêter le traitement si le numéro n'est pas valide
        }
        String email = emailField.getText();
        String description = descField.getText();
        String domaine = "azerty";

        // Vérifier si les champs sont vides
        if (nom.isEmpty() || prenom.isEmpty() || nom_etab.isEmpty() || adresse.isEmpty() || email.isEmpty() || description.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return; // Arrêter le traitement si un champ est vide
        }

        // Vérifier si l'email est valide en utilisant une expression régulière
        if (!isValidEmail(email)) {
            showAlert("Erreur", "Veuillez entrer une adresse e-mail valide.");
            return; // Arrêter le traitement si l'email n'est pas valide
        }

        // Vérifier si le numéro est un entier positif
        if (numero <= 0) {
            showAlert("Erreur", "Veuillez entrer un numéro valide.");
            return; // Arrêter le traitement si le numéro n'est pas valide
        }

        // Créer un nouvel objet Sponsoring
        Sponsoring sponsoring = new Sponsoring(-1, nom, nom_etab, prenom, domaine, adresse, email, Sponsoring.Tetab.entreprise, numero, description);
        SponService sponService = new SponService();

        try {
            // Ajouter le sponsoring à la base de données
            sponService.ajouter(sponsoring);
//            Example example = new Example();
//            example.Whatsapp();
            System.out.println(nom);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Aller à l'affichage des sponsorings
        GOTODISPLAY();
        reload();
    }

    // Méthode pour vérifier si une adresse e-mail est valide en utilisant une expression régulière
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    @FXML
    public void handleEdit(){
        String nom = nomfield.getText();
        String prenom = prenomfield.getText();
        String nom_etab = etablifield.getText();
        String adresse = addressField.getText();
        int numero = 0;
        try {
            numero = Integer.parseInt(numeroField.getText());
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Veuillez entrer un numéro valide.");
            return; // Arrêter le traitement si le numéro n'est pas valide
        }
        String email = emailField.getText();
        String description = descField.getText();
        String domaine = "azerty";

        // Vérifier si les champs sont vides
        if (nom.isEmpty() || prenom.isEmpty() || nom_etab.isEmpty() || adresse.isEmpty() || email.isEmpty() || description.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return; // Arrêter le traitement si un champ est vide
        }

        // Vérifier si l'email est valide en utilisant une expression régulière
        if (!isValidEmail(email)) {
            showAlert("Erreur", "Veuillez entrer une adresse e-mail valide.");
            return; // Arrêter le traitement si l'email n'est pas valide
        }

        // Vérifier si le numéro est un entier positif
        if (numero <= 0) {
            showAlert("Erreur", "Veuillez entrer un numéro valide.");
            return; // Arrêter le traitement si le numéro n'est pas valide
        }

        // Créer un nouvel objet Sponsoring
        Sponsoring sponsoring = new Sponsoring(instance.SelectedSub, nom, nom_etab, prenom, domaine, adresse, email, Sponsoring.Tetab.entreprise, numero, description);
        SponService sponService = new SponService();

        try {
            // Modifier le sponsoring dans la base de données
            sponService.modifier(sponsoring);
            System.out.println(prenom);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(nom);
        }

        // Aller à l'affichage des sponsorings
        GOTODISPLAY();
        reload();


        //  initAdminInputs();

    }
    @FXML
    void exportToExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter en Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier Excel", "*.xlsx"));
        File file = fileChooser.showSaveDialog(SponTableView.getScene().getWindow());

        if (file != null) {
            Workbook workbook = null;

            try {
                // Vérifie si le fichier existe
                if (file.exists()) {
                    FileInputStream fis = new FileInputStream(file);
                    workbook = new XSSFWorkbook(fis);
                } else {
                    workbook = new XSSFWorkbook();
                }

                Sheet sheet = workbook.createSheet("Données"); // Crée une nouvelle feuille de calcul

                // Ajouter les en-têtes de colonne
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Nom");
                headerRow.createCell(1).setCellValue("Prénom");
                headerRow.createCell(2).setCellValue("Nom Établissement");
                headerRow.createCell(3).setCellValue("Type Établissement");
                headerRow.createCell(4).setCellValue("Domaine d'application");
                headerRow.createCell(5).setCellValue("Adresse");
                headerRow.createCell(6).setCellValue("Email");
                headerRow.createCell(7).setCellValue("Numero de telephone");
                headerRow.createCell(8).setCellValue("Description");
                // Ajouter d'autres en-têtes de colonne comme ça

                // Ajouter les données à partir de TableView dans le fichier Excel
                ObservableList<Sponsoring> items = SponTableView.getItems();
                for (int i = 0; i < items.size(); i++) {
                    Sponsoring sponsoring = items.get(i);
                    Row row = sheet.createRow(i + 1); // Commence à partir de la deuxième ligne (après l'en-tête)
                    row.createCell(0).setCellValue(sponsoring.getNom());
                    row.createCell(1).setCellValue(sponsoring.getPrenom());
                    row.createCell(2).setCellValue(sponsoring.getNom_etab());
                    row.createCell(3).setCellValue(String.valueOf(sponsoring.getTetab()));
                    row.createCell(4).setCellValue(sponsoring.getDomaine());
                    row.createCell(5).setCellValue(sponsoring.getAdresse());
                    row.createCell(6).setCellValue(sponsoring.getEmail());
                    row.createCell(7).setCellValue(sponsoring.getNumero());
                    row.createCell(8).setCellValue(sponsoring.getDescription());


                    // Ajouter d'autres cellules pour les autres colonnes comme ça
                }

                // Écrire dans le fichier
                try (FileOutputStream fileOut = new FileOutputStream(file)) {
                    workbook.write(fileOut);
                    showAlert(AlertType.INFORMATION, "Succès", "Les données ont été exportées avec succès.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Erreur", "Une erreur est survenue lors de l'exportation des données.");
            } finally {
                try {
                    if (workbook != null) {
                        workbook.close(); // Ferme le classeur Excel
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void reload() {
        try {
            sponsorings.clear(); // Efface les données existantes
            sponsorings.addAll(sponService.afficher()); // Ajoute les nouvelles données
            SponTableView.setItems(sponsorings); // Met à jour le TableView
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erreur", "Une erreur est survenue lors du rechargement des données.");
        }
    }

    @FXML
    public void gotoedit() {

        display.setVisible(false);
        display.setManaged(false);
        addsponsor.setVisible(true);
        addsponsor.setManaged(true);
        add_button.setManaged(false);
        add_button.setVisible(false);
        add_button2.setVisible(true);
        add_button2.setManaged(true);



    }


    @FXML
    public void gotoadd() {

        add_button.setManaged(true);
        add_button.setVisible(true);
        add_button2.setVisible(false);
        add_button2.setManaged(false);



    }

    public AnchorPane getFormPane() {
        return formPane;
    }
}