package com.example.javafxtictactoe;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

//X- 1 O- 5
//Win: X=3; O=15
//-1 - Tie
//-2 - error
public class Controller {

    static ArrayList<Button> buttonArray = new ArrayList<Button>();
    private int playerTurn = 0;
    private int xCounter;
    private int oCounter;
    private int attemptCounter;
    private int count;

    @FXML
    private Text xWins;
    @FXML
    private Text oWins;
    @FXML
    private Text attempts;


    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button RestartButton;
    @FXML
    private Text whoWin;

    @FXML
    void initialize() {
        assert button1 != null : "fx:id=\"button1\" was not injected: check your FXML file 'Untitled'.";
        assert button2 != null : "fx:id=\"button2\" was not injected: check your FXML file 'Untitled'.";
        assert button3 != null : "fx:id=\"button3\" was not injected: check your FXML file 'Untitled'.";
        assert button4 != null : "fx:id=\"button4\" was not injected: check your FXML file 'Untitled'.";
        assert button5 != null : "fx:id=\"button5\" was not injected: check your FXML file 'Untitled'.";
        assert button6 != null : "fx:id=\"button6\" was not injected: check your FXML file 'Untitled'.";
        assert button7 != null : "fx:id=\"button7\" was not injected: check your FXML file 'Untitled'.";
        assert button8 != null : "fx:id=\"button8\" was not injected: check your FXML file 'Untitled'.";
        assert button9 != null : "fx:id=\"button9\" was not injected: check your FXML file 'Untitled'.";
        assert RestartButton != null : "fx:id=\"restartButton\" was not injected: check your FXML file 'Untitled'.";


        initButton();
        checkTurn();
    }


    @FXML
    private void buttonClicked(ActionEvent event) {

        //Одним методом проверяем все кнопки
        Button clickedButton = (Button) event.getSource();

        System.out.println("Id: " + clickedButton.getId());


        //Рисуем X или O
        clickedButton.setText(checkTurn());
        if (checkTurn().equals("X"))
            clickedButton.setUserData(1);
        else if (checkTurn().equals("O"))
            clickedButton.setUserData(5);
        playerTurn++;
        checkTurn();


        //Если победа - блокируем кнопки
        if (checkEnd())
            for (Button but : buttonArray)
                but.setDisable(true);

        //Блокируем кнопку
        clickedButton.setDisable(true);
        count++;
        if (count == 9) {
            whoWin.setText("Tie!");
            attemptCounter++;
            attempts.setText(Integer.toString(attemptCounter));
        }
    }

    @FXML
    private void restartButton() {
        count = 0;
        playerTurn = 0;
        initButton();
    }

    private void initButton() {
        buttonArray.add(button1);
        buttonArray.add(button2);
        buttonArray.add(button3);
        buttonArray.add(button4);
        buttonArray.add(button5);
        buttonArray.add(button6);
        buttonArray.add(button7);
        buttonArray.add(button8);
        buttonArray.add(button9);

        for (Button but : buttonArray) {
            but.setDisable(false);
            but.setUserData(0);
            but.setText("");
        }
        checkTurn();
    }

    private boolean checkEnd() {
        for (int i = 0; i < 8; i++) {

            int num = switch (i) {
                case 0 -> (int) button1.getUserData() + (int) button2.getUserData() + (int) button3.getUserData();
                case 1 -> (int) button4.getUserData() + (int) button5.getUserData() + (int) button6.getUserData();
                case 2 -> (int) button7.getUserData() + (int) button8.getUserData() + (int) button9.getUserData();
                case 3 -> (int) button1.getUserData() + (int) button4.getUserData() + (int) button7.getUserData();
                case 4 -> (int) button2.getUserData() + (int) button5.getUserData() + (int) button8.getUserData();
                case 5 -> (int) button3.getUserData() + (int) button6.getUserData() + (int) button9.getUserData();
                case 6 -> (int) button1.getUserData() + (int) button5.getUserData() + (int) button9.getUserData();
                case 7 -> (int) button3.getUserData() + (int) button5.getUserData() + (int) button7.getUserData();
                default -> -2;
            };

            if (num == 3) {
                whoWin.setText("X win!");
                xCounter++;
                attemptCounter++;
                xWins.setText(Integer.toString(xCounter));
                attempts.setText(Integer.toString(attemptCounter));
                return true;
            } else if (num == 15) {
                whoWin.setText("O win!");
                oCounter++;
                attemptCounter++;
                oWins.setText(Integer.toString(oCounter));
                attempts.setText(Integer.toString(attemptCounter));
                return true;
            }
        }
        return false;
    }

    private String checkTurn() {
        if (playerTurn % 2 == 0) {
            whoWin.setText("X turn");
            return "X";
        } else {
            whoWin.setText("O turn");
            return "O";
        }
    }
}