package com.example.ranim.abode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ranim on 5/20/2017.
 */
public class CopyTwoDimensionalArray implements Serializable {
    int[][] number;
    int[] ListNumber;

    CopyTwoDimensionalArray(){
        number = new int[22][26];
        for(int i = 0; i < 22; i++){
            for(int j = 0; j < 26; j++){
                number[i][j] = 0;
            }
        }

        ListNumber = new int[26];
        for(int i = 0; i < 25; i++){
            ListNumber[i] = 1;
        }
    }

    CopyTwoDimensionalArray(int[][] Clone){
        number = new int[22][26];
        for(int i = 0; i < 22; i++){
            for(int j = 0; j < 26; j++){
                number[i][j] = Clone[i][j];
            }
        }
    }
    CopyTwoDimensionalArray(int[] Clone){
        ListNumber = new int[26];
        for(int i = 0; i < 26; i++){
            ListNumber[i] = Clone[i];
        }
    }

    int[][] getInfo(){
        int[][] returnedArray = new int[22][26];
        for(int i = 0; i < 22; i++){
            for(int j = 0; j < 26; j++){
                returnedArray[i][j] = number[i][j];
            }
        }
        return returnedArray;
    }

    int[] getInfoOfListNumber(){
        int[] returnedArray = new int[26];
        for(int i = 0; i < 26; i++){
                returnedArray[i] = ListNumber[i];
        }
        return returnedArray;
    }

    void clear(){
        for(int i = 0; i < 22; i++){
            for(int j = 0; j < 26; j++){
                number[i][j] = 0;
            }
        }
    }

    void clearOfListNumber(){
        for(int i = 0; i < 26; i++){
            ListNumber[i] = 0;
        }
    }

    void printInfo(){
        for(int i = 0; i < 22; i++){
            for(int j = 0; j < 26; j++){
                System.out.print(number[i][j]);
            }
            System.out.print("\n");
        }
    }

    void printInfoOfListNumber(){
        for(int i = 0; i < 22; i++){
            System.out.print(ListNumber[i]);
        }
    }
}
