package com.TiLab;

import java.util.*;

public class RailwayFence {

    public String encode(String text,String key){
        String res = new String("");//результат

        int Len = text.length();
        int NKey = Integer.parseInt(key);
        int period =  2 * (NKey - 1);

        int[] NumArray = new int[Len];
        //формирование маски
        for (int i = 0; i < Len; i++) {
            int ost = i % period;
            int row = NKey - 1 - Math.abs(NKey - 1 - ost);
            NumArray[i] = row;
        }
        //расстановка букв по маске
        for (int i=0;i<NKey;i++){
            for (int l =0;l<Len;l++){
                if (i == NumArray[l]){
                    res += text.charAt(l);
                }
            }
        }
        return res;
    }

    public String decode(String text,String key){
        String res = new String("");//результат

        int Len = text.length();
        int NKey = Integer.parseInt(key);
        int period =  2 * (NKey - 1);

        int[] NumArray = new int[Len];
        //формирование маски
        for (int i = 0; i < Len; i++) {
            int ost = i % period;
            int row = NKey - 1 - Math.abs(NKey - 1 - ost);
            NumArray[i] = row;
        }

        Arrays.sort(NumArray);

        int down=0;
        int i=0;
        while (res.length() !=Len) {
            for (int l = 0; l < Len; l++) {
                if (i == NumArray[l]) {
                    res += text.charAt(l);
                    NumArray[l] = -1;
                    break;
                }
            }
            if (i == NKey - 1) {
                down = 1;
            }
            if (i == 0) {
                down = 0;
            }
            if (down == 0) {
                i++;
            } else {
                i--;
            }
        }



        return res;
    }

}
