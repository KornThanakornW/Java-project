package MailApplication.controllers;

        import MailApplication.models.*;
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
        import javafx.scene.control.Button;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.util.ArrayList;

public class FirstPageResidentController {

    private UserAccountList users3;
    private UserAccFileDataSource dataSource3;
    private ThingList things;
    private ThingFileDataSource dataSourceThing;
    private ObservableList<Thing> thingObservableList;
    private UserAccountOfficer selectedUser;

    @FXML private TableView<Thing> thingTable;
    @FXML private TextField yourUsernameTextField;
    @FXML private TextField oldpasswordTextField;
    @FXML private TextField newpasswordTextField;
    @FXML private JFXButton changeBtn;


    @FXML
    public void initialize() {

        dataSource3 = new UserAccFileDataSource("data", "/accountResident.csv");
        users3 = dataSource3.getUsersData();

        dataSourceThing = new ThingFileDataSource("data", "/things.csv");
        things = dataSourceThing.getThingsData();
        things.sortArrayList();

        System.out.println(users3.getCurrentAccount());

        showThingData();

    }

    private void showThingData() {
        thingObservableList = FXCollections.observableArrayList(things.toList());
        thingTable.setItems(thingObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room", "field:room"));
        configs.add(new StringConfiguration("title:Type Mail", "field:typeThing"));
        configs.add(new StringConfiguration("title:Status", "field:statusMail"));
        configs.add(new StringConfiguration("title:Time receive By officer", "field:senderTime"));
        configs.add(new StringConfiguration("title:Receiver Officer Name", "field:receiverOfficerName"));
        configs.add(new StringConfiguration("title:Receiver Name", "field:receiverName"));
        configs.add(new StringConfiguration("title:Time Received By Owner", "field:receiveTime"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Service Company", "field:serviceCompany"));
        configs.add(new StringConfiguration("title:Tracking Number", "field:trackingNumber"));


        for (StringConfiguration conf: configs) {

            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            thingTable.getColumns().add(col);
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
    public void handleChangePasswordBtnOnAction(ActionEvent event) {
        String inputYourUsername = yourUsernameTextField.getText();
        String inputOldPassword = oldpasswordTextField.getText();
        String inputNewPassword = newpasswordTextField.getText();


        UserAccount searchAccount = users3.searchAccount(inputYourUsername,inputOldPassword);
        searchAccount.setPassword(inputNewPassword);

        dataSource3.setUsersData(users3);
        yourUsernameTextField.clear();
        oldpasswordTextField.clear();
        newpasswordTextField.clear();
    }

    public void setUserAccountList(UserAccountList userAccountlist) {
        this.users3 = userAccountlist;
    }
}

