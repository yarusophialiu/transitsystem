package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Card;
import model.RegularUser;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dashboard extends Controller implements Initializable{
    private User user;
    private ArrayList<Card> cards;

    @FXML
    private TextField newUsername;

    @FXML
    private Label userName = new Label();

    @FXML
    private HBox hbox;


    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    void setUser(User newUser){
         this.user = newUser;
        this.userName.setText(user.getUserName());
        this.cards = ((RegularUser) user).getMyCard();
    }


    @FXML
    void addCard(javafx.event.ActionEvent event) throws IOException {
        Button button = new Button();
        int id = ((RegularUser)user).buyCard();
        button.setText(Integer.toString(id));
        hbox.getChildren().add(button);
    }

    @FXML
    void changeUsername(javafx.event.ActionEvent event) throws IOException {
        //find user
        HashMap<String, User> users = User.getUsers();
        for (User passenger: users.values()) {
            if (user.getUserName().equals(passenger.getUserName())) {
                //change name
                user.changeName(newUsername.getText());
                break;
            }
        }
        AlertBox alertBox= new AlertBox();
        alertBox.alertMessage("Successfully change username");

    }


    public void loadCard() {
        for (Card card : cards) {
            Button button = new Button(Integer.toString(card.getId()));
            button.setText(Integer.toString(card.getId()));
            hbox.getChildren().add(button);
            button.setOnAction(event -> {
                try {
                    helper(card);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void helper(Card card) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardController.fxml"));
        Parent root = loader.load();
        CardController cardController = loader.getController();
        cardController.setCard(card);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }
}
