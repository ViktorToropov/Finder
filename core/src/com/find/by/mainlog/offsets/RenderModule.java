package com.find.by.mainlog.offsets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RenderModule {
    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;
    Texture texture;
    Pixmap pixmap;
    BitmapFont bitmapFont;
    int[][] interestObject;



    int qx = 0;
    int qy = 0;
    int pWidth;
    int pHeight;

    public RenderModule(Pixmap pixmap) {
        this.pixmap = pixmap;
        pWidth = pixmap.getWidth();
        pHeight = pixmap.getHeight();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        this.texture = new Texture(scale((int)(Gdx.graphics.getHeight()*0.8),pixmap));

        bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(0.75f,0.75f);
        interestObject = new int[pWidth][pHeight];


    }
    public void render(){


        spriteBatch.begin();
        spriteBatch.draw(texture,Gdx.graphics.getWidth()/2-texture.getWidth()/2,100);

      //  masRender();
        spriteBatch.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    //    quad(qx,qy);
    //    checkQuad();
        shapeRenderer.end();


    }
    public void cT(Pixmap pixmap){
        texture.draw(scale((int)(Gdx.graphics.getHeight()*0.8),pixmap),0,0);
    }

    public void quad(float x,float y){//белый квадрат

        double koffx = 400f/pWidth; //размеры на экране/реальные размеры
        double koffy = 400f/pHeight; //размеры на экране/реальные размеры
        float mnx=(float)(koffx*1);//клетка на эеране
        float mny=(float)(koffy*1);//клетка на эеране
        x=x*mnx; y=-y*mny;//привязка к координатам
        x=x+100;y=y+100;//координаты вставки картины

        int kf1 = 400;//длина/ширина картины

        y=y+kf1;
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.line(x,y,x+mnx,y);
        shapeRenderer.line(x+mnx,y,x+mnx,y-mny);
        shapeRenderer.line(x+mnx,y-mny,x,y-mny);
        shapeRenderer.line(x,y-mny,x,y);
    }
    public void checkQuad(){//отрисовка пикселя
        if(!Gdx.input.isTouched()){
            qx = (int)((Gdx.input.getX()-100)/(400f/pWidth));
            qy = (int)(-(Gdx.graphics.getHeight() - Gdx.input.getY()-100-400)/(400f/pHeight));
            checkRamki(qx,qy);

        }//else if(Gdx.input.getY()>100&&Gdx.input.justTouched()) pixelDown((int)qx,(int)qy);

    }
    public void checkRamki(float qx,float qy){
        if(qx>pixmap.getWidth()-1)this.qx=pixmap.getWidth()-1;else if(qx<0)this.qx=0;
        if(qy>pixmap.getHeight()-1)this.qy=pixmap.getHeight()-1;else if(qy<0)this.qy=0;
    }

    public void masRender(){
            for(int i=0;i<pWidth;i++) {
                for (int j = 0; j < pHeight; j++) {
                    if (interestObject[i][j] > 0) {
                        int x = (int) (i * (400f / pWidth) + 100);

                        int y = (int) (Gdx.graphics.getHeight() - j * (400f / pHeight) - 100);

                        bitmapFont.draw(spriteBatch, Integer.toString(interestObject[i][j]), x, y);
                        // System.out.println(i);
                    }
                }
            }
    }
    public void cM(int[][] mas){
        interestObject = mas;
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
        spriteBatch.dispose();
        shapeRenderer.dispose();
        texture.dispose();
        pixmap.dispose();
        bitmapFont.dispose();
    }

}
