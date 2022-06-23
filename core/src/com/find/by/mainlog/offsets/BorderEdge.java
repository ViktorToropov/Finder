package com.find.by.mainlog.offsets;

import com.badlogic.gdx.graphics.Pixmap;

public class BorderEdge {
    Pixmap pixmap;

    int zx;
    int zy;
    public int [][] mas;

    public BorderEdge(Pixmap pixmap) {//находим границы оболочек

        this.pixmap = pixmap;
        zx = pixmap.getWidth();
        zy = pixmap.getHeight();
        mas = new int[zx][zy];
        findRedPicMas();

    }

    public void findRedPicMas(){//найти веса оболочки

        int cot = 1;
        for(int i=0;i<zx;i++){
            for(int j=0;j<zy;j++){

                if (pixmap.getPixel(i,j)!=255){
                    int count = 1;//внутренности фигур
                    int x = i;
                    int y = j;
                    for(int k=x-cot;k<=x+cot;k++)
                        for(int l=y-cot;l<=y+cot;l++)
                            if(pixmap.getPixel(k,l)==255)
                                count++;

                    mas[i][j] = count;//все цвета начинаются с 0
                }
            }
        }
    }

}
