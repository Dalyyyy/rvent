package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.entities.Sponsoring;
import org.services.SponService;
import org.w3c.dom.Text;

import java.sql.SQLException;

public class cardController {
    @FXML
    private Button Delete;

    @FXML
    private ImageView ImageEvent;

    @FXML
    private Label Numero;

    @FXML
    private Label Prenom;

    @FXML
    private Label Type_etab;

    @FXML
    private Label adresse;

    @FXML
    private Label description;

    @FXML
    private Label domaine;

    @FXML
    private Button edit;

    @FXML
    private Label email;

    @FXML
    private Label nom;

    @FXML
    private Label nom_etab;



    Sponsoring sp = new Sponsoring();


    public void setData(Sponsoring sponsoring) {

        if (sponsoring != null) {
            nom.setText(sponsoring.getNom());
            Prenom.setText(sponsoring.getPrenom());
            nom_etab.setText(sponsoring.getNom_etab());
            //T0ype_etab.setText(sponsoring.getTetab());
            domaine.setText(sponsoring.getDomaine());
            adresse.setText(sponsoring.getAdresse());
            email.setText(sponsoring.getEmail());
            Numero.setText(String.valueOf(sponsoring.getNumero()));
            description.setText(sponsoring.getDescription());
            //description.setText(sponsoring.getDescription());
            //nombreRepetition.setText(String.valueOf(exercice.getNbr_rep()));

//            try {
//                Image image = new Image(exercice.getImage());
//
//                imageview.setImage(image);
//            } catch (Exception e) {
//                System.err.println("Error loading image: " + e.getMessage());
//                e.printStackTrace();
//            }
        }
    }




}
