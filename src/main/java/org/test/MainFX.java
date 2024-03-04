package org.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        gestionEvent
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/eventcrud.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateUser.fxml"));
      
        Parent root = loader.load();
        Scene scene = new Scene(root,1200,750);
        primaryStage.setTitle("create new user ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
