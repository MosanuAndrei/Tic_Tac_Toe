package com.game;

import java.util.Scanner;

public class GameMenu{
    private GameModes modes = new GameModes();
    private GameRules rules = new GameRules();
    private GameAI ai = new GameAI();
    private Scanner sc = new Scanner(System.in);
    private char[][] newGameBoard = new char[3][3];
    private int[] co = new int[2];
    private String key;
    private String difKey;

    public void menu(){
        System.out.println("---------------Tic Tac Toe Game---------------");
        System.out.println();
        while (true){
            System.out.println("Press 1 to play against another human locally");
            System.out.println("Press 2 to play against AI");
            System.out.println("Press 3 to bit AIs against each other");
            System.out.println("Press 4 to exit");

            key = sc.nextLine();

            switch (key) {
                case "1":
                    modes.humanVSHuman(newGameBoard, rules, co);

                    break;
                
                case "2":
                    System.out.println();
                    System.out.println("Choose the AI difficulty:");
                    System.out.println("Press 1 Easy Difficulty");
                    System.out.println("Press 2 Medium Difficulty");
                    System.out.println("Press 3 Hard Difficulty");
                    System.out.println("Press 4 Extreme Difficulty");
                    System.out.println("Press 5 to return to menu");
                    difKey = sc.nextLine();
                            
                    if(difKey.equals("5")){
                        System.out.println();
                        break;
                    }
                    modes.humanVSAI(newGameBoard, rules, ai, co, difKey);

                    break;

                case "3":
                    System.out.println();
                    System.out.println("Choose the AI difficulty:");
                    System.out.println("Press 1 Easy AI vs Hard AI");
                    System.out.println("Press 2 Medium AI vs Hard AI");
                    System.out.println("Press 3 Medium AI vs Easy AI");
                    System.out.println("Press 4 Hard AI vs Extreme AI");
                    System.out.println("Press 5 to return to menu");
                    difKey = sc.nextLine();
                        
                    if(difKey.equals("5")){
                        System.out.println();
                        break;
                    }

                    System.out.println();
                    System.out.println("How many rounds should the AI fight?");
                    String n = sc.nextLine();

                    modes.aiVSAI(newGameBoard, rules, ai, co, n, difKey);

                    break;

                case "4":
                    return;

                default:
                    System.out.println("Wrong option, please try again!");
                    System.out.println();
                    continue;
            }
        }
    }
}
