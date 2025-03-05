package com.example.tictactpe;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HelloController implements Initializable {

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
    private Text outcomeText;
    @FXML
    private TextField playerXName; 
    @FXML
    private TextField playerOName; 
    @FXML
    private VBox scoreBoard;

    private boolean gameFinish;

    private ScoreBoard sb = new ScoreBoard(20);

    private int playerTurn = 0;
    ArrayList<Button> buttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });

        sb.scoreBoardContainer = scoreBoard;
        sb.updateScoreBoard("Test1"); 
        sb.updateScoreBoard("Test2"); 
        sb.updateScoreBoard("Test3"); 
        sb.updateScoreBoard("Test4"); 
        sb.updateScoreBoard("Test5"); 
        sb.updateScoreBoard("Test6"); 

        sb.updateScoreBoard("Test3"); 
        sb.updateScoreBoard("Test3"); 
        sb.updateScoreBoard("Test3"); 

        sb.updateScoreBoard("Test6"); 
        sb.updateScoreBoard("Test6"); 
        sb.updateScoreBoard("Test6"); 
        sb.updateScoreBoard("Test6"); 
        sb.updateScoreBoard("Test6"); 
        sb.updateScoreBoard("Test6"); 
        sb.updateScoreBoard("Test6"); 
        
        sb.updateScoreboardLabels(false); 
    }

    @FXML
    void clickStart(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
    }

    @FXML
    void restartGame(ActionEvent event) {
        outcomeText.setText("Tic Tac Toe!");
        gameFinish = false;  // Reset game state first
        playerTurn = 0;

        buttons.forEach(button -> {
            button.setDisable(false);  // Enable all buttons
            button.setText("");        // Clear text
            button.setStyle(button.getStyle().replaceAll("; -fx-text-fill: #[0-9A-F]{6}", ""));
            button.getStyleClass().removeAll("x-mark", "o-mark");
        });
    }

    @FXML
    void playerXChange() {
        return;
    }
    
    @FXML
    void playerOChange() {
        return;
    }

    public void setupButton(Button button) {
        button.setOnMouseClicked(null);
        button.setOnMouseClicked(mouseEvent -> {
            if (!gameFinish && button.getText().isEmpty()) {  //  empty text check
                setPlayerSymbol(button);
                button.setDisable(true);
                checkGame();
            }
        });

    }

    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
            outcomeText.setText("O turn!");
            button.setText("X");
            button.setStyle(button.getStyle() + "; -fx-text-fill: #0000FF;");  // Blue for X
            button.getStyleClass().add("x-mark");
            playerTurn = 1;
        } else {
            outcomeText.setText("X turn!");
            button.setText("O");
            button.setStyle(button.getStyle() + "; -fx-text-fill: #FF0000;");  // Red for O
            button.getStyleClass().add("o-mark");
            playerTurn = 0;
            
        }

    }

    public void checkGame() {
        if (gameFinish) {
            return;
        }

        for (int a = 0; a < 8; a++) {
            String line = switch (a) {

                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button4.getText() + button7.getText();
                case 4 -> button2.getText() + button5.getText() + button8.getText();
                case 5 -> button3.getText() + button6.getText() + button9.getText();
                case 6 -> button1.getText() + button5.getText() + button9.getText();
                case 7 -> button3.getText() + button5.getText() + button7.getText();

                default -> null;
            };

            if (line.equals("XXX")) {
                outcomeText.setText("X wins!");
                sb.updateScoreBoard(playerXName.getText());
                sb.updateScoreboardLabels(false);
                gameFinish = true;
                buttons.forEach(button -> button.setDisable(true));
            } else if (line.equals("OOO")) {
                outcomeText.setText("O wins!");
                sb.updateScoreBoard(playerOName.getText());
                sb.updateScoreboardLabels(false);
                gameFinish = true;
                buttons.forEach(button -> button.setDisable(true));
            }

        }

        boolean draw = true;
        for (Button button : buttons) {
            String text = button.getText();
            System.out.println("Button text: '" + text + "'");  // Add debug print
            if (text.isEmpty() || text.equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {
            outcomeText.setText("Draw!");
            gameFinish = true;
            buttons.forEach(button -> button.setDisable(true));
        }

    }

}

