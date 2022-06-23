package com.find.by.secondclass;


public class ZeroPic {
    int [][] pixmap;

    int zx;
    int zy;

    int [][] mas;

    public ZeroPic(int[][] pixmap) {
        this.pixmap = pixmap;
        zx = pixmap.length;
        zy = pixmap[0].length;
        mas = new int[zx][zy];
        interestZeroPic();

    }

    public void interestZeroPic(){
        for(int i=0;i<zx;i++)
            for(int j=0;j<zy;j++){
                if(pixmap[i][j]>0){//масса вокруг точки
                    int count = 0;//внутренности фигур
                    int x = i;
                    int y = j;
                    int cot = 0;


                    do{

                        for(int k=x-cot;k<=x+cot;k++)
                            for(int l=y-cot;l<=y+cot;l++){
                                //     if((k==x-cot||k==x+cot)||(l==y-cot||l==y+cot)) //проверяет рамку
                                if(pixmap[k][l]>1){//проверка на рамки
                                    count += pixmap[k][l];//считаем рамки без начинки

                                }

                            }

                        cot++;
                    }while(count==0);

                    if(cot>=0){

                        mas[i][j] = cot;

                    }
                }

            }
       // System.out.println(pixmap.getPixel(1,1));

    }
}
