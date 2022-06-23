package com.find.by.mainlog.offsets;

import java.util.ArrayList;

public class Col2 {
    int [][] pixmap;

    int zx;
    int zy;

    public int [][] mas;

    long start_time;
    long end_time;
    long difference;

    ArrayList<ArrayList<int[]>> test;

    int max=0;

    public boolean radmen = false;


    public Col2(int[][] pixmap,int maxinput) {
        this.pixmap = pixmap;
        zx = pixmap.length;
        zy = pixmap[0].length;
        mas = new int[zx][zy];
        start_time = System.currentTimeMillis();
        test = new ArrayList<ArrayList<int[]>>();




        interestZeroPic();

        if(max>0)
        if(maxinput>max)radmen=true;

        end_time = System.currentTimeMillis();
        difference=end_time-start_time;



        //     System.out.println(difference);

    }

    public void interestZeroPic(){
        boolean count;
        int cot;

        for(int i=0;i<zx;i++)//x
            for(int j=0;j<zy;j++){//y
                if(pixmap[i][j]>0){//если это участок фигуры
                    count = true;//если натыкаемся на рамку false

                    if(mas[i][j]>0)mas[i][j]-=1;//в while всегда прибавляется 1
                    cot = mas[i][j];

                    do{
                        cot++;

                        createCircle(cot);

                        for (int k=0;k<test.get(cot-1).size();k++){

                            if(pixmap[i + k][j - test.get(cot-1).get(k)[0]]>1){count = false;break;}
                            if(pixmap[i + test.get(cot-1).get(k)[0]][j - k]>1){count = false;break;}

                            if(pixmap[i + k][j + test.get(cot-1).get(k)[0]]>1){count = false;break;}
                            if(pixmap[i + test.get(cot-1).get(k)[0]][j + k]>1){count = false;break;}

                            if(pixmap[i - k][j - test.get(cot-1).get(k)[0]]>1){count = false;break;}
                            if(pixmap[i - test.get(cot-1).get(k)[0]][j - k]>1){count = false;break;}

                            if(pixmap[i - k][j + test.get(cot-1).get(k)[0]]>1){count = false;break;}
                            if(pixmap[i - test.get(cot-1).get(k)[0]][j + k]>1){count = false;break;}
                        }


                   /*     for(int k=-cot+1;k<=cot-1;k++){//создаем оболочку квадрата
                            if(pixmap[i+k][j-cot+1]>1){count = false;break;}//верж
                            if(pixmap[i-cot+1][j+k]>1){count = false;break;}//лево
                            if(pixmap[i+k][j+cot-1]>1){count = false;break;}//низ
                            if(pixmap[i+cot-1][j+k]>1){count = false;break;}//право
                        }*/
                    }while(count);

                    if(cot>=0){

                        mas[i][j] = cot;

                        if(max<cot)max=cot;//для проверки max*0,62

                        if(cot>=2){
                            if(pixmap[i+1][j-1]>0&&mas[i+1][j-1]<cot)mas[i+1][j-1]=cot-2;//проверка не пройденных зон
                            if(pixmap[i+1][j]>0&&mas[i+1][j]<cot)mas[i+1][j]=cot-1;
                            if(pixmap[i+1][j+1]>0&&mas[i+1][j+1]<cot)mas[i+1][j+1]=cot-2;
                            if(pixmap[i][j+1]>0&&mas[i][j+1]<(cot))mas[i][j+1]=cot-1;
                        }

                    }
                }

            }

    }
    void radiusOkr(ArrayList<int[]> arrayList, int r){//создаем часть дуги окружности по радиусу
        boolean break1=false;

        for (int i=0;i<=r;i++){
            if (break1)break;//достигли диагонали

            arrayList.add(new int[1]);//x и y
            for (int j=0;j<=r;j++) {//проверяем по y

                if ((i * i + (r - j) * (r - j) <= (r * r))) {

                    arrayList.get(i)[0]=r - j;

                    if(i==r-j) break1=true;//точки по x совпадают
                    else if(i>r-j) break1=true;

                    break;

                }//if
            }//j

        }//i
    }

    void createCircle(int r){//если радиуса нет, создаем

        int size=test.size();
        if(size<r)
            for (int i=size;i<r;i++){
                test.add(new ArrayList<int[]>());
                radiusOkr(test.get(i),i);
            }

    }

    public void dispose(){
        test.clear();
    }

}
