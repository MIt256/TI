package com.TiLab;

public class ColumnMethod {

        public String encode(String stext,String skey){
            char[] key = skey.toCharArray();
            char[] text = stext.toCharArray();

            char[] result = new char[text.length+10];//размер зашифрованной строки!!!

            int rows = 2 + text.length/key.length;
            if(text.length % key.length != 0){ rows++;}
            char[][] table = generateTable(key.length,rows);

            for (int j = 0; j < key.length; j++){
                table[0][j] = key[j];
            }//заполнили верхнюю строку

            int num=1;
            for (int j=0;j<26;j++){
                for (int i=0;i<key.length;i++) {
                    if ( table[0][i] == 'a'+j){
                        table[1][i] = (char) num;
                        num++;
                    }
                }
            }//заполнена вторая строка

            int curr =0;
            for (int i = 2; i < rows; i++) {
                for (int j = 0; j < key.length; j++) {
                    if (curr != text.length) {
                        table[i][j] = text[curr];
                        curr++;
                    } else {break;}
                }
            }//заполнили всю таблицу

            //шифрование. получаем строку из таблицы
            int temp;
            int resnum =0;
            int currnum = 1;
            while (currnum != key.length+1) {
                for (int j = 0; j < key.length; j++) {
                    if (table[1][j] == (char) currnum) {
                        temp = 2;
                        while ( temp < rows ){
                            //  if (table[temp][j] != ' ')
                            //пробелы тоже заносяться в результат
                            {
                                result[resnum] = table[temp][j];
                                resnum++;
                            }
                            temp++;

                        }
                    }
                }
                currnum++;
            }

            return new String(result);
        }

        public String decode(String stext,String skey){
            char[] key = skey.toCharArray();
            char[] text = stext.toCharArray();

            char[] result = new char[text.length+1];

            int rows = 2 + text.length/key.length;
            if(text.length % key.length != 0){ rows++;}
            char[][] table = generateTable(key.length,rows);

            for (int j = 0; j < key.length; j++){
                table[0][j] = key[j];
            }//заполнили верхнюю строку

            int num=1;
            for (int j=0;j<26;j++){
                for (int i=0;i<key.length;i++) {
                    if ( table[0][i] == 'a'+j){
                        table[1][i] = (char) num;
                        num++;
                    }
                }
            }//заполнена вторая строка

            //заполняяем таблицу
            int temp;
            int resnum =0;
            int currnum = 1;//номера по алфавиту
            while (currnum != key.length+1) {
                for (int j = 0; j < key.length; j++) {
                    if (table[1][j] == (char) currnum) {
                        temp = 2;
                        while ( temp < rows ){
                            {
                                table[temp][j] = text[resnum];
                                resnum++;
                            }
                            temp++;
                        }
                    }
                }
                currnum++;
            }

            int curr =0;
            for (int i = 2; i < rows; i++) {
                for (int j = 0; j < key.length; j++) {
                    if (curr != text.length) {
                        result[curr] = table[i][j];
                        curr++;
                    } else {break;}
                }
            }//заполнили всю таблицу

            return new String(result);



        }

        private char[][] generateTable(int keylen,int rows) {
            char[][] table = new char[rows][keylen];
            //заполнение пробелами, ибо нужно заполнить все место в таблице
            //если заполнить не всю, текст не расшифровать
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < keylen; j++) {
                    table[i][j] = ' ';
                }
            }
            //возврат таблицы с .

            return table;
        }
    }
