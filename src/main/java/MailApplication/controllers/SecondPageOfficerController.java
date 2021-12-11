package MailApplication.controllers;

import MailApplication.models.Thing;
import MailApplication.models.ThingList;
import MailApplication.models.UserAccountList;
import MailApplication.models.UserAccountOfficer;
import MailApplication.services.StringConfiguration;
import MailApplication.services.ThingFileDataSource;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SecondPageOfficerController {

    private UserAccountList users3;
    private UserAccFileDataSource dataSource3;
    private ThingList things;
    private Thing selectedThing;
    private ThingFileDataSource dataSourceThing;
    private ObservableList<Thing> thingObservableList;

    @FXML private Pane pane;
    @FXML private JFXButton switchBtn;
    @FXML private JFXButton addLetterBtn;
    @FXML private JFXButton changeStatusBtn;
    @FXML private JFXButton addMatterMailBtn;
    @FXML private JFXButton addParcelPostBtn;
    @FXML private JFXButton receivedMailBtn;
    @FXML private JFXButton notReceivedMailBtn;
    @FXML private JFXButton searchMailBtn;
    @FXML private TableView<Thing> mailTable;
    @FXML private TextField receiverName;
    @FXML private Label timeLabel;
    @FXML private Label roomLabel;
    @FXML private Label changeStatusLabel;



    @FXML
    public void initialize() {

        dataSource3 = new UserAccFileDataSource("data", "/accountResident.csv");
        users3 = dataSource3.getUsersData();

        dataSourceThing = new ThingFileDataSource("data", "/things.csv");
        things = dataSourceThing.getThingsData();
        things.sortArrayList();

        showThingData();

        mailTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedThing(newValue);
            }
        });

    }

    private void showThingData() {
        thingObservableList = FXCollections.observableArrayList(things.toList());
        mailTable.setItems(thingObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room", "field:room"));
        configs.add(new StringConfiguration("title:Type Mail", "field:typeThing"));
        configs.add(new StringConfiguration("title:Time receive By officer", "field:senderTime"));
        configs.add(new StringConfiguration("title:Receiver Officer Name", "field:receiverOfficerName"));
        configs.add(new StringConfiguration("title:Status", "field:statusMail"));
        configs.add(new StringConfiguration("title:Receiver Name", "field:receiverName"));
        configs.add(new StringConfiguration("title:Time Received By Owner", "field:receiveTime"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Service Company", "field:serviceCompany"));
        configs.add(new StringConfiguration("title:Tracking Number", "field:trackingNumber"));


        for (StringConfiguration conf: configs) {

            TableColumn col2 = new TableColumn(conf.get("title"));
            col2.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            mailTable.getColumns().add(col2);
        }
    }

    private void showSelectedThing(Thing thing) {
        selectedThing = thing;
        timeLabel.setText(thing.getSenderTime());
        roomLabel.setText(thing.getRoom());
        changeStatusBtn.setDisable(false);
    }


    @FXML
    public void handleLogoutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home_page_main.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();

    }
    @FXML public void handleSwitchBtnOnAction(ActionEvent event) throws IOException {
        switchBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) switchBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/first_page_officer.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();

    }

    @FXML
    public void handleAddLetterBtnOnAction(ActionEvent event) throws IOException {
        addLetterBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) addLetterBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_letter_officer.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();
    }

    @FXML
    public void handleAddMMBtnOnAction(ActionEvent event) throws IOException {
        addMatterMailBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) addMatterMailBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_matter_mail_officer.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();
    }

    @FXML
    public void handleAddPPBtnOnAction(ActionEvent event) throws IOException {
        addParcelPostBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) addParcelPostBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_parcel_post_officer.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();
    }

    @FXML
    public void handleReceivedBtnOnAction(ActionEvent event) throws IOException {
        receivedMailBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) receivedMailBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/received_mail.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();
    }

    @FXML
    public void handleNotReceivedBtnOnAction(ActionEvent event) throws IOException {
        notReceivedMailBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) notReceivedMailBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/not_receive_mail.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();
    }

    @FXML
    public void handleSearchBtnOnAction(ActionEvent event) throws IOException {
        searchMailBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) searchMailBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mail_search.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();
    }

    @FXML
    public void handleChangeStatusMailBtnOnAction(ActionEvent event) throws IOException {
        changeStatusBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) changeStatusBtn.getScene().getWindow();

        String name = receiverName.getText();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();

        if(name.equals("")){
            changeStatusLabel.setText("Please Fill in Name");
        }
        else if(selectedThing.getStatusMail().equals("received")){
            changeStatusLabel.setText("Can not change status back. ");
        }
        else{
            selectedThing.receiveMail(name, simpleDateFormat.format(date));
            dataSourceThing.setThingsData(things);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/second_page_officer.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 500));

            stage.show();
        }

    }


}
