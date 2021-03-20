package com.TiLab;

public class VigenerMethod {

    public String encode(String stext,String skey){
        while (skey.length()<stext.length()){
            skey+=skey;
        }
        //deleting this facking spases
        stext = stext.replaceAll(" ","");

        char[] key = skey.toCharArray();
        char[] text = stext.toCharArray();

        char[] result = new char[text.length];
        int row,col;
        char[][] table = generateTable();
        //шифрование
        for (int i = 0; i < text.length; i++) {
            row = (int)key[i] - 97;
            col = (int)text[i] - 97;
            result[i] = table[row][col];
        }
        return new String(result);
    }

    public String decode(String stext,String skey){
        while (skey.length()<stext.length()){
            skey+=skey;
        }

        char[] key = skey.toCharArray();
        char[] text = stext.toCharArray();

        char[] result = new char[text.length];
        int row,col;

        char[][] table = generateTable();

        for (int i = 0; i < text.length; i++) {
            row = (int)key[i] - 97;
            col = (int)text[i] - 97;
            if (row > col) {
                result[i] = table[26 + (col - row)][0];
            } else {
                result[i] = table[col - row][0];
            }
        }
        return new String(result);
    }

    private char[][] generateTable() {
        char[][] table = new char[26][26];

        for (int i = 0; i < 26; i++) {
            int overflow = i;
            for (int j = 0; j < 26; j++) {
                if(overflow == 26) {
                    overflow = 0;
                }
                table[i][j] = (char)(97 + overflow);
                overflow++;
            }
        }
        return table;
    }
}
