package com.kenzie.app;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class VersusMode {

    public static void VersusQuiz(String url) throws JsonProcessingException {
        List<CluesDTO> versusList = NormalMode.getList(url);
        System.out.println("Player 1: It's your turn");
        int playerOne = NormalMode.normalQuiz(versusList);
        System.out.println("Player 2: It's your turn");
        int playerTwo = NormalMode.normalQuiz(versusList);
        VersusWinner(playerOne, playerTwo);
    }

    public static void VersusWinner(int player1, int player2){
        if(player1 == player2){
            System.out.println("Tie Game");
        }else if(player1 < player2){
            System.out.println("PLAYER 2 WINS!!");

        }else{
            System.out.println("PLAYER 1 WINS!!");
        }
    }
}
