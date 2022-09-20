package com.kenzie.app;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class HighScore {
/*Need to finish highscore class*/

//public static HashMap<String, Integer> readHighScore(){
//    Path filePath = Path.of("HighScores.txt");
//
//    Map<String, String> map = new HashMap<String, String>();
//    BufferedReader br = null;
//
//    try {
//        File file = new File(filePath);
//
//        br = new BufferedReader(new FileReader(file));
//
//        String line = null;
//
//        while ((line = br.readLine()) != null) {
//            String[] parts = line.split(":");
//            String name = parts[0].trim();
//            String number = parts[1].trim();
//            if (!name.equals("") && !number.equals(""))
//                map.put(name, number);
//        }
//    }
//    catch (Exception e) {
//        e.printStackTrace();
//    }
//    finally {
//
//        // Always close the BufferedReader
//        if (br != null) {
//            try {
//                br.close();
//            }
//            catch (Exception e) {
//            };
//        }
//    }

    public static void checkHighScore(int points){
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> highScore = new LinkedHashMap<>();
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
        updateHighScore(highScore);
    }
    public static void updateHighScore(HashMap<String, Integer> highScore){
        Path filePath = Path.of("HighScores.txt");
        try {
            Files.writeString(filePath, highScore.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
