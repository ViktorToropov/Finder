package com.find.by.secondclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HandleControl {
    Stage stage;

    public HandleControl() {

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
          /*      pixmap.setColor(176/255f,100/255f,0,1);
                pixmap.fill();
                texture.draw(pixmap,0,0);
                arrayList.clear();
                interest.clear();
                figure.clear();
                figureCenter.clear();
                id=1;*/

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
           //     if(turn)turn = false;else turn = true;
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
            //    setBound.add(difference);


                //        findRedPic();

                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
           //     setBound.add(difference);
                System.out.println(difference);

                //        interestZero();//нахождение скопления 0//75
                //        interestZeroPic();
                //        findRedPicMas();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
         //       setBound.add(difference);
                System.out.println(difference);

                //interestPixmap();
                //       interestSizeOblast(); //разбиение пикселей на номера //область 1-3//122
                //       interestSizeOblastPic();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
          //      setBound.add(difference);

                //        interestShakeOblast();//количество номеров из разбиения//124
                //        interestShakeOblastPic();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
         //       setBound.add(difference);

                //        interestRenameOblast();//какие номера соединяются на границе//175
                //        interestRenameOblastPic();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
        //        setBound.add(difference);

                //        shakeRename();//формирование фигуры из номеров//185
                //       shakeRenamePic();
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
          //      setBound.add(difference);

                //          figureCenterComplete();//завершение основы фигуры//193
                //          figureCenterCompletePic();//завершение основы фигуры//193
                end_time = System.currentTimeMillis();
                difference = end_time-start_time;
          //      setBound.add(difference);
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
           //     System.out.println(setBound);

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
            //    button4PushMain();

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button4);

    }
    public void render(){
        stage.act();
        stage.draw();
    }
}
