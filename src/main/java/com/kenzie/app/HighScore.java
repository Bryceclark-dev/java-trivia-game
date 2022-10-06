package com.kenzie.app;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class HighScore {
    /*Need to finish highscore class*/
    static ObjectMapper mapper = new ObjectMapper();

    static File scoreFile = new File("HighScores.txt");
    public static LinkedHashMap<String, Integer> readHighScore() {
        LinkedHashMap<String, Integer> scoreMap;
        TypeReference<LinkedHashMap<String, Integer>> tyy = new TypeReference<LinkedHashMap<String, Integer>>() {
        };

        try {
            scoreMap = mapper.readValue(scoreFile, tyy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return scoreMap;
    }




    public static void checkHighScore(int points) throws IOException {

        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, Integer> highScore = readHighScore();
        LinkedHashMap<String, Integer> tempHighScore = new LinkedHashMap<>();

        String winner = "";
        int count = 0;

        for(Map.Entry<String, Integer> score : highScore.entrySet()){
            if (points > score.getValue()){
                System.out.println("New High Score!!! Enter three letters for your initials");
                winner = scan.nextLine();
                break;
            }
            count++;
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(highScore.entrySet());

        int tempCount = 0;
        for(int i = 0; i < entries.size(); i++){
            if(i == count){
                tempHighScore.put(winner, points);

            }else{
                tempHighScore.put(entries.get(tempCount).getKey(), entries.get(tempCount).getValue());
                tempCount++;
            }
        }
        highScore = tempHighScore;

        updateHighScore(highScore);

    }
    public static void updateHighScore(LinkedHashMap<String, Integer> score){
        try {
            mapper.writeValue(scoreFile, score);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String viewHighScore(){
        Path filePath = Path.of("HighScores.txt");
        LinkedHashMap<String, Integer> scoreMap = new LinkedHashMap<>();
        TypeReference<LinkedHashMap<String, Integer>> type = new TypeReference<LinkedHashMap<String, Integer>>() {
        };
        try {
            scoreMap = mapper.readValue(scoreFile, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder display = new StringBuilder();
        display.append("HIGH SCORES!!!\n");
        for(Map.Entry<String, Integer> entry : scoreMap.entrySet()){
            display.append(entry.getKey()+"\n"+entry.getValue()+"\n");
        }

        return display.toString();
    }

}
