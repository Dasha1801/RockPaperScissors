package com.test;

import com.github.freva.asciitable.AsciiTable;
import com.test.IdentifyWinner;

import java.util.List;

public class Table {

    private final List<String> options;
    private final int argsLength;

    public Table(List<String> options, int argsLength) {
        this.options = options;
        this.argsLength = argsLength;
    }

    public void drawTable() {
        IdentifyWinner rules = new IdentifyWinner();
        String[][] data = new String[argsLength+1][argsLength+1];
        for (int i = 1;  i <= argsLength; i++) {
            for (int n = 1; n <= argsLength;  n++) {
                data[i][n] = rules.whoWin( i, n, argsLength);
            }
        }
        data[0][0] = "USER/PC";

        for(int i = 1; i <= argsLength; i++){
            data[0][i] = options.get(i-1);
            data[i][0] = options.get(i-1);
        }

        System.out.println(AsciiTable.getTable(data));
    }
}
