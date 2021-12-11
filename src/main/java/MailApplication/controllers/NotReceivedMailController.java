package MailApplication.controllers;

import MailApplication.models.Thing;
import MailApplication.models.ThingList;
import MailApplication.services.StringConfiguration;
import MailApplication.services.ThingFileDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class NotReceivedMailController {
    private ThingList things;
    private Thing selectedThing;
    private ThingFileDataSource dataSourceThing;
    private ObservableList<Thing> thingObservableList;

    @FXML private Button backBtn;
    @FXML private TableView<Thing> notReceivedMailTable;



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
        thingObservableList = FXCollections.observableArrayList(things.getNotReceivedThing());
        notReceivedMailTable.setItems(thingObservableList);

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
            notReceivedMailTable.getColumns().add(col2);
        }
    }
}
