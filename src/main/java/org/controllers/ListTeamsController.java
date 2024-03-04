package org.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.entities.Team;
import org.entities.User;
import org.services.TeamService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListTeamsController {
    TeamService teamService = new TeamService();
    @FXML
    private TableView<Team> teamTableView;
    @FXML
    private TableColumn<Team, String> teamNameColumn;
    @FXML
    private TableColumn<Team, Integer> teamSizeColumn;

    @FXML
    private TableColumn<Team, Integer> memberCountColumn;
    @FXML
    private Button reloadTeamsButton;
    @FXML
    private Button onClickAddMember;
    @FXML
    private Button onClickEditTeam;
    @FXML
    private Button onClickAddTeam;
    @FXML
    private Button onClickDeleteTeam;

    @FXML
    public void initialize() {
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        memberCountColumn.setCellValueFactory(new PropertyValueFactory<>("membersNbr")); // Make sure the property name matches

        loadTeams();
    }

    private void loadTeams() {
        try {
            List<Team> teams = teamService.getAllTeams();
            ObservableList<Team> observableTeams = FXCollections.observableArrayList(teams);
            teamTableView.setItems(observableTeams);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    // Method to load team data into the TableView
    @FXML
    private void loadTeamData() {
        try {
            List<Team> teamList = teamService.getAllTeams();
            ObservableList<Team> observableUserList = FXCollections.observableArrayList(teamList);
            teamTableView.setItems(observableUserList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error or display a message to the user
        }
    }

    // Refresh teams view
    @FXML
    private void refreshTeamsTableView() {
        loadTeamData();
    }

    // Add member to team
//    @FXML
//    private void addMemberToTeam() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addTeamForm.fxml")); // Ensure the path is correct
//            Parent root = loader.load();
//
//            Scene scene = new Scene(root);
//
//            // Create a new stage for the add member form (if you want it in a new window)
//            Stage newStage = new Stage();
//            newStage.setTitle("Add Member to Team"); // Set a title or use a dynamic one if preferred
//            newStage.setScene(scene);
//
//            // Optional: Set the new stage as a modal window
//            newStage.initModality(Modality.APPLICATION_MODAL);
//            newStage.initOwner(onClickAddTeam.getScene().getWindow()); // Set the owner if you're using modality
//
//            newStage.showAndWait(); // Use showAndWait to block interaction with other windows until this one is closed
//        } catch (Exception e) {
//            e.printStackTrace(); // Log or handle the exception appropriately
//        }
//    }


    @FXML
    void addTeam() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddTeamForm.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) onClickAddTeam.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editTeam() {
        Team team = teamTableView.getSelectionModel().getSelectedItem();
        if (team != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateTeam.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) onClickEditTeam.getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No team selected");
        }
    }

    // Delete a team
    @FXML
    void deleteTeam() {
        Team team = teamTableView.getSelectionModel().getSelectedItem();
        if (team != null) {
            try {
                teamService.deleteTeam(team.getId());
                teamTableView.getItems().remove(team);
            } catch (SQLException e) {
                System.err.println("Error deleting team: " + e.getMessage());
            }
        } else {
            System.out.println("No team selected.");
        }
    }

}

