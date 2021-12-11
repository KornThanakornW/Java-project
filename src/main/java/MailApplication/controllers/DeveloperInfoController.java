package MailApplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DeveloperInfoController {
    @FXML private Button backBtn;
    @FXML
    public void initialize(){

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home_page_main.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();


    }

}
