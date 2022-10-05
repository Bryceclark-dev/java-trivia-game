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
    static ScoreDTO Highscore = new ScoreDTO();
    static File scoreFile = new File("HighScores.txt");
    public static HashMap<String, Integer> readHighScore() {
        Map<String, Integer> scoreMap = new HashMap<String, Integer>();


        try {
            Highscore = mapper.readValue(scoreFile, ScoreDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Highscore.scores;
    }




    public static void checkHighScore(int points) throws IOException {

        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> highScore = readHighScore();
        HashMap<String, Integer> tempHighScore = new LinkedHashMap<>();
        
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
        Highscore.setScores(highScore);
        //mapper.writeValue(scoreFile, Highscore);
        System.out.println(highScore);
        updateHighScore(highScore);

    }
    public static void updateHighScore(HashMap<String, Integer> score) throws IOException {
        Path filePath = Path.of("HighScores.txt");
        mapper.writeValue(scoreFile, score);

//        try {
//            mapper.writeValue(filePath, score);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static String viewHighScore(){
        Path filePath = Path.of("HighScores.txt");
        String textH;
        try {
            textH = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        textH = textH.substring(1, textH.length()-1);
        String[] arr = textH.split(",");
        StringBuilder ss = new StringBuilder();
        int count = 1;
        for(String h : arr){
            ss.append(count+". ").append(h.trim().replace("=", " - "));
            ss.append("\n");
            count++;
        }
        return ss.toString();
    }

}
