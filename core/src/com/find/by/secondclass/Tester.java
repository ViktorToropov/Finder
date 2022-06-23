package com.find.by.secondclass;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by ADMIN on 06.05.2018.
 */

public class Tester {
    Texture texture;
    SpriteBatch spriteBatch;
    public Tester() {
        texture = new Texture("tester.png");
        spriteBatch = new SpriteBatch();
    }
    public void render(){
        spriteBatch.begin();
        spriteBatch.draw(texture,100,100,400,400);
        spriteBatch.end();
    }
    public void dispose(){
        spriteBatch.dispose();
        spriteBatch.dispose();
    }
    /*
        public void pixSearch(){

        if(interest.isEmpty()){
            interest.add(0);interest.add(0);interest.add(0);interest.add(pixmap.getPixel(0,0));
        }

        //pulse(interest.get(1),interest.get(2),interest.get(3));

        findRed();
       // System.out.print(arrayList.get(0));


    }
        public void pulse(int x,int y,int col){//resize области, доработать
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        if (arrayList2.isEmpty())arrayList2.add(pixmap.getPixel(0,0));
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if((i==0||i==(x-1))&&(j==0||j==(y-1))) {
                    int siz = arrayList2.size();
                    for (int k = 0; k < siz; k++) {
                        if (pixmap.getPixel(i, j) != arrayList2.get(k))
                            arrayList2.add(pixmap.getPixel(i, j));
                    }
                }
            }
        }
        System.out.println(arrayList2.size());

    }
     */
}



