package com.find.by.mainlog.offsets;

import java.util.ArrayList;

public class FragmentPic {
    int [][] pixmap;
    int zx;
    int zy;
    int [][] mas;

    int [] tester;
    boolean tester1;

    public ArrayList<int []> oblast;

    public FragmentPic(int[][] pixmap) {
        this.pixmap = pixmap;
        zx = pixmap.length;
        zy = pixmap[0].length;
        mas = new int[zx][zy];

        tester = new int[5];

        oblast = new ArrayList<int[]>();

        interestSizeOblastPic();

        //textOut();

    }
    public void interestSizeOblastPic(){//разбиение всех фигур на номера

        tester1 = false;

        int mark = 0;//pWeight;//область 1-3!!!!!!!!!!!
        //weightF = mark;

        int figure=0;
        int figureCount=0;

        for(int i=0;i<zx;i++){
            for(int j=0;j<zy;j++){
                if((pixmap[i][j]-1)>=mark){//проход по красным клеткам, int[][] уже задан

                    int cot = 1;// область вокруг, 1 клетка
                    int x = i;
                    int y = j;

                    boolean check = false;//проверка, есть рядом красное?

                    for(int ii=x-cot;ii<=x+cot;ii++){
                        for(int jj=y-cot;jj<=y+cot;jj++){

                            if((mas[ii][jj]-1)>=mark){//поиск красных клеток, по умолчанию пусто

                                    check = true;//нашло красную точку какой-то фигуры

                                    if(!tester1)//работает для первого красного контакта
                                        if(figure<mas[ii][jj]){
                                            figure = mas[ii][jj];
                                        }

                                     if(figure>mas[ii][jj]){//ход нашел меньшее значение
                                                            //переприсваиваемся под меньшее

                                        oblast.get(mas[ii][jj]-1)[5]+=oblast.get(figure-1)[5];//объем фигуры шаг 2/3


                                        oblast.get(figure-1)[3]=i;//x2
                                            if(oblast.get(figure-1)[4]<j)oblast.get(figure-1)[4]=j-1;//y2 //обываем фигуру если находим меньший номер

                                        for(int kk=oblast.get(figure-1)[1];kk<=oblast.get(figure-1)[3];kk++){//переприсваиваем значения более значимой фигуре
                                            for(int ll=oblast.get(figure-1)[2];ll<=oblast.get(figure-1)[4];ll++){
                                                if(mas[kk][ll]==figure)
                                                    mas[kk][ll]=mas[ii][jj];
                                            }
                                        }

                                            //переприсваиваем область под меньшее
                                            if(oblast.get(mas[ii][jj]-1)[1]>oblast.get(figure-1)[1]){oblast.get(mas[ii][jj]-1)[1] = oblast.get(figure-1)[1];
                                         //       System.out.print(mas[ii][jj]);
                                         //       System.out.println(" 1");
                                    }
                                            if(oblast.get(mas[ii][jj]-1)[2]>oblast.get(figure-1)[2]){oblast.get(mas[ii][jj]-1)[2] = oblast.get(figure-1)[2];
                                          //      System.out.print(mas[ii][jj]);
                                          //      System.out.println(" 2");
                                    }
                                            if(oblast.get(mas[ii][jj]-1)[3]<oblast.get(figure-1)[3]){oblast.get(mas[ii][jj]-1)[3] = oblast.get(figure-1)[3];
                                            //    System.out.print(mas[ii][jj]);
                                            //    System.out.println(" 3");
                                    }
                                            if(oblast.get(mas[ii][jj]-1)[4]<oblast.get(figure-1)[4]){oblast.get(mas[ii][jj]-1)[4] = oblast.get(figure-1)[4];
                                            //   System.out.print(mas[ii][jj]);
                                            //    System.out.println(" 4");
                                    }


                                    oblast.get(figure-1)[0]=0;

                                    figure = mas[ii][jj];


                                }

                                if(figure<mas[ii][jj]){//ход нашел большее значение
                                                        //переприсваиваем участок под меньшее



                                    final int oblastMas = mas[ii][jj]; //необходимое зло, mas[ii][jj] стирается после первой правки, вспомнить 19 ячейку

                                    oblast.get(figure-1)[5]+=oblast.get(oblastMas-1)[5];//объем фигуры шаг 3/3

                                    for(int kk=oblast.get(oblastMas-1)[1];kk<=oblast.get(oblastMas-1)[3];kk++){//переприсваиваем значения более значимой фигуре
                                        for(int ll=oblast.get(oblastMas-1)[2];ll<=oblast.get(oblastMas-1)[4];ll++){

                                             if(mas[kk][ll]==oblastMas){ //не делаем так mas[kk][ll]==mas[ii][jj], mas[ii][jj] стирается после первой правки, вспомнить 19 ячейку

                                                mas[kk][ll]=figure;//переприсваиваем участок под меньшее

                                            }


                                        }
                                    }

                                            //переприсваиваем область под меньшее
                                            if(oblast.get(oblastMas-1)[1]<oblast.get(figure-1)[1]){oblast.get(figure-1)[1] = oblast.get(oblastMas-1)[1];
                                      //            System.out.print(oblast.get(figure-1)[0]);
                                      //             System.out.println(" 1");
                                            }
                                            if(oblast.get(oblastMas-1)[2]<oblast.get(figure-1)[2]){oblast.get(figure-1)[2] = oblast.get(oblastMas-1)[2];
                                       //             System.out.print(oblast.get(figure-1)[0]);
                                      //             System.out.println(" 2");
                                            }
                                            if(oblast.get(oblastMas-1)[3]>oblast.get(figure-1)[3]){oblast.get(figure-1)[3] = oblast.get(oblastMas-1)[3];
                                       //               System.out.print(oblast.get(figure-1)[0]);
                                       //               System.out.println(" 3");
                                            }
                                            if(oblast.get(oblastMas-1)[4]>oblast.get(figure-1)[4]){oblast.get(figure-1)[4] = oblast.get(oblastMas-1)[4];
                                         //          System.out.print(oblast.get(figure-1)[0]);
                                        //            System.out.println(" 4");
                                            }

                                          oblast.get(oblastMas-1)[0]=0;//убираем участок из поиска

                                }

                            }
                        }
                    }


                    if(!check){//есть красная точка, но рядом фигур не найдено
                        figureCount++;
                        figure = figureCount;
                        oblast.add(new int[6]);
                    }


                        if(!tester1){
                            if(oblast.get(figure-1)[0]==0){//срабатывает в первый раз
                                oblast.get(figure-1)[0]=figure;

                                oblast.get(figure-1)[1]=i;//x1
                                oblast.get(figure-1)[2]=j;//y1

                                oblast.get(figure-1)[3]=i;//x2
                                oblast.get(figure-1)[4]=j;//y2

                            }
                            if(oblast.get(figure-1)[2]>j)//срабатывает во второй и последующий
                                oblast.get(figure-1)[2]=j;//y1

                            tester1 = true;//начало красной полосы
                        }

                    mas[i][j] = figure; //если в области нет фигур, но есть красная точка, ставим новую фигуру
                    oblast.get(mas[i][j]-1)[5]++;//объем фигуры шаг 1/3

                }else{

                    if(tester1){//завершение области при обрыве красного
                        oblast.get(figure-1)[3]=i;//x2
                        if(oblast.get(figure-1)[4]<j)oblast.get(figure-1)[4]=j-1;//y2
                    }
                    tester1 = false;


                    figure = 0;//для первого красного контакта

                }
            }//figure = figureCount;
        }

    /*    System.out.println(oblast.get(18)[0]);
        System.out.println(oblast.get(18)[1]);
        System.out.println(oblast.get(18)[2]);
        System.out.println(oblast.get(18)[3]);
        System.out.println(oblast.get(18)[4]);*/
       // System.out.println(oblast.get(21)[5]);




    }

   /* public void textOut(){
        String s="";
        for(int i=0;i<oblast.size();i++){
            if(oblast.get(i)[0]>0){
    //            System.out.print(oblast.get(i)[5]);
    //            System.out.print(" ");
    //            System.out.println(i+1);

                s+=oblast.get(i)[5];
                s+="\r\n";

            }

        }


        FileHandle file = Gdx.files.local("Выходные/content.txt");
        file.writeString(s, false);
       // System.out.println(s);
    }*/

   public void dispose(){
       oblast.clear();
   }

}
