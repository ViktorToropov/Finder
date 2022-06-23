package com.find.by.mainlog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.find.by.mainlog.offsets.BorderEdge;
import com.find.by.mainlog.offsets.Col2;
import com.find.by.mainlog.offsets.FragmentPic;
import com.find.by.mainlog.offsets.ImgScaling1;
import com.find.by.mainlog.offsets.Radius;
import com.find.by.mainlog.offsets.RenderModule;
import com.find.by.mainlog.offsets.ShakeMas;


import java.util.ArrayList;

public class Repeater {

    RenderModule renderModule;

    int pWidth = 1824; //855 + 2;//3648
    int pHeight = 1368;//527 + 2;//2736

    int maxObj=150;
    int scetObj=0;

    int maximum=0;

    ImgScaling1 imgScaling1;

    boolean[] repeater = new boolean[3];

    BorderEdge borderEdge;

    Col2 col2;
    Radius radius;
    ArrayList<int[]> outputData = new ArrayList<int[]>();

    FragmentPic fragmentPic;
    ShakeMas shakeMas;

    boolean radmen=false;//если радиус меньше 0,62 от максимального

    boolean[] stages;

    int blackWhite;

    int resultLength;


    public Repeater(boolean[] stages, byte[] searchPicImg, int blackWhite, int resultLength) {

        this.resultLength = resultLength;

        this.stages = stages;
        //////////////
        Pixmap pixmap = new Pixmap(searchPicImg,0,searchPicImg.length);
        pWidth = pixmap.getWidth() + 2; //855 + 2;//3648
        pHeight = pixmap.getHeight() + 2;//527 + 2;//2736
        /////////////////

        renderModule = new RenderModule(new Pixmap(pWidth,pHeight, Pixmap.Format.RGB888));
        imgScaling1 = new ImgScaling1(pWidth,pHeight,searchPicImg,blackWhite);

    }

    public void render(){

        check();

        renderModule.render();

       // sPause();

    }
    void check(){

        if(!repeater[0]){
            imgScaling1.otrisovPic();
            renderModule.cT(imgScaling1.pixRet());

            if(imgScaling1.fin){
                repeater[0]=true;
          /*      repeater[1]=true;
                borderEdge = new BorderEdge(imgScaling1.pixRet());*/
            }

        }

        if(repeater[0]&&!repeater[1]){
            if(scetObj==0){
                borderEdge = new BorderEdge(imgScaling1.pixRet());
                col2 = new Col2(borderEdge.mas,maximum);
                radius = new Radius(imgScaling1.pixRet(),col2.mas,outputData);
                maximum=(int)(radius.max*0.36d);
            }else if(scetObj>0){
                borderEdge = new BorderEdge(radius.getPixmap());
                col2 = new Col2(borderEdge.mas,maximum);
                if(!col2.radmen)
                radius = new Radius(radius.getPixmap(),col2.mas,outputData);
            }

            scetObj++;
            if(col2.radmen)repeater[1]=true;//выход scetObj>=maxObj   col2.radmen

            renderModule.cT(radius.getPixmap());



        }
        if(repeater[1]&&!repeater[2]){

            for (int i=0;i<outputData.size();i++){
                System.out.print(outputData.get(i)[0]);
                System.out.print(";");
                System.out.println(outputData.get(i)[1]);
            }
            textOut();

        //    System.out.println(maximum);

            fragmentPic = new FragmentPic(borderEdge.mas);
            shakeMas = new ShakeMas(fragmentPic.oblast,imgScaling1.pixRet());
            repeater[2]=true;
            stages[14]=true;
        }

    }

    public void textOut(){
        String s="";
        for(int i=0;i<outputData.size();i++){

                s+=(double)outputData.get(i)[0]/(double)resultLength;
                s+=";";
                s+=outputData.get(i)[1];
                s+="\r\n";

        }



        FileHandle file = Gdx.files.local("Выходные/content.txt");
        file.writeString(s, false);
        // System.out.println(s);
    }
    public void dispose(){
        renderModule.dispose();

        imgScaling1.dispose();

        col2.dispose();

        fragmentPic.dispose();


    }

    public ArrayList<int[]> getOutputData() {
        return outputData;
    }
}
