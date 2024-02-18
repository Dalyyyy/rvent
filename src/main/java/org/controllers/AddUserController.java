package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.entities.User;
import org.services.UserService;

import javafx.event.ActionEvent;

import java.sql.SQLException;

public class AddUserController {
    private final UserService userService = new UserService();
    @FXML
    private TextField name;
    @FXML
    private TextField familyName;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private DatePicker birthDate;

    @FXML
    void addOrganizer(ActionEvent event) {
        try {
            userService.addOrganizer(new User(name.getText(), familyName.getText(), email.getText(), password.getText(), birthDate.getValue()));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


}


