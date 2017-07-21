package com.example.ranim.abode;

import java.io.Serializable;

/**
 * Created by Ranim on 5/19/2017.
 */
public class NumericWord implements Serializable {
    String Word;
    static int[][] NumberOfWord = new int[22][26];
    NumericWord(){
        Word = new String();
        for(int i = 0; i < 22; i++){
            for (int j = 0; j < 26; j++){
                NumberOfWord[i][j] = 0;
            }
        }
    }
    NumericWord(String Word,int WordLength){
        this.Word = new String(Word);
        char[] charr = this.Word.toCharArray();
        //System.out.println("the Word is : " + this.Word + " and first letter : " + charr[0] + " amd asci : " + (int)charr[0]);
        int i = ((int)charr[0]) - 97;
        if(i >= 0 && i <= 25)
            NumberOfWord[WordLength - 2][i]++;
    }
    NumericWord(NumericWord Clone){
        this.Word = new String(Clone.Word);
    }

    static void clear(){
        for(int i = 0; i < 22; i++){
            for (int j = 0; j < 26; j++){
                NumberOfWord[i][j] = 0;
            }
        }
    }
}
