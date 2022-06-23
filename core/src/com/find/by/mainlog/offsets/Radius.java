package com.find.by.mainlog.offsets;

import com.badlogic.gdx.graphics.Pixmap;

import java.util.ArrayList;

public class Radius {//отрисовка максимального радиуса
    Pixmap pixmap;
    int[][] mas;
    ArrayList<int[]>arrayList;
    public int max;
    public Radius(Pixmap pixmap, int[][] mas, ArrayList<int[]> arrayList) {
        this.arrayList = arrayList;
        this.pixmap = pixmap;
        this.mas = mas;
        setPix();
    }

    public void setPix() {
        max=0;
        for (int i=0;i<pixmap.getWidth();i++)
            for(int j=0;j<pixmap.getHeight();j++)
                    if(mas[i][j]>max){
                        max=mas[i][j];

                    }

    //    pixmap.setColor(Color.argb8888(218 / 255f, 165 / 255f, 32 / 255f, 100 / 255f));
        pixmap.setColor(255);

        boolean check=false;
        for (int i=0;i<pixmap.getWidth();i++){
            if (check)break;
            for(int j=0;j<pixmap.getHeight();j++){
                if(mas[i][j]==max){
                    mainTheme(i,j,mas[i][j]);
              //      System.out.println(mas[i][j]);


                 //   arrayList.add(new int[2]);
              //      arrayList.get(arrayList.size()-1)[0]=mas[i][j];
              //      arrayList.get(arrayList.size()-1)[1]=1;


                    if (arrayList.size()==0){
                        arrayList.add(new int[2]);
                        arrayList.get(arrayList.size()-1)[0]=mas[i][j];
                        arrayList.get(arrayList.size()-1)[1]=0;
                    }

                    boolean check1=false;//проверка на повторы
                    for(int k=0;k<arrayList.size();k++)
                        if(arrayList.get(k)[0]==mas[i][j]){
                            arrayList.get(k)[1]++;
                            check1=false;
                            break;
                        }else{
                            check1=true;
                        }

                        if (check1){
                            arrayList.add(new int[2]);
                            arrayList.get(arrayList.size()-1)[0]=mas[i][j];
                            arrayList.get(arrayList.size()-1)[1]=1;
                        }


                    check=true;
                    break;
                }
            }
        }

    }
    void mainTheme(int x,int y,int r){
        r-=1;

        for (int i=0;i<=2*r;i++){
            for (int j=0;j<=2*r;j++){
                if((((x - r + i)-x)*((x - r + i)-x)+((y - r + j)-y)*((y - r + j)-y)<=(r*r))){
                    pixmap.drawPixel(x - r + i,y - r + j);
                }
            }

        }

    }

    public Pixmap getPixmap() {
        return pixmap;
    }

}
