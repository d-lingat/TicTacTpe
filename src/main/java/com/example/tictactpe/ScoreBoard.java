package com.example.tictactpe;

import java.util.Map.Entry;
import java.util.TreeSet;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.Node;

public class ScoreBoard {
    public int numOfEntries;
    public int max;
    public String scoreTextFormat = "%s: %d";
    public TreeSet<Entry<String, Integer>> scoreBoard = new TreeSet<>(Entry.comparingByValue()); 
    //public Entry<String, Integer>[] scoreBoard = new Entry[5]; // 0 Index is the highest score while last index is the lowest

    public ScoreBoard() {
        scoreBoard = new TreeSet<>(Entry.comparingByValue()); 
    }

    protected void getScoreboardLabels(ObservableList<Node> ol) {
        scoreBoard.foreach((score) -> {
            Label lbl = new Label(String.format(scoreTextFormat, score.getKey(), score.getValue()));
            lbl.prefWidth(100);
            ol.add(lbl);     
        });        
    }

    protected void addToScoreBoard(Entry<String, Integer> newScore) {
        scoreBoard.add(newScore);
    }

    /* I at first wanted to use a normal array but I figured it is easier to use java.util package 
    protected void addToScoreBoard(Entry<String, Integer> newScore) {
        // do nothing if the lowest score in scoreBoard is greater than newScore
        if (scoreBoard[numOfEntries-1].getValue() > newScore.getValue()) {
            return;
        }

        // add to scoreBoard if empty
        if (numOfEntries == 0) {
            scoreBoard[numOfEntries] = newScore;
            numOfEntries++;
            return;
        }

        if (numOfEntries < scoreBoard.length) {
            numOfEntries++;
        }

        // Algorithm to add to scoreBoard        
        Entry<String, Integer> nextLowestScore = null; // = Map.entry("error", 999);
        Entry<String, Integer> tmp;
        for (int idx = 0; idx < numOfEntries; idx++) {
            // Continue if newScore is less than scoreBoard[idx] as we don't need to shift values
            if (scoreBoard[idx].getValue() > newScore.getValue()) {
                continue;
            }
            //  This checks if newScore has been inserted yet, if it has not then it will be inserted 
            else if (nextLowestScore == null) {
                    nextLowestScore = scoreBoard[idx];
                    scoreBoard[idx] = newScore;
                    continue;
            }

            //
            tmp = scoreBoard[idx];
            scoreBoard[idx] = nextLowestScore;
            nextLowestScore = tmp;
        }
    }
    */
}
