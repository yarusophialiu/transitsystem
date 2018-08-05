package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TransitApp extends Application{

    private AdminUser admin = new AdminUser("adminuser", "adminuser@mail.com", "admin123");


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../controller/login.fxml"));
        primaryStage.setTitle("Presto System App");
        primaryStage.setScene(new Scene(root, 800, 600 ));
        primaryStage.setResizable(false);
        Card.setAdminUser(admin);
        primaryStage.show();

        BufferedReader fileReader =
        new BufferedReader(
            new FileReader(
                "C:\\留学的东西\\2018 Summer\\CSC207\\project phase1\\group_0165\\phase2\\stations.txt"));
        String vehicle = fileReader.readLine();
        String info = fileReader.readLine();
        StationFactory stationFactory = new StationFactory();
        while (!(info == null)) {
            switch (info) {
                case "":
                    info = fileReader.readLine();
                    break;
                case "bus":
                    vehicle = "bus";
                    info = fileReader.readLine();
                    break;
                default:
                    ArrayList<String> dataArray = new ArrayList<>(Arrays.asList(info.split(" ")));
                    switch (vehicle){
                        case "subway":
                            Station subwayStation = stationFactory.newStation(dataArray.get(1), vehicle, "1");
                            for (int i = 3; i < dataArray.size(); i++) {
                                Station newNeighbour = stationFactory.newStation(dataArray.get(i), vehicle, "1");
                                ((SubwayStation)subwayStation).addNeighbours((SubwayStation) newNeighbour);
                            }
                            info = fileReader.readLine();
                            break;
                        case "bus":
                            Station busStation = stationFactory.newStation(dataArray.get(1), vehicle, dataArray.get(0));
                            for (int i = 3; i < dataArray.size(); i++){
                                Station newNeighbour = stationFactory.newStation(dataArray.get(i), vehicle, dataArray.get(0));
                                ((BusStation) busStation).addNeighbours((BusStation) newNeighbour);
                            }
                            info = fileReader.readLine();
                            break;
                    }
                    break;
            }
        }
    }


  public static void main(String[] args) {
        launch(args);
  }

}
