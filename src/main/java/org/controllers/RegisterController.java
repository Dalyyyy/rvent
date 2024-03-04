package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.entities.User;
import org.services.ShowAlertService;
import org.services.UserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class RegisterController {
    private final UserService userService = new UserService();
    private final ShowAlertService showAlertService = new ShowAlertService();
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)\\.(.+)$");


    @FXML
    private TextField name;
    @FXML
    private TextField familyName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField rPassword;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField phone;
    @FXML
    private Button singInButton;
    @FXML
    private Button backButton;

    @FXML
    void register(ActionEvent event) {
        String fullName = name.getText();
        String userFamilyName = familyName.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();
        String confirmedPassword = rPassword.getText();
        LocalDate selectedDate = birthDate.getValue();
        LocalDate currentDate = LocalDate.now();
        int phoneNumber = Integer.parseInt(phone.getText());



        try {
            if (fullName.isEmpty() || userFamilyName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || confirmedPassword.isEmpty() || selectedDate == null) {
                throw new IllegalArgumentException("All fields are required.");
            }

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

            userService.register(new User(fullName, userFamilyName, userEmail, userPassword, birthDate.getValue(),phoneNumber), confirmedPassword);
            showAlertService.showAlert("Success", "User registered successfully.");
        } catch (IllegalArgumentException e) {
            showAlertService.showAlert("Error", e.getMessage());
        } catch (SQLException e) {
            showAlertService.showAlert("Error", "An error occurred while registering the user: " + e.getMessage());
        }
    }

    @FXML
    private void onClickSignIn(ActionEvent event){
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
