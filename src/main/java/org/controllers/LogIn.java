package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import org.services.ShowAlertService;
import org.services.UserService;

public class LogIn {
    private final UserService authenticationService = new UserService();
    private final ShowAlertService showAlertService = new ShowAlertService();
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    void login(ActionEvent event) {
        String userEmail = email.getText();
        String userPassword = password.getText();

        try {
            boolean isAuthenticated = authenticationService.authenticateUser(userEmail, userPassword);

            if (isAuthenticated) {
                showAlertService.showAlert("Login Successful", "Welcome !" );
            } else {
                showAlertService.showAlert("Login Failed", "Incorrect email or password");
            }
        } catch (Exception e) {
            showAlertService.showAlert("Error", "An error occurred while attempting to login");
            e.printStackTrace();
        }
    }

}

