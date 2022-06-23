package com.find.by.secondclass;

import java.util.ArrayList;

public class BordersInception {
    int [][] fragmentPic;
    int [][] zeroPixmap;
    ArrayList<Integer> figures;
    int zx;
    int zy;
    int [][] mas;

    int weightF = 0;

    public BordersInception(int [][] pixmap,ArrayList<Integer> figures,int [][] zeroPixmap) {
        fragmentPic = pixmap;
        zx = fragmentPic.length;
        zy = fragmentPic[0].length;
        this.zeroPixmap = zeroPixmap;
        this.figures = figures;
    }
    public void figureBordersInception(){//создание первичных границ
        //figureCenter веса

        //figureConsist //количество фигур interestObject
        //interestObject.get(j+2) номер каждой фигуры

        for(int i=0;i<figures.size();i++){
            for(int j=0;j<zx;j++){
                for(int k=0;k<zy;k++){
                if(fragmentPic[j][k] == figures.get(i)){
                    int cot = weightF;

                    int x = j;
                    int y = k;
                    for(int ii=x-cot;ii<=x+cot;ii++){
                        for(int jj=y-cot;jj<=y+cot;jj++){
                                        if(zeroPixmap[ii][jj]!=weightF){
                                                //interestBorders.add(figureCenter.get(k+2));
                                        }
                        }
                    }
                }
                }
            }
        }
    }
}
