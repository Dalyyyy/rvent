package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.entities.Enterprise;
import org.services.EnterpriseService;
import org.services.ShowAlertService;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddEnterpriseController {

    private final EnterpriseService enterpriseService = new EnterpriseService();
    private final ShowAlertService showAlertService = new ShowAlertService();
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)\\.(.+)$");



    @FXML
    private Button backButton;

    @FXML
    private TextField email;

    @FXML
    private TextField description;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField rPassword;

    @FXML
    private Button singInButton;

    @FXML
    void addEnterprise(ActionEvent event) {
        String enterpriseName = name.getText();
        String enterpriseEmail = email.getText();
        String enterprisePassword = password.getText();
        String confirmedPassword = rPassword.getText();
        String enterpriseDescription = description.getText();

        try {
            // Check if any required field is empty
            if (enterpriseName.isEmpty() || enterpriseEmail.isEmpty() || enterprisePassword.isEmpty() || confirmedPassword.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            // Validate password complexity
            if (!enterprisePassword.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")) {
                throw new IllegalArgumentException("Password must contain at least one letter, one digit, and be at least 8 characters long.");
            }

            // Validate email format
            if (!EMAIL_PATTERN.matcher(enterpriseEmail).matches()) {
                throw new IllegalArgumentException("Invalid email format.");
            }

            // Register enterprise based on whether description is provided
            Enterprise enterprise;
            if (!enterpriseDescription.isEmpty()) {
                enterprise = new Enterprise(enterpriseName, enterpriseEmail, enterprisePassword, enterpriseDescription);
            } else {
                enterprise = new Enterprise(enterpriseName, enterpriseEmail, enterprisePassword);
            }

            // Register enterprise through enterpriseService
            enterpriseService.register(enterprise, confirmedPassword);

            // Show success message
            showAlertService.showAlert("Success", "Enterprise registered successfully.");
        } catch (IllegalArgumentException e) {
            // Show error for validation failures
            showAlertService.showAlert("Error", e.getMessage());
        } catch (SQLException e) {
            // Show error for database-related issues
            showAlertService.showAlert("Error", "An error occurred while registering the Enterprise: " + e.getMessage());
        }
    }


    @FXML
    void onClickBackButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/choose.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) backButton.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void onClickSignIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LogIn.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) singInButton.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
