package com.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameAI{
    private Random rand = new Random();
    private GameRules rules = new GameRules();
    private char opponent = ' ';
    
    public int[] randomMoveAI(char[][] gameBoard, char currentPlayer){
        int x,y;
        
        x = rand.nextInt(3);
        y = rand.nextInt(3);
        while(gameBoard[x][y] == 'X' || gameBoard[x][y] == 'O'){
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        }

        int[] co = {x,y};
        return co;
    }

    public int[] winMovesAI(char[][] gameBoard,char currentPlayer){
        char[][] copyBoard ;

        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                int[] bestCo = {i,j};

                copyBoard = GameRules.copyBoard(gameBoard);
                
                if(gameBoard[i][j] != 'X' && gameBoard[i][j] != 'O'){
                    if(rules.checkWinner(rules.makeMove(copyBoard, bestCo, currentPlayer)) == true){
                        return bestCo;
                    }
                }
            }
        }

        return randomMoveAI(gameBoard,currentPlayer);
    }

    public int[] winLoseMovesAI(char[][] gameBoard,char currentPlayer){
        char[][] copyBoard;

        copyBoard = GameRules.copyBoard(gameBoard);

        int[] currentMoveWins = winMovesAI(copyBoard, currentPlayer);
        copyBoard[currentMoveWins[0]][currentMoveWins[1]] = currentPlayer;

        if(rules.checkWinner(copyBoard) == true){
            return currentMoveWins;
        }

        if (currentPlayer == 'X'){
            opponent = 'O';
        }else{
            opponent = 'X';
        }

        if(rules.checkDraw(copyBoard) == false){
            int[] stopOpponentFromWinning = winMovesAI(copyBoard, opponent);  
            return stopOpponentFromWinning;
        }

        return currentMoveWins;
    }

    public List<int[]> getAllLegalMoves(char[][] gameBoard){
        List<int[]> legalMoves = new ArrayList<>();

        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if(gameBoard[i][j] != 'X' && gameBoard[i][j] != 'O'){
                    int[] co = {i,j};
                    legalMoves.add(co);
                }
            }
        }

        return legalMoves;
    }

    public int miniMaxScore(char[][] gameBoard,char currentPlayer, char playerToOptimize){
        char[][] copyBoard;
        List<Integer> scores = new ArrayList<>();
        boolean winner = rules.checkWinner(gameBoard);
        int score;

        if(winner == true && playerToOptimize == currentPlayer){
            return -10;
        }else if(winner == true && playerToOptimize != currentPlayer){
            return +10;
        }else if(rules.checkDraw(gameBoard) == true){
            return 0;
        }

        List<int[]> legalMoves = getAllLegalMoves(gameBoard);

        for (int[] move : legalMoves) {

            copyBoard = GameRules.copyBoard(gameBoard);
            copyBoard = rules.makeMove(copyBoard, move, currentPlayer);

            if(currentPlayer == 'O'){
                opponent = 'X';
            }else if(currentPlayer == 'X'){
                opponent = 'O';
            }

            score = miniMaxScore(copyBoard, opponent, playerToOptimize);

            if(score >= 0 && playerToOptimize == currentPlayer){
                scores.add(score);
                break;
            }

            scores.add(score);
        }

        if(currentPlayer == playerToOptimize){
            return Collections.max(scores);
        }else{
            return Collections.min(scores);
        }
    }

    public int[] minMaxAI(char[][] gameBoard,char currentPlayer){
        int[] bestMove = new int[2];
        int bestScore = Integer.MIN_VALUE;
        int score = 0;
        char[][] copyBoard;
        char opponent = ' ';

        for (int[] move : getAllLegalMoves(gameBoard)) {

            copyBoard = GameRules.copyBoard(gameBoard);
            copyBoard = rules.makeMove(copyBoard, move, currentPlayer);

            if(currentPlayer == 'O'){
                opponent = 'X';
            }else if(currentPlayer == 'X'){
                opponent = 'O';
            }

            score = miniMaxScore(copyBoard, opponent, currentPlayer);
            
            if(bestScore == Integer.MIN_VALUE || score > bestScore){
                bestMove = move;
                bestScore = score;
                if(bestScore == +10){
                    return bestMove;
                }
            }
        }
        return bestMove;
    }

}