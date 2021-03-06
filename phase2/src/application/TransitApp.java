package application;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TransitApp extends Application{


    private void vehicleCase(ArrayList<String> dataArray,  StationFactory stationFactory, String vehicle, int index) {
        if (index == 1) {
            Station subwayStation = stationFactory.newStation(dataArray.get(index), vehicle, "1");
            for (int i = 3; i < dataArray.size(); i++) {
                Station newNeighbour = stationFactory.newStation(dataArray.get(i), vehicle, "1");
                ((SubwayStation)subwayStation).addNeighbours((SubwayStation) newNeighbour);
            }
        }

        if (index == 0) {
            Station busStation = stationFactory.newStation(dataArray.get(index), vehicle, "1");
            for (int i = 3; i < dataArray.size(); i++) {
                Station newNeighbour = stationFactory.newStation(dataArray.get(i), vehicle, "1");
                ((BusStation)busStation).addNeighbours((BusStation) newNeighbour);
            }
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        HelpSerialize helpSerialize = new HelpSerialize();
        BufferedReader tryToRead = new BufferedReader(new FileReader("phase2/serializedUser.ser"));
        if (! (tryToRead.readLine() == null)) {
            HashMap<String, User> users = helpSerialize.deserializeUser();
            User.setUsers(users);
            Card.setAdminUser((AdminUser) users.get("adminuser@mail.com"));
        }
        else{
            AdminUser admin = new AdminUser("adminuser", "adminuser@mail.com", "admin123");
            Card.setAdminUser(admin);
        }
        Parent root = FXMLLoader.load(getClass().getResource("../controller/login.fxml"));
        primaryStage.setTitle("Presto System App");
        primaryStage.setScene(new Scene(root, 800, 500 ));
        primaryStage.setResizable(false);
        primaryStage.show();

        BufferedReader fileReader =
        new BufferedReader(
            new FileReader(
                "phase2/stations.txt"));
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
                            vehicleCase(dataArray, stationFactory, vehicle, 1);
                            info = fileReader.readLine();
                            break;
                        case "bus":
                            vehicleCase(dataArray, stationFactory, vehicle, 0);
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
