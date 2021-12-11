package MailApplication.controllers;

import MailApplication.models.*;
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

public class FirstPageOfficerController {

    private UserAccountList users2, users3;
    private UserAccFileDataSource dataSource2, dataSource3;
    private ThingList things;
    private ObservableList<UserAccount> userAccountObservableList;
    private UserAccountOfficer selectedUser;

    @FXML private TableView<UserAccount> usersRecidentTable;
    @FXML private TextField yourUsernameTextField;
    @FXML private TextField oldpasswordTextField;
    @FXML private TextField newpasswordTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField roomTextField;
    @FXML private Label addLabel;
    @FXML private Label changePasswLabel;
    @FXML private JFXButton changeBtn;
    @FXML private JFXButton switchBtn;


    @FXML
    public void initialize() {

        dataSource2 = new UserAccFileDataSource("data", "/accountOfficer.csv");
        users2 = dataSource2.getUsersData();

        dataSource3 = new UserAccFileDataSource("data", "/accountResident.csv");
        users3 = dataSource3.getUsersData();
        users3.sortArrayList();

        showUserData();

    }

    private void showUserData() {
        userAccountObservableList = FXCollections.observableArrayList(users3.toList());
        usersRecidentTable.setItems(userAccountObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();

        configs.add(new StringConfiguration("title:Resident Name", "field:name"));
        configs.add(new StringConfiguration("title:Room { Bulding(A/B)+ floor(1-9) +roomNumber(01-10) }", "field:room"));

        for (StringConfiguration conf: configs) {

            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            usersRecidentTable.getColumns().add(col);
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

    @FXML
    public void handleChangePasswordOfficerBtnOnAction(ActionEvent event) {
        String inputYourUsername = yourUsernameTextField.getText();
        String inputOldPassword = oldpasswordTextField.getText();
        String inputNewPassword = newpasswordTextField.getText();

        if(inputYourUsername.isEmpty() || inputOldPassword.isEmpty() || inputNewPassword.isEmpty() ){
            changePasswLabel.setText("Please fill in Change Password Info.");
        }
        else{
            UserAccount searchAccount = users2.searchAccount(inputYourUsername,inputOldPassword);
            searchAccount.setPassword(inputNewPassword);

            dataSource2.setUsersData(users2);
            yourUsernameTextField.clear();
            oldpasswordTextField.clear();
            newpasswordTextField.clear();
        }

    }

    @FXML
    public void handleAddBtnOnAction(ActionEvent event) throws IOException {
        String inputType = "resident";
        String inputUsername = usernameTextField.getText();
        String inputPassword = passwordTextField.getText();
        String inputName = nameTextField.getText();
        String inputRoom = roomTextField.getText();

        if(users3.searchUsername(inputUsername)){
            addLabel.setText("Username is already used.");
        }
        else if(inputUsername.equals("") || inputPassword.equals("") || inputName.equals("") || inputRoom.equals("")){
            addLabel.setText("  !!! Please fill in all the information.");
        }
        else{
            String roomStr[];
            roomStr = inputRoom.split("");
            int noRoom = Integer.parseInt(roomStr[2].concat(roomStr[3]));

            if(!roomStr[0].matches("[A-B?]")){
                addLabel.setText("Building name must be A or B.");
            }
            else if(roomStr[1].equals("0")){
                addLabel.setText("Floor must be 1-9.");
            }
            else if(noRoom<=0 || noRoom>=11){
                addLabel.setText("No. room at each of floor = 01-10.");
            }
            else {
                ArrayList<UserAccount> usersCheckRoom = users3.toList();
                String checkResult = "notOK";
                for(UserAccount account : usersCheckRoom) {
                    UserAccountResident userCheck = (UserAccountResident)account;
                    String roomNo = userCheck.getRoom();
                    if (roomNo.equalsIgnoreCase(inputRoom)) {
                        checkResult = "notOK";
                        break;
                    } else {
                        checkResult = "OK";
                    }
                }
                if (checkResult.equals("notOK")) {
                    addLabel.setText("Room is already used.");
                }
                else {
                    UserAccount addUser = new UserAccountResident(inputType, inputName, inputUsername, inputPassword,
                            inputRoom);

                    users3.add(addUser);
                    dataSource3.setUsersData(users3);

                    Button b2 = (Button) event.getSource();
                    Stage stage2 = (Stage) b2.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/first_page_officer.fxml"));
                    stage2.setScene(new Scene(loader.load(), 700, 500));

                    stage2.show();
                }
            }
        }

    }


    @FXML public void handleSwitchBtnOnAction(ActionEvent event) throws IOException {
        switchBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) switchBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/second_page_officer.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();

    }


    public void setUserAccountList(UserAccountList userAccountlist) {
        this.users3 = userAccountlist;
    }
}
