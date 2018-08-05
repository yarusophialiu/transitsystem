package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import model.AdminUser;

public class TransitApp extends Application{

    private static AdminUser admin = new AdminUser("adminuser", "adminuser@mail.com", "admin123");


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../controller/login.fxml"));
        primaryStage.setTitle("Presto System App");
        primaryStage.setScene(new Scene(root, 800, 500 ));
        primaryStage.show();
    }

    public static AdminUser getAdmin() {
        return admin;
    }

  public static void main(String[] args) {
        launch(args);
  }
}
