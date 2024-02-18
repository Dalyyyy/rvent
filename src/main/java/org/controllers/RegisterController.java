package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.entities.User;
import org.services.ShowAlertService;
import org.services.UserService;

import java.sql.SQLException;

public class RegisterController {
    private final UserService userService = new UserService();
    private final ShowAlertService showAlertService = new ShowAlertService();

    @FXML
    private TextField name;
    @FXML
    private TextField familyName;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField rPassword;
    @FXML
    private DatePicker birthDate;
    @FXML
    void register(ActionEvent event) {
        String fullName = name.getText();
        String userFamilyName = familyName.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();
        String confirmedPassword = rPassword.getText();

        try {
            userService.register(new User(fullName, userFamilyName, userEmail, userPassword, birthDate.getValue()), confirmedPassword);
            showAlertService.showAlert("Success", "User registered successfully.");
        } catch (IllegalArgumentException e) {
            showAlertService.showAlert("Error", e.getMessage());
        } catch (SQLException e) {
            showAlertService.showAlert("Error", "An error occurred while registering the user: " + e.getMessage());
        }
    }
}
