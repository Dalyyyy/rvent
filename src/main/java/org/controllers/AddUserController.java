package org.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.entities.Role;
import org.entities.User;
import org.services.ShowAlertService;
import org.services.UserService;

import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class AddUserController {
    private final UserService userService = new UserService();
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)\\.(.+)$");
    private final ShowAlertService showAlertService = new ShowAlertService();

    @FXML
    private TextField name;
    @FXML
    private TextField familyName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField phone;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<Role> roleComboBox;

    @FXML
    public void initialize() {
        List<Role> roles = Arrays.asList(Role.ADMIN, Role.ORGANIZER);
        roleComboBox.setItems(FXCollections.observableArrayList(roles));
    }
    @FXML
    void addUser(ActionEvent event) {

        try {
            Role selectedRole = roleComboBox.getValue();

            if (name.getText().isEmpty() || familyName.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || birthDate.getValue() == null) {
                throw new IllegalArgumentException("All fields are required.");
            }

            String fullName = name.getText();
            String userFamilyName = familyName.getText();
            String userEmail = email.getText();
            String userPassword = password.getText();
            LocalDate selectedDate = birthDate.getValue();
            LocalDate currentDate = LocalDate.now();
            int phoneNumber = Integer.parseInt(phone.getText());

            if (!fullName.matches("[a-zA-Z]+") || !userFamilyName.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Full name and family name must contain only letters.");
            }

            if (!EMAIL_PATTERN.matcher(userEmail).matches()) {
                throw new IllegalArgumentException("Invalid email format.");
            }

            if (!userPassword.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")) {
                throw new IllegalArgumentException("Password must contain at least one letter, one digit, and be at least 8 characters long.");
            }
            if (selectedDate != null && selectedDate.isAfter(currentDate)) {
                throw new IllegalArgumentException("Selected date cannot be in the future.");
            }

            userService.addUser(new User(fullName, userFamilyName, userEmail, userPassword, selectedDate, phoneNumber), selectedRole);
            showAlertService.showAlert("Success", "Enterprise registered successfully.");
        }catch (IllegalArgumentException e) {
            // Show error for validation failures
            showAlertService.showAlert("Error", e.getMessage());
        } catch (SQLException e) {
            // Show error for database-related issues
            showAlertService.showAlert("Error", "An error occurred while registering the Enterprise: " + e.getMessage());
        }
    }

    @FXML
    private void onClickBackButton(ActionEvent event){
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


}


