package MailApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainProgram extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/home_page_main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Mail Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getScene().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
