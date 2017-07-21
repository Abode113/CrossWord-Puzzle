package com.example.ranim.abode;

import java.util.ArrayList;

/**
 * Created by Ranim on 5/16/2017.
 */
public class PropableChar {
    ArrayList<ArrayList<Character>> listOLists;
    ArrayList<Character> singleList;
    PropableChar(){
        listOLists = new ArrayList<ArrayList<Character>>();
        singleList = new ArrayList<Character>();
    }
    PropableChar(ArrayList<ArrayList<Character>> Obj){
        listOLists = new ArrayList<ArrayList<Character>>();
        int i = 0;
        for (ArrayList<Character> var:Obj){
            singleList = new ArrayList<Character>();
            int j = 0;
            for (Character var1:var){
                singleList.add(var1.charValue());
            }
            listOLists.add(singleList);
        }
    }

    PropableChar(PropableChar clone){
        this.listOLists = new ArrayList<ArrayList<Character>>();
        int i = 0;
        for (ArrayList<Character> var:clone.listOLists){
            this.singleList = new ArrayList<Character>();
            int j = 0;
            for (Character var1:var){
                this.singleList.add(var1.charValue());
            }
            this.listOLists.add(this.singleList);
        }
    }
}
