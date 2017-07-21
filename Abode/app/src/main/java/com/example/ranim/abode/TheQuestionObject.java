package com.example.ranim.abode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ranim on 5/16/2017.
 */
public class TheQuestionObject implements Serializable {
    private String TheWordThatWeWantToAdd;
    private int id;
    int NumberOfLetter;
    public List<String> theTranslate;
    static int[] NumberOfWord = new int[26];

    public TheQuestionObject(){
        TheWordThatWeWantToAdd = "#";
        this.id = -1;
        List<String> tempNull = new ArrayList<String>();
        theTranslate = tempNull;
        NumberOfLetter = -1;
    }

    public TheQuestionObject(String word,  List<String> translate_w, int id) {

        TheWordThatWeWantToAdd = word;
        this.id = id;
        theTranslate = translate_w;
        NumberOfLetter=word.length();
        char[] charr = word.toCharArray();

        int i = ((int)charr[0]) - 97;
        if(i >= 0 && i <= 25)
            NumberOfWord[i]++;
        int o = 0;
    }

    TheQuestionObject(TheQuestionObject clone){
        this.TheWordThatWeWantToAdd = new String(clone.TheWordThatWeWantToAdd);
        this.theTranslate = new ArrayList<String>();
        for(String var:clone.theTranslate){
            this.theTranslate.add(var);
        }
        this.NumberOfLetter = clone.NumberOfLetter;
        this.id = clone.id;
    }

    static void clearTheNumber(){
        for(int i = 0; i < 26; i++){
            NumberOfWord[i] = 0;
        }
    }

    public String getWord() {   return TheWordThatWeWantToAdd;  }
    public void setWord(String word) {  TheWordThatWeWantToAdd = word;  }

    public List<String> getTranslate_w() {  return theTranslate;    }
    public void setTranslate_w(String temp) {   theTranslate.add(temp); }

    public int getId() {    return id;  }
    public void setId(int id) {     this.id = id;   }

    void PrintInfo(){
        System.out.println(this.TheWordThatWeWantToAdd + " : \n");
        for(String var:this.theTranslate){
            System.out.println("\t" + var + "\n");
        }
        System.out.println("Number Of Letter : " + this.NumberOfLetter);
        System.out.println("ID : " + this.id);
    }

}