package MailApplication.controllers;

import MailApplication.models.UserAccountList;
import MailApplication.services.UserAccFileDataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageSignInController {

    private UserAccountList users1, users2, users3;
    private UserAccFileDataSource dataSource1, dataSource2, dataSource3;

    @FXML private Button signInBtn;
    @FXML private ImageView iconPicture;
    @FXML private Button developerBtn;
    @FXML private Button aboutBtn;
    @FXML private Button createAccountBtn;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label loginLabel;

    @FXML
    public void initialize() {
        dataSource1 = new UserAccFileDataSource("data", "/accountSystemAdministrator.csv");
        users1 = dataSource1.getUsersData();

        dataSource2 = new UserAccFileDataSource("data", "/accountOfficer.csv");
        users2 = dataSource2.getUsersData();

        dataSource3 = new UserAccFileDataSource("data", "/accountResident.csv");
        users3 = dataSource3.getUsersData();

    }

    @FXML public void handleDeveloperBtnOnAction(ActionEvent event) throws IOException {
            developerBtn = (Button) event.getSource();
            Stage stage = (Stage) developerBtn.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/developer_info.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 500));

            stage.show();

    }

    @FXML public void handleAboutBtnOnAction(ActionEvent event) throws IOException {
            aboutBtn = (Button) event.getSource();
            Stage stage2 = (Stage) aboutBtn.getScene().getWindow();

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/about_app_recommendation.fxml"));
            stage2.setScene(new Scene(loader2.load(), 700, 500));

            stage2.show();

    }


    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();


        if (users1.signInCheck(username,password) == 2){
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/first_page_system_administrator.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 500));

            FirstPageSystemAdministratorController dw = loader.getController();
            dw.setUserAccountList(users2);
            stage.show();
        }
        else if (users2.signInCheck(username,password) == 0){
            loginLabel.setText("!!! User was susdended.");
            dataSource2.setUsersData(users2);

        }
        else if(users2.signInCheck(username,password) == 1){
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/first_page_officer.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 500));
            dataSource2.setUsersData(users2);

            FirstPageOfficerController dw = loader.getController();
            dw.setUserAccountList(users3);
            stage.show();
        }
        else if (users3.signInCheck(username,password) == 2){
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/first_page_resident.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 500));

            FirstPageResidentController dw = loader.getController();
            dw.setUserAccountList(users3);
            stage.show();
        }
        else if(users1.signInCheck(username,password) == -1 || users2.signInCheck(username,password) == -1
                            ||users3.signInCheck(username,password) == -1){
            loginLabel.setText("The password that you've entered is incorrect.");
        }
        else if(username.equals("") || password.equals("")){
            loginLabel.setText("Please fill in the blank.");
        }
        else {
            loginLabel.setText("!!! Login failed.");
        }
    }



}
