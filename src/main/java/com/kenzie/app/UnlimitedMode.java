package com.kenzie.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;
import java.util.Scanner;



public class UnlimitedMode {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static ObjectMapper objectMapper = new ObjectMapper();

    public static int unlimitedQuiz(String url) throws JsonProcessingException {
        int points = 0;
        String answer;
        String clueURL;
        String httpResponse;
        CluesDTO clue;
        int num;
        while(true){
            StringBuilder questionCategory = new StringBuilder();
            num = rand.nextInt(355237) + 1;
            clueURL = url+num;
            httpResponse = CustomHttpClient.sendGET(clueURL);
            clue = objectMapper.readValue(httpResponse, CluesDTO.class);
            questionCategory.append("Category: " + clue.getCategory().getTitle()).append("\n").append("Question: " + clue.getQuestion());
            answer = scan.nextLine();
            if(answer.equalsIgnoreCase("stop")||answer.equalsIgnoreCase("end")){
                break;
            }
            if(answer.equals("") || answer.equals(" ")) {
                while(answer.equals("") || answer.equals(" ")){
                    System.out.println("Invalid Input: Please Try again");
                    answer = scan.nextLine();
                }
            }

            if(clue.getAnswer().equalsIgnoreCase(answer)){
                points++;
                System.out.println("Correct");
                System.out.println("Total Points: " + points);
            }else{
                System.out.println("Incorrect: \nThe correct answer is - " + clue.getAnswer());
            }
        }
        return points;
    }
}
