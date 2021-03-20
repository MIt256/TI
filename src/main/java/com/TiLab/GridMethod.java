package com.TiLab;

import java.util.Random;
import java.util.Scanner;


public class GridMethod {

    private String reskey;

    public String getReskey() {
        return reskey;
    }

    public static int[][] rotateClockwise(int[][] mas) {
    int SIDE = mas.length;
    int[][] rezult = new int[SIDE][SIDE];

    for (int i = 0; i < SIDE; i++) {
        for (int j = 0; j < SIDE; j++) {
            rezult[i][j] = mas[SIDE - j - 1][i];
        }
    }
    return rezult;
}

    public String encode(String stext,String skey) {
        //deleting this facking spases
        stext = stext.replaceAll(" ","");
        char[] result = new char[stext.length()+10];

        char[] string = stext.toCharArray();
        int size = 0;
        for(int i = 1; i <= 10; i++) {
            if(Math.pow((double)i, 2.0) * 4 >= string.length) {
                size = i;
                break;
            }
        }
        char[][] matrix = new char[size * 2][size * 2];
        int[][] key = new int[size * 2][size * 2];
        int number = 1;
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                matrix[i][j] = '_';
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                key[i][j] = number;
                number++;
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                key[j][size * 2 - 1 - i] = key[i][j];
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                key[size * 2 - 1 - i][size * 2 - 1 - j] = key[i][j];
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                key[size * 2 - 1 - j][i] = key[i][j];
            }
        }
        Random random = new Random();
        int row;
        int column;
        for(int i = 1; i <= size * size;) {
            column = random.nextInt(size * 2);
            row = random.nextInt(size * 2);
            if(key[row][column] == i) {
                key[row][column] = 0;
                i++;
            }
        }
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                if(key[i][j] != 0)
                    key[i][j] = 1;
            }
        }
        int temp=0;
        char[] keyresult = new char[size*20];
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                System.out.print(key[i][j]);
                System.out.print(' ');
                keyresult[temp] = (char) ('0' + key[i][j]);
                temp++;
            }
            System.out.println();
        }
        //передача ключа
        reskey = String.valueOf(keyresult);
      //  PrimaryController.setKeyres(new String(keyresult));

        int count = -1;
        do {
            for (int j = 0; j < size * 2; j++) {
                for (int h = 0; h < size * 2; h++) {
                    if (count == string.length - 1)
                        break;
                    if (key[j][h] == 0) {
                        count++;
                        matrix[j][h] = string[count];
                    }
                }
                if (count == string.length - 1)
                    break;
            }
            key = rotateClockwise(key);
        } while(count < string.length - 1) ;
        System.out.println();
        temp = 0;
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                //на консоль
                System.out.print(matrix[i][j]);
                //гуи
                result[temp] = matrix[i][j];
                temp++;
            }
        }
        System.out.println();
        return new String(result);
    }

    public String decode(String stext,String skey) {
        char[] string = stext.toCharArray();
        char[] keyarr = new char[skey.length()+5];
        int size = 0;
        for(int i = 1; i <= 10; i++) {
            if(Math.pow((double)i, 2.0) * 4 >= string.length) {
                size = i;
                break;
            }
        }

        keyarr = skey.toCharArray();
        int temp = 0;
        char[][] matrix = new char[size * 2][size * 2];
        int[][] key = new int[size * 2][size * 2];

        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                key[i][j] =keyarr[temp] - '0';
                temp++;
            }
        }
        int count = -1;
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                count++;
                matrix[i][j] = string[count];
            }
        }
        count = 0;
        char[] result = new char[stext.length()+10];
        System.out.println();
        temp = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < size * 2; j++) {
                for(int h = 0; h < size * 2; h++) {
                    if(key[j][h] == 0) {
                        if(matrix[j][h] != '_') {
                            count++;
                            System.out.print(matrix[j][h]);
                            result[temp] = matrix[j][h];
                            temp++;
                        }
                        if(count == string.length)
                            break;
                    }
                }
                if(count == string.length)
                    break;
            }
            key = rotateClockwise(key);
        }

        return new String(result);

    }

}
