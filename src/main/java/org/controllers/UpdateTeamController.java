package org.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.entities.Team;
import org.entities.User;
import org.services.TeamService;

import java.sql.SQLException;
import java.util.List;


public class UpdateTeamController {
    @FXML
    private TextField teamNameField;

    @FXML
    private ComboBox<String> organizerComboBox; // If you're choosing an organizer, assuming names are strings

    @FXML
    private Button editTeamButton;
    @FXML
    private TableView<Team> teamTableView;
    TeamService teamService = new TeamService();
    private Team currentTeam;


    @FXML
    private void initialize() {
        teamTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            currentTeam = newSelection; // Update currentTeam with the selected team
        });
    }

    @FXML
    private void handleEditTeam() {
        try {
            // Assuming currentTeam is already selected and initialized elsewhere

            String newTeamName = teamNameField.getText().trim();
            if (newTeamName.isEmpty()) {
                showAlert("Validation Error", "Team name cannot be empty.");
                return;
            }

            // Now, use the service to update the team with the new name
            teamService.updateTeam(currentTeam.getId(), newTeamName);
            // Optionally, refresh the team list to reflect the update
            refreshTeams();

            // Show success message
            showAlert("Success", "Team information updated successfully.");
        } catch (SQLException e) {
            // Handle SQL exceptions, such as connection issues or constraints violations
            showAlert("Database Error", "Failed to update team information.");
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other unexpected exceptions
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void refreshTeams() {
        try {
            // Fetch the updated list of teams from the database
            List<Team> teams = teamService.getAllTeams(); // Assuming you have such a method
            // Convert the list to an ObservableList, which is required by TableView
            ObservableList<Team> observableTeams = FXCollections.observableArrayList(teams);
            // Update the TableView's items
            teamTableView.setItems(observableTeams);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors, possibly showing an alert to the user
            showAlert("Database Error", "Failed to fetch updated teams.");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other unexpected errors
            showAlert("Error", "An unexpected error occurred while refreshing teams.");
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}







