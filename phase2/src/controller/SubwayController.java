package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Card;
import model.Station;
import model.StationFactory;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SubwayController extends Controller implements Initializable, SelectStation {

    private Card card;

    private Stage previousStage;

    @FXML
    private CheckBox stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,
            stop10,stop11;

    private ArrayList<CheckBox> line1 = new ArrayList<>();

    private ArrayList<CheckBox> line2 = new ArrayList<>();

    private HashMap<CheckBox, String> boxToString = new HashMap<>();

    private CardController cardController;

    ArrayList<CheckBox> selected = new ArrayList<>();

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    public void setCard(Card card){
        this.card = card;
    }

    public void goBackPage(javafx.event.ActionEvent event){
        Stage subwayController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        subwayController.close();
    }

    public void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }

    public void initialize(URL location, ResourceBundle resources) {
        line1.add(stop1);
        line1.add(stop2);
        line1.add(stop3);
        line1.add(stop4);
        line1.add(stop5);
        line1.add(stop6);

        line2.add(stop7);
        line2.add(stop8);
        line2.add(stop9);
        line2.add(stop4);
        line2.add(stop10);
        line2.add(stop11);

        boxToString.put(stop1, "Bloor-Yonge");
        boxToString.put(stop2, "Wellesley");
        boxToString.put(stop3, "College");
        boxToString.put(stop4, "Dundus");
        boxToString.put(stop5, "Queen");
        boxToString.put(stop6, "King");
        boxToString.put(stop7, "Terminal165");
        boxToString.put(stop8, "Yaru");
        boxToString.put(stop9, "Yuxin");
        boxToString.put(stop10, "Yunfan");
        boxToString.put(stop11, "Xiaolei");
    }

    @FXML
    public void selectBox(javafx.event.ActionEvent event){
        ArrayList<CheckBox> newSelected = new ArrayList<>();
        for (CheckBox checkBox: boxToString.keySet()){
            if (checkBox.isSelected()){
                newSelected.add(checkBox);
            }
        }
        for (CheckBox checkBox: newSelected){
            if (!selected.contains(checkBox)){
                selected.add(checkBox);
            }
        }
        if (selected.size() > newSelected.size()){
            selected = newSelected;
        }
        if (selected.size() == 0 | selected.size() == 1){
            for (CheckBox cb: boxToString.keySet()){
                cb.setDisable(false);
            }
        }
        if (selected.size() == 2){
            for (CheckBox cb: boxToString.keySet()){
                if (!cb.isSelected()){
                    cb.setDisable(true);
                }
            }
        }
    }

    public void confirmTrip() throws ParseException {
        StationFactory stationFactory = new StationFactory();
        String start = boxToString.get(selected.get(0));
        String end = boxToString.get(selected.get(1));
        String line;
        if (line1.contains(selected.get(0)) & line1.contains(selected.get(1))){
            line = "1";
        }else{
            line = "2";
        }

        Station startStation = stationFactory.newStation(start, "subway", line);
        Station endStation = stationFactory.newStation(end, "subway", line);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = df.parse(startTime.getText() + ":00");
        Date endDate = df.parse(endTime.getText() + ":00");

        card.updateOnTap("enters", startStation, startDate,
                "subway", stationFactory);
        card.updateOnTap("exits", endStation, endDate,
                "subway", stationFactory);
        for (CheckBox cb: boxToString.keySet()){
            cb.setDisable(false);
            cb.setSelected(false);
        }
        selected.clear();
        startTime.clear();
        endTime.clear();
        cardController.helpShowBalance(card.getBalance());
    }

    void setPreviousController(CardController cardController){
        this.cardController = cardController;
    }

}
