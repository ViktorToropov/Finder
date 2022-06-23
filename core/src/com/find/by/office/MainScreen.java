package com.find.by.office;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.find.by.GalleryOpener;
import com.find.by.drawline.DrawLine;
import com.find.by.graphs.Graphs;
import com.find.by.introduce.Intro;
import com.find.by.mainlog.Repeater;
import com.find.by.searchpic.SearchLent;
import com.find.by.searchpic.SearchPic;
import com.find.by.statica.Statica;



/**
 * Created by ADMIN on 06.05.2018.
 */

public class MainScreen implements Screen {
    Camera1 camera1;

    Intro intro;
    SearchPic searchPic;
    SearchLent searchLent;
    DrawLine drawLine;
    Repeater repeater;
    Statica statica;
    Graphs graphs;


    private Game game;

    public boolean[] stages = new boolean[16];

    byte[] searchPicImg;
    byte[] lentPicImg;

    int blackWhite;

    int resultLength = 1;

    GalleryOpener galleryOpener;




    public MainScreen(Game aGame, GalleryOpener opener) {
        game = aGame;
        this.galleryOpener = opener;
    }

    @Override
    public void show() {
        //    game.setScreen(new DrawLine(game));
        stages[0]=true;

        camera1 = new Camera1();





        //   repeater = new Repeater();
        //   statica = new Statica();
    }


    @Override
    public void render(float delta) {
        clear();

        if(stages[15])
            graphs.render();

        if(stages[14]){
            stages[14]=false;
            stages[13]=false;
            stages[15]=true;
            graphs = new Graphs(repeater.getOutputData(),resultLength);
            repeater.dispose();

        }

        if(stages[13])
            repeater.render();

        if(stages[12]){
            stages[12]=false;
            stages[1]=false;
            stages[13]=true;

            repeater = new Repeater(stages,searchPicImg,blackWhite,resultLength);
        }

        if(stages[11]){
            stages[10]=false;
            stages[11]=false;
            stages[0]=true;
            resultLength=drawLine.getResultLength();

            drawLine.dispose();



        }

        if(stages[10]){
            drawLine.render(delta);
        }

        if(stages[9]){

            stages[9]=false;
            stages[10]=true;
         //   lentPicImg = searchLent.getSearchPicImg();
          //  searchLent.dispose();
            drawLine = new DrawLine(camera1,galleryOpener.getBytes(),stages);
            lentPicImg = galleryOpener.getBytes();
        }

        if(stages[8]){
          //  searchLent.render();
            galleryOpener.getGalleryImagePath();
            stages[8]=false;
            stages[9]=true;
        }


        if(stages[7]){
            stages[8]=true;
            stages[7]=false;
            stages[1]=false;

          //--  intro.dispose();
           // searchLent = new SearchLent(stages,lentPicImg);
        }

        if(stages[6]){
            stages[6]=false;
            stages[5]=false;
            stages[0]=true;
            blackWhite = statica.getwhiteBlack();
            statica.dispose();

        }

        if(stages[5])
            statica.render();

        if(stages[4]){

          //  searchPic.dispose();
          //  searchPicImg=searchPic.getSearchPicImg();
                statica = new Statica(stages,galleryOpener.getBytes());
                searchPicImg = galleryOpener.getBytes();
                stages[5]=true;
                stages[4]=false;

        }

        if(stages[3]){
          //  searchPic.render();
            galleryOpener.getGalleryImagePath();
            stages[4]=true;
            stages[3]=false;

        }

        if(stages[2]){
            intro.dispose();
           // searchPic = new SearchPic(stages,searchPicImg);
            stages[3]=true;
            stages[2]=false;
            stages[1]=false;
        }

        if(stages[1])
            intro.render();

        if(stages[0]){
            intro = new Intro(stages,searchPicImg,lentPicImg,resultLength,blackWhite);
            stages[1]=true;
            stages[0]=false;
        }


   //     drawLine.render(delta);

   //     camera1.render();
   //     repeater.render();
    //    statica.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    void clear(){
        Gdx.gl.glClearColor( 1, 200/255f, 191/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }
}





