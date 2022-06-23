package com.find.by.introduce;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.find.by.Finder;


public class Intro {
    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;

    Texture texture;
    Texture texture1;

    Pixmap chastic;
    Pixmap linear;

    int upscale = 400;

    boolean[] stages;

    Button button;
    Stage stage;

    byte[] searchPicImg;
    int resultLength;

    int blackWhite;

    public Intro(final boolean[] stages, final byte[] searchPicImg,final byte[] searchPicImg1, final int resultLength, final int blackWhite) {

        this.searchPicImg = searchPicImg;
        this.resultLength = resultLength;
        this.blackWhite = blackWhite;

        this.stages = stages;

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

       // chastic = new Pixmap(Gdx.files.local("f320.jpg"));
        if(searchPicImg!=null){
            chastic = new Pixmap(searchPicImg,0,searchPicImg.length);
        }else{
            chastic = new Pixmap(100,100, Pixmap.Format.RGBA8888);
            chastic.setColor(Color.WHITE);
            chastic.fill();
        }

      //  linear = new Pixmap(Gdx.files.local("im16.jpg"));
        if(searchPicImg1!=null){
            linear = new Pixmap(searchPicImg1,0,searchPicImg1.length);
        }else{
            linear = new Pixmap(100,100, Pixmap.Format.RGBA8888);
            linear.setColor(Color.WHITE);
            linear.fill();
        }

        chastic = scale(upscale-1,chastic);
        linear = scale(upscale-1,linear);

        texture = new Texture(chastic);
        texture1 = new Texture(linear);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        ///////////////////////////////////////////////////////////////////////
        // Button 1
        button = new TextButton("+",Finder.skin,"small");
        int w = (int)(Gdx.graphics.getWidth()*0.2);
        int h = (int)(Gdx.graphics.getHeight()*0.1);
        button.setSize(w,h);
        button.setPosition(Gdx.graphics.getWidth()/2-w/2, Gdx.graphics.getHeight()/4.8f);
        button.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if(searchPicImg!=null){
                    System.out.println(resultLength);
                    System.out.println(searchPicImg);
                    System.out.println(blackWhite);
                    stages[12]=true;
                }



            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button);
///////////////////////////////////////////////////////////////////////


    }
    public void render(){

        stage.act();
        stage.draw();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/2-100,upscale,upscale);
        shapeRenderer.rect(Gdx.graphics.getWidth()- Gdx.graphics.getWidth()/12-texture1.getWidth(), Gdx.graphics.getHeight()/2-100,upscale,upscale);
        shapeRenderer.end();

        spriteBatch.begin();
        spriteBatch.draw(texture, Gdx.graphics.getWidth()/12+1, Gdx.graphics.getHeight()/2-100+(upscale/2-texture.getHeight()/2));
        spriteBatch.draw(texture1, Gdx.graphics.getWidth()- Gdx.graphics.getWidth()/12-texture1.getWidth()+1, Gdx.graphics.getHeight()/2-100+(upscale/2-texture.getHeight()/2));
        spriteBatch.end();

        if(Gdx.input.justTouched()){

            if(Gdx.input.getX()>= Gdx.graphics.getWidth()/12&& Gdx.input.getY()<= Gdx.graphics.getHeight()/2-texture1.getWidth()/2+upscale)
                if(Gdx.input.getX()<= Gdx.graphics.getWidth()- Gdx.graphics.getWidth()/12-texture1.getWidth()+1+upscale&& Gdx.input.getY()>= Gdx.graphics.getHeight()/2-100)
                    if(Gdx.input.getX()<= Gdx.graphics.getWidth()/12+upscale){
                        stages[2]=true;
                    }else if(Gdx.input.getX()>= Gdx.graphics.getWidth()- Gdx.graphics.getWidth()/12-texture1.getWidth()+1){
                        stages[7]=true;
                    }
        }
    }
    Pixmap scale(int scalex, Pixmap imgStart){
        Pixmap imgScale;

        double width=imgStart.getWidth();
        double height=imgStart.getHeight();

        if (imgStart.getWidth()>imgStart.getHeight()){
            width*=(double)scalex/(double)imgStart.getWidth();
            height*=((double)imgStart.getHeight()/(double)imgStart.getWidth())*((double)scalex/(double)imgStart.getHeight());

        }else if(imgStart.getWidth()<imgStart.getHeight()){
            width*=((double)imgStart.getWidth()/(double)imgStart.getHeight())*((double)scalex/(double)imgStart.getWidth());
            height*=(double)scalex/(double)imgStart.getHeight();

        }else{
            width*=(double)scalex/(double)imgStart.getWidth();
            height*=(double)scalex/(double)imgStart.getHeight();
        }


        imgScale = new Pixmap((int)width,(int)height,imgStart.getFormat());
        imgScale.drawPixmap(imgStart,0,0,imgStart.getWidth(),imgStart.getHeight(),0,0,(int)width,(int)height);

        return imgScale;
    }

    public void dispose(){
        chastic.dispose();
        linear.dispose();
        texture.dispose();
        texture1.dispose();
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }
}
