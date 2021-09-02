package com.game;

import java.util.Arrays;
import java.util.Scanner;

public class GameRules {
    private Scanner sc = new Scanner(System.in);
    private boolean toggle;

    public static char[][] copyBoard(char[][] gameBoard) {
        if (gameBoard == null) {
            return null;
        }
    
        final char[][] copyBoard = new char[gameBoard.length][];
        for (int i = 0; i < gameBoard.length; i++) {
            copyBoard[i] = Arrays.copyOf(gameBoard[i], gameBoard[i].length);
         }
        return copyBoard;
    }

    public char currentPlayer(){
        toggle = !toggle;
        if(toggle){
            return 'X';
        }
        else{
            return 'O';
        }
    }

    public void renderBoard(char[][] gameBoard){

        System.out.println("   0 1 2");
        System.out.println("  -------");
        for(int i=0;i<3;i++){
            System.out.print(i + "| ");
            for(int j = 0;j<3;j++){
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("  -------");
    }

    public boolean isValidMove(char[][] gameBoard,int[] co){

        if (gameBoard[co[0]][co[1]] == 'O' || gameBoard[co[0]][co[1]] == 'X'){
            return false;
        }
        else{
            return true;
        }
    }

    public char[][] makeMove(char[][] gameBoard, int[] co, char currentPlayer ){
        char[][] updatedGameBoard;

        if (isValidMove(gameBoard,co) == true){
            updatedGameBoard = copyBoard(gameBoard);
            updatedGameBoard[co[0]][co[1]] = currentPlayer;

            return updatedGameBoard;
        }
        else{
            System.out.println("Can't make move, square already taken!");
            co = humanPlayer(gameBoard, currentPlayer);
            return makeMove(gameBoard, co, currentPlayer);
        }
    }

    public boolean checkWinner(char[][] gameBoard){

        for(int i = 0;i<3;i++){
            for(int j=0;j<3;j++){
                if((gameBoard[i][0] == 'X'|| gameBoard[i][0] == 'O') && gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2]){
                    return true;
                }
                else if((gameBoard[0][j] == 'X'|| gameBoard[0][j] == 'O') && gameBoard[0][j] == gameBoard[1][j] && gameBoard[0][j] == gameBoard[2][j]){
                    return true;
                }
                else if((gameBoard[0][0] == 'X'|| gameBoard[0][0] == 'O') && gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2]){
                    return true;
                }
                else if((gameBoard[0][2] == 'X'|| gameBoard[0][2] == 'O') && gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0]){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDraw(char[][] gameBoard){

        for(int i = 0;i<3;i++){
            for(int j=0;j<3;j++){
                if(gameBoard[i][j] != 'X' && gameBoard[i][j] != 'O'){
                    return false;
                }
            }
        }
        return true;
    }

    public int[] humanPlayer(char[][] gameBoard, char currentPlayer){
        int x,y;

        System.out.println("What is your move's X co-ordinate?");
        x = sc.nextInt();
        System.out.println("What is your move's Y co-ordinate?");
        y = sc.nextInt();

        int[] co = {x,y};
        return co;
    }  
}
