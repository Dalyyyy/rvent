package org.controllers;

import javafx.scene.text.Text;
import org.entities.Sponsoring;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DevenirSponsorForm implements Initializable {

    @FXML
    TableView<Sponsoring> SponTableView;

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

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }



                            // Add your delete action here
                        });

                        editButton.setOnAction(event -> {
                            Sponsoring sponsoring = getTableView().getItems().get(getIndex());
                            System.out.println("Edit: " + sponsoring.getId());
                            gotoedit();
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
        reload_page();


    }

    @FXML
    public void handleAdd(){

        String nom = nomfield.getText();
        String prenom =  prenomfield.getText();
        String nom_etab =  etablifield.getText();
        String adresse =  addressField.getText();
        int numero =  Integer.parseInt(numeroField.getText());
        String email = emailField.getText();
        String description =  descField.getText();
        String domaine = "azerty";


        Sponsoring sponsoring = new Sponsoring(-1, nom,nom_etab, prenom,domaine,adresse,email, Sponsoring.Tetab.entreprise,numero,description);
        SponService sponService =new SponService();

        try {
            sponService.ajouter(sponsoring);
            System.out.println(nom);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        //  initAdminInputs();

        GOTODISPLAY();
        reload_page();

    }

    private void reload_page() {
        SponService sponService =new SponService();
        ObservableList<Sponsoring> sponsorings;
        {
            try {
                sponsorings = sponService.afficher();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        display.setVisible(true);
        display.setManaged(true);

    }


    @FXML
    public void handleEdit(){
        String nom = nomfield.getText();
        String prenom =  prenomfield.getText();
        String nom_etab =  etablifield.getText();
        String adresse =  addressField.getText();
        int numero =  Integer.parseInt(numeroField.getText());
        String email = emailField.getText();
        String description =  descField.getText();
        String domaine = "azerty";

        Sponsoring sponsoring = new Sponsoring(instance.SelectedSub, nom,nom_etab, prenom,domaine,adresse,email, Sponsoring.Tetab.entreprise,numero,description);
        SponService sponService =new SponService();

        try {
            sponService.modifier(sponsoring);
            System.out.println(prenom);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(nom);
        }
        GOTODISPLAY();


        //  initAdminInputs();
        // reload_page();
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

}



