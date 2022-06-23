package com.find.by.secondclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by ADMIN on 06.05.2018.
 */

public class Paster {
    Pixmap pixmap;
    Pixmap interestPix;
    Texture texture;

    Pixmap pixmapPic;
    Pixmap interestZeroPic;
    Pixmap massaVokrugPic;
    Pixmap interestSizeOblastPic;
    Pixmap interestShakeOblastPic;
    Pixmap interestRenameOblastPic;

    Pixmap shakeRenamePic;
    Pixmap figureConsistPic;

    BitmapFont bitmapFont;
    BitmapFont interestFont;
    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;

    ArrayList<Integer> arrayList;
    ArrayList<Integer> interest;
    ArrayList<Integer> figure;
    ArrayList<Integer> figureCenter;
    ArrayList<Integer> interestObject;
    ArrayList<Integer> interestCount;
    ArrayList<Integer> interestBorders;
    ArrayList<ArrayList<Integer>> interestShakeMas;
    ArrayList<ArrayList<Integer>> figureConsist;

    ArrayList<Long> setBound;



    //////////////////////////////////////////////////////////////
    int pWidth = 35+2;
   // int pWidth = imgStart.getWidth()+2;
    int pHeight = 35+2;
  //  int pHeight = imgStart.getHeight()+2;
    int pWeight = 0;
    int pFigureNumb = 0;

    boolean pOne = false;
    boolean pTwo = false;
    boolean pThree = false;
    //////////////////////////////////////////////////////////////




    int zx,zy;//размеры проверки
    float qx = 0,qy = 0;//начальные координаты клетки

    Stage stage;

    int id=1;
    int weightF; //вес основы фигуры
    private boolean turn = false;

    int interestRename;//количество фигур
    int figureConsist1;//количество фигур

    public Paster() {
      //  pHeight = pWidth;
        bitmapFont = new BitmapFont();

        interestFont = new BitmapFont();
        interestFont.getData().setScale(0.75f,0.75f);

        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();

        interestPix = new Pixmap(pWidth,pHeight, Pixmap.Format.RGBA8888);

        pixmap = new Pixmap(pWidth,pHeight, Pixmap.Format.RGBA8888);
        pixmap.setColor(176/255f,100/255f,0,1);
        pixmap.fill();

    //    pixmapPic = new Pixmap(pWidth,pHeight, Pixmap.Format.RGBA8888);//вес оболочки
    //    interestZeroPic = new Pixmap(pWidth,pHeight, Pixmap.Format.RGBA8888);//нули в округе
    //    massaVokrugPic = new Pixmap(pWidth,pHeight, Pixmap.Format.RGBA8888);//масса вокруг пикселя
    //    interestSizeOblastPic = new Pixmap(pWidth,pHeight, Pixmap.Format.RGBA8888);//масса вокруг пикселя
    //    interestShakeOblastPic = new Pixmap(1,pWidth * pHeight, Pixmap.Format.RGBA8888);//фигуры из номеров
    //    interestRenameOblastPic = new Pixmap(pWidth,pWidth*pHeight, Pixmap.Format.RGBA8888);

    //    shakeRenamePic = new Pixmap(pWidth,pWidth*pHeight, Pixmap.Format.RGBA8888);
   //     figureConsistPic = new Pixmap(pWidth,pWidth*pHeight, Pixmap.Format.RGBA8888);

        texture = new Texture(pixmap);

        zx = pixmap.getWidth(); zy = pixmap.getHeight();//+1

        arrayList = new ArrayList<Integer>();
        interest = new ArrayList<Integer>();
        figure = new ArrayList<Integer>();
        figureCenter = new ArrayList<Integer>();
        interestObject = new ArrayList<Integer>();
        interestCount = new ArrayList<Integer>();
        interestBorders = new ArrayList<Integer>();
        interestShakeMas = new ArrayList<ArrayList<Integer>>();
        figureConsist = new ArrayList<ArrayList<Integer>>();

        setBound = new ArrayList<Long>();

//////////////////////////////////////////////////////////
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // Button
        Button button1 = new Button(mySkin,"small");
        button1.setSize(col_width*2,row_height);
        button1.setPosition(col_width, Gdx.graphics.getHeight()-row_height*2);
        button1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               // takeScreenshot();
            pixmap.setColor(176/255f,100/255f,0,1);
            pixmap.fill();
            texture.draw(pixmap,0,0);
            arrayList.clear();
            interest.clear();
            figure.clear();
            figureCenter.clear();
            id=1;

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button1);
        // Button 2
        Button button2 = new Button(mySkin,"small");
        button2.setSize(col_width*2,row_height);
        button2.setPosition(col_width+200, Gdx.graphics.getHeight()-row_height*2);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(turn)turn = false;else turn = true;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button2);
        // Button 3
        Button button3 = new Button(mySkin,"small");
        button3.setSize(col_width*2,row_height);
        button3.setPosition(col_width+400, Gdx.graphics.getHeight()-row_height*2);
        button3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                long start_time;
                long end_time;
                long difference;
              //  long end_time = System.currentTimeMillis();
              //  long difference = end_time-start_time;

                start_time = System.currentTimeMillis();

          //      findRed(); //37
          //      interestSet(); //пиксели оболочки//38
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);


       //        findRedPic();

                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);
                System.out.println(difference);

        //        interestZero();//нахождение скопления 0//75
        //        interestZeroPic();
        //        findRedPicMas();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);
                System.out.println(difference);

                //interestPixmap();
         //       interestSizeOblast(); //разбиение пикселей на номера //область 1-3//122
         //       interestSizeOblastPic();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);

        //        interestShakeOblast();//количество номеров из разбиения//124
        //        interestShakeOblastPic();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);

        //        interestRenameOblast();//какие номера соединяются на границе//175
        //        interestRenameOblastPic();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);

        //        shakeRename();//формирование фигуры из номеров//185
         //       shakeRenamePic();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);

      //          figureCenterComplete();//завершение основы фигуры//193
      //          figureCenterCompletePic();//завершение основы фигуры//193
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);
/*
                interestObjectPix();//отрисовка красным//194
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);

                figureBordersInception();//поиск первичных границ//221
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);

                interestBorderPix();//отрисовка первичных границ//221
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
                setBound.add(difference);
*/
                System.out.println(setBound);

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
        stage.addActor(button3);

        // Button 4
        Button button4 = new Button(mySkin,"small");
        button4.setSize(col_width*2,row_height);
        button4.setPosition(col_width+400, Gdx.graphics.getHeight()-row_height*2-450);
        button4.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               //button4Push();
                button4PushMain();

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button4);
////////////////////////////////////////////////////////////////////////////




    }
    public void render(){
        stage.act();
        stage.draw();

        spriteBatch.begin();
        spriteBatch.draw(texture,100,100,400,400);
        bitmapFont.draw(spriteBatch,arrayList.toString(),50,50);
/*
        if(pOne){
            if(!interestObject.isEmpty()){
                for(int i=0;i<interestObject.size();i+=3){
                    int x = (int)(interestObject.get(i)*(400f/pWidth)+100);

                    int y = (int)(Gdx.graphics.getHeight()-interestObject.get(i+1)*(400f/pHeight)-100);
                    interestFont.draw(spriteBatch,Integer.toString(interestObject.get(i+2)),x,y);
                    // System.out.println(i);
                }

            }
        }

        if(pTwo){
            if(!figureCenter.isEmpty()){
                for(int i=0;i<figureCenter.size();i+=3){
                    int x = (int)(figureCenter.get(i)*(400f/pWidth)+100);

                    int y = (int)(Gdx.graphics.getHeight()-figureCenter.get(i+1)*(400f/pHeight)-100);
                    interestFont.draw(spriteBatch,Integer.toString(figureCenter.get(i+2)),x,y);
                    // System.out.println(i);
                }

            }
        }
        ////////////////////////////////////////////////////////////////////////////////////
        if(pThree){
            if(!interest.isEmpty()){
                for(int i=0;i<interest.size();i+=3){
                    int x = (int)(interest.get(i)*(400f/pWidth)+100);

                    int y = (int)(Gdx.graphics.getHeight()-interest.get(i+1)*(400f/pHeight)-100);
                    interestFont.draw(spriteBatch,Integer.toString(interest.get(i+2)),x,y);
                    // System.out.println(i);
                }

            }
        }
        ///////////////////////////////////////////////////////////////////////////////////
*/
        spriteBatch.end();
/*
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        if(turn){
        quad(qx,qy);
        checkQuad();
        }else{
        quad(qx,qy);
        visionQuad();
        boundSet();
        }
        shapeRenderer.end();*/
    }



    public void findRed(){

        for(int i=0;i<zx;i++){
            for(int j=0;j<zy;j++){

                if (pixmap.getPixel(i,j)!=255){


                    if(!findRedRepeat(i,j)){//проверка на повтор в array list
                    arrayList.add(i);//x
                    arrayList.add(j);//y

                    arrayList.add(id); id++;

                    if(pixmap.getPixel(i,j+1)==255)arrayList.add(1);else arrayList.add(0);
                    if(pixmap.getPixel(i-1,j+1)==255)arrayList.add(1);else arrayList.add(0);
                    if(pixmap.getPixel(i-1,j)==255)arrayList.add(1);else arrayList.add(0);
                    if(pixmap.getPixel(i-1,j-1)==255)arrayList.add(1);else arrayList.add(0);

                    if(pixmap.getPixel(i,j-1)==255)arrayList.add(1);else arrayList.add(0);
                    if(pixmap.getPixel(i+1,j-1)==255)arrayList.add(1);else arrayList.add(0);
                    if(pixmap.getPixel(i+1,j)==255)arrayList.add(1);else arrayList.add(0);
                    if(pixmap.getPixel(i+1,j+1)==255)arrayList.add(1);else arrayList.add(0);
                    }
                }
            }
        }
    }

    public void findRedPic(){//найти веса оболочки//20 убрать
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

                    pixmapPic.drawPixel(i,j,count + 1);//все цвета начинаются с 0, если берем 8, пишется 7


                }
            }
        }
        texture.draw(pixmapPic,0,0);
    }

    public void findRedPicMas(){//найти веса оболочки//20 убрать
        ArrayList<ArrayList<Integer>> mas = new ArrayList<ArrayList<Integer>>(zy);
        int cot = 1;
        for(int i=0;i<zx;i++){
            mas.add(new ArrayList<Integer>(zx));
            for(int j=0;j<zy;j++){

                if (pixmap.getPixel(i,j)!=255){
                    int count = 1;//внутренности фигур
                    int x = i;
                    int y = j;
                    for(int k=x-cot;k<=x+cot;k++)
                        for(int l=y-cot;l<=y+cot;l++)
                            if(pixmap.getPixel(k,l)==255)
                                count++;

                    mas.get(i).add(count);//все цвета начинаются с 0, если берем 8, пишется 7


                }
            }
        }
       // texture.draw(pixmapPic,0,0);
    }

    public void quad(float x,float y){//белый квадрат

        double koff = 400f/pWidth; //размеры на экране/реальные размеры
        float mn=(float)(koff*1);//клетка на эеране
        x=x*mn; y=-y*mn;//привязка к координатам
        x=x+100;y=y+100;//координаты вставки картины

        int kf1 = 400;//длина/ширина картины

        y=y+kf1;
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.line(x,y,x+mn,y);
        shapeRenderer.line(x+mn,y,x+mn,y-mn);
        shapeRenderer.line(x+mn,y-mn,x,y-mn);
        shapeRenderer.line(x,y-mn,x,y);
    }
    public void checkQuad(){//отрисовка пикселя
        if(!Gdx.input.isTouched()){
            qx = (int)((Gdx.input.getX()-100)/(400f/pWidth));
            qy = (int)(-(Gdx.graphics.getHeight() - Gdx.input.getY()-100-400)/(400f/pHeight));
            checkRamki(qx,qy);

        }else if(Gdx.input.getY()>100&& Gdx.input.justTouched()) pixelDown((int)qx,(int)qy);

    }
    public void visionQuad(){
        if(!Gdx.input.isTouched()){
            qx = (int)((Gdx.input.getX()-100)/(400f/pWidth));
            qy = (int)(-(Gdx.graphics.getHeight() - Gdx.input.getY()-100-400)/(400f/pHeight));
            checkRamki(qx,qy);

        }else if(Gdx.input.justTouched()){
            if(pixmap.getPixel((int)qx,(int)qy)!=255){

                    arrayList.add((int)qx);//x
                    arrayList.add((int)qy);//y

            }else arrayList.clear();
        }

    }
    public void checkRamki(float qx,float qy){
        if(qx>pixmap.getWidth()-1)this.qx=pixmap.getWidth()-1;else if(qx<0)this.qx=0;
        if(qy>pixmap.getHeight()-1)this.qy=pixmap.getHeight()-1;else if(qy<0)this.qy=0;
    }
    public void pixelDown(int qx,int qy){
        pixmap.drawPixel(qx,qy, Color.argb8888(1,0,0,150/255f));
        texture.draw(pixmap,0,0);
    }
    public boolean findRedRepeat(int i, int j){//проверка на повтор
        boolean check = false;

        for(int ii=0;ii<arrayList.size();ii+=11){
            if((arrayList.get(ii)==i)&&(arrayList.get(ii+1)==j)){check = true; ii+=arrayList.size();} else check = false;
        }

        return check;
    }
    public void boundSet(){
        for (int i=0;i<arrayList.size();i+=4){
            if(arrayList.size()%4==0){
                int x,x1,y,y1;

                x = (int)(arrayList.get(i)*(400f/pWidth)+100+(400f/pWidth)/2);
                x1 = (int)(arrayList.get(i+2)*(400f/pWidth)+100+(400f/pWidth)/2);
                y = (int)(Gdx.graphics.getHeight()-arrayList.get(i+1)*(400f/pHeight)-100-(400f/pHeight)/2);
                y1 = (int)(Gdx.graphics.getHeight()-arrayList.get(i+3)*(400f/pHeight)-100-(400f/pHeight)/2);

                shapeRenderer.line(x,y,x1,y1);

            }
        }
    }
    public void interestSet(){//пиксели оболочки
        for(int i=0;i<arrayList.size();i+=11){
            interest.add(arrayList.get(i));
            interest.add(arrayList.get(i+1));
            int count = arrayList.get(i+3)+arrayList.get(i+4)+arrayList.get(i+5)
                      + arrayList.get(i+6)+arrayList.get(i+7)+arrayList.get(i+8)
                      + arrayList.get(i+9)+arrayList.get(i+10);
            interest.add(count);
           // System.out.println(count);
        }
    }
    public void interestZero(){//проверка площадь вокруг нуля
        for(int i=0;i<interest.size();i+=3){
            if(interest.get(i+2)==0){//не реализовано, масса вокруг пикселя, для более точного расчета центра для figureCenter

                figure.add(interest.get(i));
                figure.add(interest.get(i+1));

                figure.add(interestSearch(interest,interest.get(i),interest.get(i+1)));
            }
            figureCenter.add(interest.get(i));
            figureCenter.add(interest.get(i+1));

            figureCenter.add(interestPosCenter(interest,interest.get(i),interest.get(i+1)));

        }
    }
    public void interestZeroPic(){
        for(int i=0;i<zx;i++)
            for(int j=0;j<zy;j++){
                if(pixmapPic.getPixel(i,j)>0){//масса вокруг точки
                    int count = 0;//внутренности фигур
                    int x = i;
                    int y = j;
                    int cot = 1;
            /*        for(int k=x-cot;k<=x+cot;k++)
                        for(int l=y-cot;l<=y+cot;l++)
                            if(pixmapPic.getPixel(k,l)>0)
                                if(k!=i||l!=j)//проверить!!!??
                                    count+=pixmapPic.getPixel(k,l)-1;

                    count++;//поправка, чтобы не совпадало с нулевым фоном
                    massaVokrugPic.drawPixel(i,j,(count+1)*5);
*/


                    count = 0;
                    cot = 0;
               //     if(interestZeroPic.getPixel(i,j)!=0) cot = interestZeroPic.getPixel(i,j);
                    do{
                        for(int k=x-cot;k<=x+cot;k++)
                            for(int l=y-cot;l<=y+cot;l++)
                                if((k==x-cot||k==x+cot)||(l==y-cot||l==y+cot))
                                    if(pixmapPic.getPixel(k,l)>0)
                                        count += pixmapPic.getPixel(k,l)-1;
                        cot++;
                    }while(count==0);

                    if(cot>=0){
                   //     for(int m = 0;m <= cot;m++)
                    //        for(int k=x-m;k<=x+m;k++)
                     //           for(int l=y-m;l<=y+m;l++)
                      //              if((k==x-m||k==x+m)||(l==y-m||l==y+m))
              //                          if((interestZeroPic.getPixel(k,l)==0)||(interestZeroPic.getPixel(k,l)>(cot-m)))

                   //     interestZeroPic.drawPixel(k,l,cot-m);
                        interestZeroPic.drawPixel(i,j,cot+1);
                    //    interest.add(i);
                    //    interest.add(j);
                    //    interest.add(cot);
                    }
                }

            }
            texture.draw(interestZeroPic,0,0);
      //  System.out.println(pixmapPic.getPixel(2,2));

    }
    public int interestSearch(ArrayList<Integer> arr,int x,int y){//сумма вокруг пикселя
        int cot=1;
        int count=0;
        for(int i=0;i<arr.size();i+=3){
            if(arr.get(i)>=x-cot)
                if(arr.get(i+1)>=y-cot)
                    if(arr.get(i)<=x+cot)
                        if(arr.get(i+1)<=y+cot)count+=arr.get(i+2);


        }
       // if(count==0)
     //   System.out.println(count);
        return count;

    }
    public int interestPosCenter(ArrayList<Integer> arr,int x,int y){//максимум площади вокруг 0
        int cot = 0;

        int count=0;

        do{
            for(int i=0;i<arr.size();i+=3){
                if(arr.get(i)>=x-cot)
                    if(arr.get(i+1)>=y-cot)
                        if(arr.get(i)<=x+cot)
                            if(arr.get(i+1)<=y+cot)count+=arr.get(i+2);
            }
            cot++;
        }while(count==0);

        cot--;
      return cot;
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
                Gdx.files.local("123.png"),    //dateFormat.format(new Date()) + ".png"
                pixmap);
       // pixmap.dispose();
    }

    public void interestObjectPix(){///фигуры 1-бесконечность.........................................................
        int mark = pFigureNumb;
        //System.out.println(interestObject);

        for(int i=0;i<interestObject.size();i+=3){
            if(interestObject.get(i+2)==mark){
                interestPix.setColor(0,0,1,1);
                interestPix.drawPixel(interestObject.get(i),interestObject.get(i+1));
            }else {
                interestPix.setColor(1,1,1,150/255f);
                interestPix.drawPixel(interestObject.get(i),interestObject.get(i+1));
            }
        }
        texture.draw(interestPix,0,0);
    }
    public void interestSizeOblast(){//разбиение всех фигур на номера
        int mark = pWeight;//область 1-3!!!!!!!!!!!
        weightF = mark;

        int figure=1;
        int figureCount=0;

        for(int i=0;i<figureCenter.size();i+=3){
            if(figureCenter.get(i+2)>=mark){



                        //System.out.println(figure);


                        int cot = 1;// область вокруг 1 клетка
                        int x = figureCenter.get(i);
                        int y = figureCenter.get(i+1);
                        boolean check = false;
                        for(int ii=0;ii<figureCenter.size();ii+=3){
                            if(figureCenter.get(ii)>=x-cot)
                                if(figureCenter.get(ii+1)>=y-cot)
                                    if(figureCenter.get(ii)<=x+cot)
                                        if(figureCenter.get(ii+1)<=y+cot)
                                            if(figureCenter.get(ii+2)>=mark){

                                                //check = false;//проверка, не дублировать пиксели
                                                for(int iii=0;iii<interestObject.size();iii+=3){//System.out.println("sovpadenie");
                                                    if(interestObject.get(iii)==figureCenter.get(ii))
                                                        if(interestObject.get(iii+1)==figureCenter.get(ii+1)){
                                                            check = true;
                                                                figure=interestObject.get(iii+2);


                                                        }

                                                }


                                            }
                        }
                    if(!check){//System.out.println("ne sovpadenie");
                        figureCount++;
                        figure=figureCount;
                    }


             //   }


                interestObject.add(figureCenter.get(i));
                interestObject.add(figureCenter.get(i+1));
                interestObject.add(figure);



            }else{
      /*          interestObject.add(figureCenter.get(i));
                interestObject.add(figureCenter.get(i+1));

                interestObject.add(0);*/
            }
            //i=i+figureCenter.size();
        }
        System.out.println(interestObject.size());
    }
    public void interestSizeOblastPic(){//разбиение всех фигур на номера
        int mark = pWeight;//область 1-3!!!!!!!!!!!
        weightF = mark;

        int figure=1;
        int figureCount=0;

        for(int i=0;i<zx;i++){
            for(int j=0;j<zy;j++){
                if((interestZeroPic.getPixel(i,j)-1)>=mark){



                int cot = 1;// область вокруг 1 клетка
                int x = i;
                int y = j;
                boolean check = false;
                    for(int ii=x-cot;ii<=x+cot;ii++){
                        for(int jj=y-cot;jj<=y+cot;jj++){
                                    if((interestZeroPic.getPixel(ii,jj)-1)>=mark){
                                            //поставить min минимум вокруг чтобы избавиться от дополнительных цифр

                                                if(interestSizeOblastPic.getPixel(ii,jj)!=0){
                                                    check = true;
                                                    figure=interestSizeOblastPic.getPixel(ii,jj);

                                                    }

                                    }
                }}
                if(!check){//System.out.println("ne sovpadenie");
                    figureCount++;
                    figure=figureCount;
                }


                //   }

                interestSizeOblastPic.drawPixel(i,j,figure+1);

            //    interestObject.add(figureCenter.get(i));
            //    interestObject.add(figureCenter.get(i+1));
            //    interestObject.add(figure);



            }
        }}
       // System.out.println(interestObject.size());
    }
    public void interestShakeOblast(){//проверка на наличие всех номеров 1,2,3,4,5,6,7,8

        for(int i=0;i<interestObject.size();i+=3){
            if(interestCount.isEmpty())interestCount.add(interestObject.get(i+2));
                else{
                    boolean check = false;
                    for(int j=0;j<interestCount.size();j++){
                        if(interestCount.get(j)==interestObject.get(i+2))check = true;
                    }
                if(!check) interestCount.add(interestObject.get(i+2));

                }
        }
        System.out.println(interestCount);
    }
    public void interestShakeOblastPic(){//проверка на наличие всех номеров 1,2,3,4,5,6,7,8
        int count = 0;
        for(int i=0;i<zx;i++){
            for(int j=0;j<zy;j++){
                if(interestSizeOblastPic.getPixel(i,j)>0) {

                    boolean check = false;
                    for (int k = 0; k < count; k++) {//убираем повторы
                        if (interestSizeOblastPic.getPixel(i, j) == interestShakeOblastPic.getPixel(0, k))
                            check = true;
                    }

                    if (!check) {
                        interestShakeOblastPic.drawPixel(0, count, interestSizeOblastPic.getPixel(i, j) + 1);
                        count++;
                    }
                }

            }
        }
      //  for(int i=0;i<count;i++) System.out.print(interestShakeOblastPic.getPixel(0,i));
        interestRename = count;
       // System.out.println(interestRename);
    }
    public void interestRenameOblast(){//какие номера соединяются между собой
        for(int i=0;i<interestCount.size();i++){
            interestShakeMas.add(new ArrayList<Integer>());
        }


        for(int i=0;i<interestCount.size();i++){
            for(int j=0;j<interestObject.size();j+=3){
                if(interestObject.get(j+2)==interestCount.get(i)){
                    if(interestShakeMas.get(i).isEmpty()){
                        interestShakeMas.get(i).add(interestCount.get(i));
                    }else{
                        for(int k=0;k<interestObject.size();k+=3){
                            int x = interestObject.get(j);
                            int y = interestObject.get(j+1);
                            int cot=1;

                            if(interestObject.get(k)>=x-cot)
                                if(interestObject.get(k+1)>=y-cot)
                                    if(interestObject.get(k)<=x+cot)
                                        if(interestObject.get(k+1)<=y+cot)
                                            if(interestObject.get(k+2)!=interestCount.get(i)){
                                                boolean check = false;//проверка, не дублировать
                                                for(int m=0;m<interestShakeMas.get(i).size();m++)
                                                    if(interestShakeMas.get(i).get(m)==interestObject.get(k+2))
                                                        check = true;
                                                if(!check)interestShakeMas.get(i).add(interestObject.get(k+2));
                                            }

                        }
                    }
                }
            }
        }
        System.out.println(interestShakeMas.size());
        for(int i=0;i<interestShakeMas.size();i++)
        System.out.println(interestShakeMas.get(i));
    }
    public void interestRenameOblastPic(){//какие номера соединяются между собой

        for(int j=0;j<zx;j++){//номера в массиве
            for(int j1=0;j1<zy;j1++){//номера в массиве
                 for(int i=0;i<interestRename;i++){//количество номеров

                    if(interestSizeOblastPic.getPixel(j,j1)==interestShakeOblastPic.getPixel(0,i)){//находим номер из списка
                    if(interestRenameOblastPic.getPixel(0,i)==0){//если заготовка пуста
                        interestRenameOblastPic.drawPixel(0,i,2);//добавляем номер на первую позицию//позиция 1 2-1=1
                        interestRenameOblastPic.drawPixel(1,i,interestShakeOblastPic.getPixel(0,i)+1);
                    }else{ ///*

                            int cot=1;

                            int x = j;
                            int y = j1;
                            for(int ii=x-cot;ii<=x+cot;ii++){
                                for(int jj=y-cot;jj<=y+cot;jj++){
                                    if(interestSizeOblastPic.getPixel(ii,jj)>0)
                                            if(interestSizeOblastPic.getPixel(ii,jj)!=interestShakeOblastPic.getPixel(0,i)){//проверяем номера соседей
                                                boolean check = false;//проверка, не дублировать

                                                for(int m=1;m<zx;m++) {//1 тк в нуле количество
                                                    if (interestRenameOblastPic.getPixel(m, i) == interestSizeOblastPic.getPixel(ii, jj))//если есть
                                                        check = true;
                                                    if(interestRenameOblastPic.getPixel(m+1, i)==0)m+=zx;
                                                }
                                                if(!check){


                                                    interestRenameOblastPic.drawPixel(0,i,2);//добавляем 2-1=1 к картине
                                                    interestRenameOblastPic.drawPixel(interestRenameOblastPic.getPixel(0,i),i,interestSizeOblastPic.getPixel(ii,jj)+1);//если нет добавляем к соседям

                                                }
                                            }

                        }}
                    }  //*/
                }

            }
        }}
        System.out.println(interestRename);//проверка и перебор
        for(int i=0;i<interestRename;i++){
            for(int j=0;j<10;j++){
                System.out.print(interestRenameOblastPic.getPixel(j,i));
                if(interestRenameOblastPic.getPixel(j+1,i)==0)j+=zx;
            }

            System.out.print(" d");
            System.out.println(i);
        }

    }


    public void shakeRename(){//создание фигур из номеров
       // interestShakeMas.get(0).remove(2);
        ArrayList<ArrayList<Integer>> tester = new ArrayList<ArrayList<Integer>>();

        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();

        for(int i=0;i<interestShakeMas.size();i++)
            arr.add(new ArrayList<Integer>());

        ArrayList<Integer> arr1 = new ArrayList<Integer>();



        for(int i=0;i<interestShakeMas.size();i++){//номера соседи// все массивы
            tester.add(new ArrayList<Integer>());
            for(int j=0;j<interestShakeMas.get(i).size();j++){//каждый элемент проверяется


                for(int k=0;k<interestShakeMas.size();k++){//номера соседи// все массивы
                    boolean check = false;//interestShakeMas.get(i).get(j) встречается в массиве соседа


                        boolean checkShake = false;//черный список номеров соседей//ищем под номером k или нет
                        for(int m=0;m<arr1.size();m++)
                            if(arr1.get(m)==k)checkShake = true;//не ищем

                        if(!checkShake)//нет номера //черный список номеров соседей
                             for(int l=0;l<interestShakeMas.get(k).size();l++){
                                if(interestShakeMas.get(i).get(j)==interestShakeMas.get(k).get(l))
                                    check = true;//interestShakeMas.get(i).get(j) встречается в массиве соседа
                             }

                    if(check){

                            arr1.add(k);
                            arr.get(i).add(k);

                        for(int jj=0;jj<interestShakeMas.get(k).size();jj++){//вставка в interestShakeMas

                            boolean checkLast = false;//вставка в interestShakeMas //проверка на повторы
                                for (int kk=0;kk<interestShakeMas.get(i).size();kk++)
                                    if(interestShakeMas.get(i).get(kk)==interestShakeMas.get(k).get(jj))
                                        checkLast = true;
                            if(!checkLast)
                                interestShakeMas.get(i).add(interestShakeMas.get(k).get(jj));
                        }






                    }
                }
            }
        }
        System.out.println(arr);
       // System.out.println(arr);
       // System.out.println(interestShakeMas);
        for(int i=0;i<arr.size();i++)
            if(arr.get(i).isEmpty())
                interestShakeMas.get(i).clear();
      //  System.out.println(arr);
     //   System.out.println(interestShakeMas);


        for (int i=0;i<interestShakeMas.size();i++){//перепись в конечный массив
            if(!interestShakeMas.get(i).isEmpty()){
                ArrayList<Integer> arr11 = new ArrayList<Integer>();

                for(int j=0;j<interestShakeMas.get(i).size();j++){

                    arr11.add(interestShakeMas.get(i).get(j));
                }
                figureConsist.add(arr11);
            }
        }
        System.out.println(figureConsist);
        for(int i=0;i<arr.size();i++)
            for(int j=0;j<arr.get(i).size();j++)
                tester.get(i).add(j);

        System.out.println(tester);
    }
    public void shakeRenamePic(){//создание фигур из номеров
        ArrayList<ArrayList<Integer>> tester = new ArrayList<ArrayList<Integer>>();

        Pixmap arr1Pic = new Pixmap(1,interestRename+1, Pixmap.Format.RGBA8888);

        Pixmap arrPic = new Pixmap(interestRename,interestRename+1, Pixmap.Format.RGBA8888);

        //interestShakeMas<><> какие номера соединяются между собой


        for(int i=0;i<interestRename;i++){//номера соседи// все массивы


            for(int j=1;j<interestRenameOblastPic.getPixel(0,i)+1;j++){//каждый элемент проверяется//+1 поправка на начало 1

                for(int k=0;k<interestRename;k++){//номера черного списка

                    boolean check = false;
                    boolean checkShake = false;

                    for(int m=1;m<arr1Pic.getPixel(0,0)+1;m++){
                        if(arr1Pic.getPixel(0,m)==(k))
                            checkShake = true;//список соседей в черном списке
                    }

                    if(!checkShake) {//нет списка соседей в черном списке

                        for(int l=1;l<interestRenameOblastPic.getPixel(0,k)+1;l++){
                            if(interestRenameOblastPic.getPixel(j,i)==interestRenameOblastPic.getPixel(l,k))//если встречается
                                check = true;//элемент встречается в другом списке соседей
                        }
                    }

                    if(check){

                            arr1Pic.drawPixel(0,0,2);
                            arr1Pic.drawPixel(0,arr1Pic.getPixel(0,0),k+1);//добавление в черный список номеров с соседями 0,1,2,3,4,5,6...

                            arrPic.drawPixel(0,i,2);
                            arrPic.drawPixel(arrPic.getPixel(0,i),i,k+1);//вставка номеров в фигуру, её формирование из номеров черного списка
                         //   tester.get(i).add(arr1Pic.getPixel(0,arr1Pic.getPixel(0,0))+1);

                        for(int jj=1;jj<interestRenameOblastPic.getPixel(0,k)+1;jj++){//добавляем номера из другого списка соседей, и соседей его соседей, формируем фигуру,

                            boolean checkLast = false;//вставка в interestShakeMas //проверка, не повторяется?

                            for (int kk=1;kk<interestRenameOblastPic.getPixel(0,i)+1;kk++)
                                if(interestRenameOblastPic.getPixel(kk,i)==interestRenameOblastPic.getPixel(jj,k))
                                    checkLast = true;//есть повтор

                            if(!checkLast){//добавляем соседей из проверяемого списка, в основной
                                interestRenameOblastPic.drawPixel(0,i,2);
                                interestRenameOblastPic.drawPixel(interestRenameOblastPic.getPixel(0,i),i,interestRenameOblastPic.getPixel(jj,k)+1);
                            }
                        }

                    }
                }
            }
        }

    figureConsist1 = 0;

        for (int i=0;i<interestRename;i++){//перепись в конечный массив
            if(!(arrPic.getPixel(0,i)==0)){

                for(int j=1;j<interestRenameOblastPic.getPixel(0,i)+1;j++){
                    figureConsistPic.drawPixel(0,figureConsist1,2);
                    figureConsistPic.drawPixel(figureConsistPic.getPixel(0,figureConsist1),figureConsist1,interestRenameOblastPic.getPixel(j,i)+1);
                    System.out.print(figureConsistPic.getPixel(figureConsistPic.getPixel(0,figureConsist1),figureConsist1));
                    System.out.print(" ");
                }
                figureConsist1++;
                System.out.println(" ");
            }
        }

    //interestRename = figureConsist1;
    }
    public void figureCenterComplete(){//завершение центра масс фигуры
        for(int i=0;i<figureConsist.size();i++){//количество фигур interestObject
            for(int j=0;j<interestObject.size();j+=3){
                for(int k=0;k<figureConsist.get(i).size();k++){
                    if(interestObject.get(j+2)==figureConsist.get(i).get(k))
                        interestObject.set(j+2,i);
                }
            }
        }

    }
    public void figureCenterCompletePic() {//завершение центра масс фигуры
        Pixmap zeroP = new Pixmap(100,100, Pixmap.Format.RGBA8888);
        Pixmap zeroP1 = new Pixmap(interestSizeOblastPic.getWidth(),interestSizeOblastPic.getHeight(), Pixmap.Format.RGBA8888);
        //    for(int i=0;i<figureConsist.size();i++){//количество фигур interestObject  figureConsistPic

       texture.draw(zeroP1,0,0);

        for (int i = 0; i < interestRename; i++) {//количество фигур interestObject  figureConsistPic

            //         for(int j=0;j<interestObject.size();j+=3){//interestSizeOblastPic
            for (int j = 0; j < interestSizeOblastPic.getWidth(); j++) {//interestSizeOblastPic
                interestSizeOblastPic.drawPixel(i, j,0);
                for (int l = 0; l < interestSizeOblastPic.getHeight(); l++) {//interestSizeOblastPic

                    //             for(int k=0;k<figureConsist.get(i).size();k++){//figureConsistPic
                    for (int k = 1; k < figureConsistPic.getPixel(0, i) + 1; k++) {//figureConsistPic
                        //  if(interestObject.get(j+2)==figureConsist.get(i).get(k))
                        if (interestSizeOblastPic.getPixel(j, l) == figureConsistPic.getPixel(k, i)) {


                         //   interestSizeOblastPic.setColor(Color.CLEAR);
                         //   interestSizeOblastPic.fill();
                        //    interestSizeOblastPic.drawPixmap(zeroP,j,l);

                           // interestSizeOblastPic.drawPixel(l, j, 2);
                          //  interestSizeOblastPic.drawPixel(l, j, i + 70);
                          //  System.out.println("punch");
                        //    interest.add(j);
                        //    interest.add(l);
                        //    interest.add(interestSizeOblastPic.getPixel(j,l));
                        }
                        //interestObject.set(j+2,i);
                    }
                }
            }

        }
        interestSizeOblastPic.fill();
    //    texture.draw(interestSizeOblastPic,0,0);


      //  texture.draw(zeroP,0,0);

        zeroP1.setColor(0.5f,0,0,0.5f);//2147483647
        zeroP1.drawPixel(0,0);
      //  zeroP1.drawPixmap(zeroP,-1,-1);
        //zeroP1.drawPixel(0,0);
     //   zeroP1.drawPixel(0,0);
       // zeroP1.drawPixel(0,0,2);



        System.out.println(Integer.toHexString(zeroP1.getPixel(0,0)));
        //System.out.println(zeroP1.getPixel(0,0));

    }
    public void figureBordersInception(){//создание первичных границ
        //figureCenter веса

        //figureConsist //количество фигур interestObject
        //interestObject.get(j+2) номер каждой фигуры

        for(int i=0;i<figureConsist.size();i++){
            for(int j=0;j<interestObject.size();j+=3){
                if(interestObject.get(j+2) == i){
                    int cot = weightF;
                    int count=0;
                    ArrayList<Integer> arr = figureCenter;
                    int x = interestObject.get(j);
                    int y = interestObject.get(j+1);
                    for(int k=0;k<arr.size();k+=3){//проверка окрестностей
                        if(arr.get(k)>=x-cot)
                            if(arr.get(k+1)>=y-cot)
                                if(arr.get(k)<=x+cot)
                                    if(arr.get(k+1)<=y+cot)
                                        if(figureCenter.get(k+2)!=weightF){
                                            boolean check = false;
                                            for(int l=0;l<interestBorders.size();l+=3){
                                                if(figureCenter.get(k)==interestBorders.get(l))
                                                    if(figureCenter.get(k+1)==interestBorders.get(l+1))
                                                        check = true;
                                            }
                                            if(!check){
                                                interestBorders.add(figureCenter.get(k));
                                                interestBorders.add(figureCenter.get(k+1));
                                                interestBorders.add(figureCenter.get(k+2));
                                            }
                                        }
                    }
                }
            }
        }
    }

    public void interestBorderPix(){//определение первичных границ зеленым
        int mark=0;
        //System.out.println(interestObject);

        for(int i=0;i<interestBorders.size();i+=3){
            if(interestBorders.get(i+2)>=mark){
                interestPix.setColor(0,255/255f,0,1);
                interestPix.drawPixel(interestBorders.get(i),interestBorders.get(i+1));
            }
        }
        texture.draw(interestPix,0,0);
    }
    public Color colC(int c){
        String str = Integer.toHexString(c);//2147483647//потолок

        String str1="";
        for(int i=0;i<8-str.length();i++)
            str1+=0;

        str1+=str;
        str=str1;
        System.out.println(str);
        return Color.valueOf(str);
    }

    public void button4Push(){
        // takeScreenshot();

        //уменьшаем
        Pixmap transfer = new Pixmap(Gdx.files.local("1234.png"));

        pixmap.setColor(255);//для удаления рамок
        pixmap.fill();
        pixmap.drawPixmap(transfer,0,0,transfer.getWidth(),transfer.getHeight(),1,1,pWidth-2,pHeight-2);//размеры для рамки, всё точно

        transfer = pixmap;

        for(int i=0;i<pixmap.getWidth();i++)
            for(int j=0;j<pixmap.getHeight();j++)
                if(pixmap.getPixel(i,j)!=255){
                    transfer.drawPixel(i,j,255);
                    transfer.drawPixel(i,j, Color.argb8888(1,0,0,150/255f));
                }
                else transfer.drawPixel(i,j,255);

        pixmap = transfer;
        texture.draw(pixmap,0,0);
    }

    public void button4PushMain(){



    }










}
