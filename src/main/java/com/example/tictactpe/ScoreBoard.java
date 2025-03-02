package com.example.tictactpe;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.tictactpe.Scores.Score;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ScoreBoard {
    public int max;
    public String scoreTextFormat = "%s: %d";
    public Pane scoreBoardContainer;
    public Scores scores = new Scores();

    public ScoreBoard(int max) {
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
        ObservableList<Node> ol = scoreBoardContainer.getChildren();
        ArrayList<Node> al = new ArrayList<>();
        Iterator<Node> labels = ol.iterator();
        Label lbl;

        scores.iteratorDescending = direction;
        scores.resetIndex();
        System.out.println("index: " + scores.index);
        for (Score s = scores.next(); scores.hasNext(); s = scores.next()) {
            System.out.println("index: " + scores.index);
            if (idx > max) {
                return;
            } 

            if (labels.hasNext()) {
                lbl = (Label) labels.next();
                lbl.setText(String.format(scoreTextFormat, s.name, s.score));
                idx++;
                continue;
            }

            lbl = new Label(String.format(scoreTextFormat, s.name, s.score));
            lbl.prefWidth(100);
            lbl.getStyleClass().add("score-lbl");
            al.add(lbl);     
            idx++;
        }

        ol.addAll(al);
    }

    

    protected void updateScoreBoard(final String player) {
        scores.addIfAbsent(player);
    }

    // Need a way to store information in a file, maybe json
}
