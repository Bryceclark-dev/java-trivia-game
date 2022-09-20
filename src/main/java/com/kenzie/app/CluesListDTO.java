package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CluesListDTO {
    @JsonProperty("clues")
    List<CluesDTO> clues;

    public List<CluesDTO> getClues() {
        return clues;
    }
    public void setClues(List<CluesDTO> clues) {
        this.clues = clues;
    }

    @Override
    public String toString() {
        return "CluesListDTO{" +
                "clues=" + clues.toString() +
                '}';
    }
}
