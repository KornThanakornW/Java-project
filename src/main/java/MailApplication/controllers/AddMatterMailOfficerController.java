package MailApplication.controllers;

import MailApplication.models.MatterMail;
import MailApplication.models.Thing;
import MailApplication.models.ThingList;
import MailApplication.services.ThingFileDataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMatterMailOfficerController {

    private ThingList things;
    private ThingFileDataSource dataSourceThing;

    @FXML private TextField senderNameTextField;
    @FXML private TextField roomTextField;
    @FXML private TextField sizeTextField;
    @FXML private TextField priorityTextField;
    @FXML private TextField officerNameTextField;
    @FXML private Label addLetterLabel;


    @FXML
    public void initialize() {
        dataSourceThing = new ThingFileDataSource("data", "/things.csv");
        things = dataSourceThing.getThingsData();

    }

    @FXML
    public void handleCancleBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/second_page_officer.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();
    }

    @FXML
    public void handleOkBtnOnAction(ActionEvent event) throws IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();

        String inputTypeMail = "MatterMail";
        String inputSendername = senderNameTextField.getText();
        String inputRoomText = roomTextField.getText();
        String inputsize = sizeTextField.getText();
        String inputOfficer = officerNameTextField.getText();
        String inputreceiverName = "-";
        String inputPriority = priorityTextField.getText();


        if(inputSendername.equals("") || inputRoomText.equals("") || inputsize.equals("")
                || inputOfficer.equals("") ||inputPriority.equals("")){
            addLetterLabel.setText("  !!! Please fill in all the information.");
        }
        else{
            String roomStr[];
            roomStr = inputRoomText.split("");
            int noRoom = Integer.parseInt(roomStr[2].concat(roomStr[3]));

            if(!roomStr[0].matches("[A-B?]")){
                addLetterLabel.setText("Building name must be A or B.");
            }
            else if(roomStr[1].equals("0")){
                addLetterLabel.setText("Floor must be 1-9.");
            }
            else if(noRoom<=0 || noRoom>=11){
                addLetterLabel.setText("No. room at each of floor = 01-10.");
            }
            else{
                Thing addThing = new MatterMail(inputTypeMail,"notReceivedYet",inputSendername, dateFormat.format(date),
                        inputOfficer,inputRoomText,inputreceiverName, "-",inputsize,inputPriority);
                things.add(addThing);
                dataSourceThing.setThingsData(things);

                Button bb = (Button) event.getSource();
                Stage stagee = (Stage) bb.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/second_page_officer.fxml"));
                stagee.setScene(new Scene(loader.load(), 700, 500));

                stagee.show();

            }
        }


    }
}
