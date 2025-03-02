package com.example.tictactpe;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

public class Scores implements Iterator<Object> {
    public Score[] scores;
    public HashSet<String> names = new HashSet<>();
    public int numOfEntries = 0;
    public int capacity = 32;
    public int scoreIncrement = 100;
    public int index;
    public int indexCrement = 1;
    public boolean iteratorDescending = false; //false ascending, true decsending

    public Scores() {
        scores = new Score[capacity];
    }

    public Scores(int size) {
        scores = new Score[size];
        capacity = size;
    }


    public void resetIndex() {
        if (!iteratorDescending) {
            index=-1;
            return;
        }
        index = numOfEntries-1;
    }
    
    @Override
    public boolean hasNext() {
        if (!iteratorDescending) {
            return (index+indexCrement) < numOfEntries+1;
        }
        return (index-indexCrement) > -1;
    }

    @Override
    public Score next() {
        if (!iteratorDescending) {
            index += indexCrement;
            return scores[index];
        } 
        index -= indexCrement;
        return scores[index];
    }

    public Integer addEntry(String name) {
        if (names.contains(name)) {
            return -1;
        }

        if (numOfEntries == scores.length) {
            scores = copy();
        }
        names.add(name);
        scores[numOfEntries] = new Score(name);
        numOfEntries++;
        return scoreIncrement;
    }

    public Integer addIfAbsent(String name) {
        if (names.contains(name)) {
            return updateScore(name);
        }

        return addEntry(name);
    }

    public Integer updateScore(String name) {
        Score s;
        Score updatedScore = null;
        Integer newScore = 0;
        for (int idx = numOfEntries-1; idx > -1; idx--) {
            s = scores[idx];
            if (s.name.equals(name)) {
                s.score += scoreIncrement;
                newScore = s.score;
                updatedScore = s;
                continue;
            }
            else if (updatedScore == null) {
                continue;
            }

            if (updatedScore.compareTo(s) != 1 ) {
                scores[idx+1] = updatedScore;
                break;
            }

            scores[idx+1] = scores[idx];
            if (idx == 0) {
                scores[idx] = updatedScore;
            }
        }
        return newScore;
    }

    public void sort() {
        for (int idx = 0; idx < numOfEntries; idx++) {
            Score s = scores[idx];
            
            if (s.compareTo(s) == 1) {

            }
        }
    }

    public Score[] copy() {
        capacity *= 2; 
        Score[] scores = new Score[capacity];
    
        for (int idx = 0; idx < numOfEntries; idx++) {
            scores[idx] = this.scores[idx];
        }
        return scores;
    }

    public class Score implements Entry<String, Integer>, Comparator<Score>, Comparable<Score>{
        public String name;
        public Integer score;

        public Score(String name, Integer score) {
            this.name = name;
            this.score = score;
        }
        public Score(String name) {
            this.name = name;
            score = scoreIncrement;
        }

        @Override
        public int compareTo(Score s) {
            if(this.name.equals(s.name)) return 0;
            return this.score.compareTo(s.score);
        }
        @Override
        public int compare(Score s1, Score s2) {
            return s1.compareTo(s2);
        }
        @Override
        public String getKey() {
            return name;
        }
        @Override
        public Integer getValue() {
            return score;
        }
        @Override
        public Integer setValue(Integer arg0) {
            Integer temp = score;
            score = arg0;
            return temp;
        }

    }
}
