package com.test;

import java.util.*;
import java.security.SecureRandom;

public class Main {
    static List<String> options = new ArrayList<>();

    public Main(){
        initGame();
    }

    public void initGame()  {
        String choiceComp = computerMove();
        String key = getHmac(choiceComp)[1];
        System.out.print("\nEnter your move: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        switch (choice) {
            case ("0"):
                System.out.println("exit");
                break;
            case ("?"):
                new Table(options, options.size()).drawTable();
                break;
            default:
                System.out.print("Your move: " + playerMove(Integer.parseInt(choice)));
                System.out.println("\nComputer move: " + choiceComp);
                System.out.println(new IdentifyWinner().whoWin(Integer.parseInt(choice),
                        (options.indexOf(choiceComp)+1), options.size()));
                System.out.print("HMAC key : " + key);
                System.out.println("\n\nLet's play again? N/Y");
                newGame();
                break;
        }
    }

    public String computerMove() {
        SecureRandom random = new SecureRandom();
        int move = random.nextInt(options.size());
        String choice = options.get(move);
        return choice;
    }

    public String playerMove(int move) {
        return options.get(move - 1);
    }

    public String[] getHmac(String moveComp){
        HMAC info = new HMAC();
        String[] dataComp = info.Hmac(moveComp);
        System.out.print("\nHMAC : " + dataComp[0]);
        return dataComp;
    }
    public void newGame(){
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        switch (choice) {
            case ("n"):
                System.out.println("exit");
                break;
            case ("y"):
                showMoves(options);
                initGame();
                break;
            default:
                System.out.println("\n" +
                        "It's a pity!");
                break;
        }
    }

    public static void showMoves(List<String> moves){
        System.out.println("Available moves:");
        for(int l = 0; l < moves.size(); l++ ){
            System.out.println((l+1)+" -> "+ moves.get(l));
        }
        System.out.println("0 - exit\n? - help");
    }

    public static void validationParams(String[] args){
        List<String> arrayList = Arrays.asList(args);
        Map<String, Integer> map = new HashMap<>();
        for (String s : arrayList) {
            Integer count = map.computeIfAbsent(s, key -> 0);
            map.put(s, count + 1);
        }
        for (String s : arrayList){
            if(map.get(s) > 1){
                System.out.println("Error! Arguments can't be repeated! Example: rock paper scissors...");
                System.exit(0);
            }
        }
        if(args.length%2 == 0 || args.length<3){
            System.out.println("Error! Enter an odd number of arguments and not less than 3!");
            System.exit(0);
        }
    }

    public static void main(String[] args){
        validationParams(args);
        System.out.println("Available moves:");
        for(int l = 0; l < args.length; l++ ){
            options.add(l, args[l]);
            System.out.println((l+1)+" -> "+ args[l]);
        }
        System.out.println("0 - exit\n? - help");
        new Main();
    }
}
