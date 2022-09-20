package com.kenzie.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class CategoriesMode {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static ObjectMapper objectmapper = new ObjectMapper();


    public static String chooseCategory(String url) throws JsonProcessingException {
        String categoryURL = url + "?category=";
        List<CluesDTO> list = new ArrayList<>();
        int userChoice;
        StringBuilder optionList;
        int options;
        String clueURL;
        String httpResponse;
        String choice =  "";
        Scanner scan = new Scanner(System.in);
        while(choice.equals("")){
            System.out.println("You can choice one of these categories or hit ENTER to get a new selection");
            list = new ArrayList<>();
            CluesDTO clue;
            optionList = new StringBuilder();
            for(int i = 1; i <= 5; i++){
                options = rand.nextInt(40950)+1;
                clueURL = url+options;
                httpResponse = CustomHttpClient.sendGET(clueURL);
                clue = objectmapper.readValue(httpResponse, CluesDTO.class);
                optionList.append(i+". "+clue.getCategory().getTitle());
                if(i != 5){
                    optionList.append("\n");
                }
                list.add(clue);
            }
            System.out.println(optionList);
            choice = scan.nextLine();

        }
        userChoice = list.get(Integer.parseInt(choice)-1).getCategoryId();
        String choiceURL = categoryURL+userChoice;

        return choiceURL;
    }

    public static List<CluesDTO> getList(String url) throws IOException {
        int num = 0;
        List<Integer> catClueList = new ArrayList<>();
        List<CluesDTO> list = new ArrayList<>();
        int clueId;
        CluesDTO clue;
        String httpResponse = CustomHttpClient.sendGET(url);
        CluesListDTO clueList = objectmapper.readValue(httpResponse, CluesListDTO.class);
        String categoryTitle = clueList.clues.get(0).getCategory().getTitle();
        int listSize = clueList.clues.size();
        for(int i = 0; i < listSize; i++){
            catClueList.add(clueList.clues.get(i).getId());

        }
        System.out.println(catClueList);
        if(listSize < 10) {
            System.out.println("There are only " + listSize + " questions in the "+ categoryTitle + " category");
            Collections.shuffle(clueList.clues);
            return clueList.clues;

        }else{
            System.out.println("Answer 10 questions from the " + categoryTitle + " category");
            int randQuestions = url.lastIndexOf("/");
            String newUrl;
            int randClue;

            for (int i = 0; i < 10; i++){
                randClue = rand.nextInt(catClueList.size());
                clueId = catClueList.get(randClue);
                newUrl = url.substring(0, randQuestions+1)+clueId;
                httpResponse = CustomHttpClient.sendGET(newUrl);
                catClueList.remove(randClue);
                clue = objectmapper.readValue(httpResponse, CluesDTO.class);
                list.add(clue);
            }
            return list;

        }
    }

    public static int CategoryQuiz(List<CluesDTO> list){
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
                System.out.println("Points: " + points);
            }else{
                System.out.println("Incorrect: \nThe correct answer is - " + list.get(i).getAnswer());
                System.out.println("Points: " + points);
            }
        }


        return points;
    }
}
