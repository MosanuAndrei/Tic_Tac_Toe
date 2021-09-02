package com.game;

public class GameModes{
    private char[][] gameBoard = {
        {' ',' ',' '},
        {' ',' ',' '},
        {' ',' ',' '}
    };

    public void humanVSHuman(char[][] newGameBoard, GameRules rules, int[] co){

        newGameBoard = GameRules.copyBoard(gameBoard);

        while(true){
            rules.renderBoard(newGameBoard);
            char currentPlayer = rules.currentPlayer();

            if(rules.checkWinner(newGameBoard)){
                System.out.println("---------------The " + rules.currentPlayer() + " player has won!---------------");
                break;
            }
            else if(rules.checkDraw(newGameBoard)){
                System.out.println("---------------It's a draw!---------------");
                break;
            }

            co = rules.humanPlayer(newGameBoard, currentPlayer);
            newGameBoard = rules.makeMove(newGameBoard, co, currentPlayer);
        }
    }

    public void humanVSAI(char[][] newGameBoard, GameRules rules, GameAI ai, int[] co, String difKey){

        newGameBoard = GameRules.copyBoard(gameBoard);

        while(true){
            rules.renderBoard(newGameBoard);
            char currentPlayer = rules.currentPlayer();

            if(rules.checkWinner(newGameBoard)){
                System.out.println("---------------The " + rules.currentPlayer() + " player has won!---------------");
                break;
            }
            else if(rules.checkDraw(newGameBoard)){
                System.out.println("---------------It's a draw!---------------");
                break;
            }

            if(currentPlayer == 'X'){ 
                co = rules.humanPlayer(newGameBoard, currentPlayer);
            }else{
                if(difKey.equals("1")){
                    co = ai.randomMoveAI(newGameBoard, currentPlayer);
                }else if (difKey.equals("2")){
                    co = ai.winMovesAI(newGameBoard, currentPlayer);
                }else if (difKey.equals("3")){
                    co = ai.winLoseMovesAI(newGameBoard, currentPlayer);
                }else if (difKey.equals("4")){
                    co = ai.minMaxAI(newGameBoard, currentPlayer);
                }else{
                    break;
                }
            }
            newGameBoard = rules.makeMove(newGameBoard, co, currentPlayer);
        }
    }

    public void aiVSAI(char[][] newGameBoard, GameRules rules, GameAI ai, int[] co,String n,String difKey){
        int drawStats = 0;
        int[] winStats = {0,0};

        for(int i = 0;i<Integer.parseInt(n);i++){

            newGameBoard = GameRules.copyBoard(gameBoard);

            while(true){
                rules.renderBoard(newGameBoard);
                char currentPlayer = rules.currentPlayer();

                if(rules.checkWinner(newGameBoard)){
                    System.out.println("---------------The " + rules.currentPlayer() + " player has won!---------------");

                    if(currentPlayer == 'X'){
                        winStats[1]+=1;
                    }else{
                        winStats[0]+=1;
                    }

                    break;
                }else if(rules.checkDraw(newGameBoard)){
                    System.out.println("---------------It's a draw!---------------");
                    drawStats++;
                    break;
                }

                if(difKey.equals("1")){
                    if(currentPlayer == 'X'){ 
                        co = ai.randomMoveAI(newGameBoard, currentPlayer);
                    }else{
                        co = ai.winLoseMovesAI(newGameBoard, currentPlayer);
                    }

                }else if (difKey.equals("2")){
                    if(currentPlayer == 'X'){ 
                        co = ai.winMovesAI(newGameBoard, currentPlayer);
                    }else{
                        co = ai.winLoseMovesAI(newGameBoard, currentPlayer);
                    }

                }else if (difKey.equals("3")){
                    if(currentPlayer == 'X'){ 
                        co = ai.winMovesAI(newGameBoard, currentPlayer);
                    }else{
                        co = ai.randomMoveAI(newGameBoard, currentPlayer);
                    }

                }else if (difKey.equals("4")){
                    if(currentPlayer == 'X'){ 
                        co = ai.winLoseMovesAI(newGameBoard, currentPlayer);
                    }else{
                        co = ai.minMaxAI(newGameBoard, currentPlayer);
                    }
                }else{
                    break;
                }
                newGameBoard = rules.makeMove(newGameBoard, co, currentPlayer);
            }
        }
        System.out.println("X: " + winStats[0] + " O: " + winStats[1]);
        System.out.println("D: " + drawStats);
        System.out.println();
    }
}
