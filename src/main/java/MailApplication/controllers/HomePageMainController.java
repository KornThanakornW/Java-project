package MailApplication.controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePageMainController implements Initializable {
    @FXML private VBox vbox;
    @FXML Parent fxml;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), this.vbox);
        t.setToX(332);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(this.getClass().getResource("/home_page_sign_in.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomePageMainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    @FXML
    private void handleOpenSignInBtnOnAction(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(332);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(this.getClass().getResource("/home_page_sign_in.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomePageMainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void handleOpenSignUpBtnOnAction(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1.0D), vbox);
        t.setToX(vbox.getLayoutX() / 20);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(this.getClass().getResource("/home_page_sign_up.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomePageMainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }
}
