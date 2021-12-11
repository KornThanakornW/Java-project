package MailApplication.controllers;

import MailApplication.models.UserAccount;
import MailApplication.models.UserAccountList;
import MailApplication.models.UserAccountOfficer;
import MailApplication.services.StringConfiguration;
import MailApplication.services.UserAccFileDataSource;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FirstPageSystemAdministratorController {

    private UserAccountList users1, users2, users3;
    private UserAccFileDataSource dataSource1, dataSource2, dataSource3;
    private ObservableList<UserAccount> userAccountObservableList;
    private UserAccountOfficer selectedUser;

   @FXML private TableView<UserAccount> usersOfficerTable;
   @FXML private Label nameLabel;
   @FXML private Label addLabel;
   @FXML private Label updateStatusLabel;
   @FXML private Label changePasswordLabel;
   @FXML private TextField statusTextField;
   @FXML private TextField nameTextField;
   @FXML private TextField usernameTextField;
   @FXML private TextField passwordTextField;
   @FXML private TextField yourUsernameTextField;
   @FXML private TextField oldpasswordTextField;
   @FXML private TextField newpasswordTextField;
   @FXML private JFXButton updateStatusBtn;
   @FXML private JFXButton addBtn;
   @FXML private JFXButton changeBtn;

    @FXML
    public void initialize() {
        dataSource1 = new UserAccFileDataSource("data", "/accountSystemAdministrator.csv");
        users1 = dataSource1.getUsersData();


        dataSource2 = new UserAccFileDataSource("data", "/accountOfficer.csv");
        users2 = dataSource2.getUsersData();
        users2.sortArrayList();

        dataSource3 = new UserAccFileDataSource("data", "/accountResident.csv");
        users3 = dataSource3.getUsersData();

        showUserData();

        usersOfficerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedUser((UserAccountOfficer) newValue);
            }
        });
    }

    private void showUserData() {
        userAccountObservableList = FXCollections.observableArrayList(users2.toList());
        usersOfficerTable.setItems(userAccountObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Name", "field:name"));
        configs.add(new StringConfiguration("title:Username", "field:username"));
        configs.add(new StringConfiguration("title:Last Sign In", "field:lastTimeLogin"));
        configs.add(new StringConfiguration("title:Status", "field:status"));
        configs.add(new StringConfiguration("title:Times Login Before Suspended", "field:timesLoginBfSusp"));

        for (StringConfiguration conf: configs) {

            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            usersOfficerTable.getColumns().add(col);
        }
    }

    @FXML
    public void handleLogoutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home_page_main.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();

    }

    private void showSelectedUser(UserAccountOfficer userAccount) {
        selectedUser = userAccount;
        nameLabel.setText(userAccount.getName());
        statusTextField.setText(String.valueOf(userAccount.getStatus()));
        updateStatusBtn.setDisable(false);
    }

    private void clearSelectedUser() {
        selectedUser = null;
        nameLabel.setText("...");
        statusTextField.clear();
    }

    @FXML
    public void handleUpdateStatusBtnOnAction(ActionEvent event) {
        String inputStatus = statusTextField.getText();
        if(inputStatus.isEmpty()){
            updateStatusLabel.setText("Please Click User!");
        }
        else{
            if(inputStatus.equalsIgnoreCase("NORMAL")){
                selectedUser.setStatus("NORMAL");
                selectedUser.setLastTimeLoginBfSuspToZero();
            }
            else if(inputStatus.equalsIgnoreCase("SUSPENDED")){
                selectedUser.setStatus("SUSPENDED");
            }
            clearSelectedUser();
            usersOfficerTable.refresh();
            usersOfficerTable.getSelectionModel().clearSelection();

            dataSource2.setUsersData(users2);
            newpasswordTextField.clear();
            updateStatusLabel.setText("");
        }

    }

    @FXML
    public void handleAddBtnOnAction(ActionEvent event) throws IOException {
        String inputType = "officer";
        String inputName = nameTextField.getText();
        String inputUsername = usernameTextField.getText();
        String inputPassword = passwordTextField.getText();
        String inputTime = "-";
        String inputStatus = "NORMAL";
        int inputTimes = 0;

        if(users2.searchUsername(inputUsername)){
            addLabel.setText("Username is used.");
        }
        if(inputName.isEmpty() || inputUsername.isEmpty() || inputPassword.isEmpty() ){
            addLabel.setText("Please fill in Add User Information.");
        }
        else{
            UserAccount addUser = new UserAccountOfficer(inputType,inputName,inputUsername,inputPassword,
                    inputTime,inputStatus,inputTimes);

            users2.add(addUser);
            clearSelectedUser();
            usersOfficerTable.refresh();
            usersOfficerTable.getSelectionModel().clearSelection();

            dataSource2.setUsersData(users2);
            nameTextField.clear();
            usernameTextField.clear();
            passwordTextField.clear();
            addLabel.setText("");

            Button b2 = (Button) event.getSource();
            Stage stage2 = (Stage) b2.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/first_page_system_administrator.fxml"));
            stage2.setScene(new Scene(loader.load(), 700, 500));

            stage2.show();

        }

    }

    @FXML
    public void handleChangeBtnOnAction(ActionEvent event) {
        String inputYourUsername = yourUsernameTextField.getText();
        String inputOldPassword = oldpasswordTextField.getText();
        String inputNewPassword = newpasswordTextField.getText();

        if(inputYourUsername.isEmpty() || inputOldPassword.isEmpty() || inputNewPassword.isEmpty() ){
            changePasswordLabel.setText("Please fill in Change Password Information!");
        }
        else{
            UserAccount searchAccount = users1.searchAccount(inputYourUsername,inputOldPassword);
            searchAccount.setPassword(inputNewPassword);
            clearSelectedUser();
            usersOfficerTable.refresh();
            usersOfficerTable.getSelectionModel().clearSelection();

            dataSource1.setUsersData(users1);
        }

    }



    public void setUserAccountList(UserAccountList userAccountList) {
        this.users2 = userAccountList;
    }


}
