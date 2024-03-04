package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.entities.User;
import org.services.UserService;

import java.sql.SQLException;

public class UpdateUserController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField familyNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TableView<User> userTableView;
    private User currentUser;
    UserService userService = new UserService();

    @FXML
    public void handleGetSelectedUserData() {
        try {
            // Basic validation (e.g., check if name or family name fields are empty)
            if (nameField.getText().isEmpty() || familyNameField.getText().isEmpty()) {
                showAlert("Validation Error", "Name and Family Name cannot be empty.");
                return;
            }

            currentUser.setName(nameField.getText());
            currentUser.setFamilyName(familyNameField.getText());
            try {
                currentUser.setPhoneNumber(Integer.parseInt(phoneNumberField.getText()));
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Please enter a valid phone number.");
                return; // Stop processing if the phone number is invalid
            }
            currentUser.setPassword(passwordField.getText()); // Handle password updates carefully

            // Update the user information in the database
            userService.updateUser(currentUser);

            // Show success message and possibly close the form or navigate away
            showAlert("Success", "User information updated successfully.");
        } catch (SQLException e) {
            // Handle SQL exceptions, such as connection issues or constraints violations
            showAlert("Database Error", "Failed to update user information.");
            e.printStackTrace();
        } catch (Exception e) {
            // Handle unexpected exceptions
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void setUserDetails(User user) {
        this.currentUser = user; // Properly initialize currentUser with the user object
        // Populate form fields with user data
        nameField.setText(user.getName());
        familyNameField.setText(user.getFamilyName());
        phoneNumberField.setText(String.valueOf(user.getPhoneNumber()));
        passwordField.setText(user.getPassword()); // Note: Consider security implications of handling passwords
    }


}
