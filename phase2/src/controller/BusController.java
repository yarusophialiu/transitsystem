package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import model.Card;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BusController extends Controller implements Initializable{

    @FXML
    private CheckBox stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,
            stop10,stop11,stop12,stop13,stop14;

    private ArrayList<CheckBox> line1 = new ArrayList<>();

    private ArrayList<CheckBox> line2 = new ArrayList<>();

    private HashMap<CheckBox, String> boxToString = new HashMap<>();

    private Card card;

    public void setCard(Card card){
        this.card = card;
    }


    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "cardController.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        line1.add(stop1);
        line1.add(stop2);
        line1.add(stop3);
        line1.add(stop4);
        line1.add(stop5);
        line1.add(stop6);
        line1.add(stop7);

        line2.add(stop8);
        line2.add(stop9);
        line2.add(stop3);
        line2.add(stop10);
        line2.add(stop11);
        line2.add(stop12);
        line2.add(stop13);
        line2.add(stop6);
        line2.add(stop14);

        boxToString.put(stop1, "Bloor-Yonge");
        boxToString.put(stop2, "Queens_Park");
        boxToString.put(stop3, "College");
        boxToString.put(stop4, "Museum");
        boxToString.put(stop5, "Queen");
        boxToString.put(stop6, "King");
        boxToString.put(stop7, "Union_Station");
        boxToString.put(stop8, "Yuxin");
        boxToString.put(stop9, "Yunfan");
        boxToString.put(stop10, "Xiaolei");
        boxToString.put(stop11, "Yaru");
        boxToString.put(stop12, "Frank");
        boxToString.put(stop13, "Sophia");
        boxToString.put(stop14, "Steven");
    }


}
