package com.kenzie.app;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
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
        int input;


            System.out.println("What game mode would you like to play? Select a number");
            System.out.println("1. Normal\n" +
                    "2. By Category\n" +
                    "3. Unlimited\n"
                    //"4. Versus\n"
                    );
            input = scan.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Answer 10 random questions");
                    List<CluesDTO> normList = NormalMode.getList(url);
                    points = NormalMode.normalQuiz(normList);

                    totalPoints += points;
                    break;
                case 2:
                    System.out.println("Answer questions from the category of your choice");
                    String categoryURL = CategoriesMode.chooseCategory(url);
                    List<CluesDTO> categoryList = CategoriesMode.getList(categoryURL);
                    points = CategoriesMode.CategoryQuiz(categoryList);
                    totalPoints += points;
                    break;
                case 3:
                    System.out.println("Answer as many questions as you want");
                    points = UnlimitedMode.unlimitedQuiz(url);
                    totalPoints += points;
                    break;
                    /*Versus mode is still being worked on*/
//            case 4:
//                System.out.println("Play against a friend and see who can get the most out of 10");
//                List<CluesDTO> versusList = NormalMode.getList(url);
//                System.out.println("Player 1: It's your turn");
//                Player1 = RandomQuestions.Quiz(versusList);
//                System.out.println("Player 2: It's your turn");
//                Player2 = RandomQuestions.Quiz(versusList);
//                HighScore.VersusWinner(Player1, Player2);
//                break;
            }
            System.out.println("Your total score is " + totalPoints);


    }
}

