package com.kenzie.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!

     */
    public static void main(String[] args) throws IOException {
        //Write main execution code here
        final String url = "https://jservice.kenzie.academy/api/clues/";
        int totalPoints = 0;
        int points = 0;
        String playAgain = "Y";
        Scanner scan = new Scanner(System.in);
        String input = " ";
        boolean testInput = true;


        HighScore.checkHighScore(8);

            System.out.println("What game mode would you like to play? Select a number");
            System.out.println("1. Normal\n" +
                    "2. By Category\n" +
                    "3. Unlimited\n" +
                    "4. Versus\n"
                    );
            input = scan.nextLine();
            List<String>  inputList = Arrays.asList("1", "2", "3", "4");
            while(testInput){
                if(inputList.contains(input)){
                    testInput = false;

                }else {
                    System.out.println("Invalid input please try again");
                    input = scan.nextLine();
                }
            }

            switch (input) {
                case "1":
                    System.out.println("Answer 10 random questions");
                    List<CluesDTO> normList = NormalMode.getList(url);
                    points = NormalMode.normalQuiz(normList);

                    totalPoints += points;
                    break;
                case "2":
                    System.out.println("Answer questions from the category of your choice");
                    String categoryURL = CategoriesMode.chooseCategory(url);
                    List<CluesDTO> categoryList = CategoriesMode.getList(categoryURL);
                    points = CategoriesMode.CategoryQuiz(categoryList);
                    totalPoints += points;
                    break;
                case "3":
                    System.out.println("Answer as many questions as you want");
                    points = UnlimitedMode.unlimitedQuiz(url);
                    totalPoints += points;
                    break;
                case "4":
                    System.out.println("Play against a friend and see who can get the most out of 10");
                    VersusMode.VersusQuiz(url);
                    break;
                }

            System.out.println("Your total score is " + totalPoints);




    }
}

