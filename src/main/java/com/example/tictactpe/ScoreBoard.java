package com.example.tictactpe;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ScoreBoard {
    public int max;
    public String scoreTextFormat = "%s: %d";
    public TreeMap<String, Integer> scoreBoard = new TreeMap<>(); 
    public Pane scoreBoardContainer;
    public String playerX = "";
    public String playerO = "";
    public int playerXScore = 0;
    public int playerOScore = 0;
    //public Entry<String, Integer>[] scoreBoard = new Entry[5]; // 0 Index is the highest score while last index is the lowest

    public ScoreBoard(int max) {
        //scoreBoard = new TreeSet<>(Entry.comparingByValue()); 
        this.max = max;
    }

    /*
     * ol is children of a node
     * direction is should be sorted descending or ascending
     *  - false ascending
     *  - true descending 
     */
    public void updateScoreboardLabels(boolean direction) {
        int idx = 0;
        Entry<String, Integer> scoreEntry;
        Iterator<Entry<String, Integer>> scoreEntries;
        ObservableList<Node> ol = scoreBoardContainer.getChildren();
        Iterator<Node> labels = ol.iterator();
        Label lbl;

        if (direction) {
            scoreEntries = scoreBoard.descendingMap().entrySet().iterator();
        }
        else {
            scoreEntries = scoreBoard.entrySet().iterator();
        }
        
        for (Iterator<Entry<String, Integer>> it = scoreEntries; it.hasNext();) {
            if (idx > max) {
                return;
            } 

            scoreEntry = it.next();
            if (labels.hasNext()) {

                lbl = (Label) labels.next();
                lbl.setText(String.format(scoreTextFormat, scoreEntry.getKey(), scoreEntry.getValue()));
                continue;
            }

            lbl = new Label(String.format(scoreTextFormat, scoreEntry.getKey(), scoreEntry.getValue()));
            lbl.prefWidth(100);
            ol.add(lbl);     
        }
        /*
        scoreBoard.forEach((score) -> {
            Label lbl = new Label(String.format(scoreTextFormat, score.getKey(), score.getValue()));
            lbl.prefWidth(100);
            ol.add(lbl);     
        });        
        */
    }

    protected void updateScoreBoard(final String player) {
        int score = 100;
        // this prevents duplicate player names as the tree map allows duplicate values
        if (scoreBoard.containsKey(player)) {
            score = scoreBoard.entrySet().stream().filter(s -> 
                s.getKey().equals(player)
            ).findAny().map(Map.Entry::getValue).orElse(100);
            scoreBoard.remove(player, score);
            score += 100;
        }
        scoreBoard.put(player, score);
    }


    // Need a way to store information in a file, maybe json

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
