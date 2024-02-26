package main.projet;

import javafx.fxml.FXML;
import org.entities.Event;

import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;


public class eventController implements Initializable {
    @FXML
    private VBox eventAffichagee;

    @FXML
    private Pane AddeventPagee;

    @FXML
    private TextField DescField;

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
    EventService eventService = new EventService();

    ObservableList<Event> events;

    {
        try {
            events = eventService.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    }

    @FXML
    public void handleAddAdmin() {



        String eventname = eventNameField.getText();
        String description = DescField.getText();
        String nom_entreprise = entrepriseNameField.getText();
        int nbrmax = Integer.parseInt(maxnbrField.getText());
        boolean status = statusCheckbox.isSelected(); // Retrieve the selected state
        boolean full = isfull_checkbox.isSelected(); // Retrieve the selected state

        Event event = new Event(-1, full, eventname, description, status, nom_entreprise, nbrmax);
        EventService eventService = new EventService();

        try {
            eventService.addEvent(event);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
    }

    /*reload_page(){



    }*/

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
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        editButton.setOnAction(event -> {
                            Event event1 = getTableView().getItems().get(getIndex());
                            System.out.println("Edit: " + event1.getId());
                            showEditeventbtn();
                            instance.Selectedevent = event1.getId();
                            // fillSubinputs(abonnement);
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
