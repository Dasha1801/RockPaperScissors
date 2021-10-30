package com.test;

public class IdentifyWinner {
    public String whoWin(int x, int y, double argsLength) {
        if (x == y) {
            return "Draw";
        } else if ((y > x && y - x > (argsLength / 2)) || (x > y && x - y < (argsLength / 2))){
            return "Win";
        }
            return "Lose";
    }
}

