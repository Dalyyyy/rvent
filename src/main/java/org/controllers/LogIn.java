package org.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.services.ShowAlertService;
import org.services.UserService;
import javafx.scene.control.Button;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable {
    private final UserService authenticationService = new UserService();
    private final ShowAlertService showAlertService = new ShowAlertService();

    @FXML
    private TextField email;

    @FXML
    private ImageView loginPic;

    @FXML
    private PasswordField password;
    @FXML
    private Button registerButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("/pics/login .png");
        Image image = new Image(file.toURI().toString());
        loginPic.setImage(image);

    }

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


@FXML
private void handleRegisterButtonClick(ActionEvent event) {
    try {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/choose.fxml"));
        Parent root = loader.load();

        // Create the scene
        Scene scene = new Scene(root);

        // Get the stage from the button
        Stage stage = (Stage) registerButton.getScene().getWindow();

        // Set the new scene
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}


