package org.controllers;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.fxml.FXML;
import org.controlsfx.control.Notifications;
import org.entities.Event;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.services.EventService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;



public class eventController implements Initializable {
    @FXML
    private VBox eventAffichagee;

    @FXML
    private Pane AddeventPagee;

    @FXML
    private TextField DescField;
    @FXML
    private TextField searchField;

    @FXML
    private Button search;

    @FXML
    private Pane ListContainer;

    @FXML
    private Pane UpperSection;

    @FXML
    private TableView<Event> accountTableView;

    @FXML
    private TableColumn<Event, Void> actionsColumn;

    @FXML
    private TableColumn<Event, String> eventnamecolomn;

    @FXML
    private TableColumn<Event, String> descriptioncolomn;

    @FXML
    private TableColumn<Event, String> entreprisenamecolomn;

    @FXML
    private TableColumn<Event, Integer> maxnbrcolomn;

    @FXML
    private TableColumn<Event, Boolean> status;

    @FXML
    private TableColumn<Event, Boolean> Full;

    @FXML
    private Button add_event, edit_event;

    @FXML
    private TableColumn<Event, Integer> ageColumn;

    @FXML
    private TextField entrepriseNameField;

    @FXML
    private TextField eventNameField;

    @FXML
    private CheckBox isfull_checkbox;

    @FXML
    private TextField maxnbrField;

    @FXML
    private CheckBox statusCheckbox;

    @FXML
    private Label title;

    private static eventController instance = new eventController();

    public static eventController getInstance() {
        return instance;
    }

    private int Selectedevent;
    private void updateTableView(ObservableList<Event> filteredEvents) {

        accountTableView.setItems(filteredEvents);
        //accountTableView.getItems().setAll(events);
    }

    EventService eventService = new EventService();

    ObservableList<Event> events;

    {
        try {
            events = eventService.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void fillSubinputs(Event event) {
        eventNameField.setText(String.valueOf(event.getEventName()));
        DescField.setText(String.valueOf(event.getDescription()));
        //nomField.setText(sponsoring.getNom());
        entrepriseNameField.setText(String.valueOf(event.getEnterpriseName()));
        //myChoiceBox.setText(String.valueOf(sponsoring.getTetab()));
        //autresField.setText(String.valueOf(sponsoring.getTetab()));
        //adresseField.setText(String.valueOf(sponsoring.getAdresse()));
        //mailField.setText(String.valueOf(sponsoring.getEmail()));
        maxnbrField.setText(String.valueOf(event.getMaxParticipantNbr()));
        statusCheckbox.setText(String.valueOf(event.isStatus()));
        isfull_checkbox.setText(String.valueOf(event.isFull()));
        // prixField.setText(String.valueOf(abonnement.getPrix()));
    }

    public void showEditeventbtn() {

        title.setText("edit new event");

        add_event.setVisible(false);
        add_event.setManaged(false);
        edit_event.setVisible(true);
        edit_event.setManaged(true);
        eventAffichagee.setVisible(false);
        eventAffichagee.setManaged(false);

        // show addeeventPage interface
        AddeventPagee.setVisible(true);
        AddeventPagee.setManaged(true);


    }


    public void reload() {
        try {
            events.clear(); // Efface les données existantes
            events.addAll(eventService.afficher()); // Ajoute les nouvelles données
            accountTableView.setItems(events); // Met à jour le TableView
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erreur", "Une erreur est survenue lors du rechargement des données.");
        }
    }
    @FXML
    public void handleEditevent() {
        String eventname = eventNameField.getText();
        String description = DescField.getText();
        String nom_entreprise = entrepriseNameField.getText();

        int nbrmax = Integer.parseInt(maxnbrField.getText());
        boolean status = statusCheckbox.isSelected(); // Retrieve the selected state
        boolean full = isfull_checkbox.isSelected(); // Retrieve the selected state

        Event event = new Event(instance.Selectedevent, full, eventname, description, status, nom_entreprise, nbrmax);
        EventService eventService = new EventService();

        try {
            eventService.updateEvent(event);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        reload();
    }



    // Appelez cette méthode lorsque l'utilisateur appuie sur Entrée dans le champ de recherche


    private void searchEvent(String keyword) {
        try {
            // Fetch the list of events from the database
            EventService eventService = new EventService();
            List<Event> eventList = eventService.afficher();

            // Clear the existing content of the TableView
            accountTableView.getItems().clear();

            // Filter events based on the search keyword
            List<Event> filteredEvents = eventList.stream()
                    .filter(event -> event.getEventName().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());

            // Update the TableView with filtered events
            updateTableView(FXCollections.observableArrayList(filteredEvents));

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }


    @FXML
    public void handleAddAdmin() {
        // Retrieve input fields and checkbox states
        String eventname = eventNameField.getText();
        String description = DescField.getText();
        String nom_entreprise = entrepriseNameField.getText();
        int nbrmax;

        try {
            // Validate input for event name and description
            if (!eventname.matches("[a-zA-Z]+") || !description.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Event name and description must contain only letters.");
            }
            // Validate input for enterprise name
            if (!nom_entreprise.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Enterprise name must contain only letters.");
            }

        try {
            nbrmax = Integer.parseInt(maxnbrField.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Invalid input", "Please enter a valid integer for max number.");
            return;  // Exit the method if input is invalid
        }

        boolean status = statusCheckbox.isSelected();
        boolean full = isfull_checkbox.isSelected();





            // Create Event object
            Event event = new Event(-1, full, eventname, description, status, nom_entreprise, nbrmax);
            EventService eventService = new EventService();

            // Add event
            eventService.addEvent(event);
            if (nbrmax < 20) {
                System.out.println("Le nombre maximal doit être supérieur à 20.");
                Notifications.create()
                        .title("Nombre insuffisant")
                        .text("évennement risque d'être annulé.")
                        .showWarning();
            }
            // Show success message
            showAlert(AlertType.ERROR, "Success", "Event added successfully!");
            //reload();
        } catch (IllegalArgumentException e) {
            showAlert(AlertType.ERROR, "Invalid input", e.getMessage());
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Error", "An error occurred while adding the event: " + e.getMessage());
        }
        reload();
    }

    // Helper method to show an alerts
    private void showAlert(AlertType error, String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



    @FXML
    public void addEventInterfacee() {
        title.setText("add new event");


        edit_event.setVisible(false);
        edit_event.setManaged(false);
        add_event.setVisible(true);
        add_event.setManaged(true);
        // hide interfaces
        eventAffichagee.setVisible(false);
        eventAffichagee.setManaged(false);

        // show addeeventPage interface
        AddeventPagee.setVisible(true);
        AddeventPagee.setManaged(true);
       /* reloadPage();*/
    }

    @FXML
    public void GoToDisplayInterfacee() {
        // hide interfaces
        eventAffichagee.setVisible(true);
        eventAffichagee.setManaged(true);

        // show addeeventPage interface
        AddeventPagee.setVisible(false);
        AddeventPagee.setManaged(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventAffichagee.setVisible(true);
        eventAffichagee.setManaged(true);
        AddeventPagee.setVisible(false);
        AddeventPagee.setManaged(false);


        eventnamecolomn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        descriptioncolomn.setCellValueFactory(new PropertyValueFactory<>("description"));
        entreprisenamecolomn.setCellValueFactory(new PropertyValueFactory<>("enterpriseName"));
        maxnbrcolomn.setCellValueFactory(new PropertyValueFactory<>("maxParticipantNbr"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Full.setCellValueFactory(new PropertyValueFactory<>("full"));
        actionsColumn.setCellFactory(createButtonCellFactory());
        accountTableView.setItems(events);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                reload(); // Reload content when search field is cleared
            } else {
                searchEvent(newValue); // Filter events based on the search keyword
            }
        });
        reload();



    }


    public Callback<TableColumn<Event, Void>, TableCell<Event, Void>> createButtonCellFactory() {
        return new Callback<TableColumn<Event, Void>, TableCell<Event, Void>>() {
            @Override
            public TableCell<Event, Void> call(final TableColumn<Event, Void> param) {
                return new TableCell<Event, Void>() {

                    final Button deleteButton = createButton("Delete");
                    final Button editButton = createButton("Edit");

                    {
                        // Set actions for the buttons
                        deleteButton.setOnAction(event -> {
                            Event event1 = getTableView().getItems().get(getIndex());
                            System.out.println("Delete: " + event1.getId());
                            try {
                                eventService.deleteEvent(event1.getId());
                                 //reload_page();
                                reload();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        editButton.setOnAction(event -> {
                            Event event1 = getTableView().getItems().get(getIndex());
                            System.out.println("Edit: " + event1.getId());
                            showEditeventbtn();
                           // reload();
                            instance.Selectedevent = event1.getId();
                            fillSubinputs(event1);
                            reload();
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

}
