package MailApplication.controllers;

import MailApplication.models.UserAccount;
import MailApplication.models.UserAccountList;
import MailApplication.models.UserAccountResident;
import MailApplication.services.UserAccFileDataSource;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HomePageSignUpController {

    private UserAccountList users3;
    private UserAccFileDataSource dataSource3;

    @FXML private Button backBtn;
    @FXML private JFXButton createAccountBtn;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField roomTextField;
    @FXML private Label createAccountLabel;

    @FXML
    public void initialize(){
        dataSource3 = new UserAccFileDataSource("data", "/accountResident.csv");
        users3 = dataSource3.getUsersData();

    }

    @FXML
    public void handleCreateAccountBtnOnAction(ActionEvent event) throws IOException {
        String inputType = "resident";
        String inputUsername = usernameTextField.getText();
        String inputPassword = passwordTextField.getText();
        String inputName = nameTextField.getText();
        String inputRoom = roomTextField.getText();

        if(users3.searchUsername(inputUsername)){
            createAccountLabel.setText("Username is already used.");
        }
        else if(inputUsername.equals("") || inputPassword.equals("") || inputName.equals("") || inputRoom.equals("")){
            createAccountLabel.setText("  !!! Please fill in all the information.");
        }
        else{
            String roomStr[];
            roomStr = inputRoom.split("");
            int noRoom = Integer.parseInt(roomStr[2].concat(roomStr[3]));

            if(!roomStr[0].matches("[A-B?]")){
                createAccountLabel.setText("Building name must be A or B.");
            }
            else if(roomStr[1].equals("0")){
                createAccountLabel.setText("Floor must be 1-9.");
            }
            else if(noRoom<=0 || noRoom>=11){
                createAccountLabel.setText("No. room at each of floor = 01-10.");
            }
            else{
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
                if(checkResult.equals("notOK")){
                    createAccountLabel.setText("Room is already used.");
                }
                else{
                    UserAccount addUser = new UserAccountResident(inputType,inputName,inputUsername,inputPassword,
                            inputRoom);

                    users3.add(addUser);
                    dataSource3.setUsersData(users3);

                    Button b2 = (Button) event.getSource();
                    Stage stage2 = (Stage) b2.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/home_page_main.fxml"));
                    stage2.setScene(new Scene(loader.load(), 700, 500));

                    stage2.show();
                }




            }
        }

    }

}
