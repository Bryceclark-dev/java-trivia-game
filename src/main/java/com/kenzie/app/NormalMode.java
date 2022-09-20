package com.kenzie.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
public class NormalMode {
    static ObjectMapper objectmapper = new ObjectMapper();
    static Scanner scan = new Scanner(System.in);

    public static List<CluesDTO> getList(String url) throws JsonProcessingException {
        int num = 0;
        List<CluesDTO> list = new ArrayList<>();
        Random rand = new Random();
        String clueURL;
        String httpResponse;

        CluesDTO clue;
        for(int i = 0; i < 10; i++){
            num = rand.nextInt(355237) + 1;
            clueURL = url+num;
            httpResponse = CustomHttpClient.sendGET(clueURL);
            clue = objectmapper.readValue(httpResponse, CluesDTO.class);
            list.add(clue);
        }

       return list;
    }
    public static int normalQuiz(List<CluesDTO> list){
        int points = 0;
        String answer;
        for(int i =0; i < list.size(); i++){
            System.out.println("Category: " + list.get(i).getCategory().getTitle());
            System.out.println("Question: " + list.get(i).getQuestion());
            answer = scan.nextLine();
            if(answer.equals("") || answer.equals(" ")) {
                while(answer.equals("") || answer.equals(" ")){
                    System.out.println("Invalid Input: Please Try again");
                    answer = scan.nextLine();
                }
            }

            if(list.get(i).getAnswer().equalsIgnoreCase(answer)){
                points++;
                System.out.println("Correct");
                System.out.println("Total Points: " + points);
            }else{
                System.out.println("Incorrect: \nThe correct answer is - " + list.get(i).getAnswer());
            }
        }
        return points;
    }

}
