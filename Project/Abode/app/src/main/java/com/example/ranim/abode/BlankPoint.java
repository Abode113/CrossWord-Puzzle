package com.example.ranim.abode;

/**
 * Created by Ranim on 5/29/2017.
 */
public class BlankPoint {
    int x, y;
    BlankPoint(){
        x = 0; y = 0;
    }
    BlankPoint(int x, int y){
        this.x = x;
        this.y = y;
    }
    void clear(){
        this.x = 0;
        this.y = 0;
    }
    BlankPoint(BlankPoint Clone){
        this.x = Clone.x;
        this.y = Clone.y;
    }
}
