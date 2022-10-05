package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoreDTO {
    HashMap<String, Integer> scores;

    public ScoreDTO() {
        scores = new HashMap<>();
    }

    public ScoreDTO(HashMap<String, Integer> scores) {
        this.scores = scores;
    }

    public HashMap<String, Integer> getValue() {
        return scores;
    }

    public void setScores(HashMap<String, Integer> scores) {

        this.scores = scores;
    }

    @Override
    public String toString() {
        return "ScoreDTO{" +
                "scores=" + scores +
                '}';
    }
}
