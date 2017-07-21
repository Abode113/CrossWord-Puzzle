package com.example.ranim.abode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ranim on 5/16/2017.
 */
public class TheGame {
    char[][] TheGame;
    int Rows, Columns;
    int numberOfBlankBox;
    int OptimalSolution, foundedSolution;
    List<char[][]> ValidAswer;

    TheGame(int Rows, int Columns, int OptimalSolution, int foundedSolution){
        this.OptimalSolution = OptimalSolution;
        this.foundedSolution = foundedSolution;
        ValidAswer  = new ArrayList<char[][]>();
        this.Rows = Rows + 2;
        this.Columns = Columns + 2;
        TheGame = new char[this.Rows][this.Columns];
        for (int i = 0; i < this.Rows; i++){
            for(int j = 0; j < this.Columns; j++){
                TheGame[i][j] = '$';
            }
        }
        for(int i = 0; i < this.Columns; i++){
            TheGame[0][i] = '#';
        }
        for(int i = 0; i < this.Columns; i++){
            TheGame[this.Rows - 1][i] = '#';
        }
        for(int i = 0; i < this.Rows; i++){
            TheGame[i][0] = '#';
        }
        for(int i = 0; i < this.Rows; i++){
            TheGame[i][this.Columns - 1] = '#';
        }
    }
    void PrinInfo(){
        for (int i = 0; i < this.Rows; i++){
            for(int j = 0; j < this.Columns; j++){
                System.out.print(TheGame[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }


    List<List<String>> Ques1 = new ArrayList<List<String>>();
    List<String> OneGameQues1 = new ArrayList<String>();

    List<List<String>> Ques2 = new ArrayList<List<String>>();
    List<String> OneGameQues2 = new ArrayList<String>();

    int[][] PosArray1;
    int[][] PosArray2;

    List<Integer> numberOfValidSolution = new ArrayList<Integer>();

    void addd(char[][] Clone){
        char[][] array = new char[this.Rows][this.Columns];
        for (int i = 0; i < this.Rows; i++){
            for(int j = 0; j < this.Columns; j++){
                array[i][j] = Clone[i][j];
            }
        }
        ValidAswer.add(array);
        numberOfValidSolution.add(numberOfBlankBox(array));
    }
    void PrintListOfValidGame(){
        System.out.println("Valid : ============================= : ");
        for(char[][] var:ValidAswer) {
            for (int i = 0; i < this.Rows; i++) {
                for (int j = 0; j < this.Columns; j++) {
                    System.out.print(var[i][j] + "\t");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        System.out.println("Finish : ============================= : ");
    }
    void printQuestion1(){
        int countGame = 0;
        for(List<String> var:this.Ques1){
            System.out.println("Game1 ( " + countGame + " )");
            System.out.println(var);
            countGame++;
        }
    }
    void printQuestion2(){
        int countGame = 0;
        for(List<String> var:this.Ques2){
            System.out.println("Game2 ( " + countGame + " )");
            System.out.println(var);
            countGame++;
        }
    }
    int numberOfBlankBox(char[][] array){
        int count = 0;
        for (int i = 0; i < this.Rows; i++) {
            for (int j = 0; j < this.Columns; j++) {
                if(array[i][j] == '#'){
                    count++;
                }
            }
        }
        return count;
    }
    int numberOfBlankBox(){
        int count = 0;
        for (int i = 0; i < this.Rows; i++) {
            for (int j = 0; j < this.Columns; j++) {
                if(this.TheGame[i][j] == '#'){
                    count++;
                }
            }
        }
        return count;
    }
    static char[][] Clone(char[][] Clone){
        int x = Clone.length;
        int y = Clone[0].length;
        char[][] New = new char[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                New[i][j] = Clone[i][j];
            }
        }
        return New;
    }
}
