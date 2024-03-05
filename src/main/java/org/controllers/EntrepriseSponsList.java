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


public class EntrepriseSponsList implements Initializable {


    @FXML
    TableView<Sponsoring> SponTableView;

    @FXML
    private TableColumn<?, ?> TypeColumn;

    @FXML
    private TableColumn<Sponsoring, Void> actionsColumn;

    @FXML
    private TableColumn<?, ?> adresseColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> domaineColumn;

    @FXML
    private TableColumn<?, ?> etabColumn;

    @FXML
    private TableColumn<?, ?> mailColumn;

    @FXML
    private TableColumn<?, ?> nomColumn;

    @FXML
    private TableColumn<?, ?> numColumn;

    @FXML
    private TableColumn<?, ?> prenomColumn;


    SponService sponService =new SponService();
    ObservableList<Sponsoring> sponsorings;
    {
        try {
            sponsorings = sponService.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static EntrepriseSponsList instance =new EntrepriseSponsList();
    public static  EntrepriseSponsList getInstance() {
        return instance;
    }

    private int SelectedSub;

    public Callback<TableColumn<Sponsoring, Void>, TableCell<Sponsoring, Void>> createButtonCellFactory() {
        return new Callback<TableColumn<Sponsoring, Void>, TableCell<Sponsoring, Void>>() {
            @Override
            public TableCell<Sponsoring, Void> call(final TableColumn<Sponsoring, Void> param) {
                return new TableCell<Sponsoring, Void>() {

                    final Button deleteButton = createButton("accepted");
                    final Button editButton = createButton("Refused");
                    final org.services.SponService SponService = new SponService();

                    {
                        // Set actions for the buttons
                        deleteButton.setOnAction(event -> {
                            Sponsoring sponsoring = getTableView().getItems().get(getIndex());
                            System.out.println("accepted: " + sponsoring.getId());
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
                            System.out.println("Refused: " + sponsoring.getId());
                            //gotoedit();
                            instance.SelectedSub=sponsoring.getId();
                            //fillSubinputs(sponsoring);


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
}