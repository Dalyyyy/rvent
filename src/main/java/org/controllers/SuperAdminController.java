package org.controllers;

import com.pdfjet.*;
import com.pdfjet.Font;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import com.pdfjet.Cell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import org.entities.Role;
import org.entities.User;
import org.services.UserService;




import java.awt.*;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SuperAdminController implements Initializable {
    UserService userservice = new UserService();

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<User, Role> roleColumn;

    @FXML
    private TableColumn<?, ?> familyNameColumn;

    @FXML
    private TableColumn<User, Integer> reservationNbrColumn;

    @FXML
    private Font x1;

    @FXML
    private VBox SuperAdminPages;

    @FXML
    private Color x2;

    @FXML
    private TableColumn<?, ?> phoneColumn;

    @FXML
    private TableColumn<?, ?> passwordColumn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private Button onClickAddUser;
    @FXML
    private Button onClickDelete;
    @FXML
    private Button onClickUpdate;


    public void initialize(URL url, ResourceBundle resourceBundle) {


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyNameColumn.setCellValueFactory(new PropertyValueFactory<>("familyName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber")); // Adjusted to "phoneNumber"
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password")); // Assuming "Motdepasse" is "password"
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleUser"));
        //reservationNbrColumn.setCellValueFactory(new PropertyValueFactory<>("reservationNbr"));

        try {
            List<User> userList = userservice.getAllUsers();
            ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);
            userTableView.setItems(observableUserList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error or display a message to the user
        }
    }
    public void exportToPDF() {
        try {
            FileOutputStream fos = new FileOutputStream("UsersList.pdf");
            PDF pdf = new PDF(fos);
            Page page = new Page(pdf, A4.PORTRAIT);

            Font font = new Font(pdf, CoreFont.HELVETICA);
            font.setSize(12);

            Table table = new Table();
            List<List<Cell>> tableData = new ArrayList<>();

            // Add table header
            List<Cell> headerRow = new ArrayList<>();
            headerRow.add(new Cell(font, "Name"));
            headerRow.add(new Cell(font, "Family Name"));
            headerRow.add(new Cell(font, "Email"));
            headerRow.add(new Cell(font, "Role"));
            tableData.add(headerRow);

            // Fetch users from your service
            List<User> userList = userservice.getAllUsers();

            // Add user details to the table
            for (User user : userList) {
                List<Cell> row = new ArrayList<>();
                row.add(new Cell(font, user.getName()));
                row.add(new Cell(font, user.getFamilyName()));
                row.add(new Cell(font, user.getEmail()));
                row.add(new Cell(font, user.getRoleUser().toString())); // Assuming getRoleUser() returns a String or something with a meaningful toString()
                tableData.add(row);
            }

            table.setData(tableData, Table.DATA_HAS_1_HEADER_ROWS);
            table.setLocation(70f, 60f);

            // Assuming you have 4 columns and want to set their widths
            // You would need to call setColumnWidth for each column individually
            // Note: This is pseudocode; PDFjet may not have a direct method like setColumnWidth(int, float).
            // You might need to adjust column widths in another way, such as by setting widths on individual cells.

            pdf.flush();
            fos.close();

            System.out.println("PDF exported successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error exporting to PDF: " + e.getMessage());
        }
    }
    @FXML
    void onClickDelete() {
        User user = userTableView.getSelectionModel().getSelectedItem();
        if (user != null) {
            try {
                userservice.deleteUser(user.getId()); // Pass the user ID to deleteUser
                userTableView.getItems().remove(user); // Remove the user from the table view
            } catch (SQLException e) {
                // Handle the SQLException (e.g., show an error message)
                System.err.println("Error deleting user: " + e.getMessage());
            }
        } else {
            // Optionally, handle the case where no user is selected (e.g., show a warning message)
            System.out.println("No user selected.");
        }
    }


    @FXML
    void editUser() {
        User user = userTableView.getSelectionModel().getSelectedItem();
        if (user != null) {
            try {
                // Load the Update User view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateUser.fxml")); // Correct path to your FXML
                Parent root = loader.load();

                // Get the controller and set the user details
                UpdateUserController updateUserController = loader.getController();
                updateUserController.setUserDetails(user);

                // Show the Update User view in a new stage or scene as needed
                Scene scene = new Scene(root);
                Stage stage = (Stage) onClickUpdate.getScene().getWindow();
                stage.setTitle("Edit User");
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                // Handle errors
            }
        } else {
            System.out.println("User not selected");
        }
    }

    @FXML
    void addUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddUser.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) onClickAddUser.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void refreshTableView(ActionEvent event) {
    }



}
