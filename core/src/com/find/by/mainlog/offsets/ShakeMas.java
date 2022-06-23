package com.find.by.mainlog.offsets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShakeMas {
    ArrayList<int []> pixmap;

    ArrayList<int []> mas;

    Pixmap whiteQ;

    public ShakeMas(ArrayList<int []> pixmap, Pixmap whiteQ) {
        this.pixmap = pixmap;
        mas = new ArrayList<int[]>();

        interestShakeOblast();

        this.whiteQ = whiteQ;

        whiteQuad();

        takeScreenshot();
    }
    public void interestShakeOblast(){//проверка на наличие всех номеров 1,2,3,4,5,6,7,8

        for(int i=0;i<pixmap.size();i++){
            if(pixmap.get(i)[0]!=0){
                mas.add(new int [1]);
           //     mas.get(mas.size()-1)[0] = mas.size()-1;
                mas.get(mas.size()-1)[0] = pixmap.get(i)[0];

            //    System.out.print(mas.get(mas.size()-1)[0]);
            //    System.out.println(mas.get(mas.size()-1)[1]);
            }
        }

        System.out.println();
        System.out.print(mas.size());
        System.out.print(" ");
        System.out.println("фигур");

    }

    public void whiteQuad(){
        int x1;
        int y1;
        int x2;
        int y2;

      //  int fig = 1;

        for(int ram=0;ram<mas.size();ram++){
            x1=pixmap.get(mas.get(ram)[0]-1)[1]-1;//для рамки
            y1=pixmap.get(mas.get(ram)[0]-1)[2]-1;
            x2=pixmap.get(mas.get(ram)[0]-1)[3]+1;
            y2=pixmap.get(mas.get(ram)[0]-1)[4]+1;

          //  System.out.println(x1);
         //   System.out.println(x2);

   /*         whiteQ.setColor(Color.WHITE);
            //whiteQ.fill();
            for(int i=x1;i<=x2;i++){
                whiteQ.drawPixel(i,y1);
                whiteQ.drawPixel(i,y2);
            }

            for(int i=y1;i<=y2;i++){
                whiteQ.drawPixel(x1,i);
                whiteQ.drawPixel(x2,i);
            }*/
        }



    }


    public void takeScreenshot() {

/*        byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0,
                Gdx.graphics.getBackBufferWidth(),
                Gdx.graphics.getBackBufferHeight(), true);

        Pixmap pixmap =  new Pixmap(Gdx.graphics.getBackBufferWidth(),
                                    Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);

        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
*/
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH-mm-ss");


        PixmapIO.writePNG(
                Gdx.files.local("Выходные/out.png"),    //dateFormat.format(new Date()) + ".png"
                whiteQ);
        // pixmap.dispose();
    }




}
