package com.find.by.secondclass;

import com.badlogic.gdx.graphics.Pixmap;

public class PathFinder {
    Pixmap pixmap;
    int zx;
    int zy;
    int [][] mas;

    public PathFinder(Pixmap pixmap) {
        this.pixmap = pixmap;
        zx = pixmap.getWidth();
        zy = pixmap.getHeight();
        mas = new int[zx][zy];
        init();
    }

    public void init(){
        int cou =0;
        for(int i=0;i<zx;i++){
            for(int j=0;j<zy;j++){
                if(pixmap.getPixel(i,j)!=255){
                    cou++;
                    mas[i][j]=cou;
                }else cou=0;

            }
        }
    }

}
