package MailApplication.controllers;

import MailApplication.models.Thing;
import MailApplication.models.ThingList;
import MailApplication.services.StringConfiguration;
import MailApplication.services.ThingFileDataSource;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MailSearchController {

    private ThingList things;
    private Thing selectedThing;
    private ThingFileDataSource dataSourceThing;
    private ObservableList<Thing> thingObservableList;

    @FXML private TextField roomSearch;
    @FXML private Label searchLabel;
    @FXML private Button backBtn;
    @FXML private JFXButton searchBtn;
    @FXML private TableView<Thing> searchMailTable;



    @FXML
    public void initialize() {

        dataSourceThing = new ThingFileDataSource("data", "/things.csv");
        things = dataSourceThing.getThingsData();
        things.sortArrayList();

        showThingData();

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/second_page_officer.fxml"));
        stage.setScene(new Scene(loader.load(), 700, 500));

        stage.show();

    }

    private void showThingData() {
        thingObservableList = FXCollections.observableArrayList(things.toList());
        searchMailTable.setItems(thingObservableList);

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
            searchMailTable.getColumns().add(col2);
        }
    }

    @FXML public void handlesearchBtnOnAction(ActionEvent event) throws IOException {
        searchBtn = (JFXButton) event.getSource();
        Stage stage = (Stage) searchBtn.getScene().getWindow();

        String room = roomSearch.getText();

        searchMailTable.getItems().clear();
        searchMailTable.getColumns().clear();

        if(room.equals("")){
            searchLabel.setText("Please Fill in Name");
        }
        else{
            thingObservableList = FXCollections.observableArrayList(things.getMailFromRoom(room));
            searchMailTable.setItems(thingObservableList);

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

                TableColumn col3 = new TableColumn(conf.get("title"));
                col3.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
                searchMailTable.getColumns().add(col3);
            }

        }

    }
}
