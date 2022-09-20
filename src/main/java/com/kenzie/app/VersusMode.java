package com.kenzie.app;

public class VersusMode {


    public static void VersusWinner(int player1, int player2){
        if(player1 == player2){
            System.out.println("Determine who wins by difficulty of questions or play again?");
        }else if(player1 < player2){
            System.out.println("PLAYER 2 WINS!!");

        }else{
            System.out.println("PLAYER 1 WINS!!");
        }
    }
}
