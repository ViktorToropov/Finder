package com.find.by.searchpic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class SearchPic {
    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;
    ArrayList<Texture> textures;

    Texture texture;

    int razmer = 200;//максимальный размер в длину и ширину

    boolean[] stages;

    String[] string = new String[2];

    String searchPicImg;



    public SearchPic(boolean[] stages,String searchPicImg) {

        this.searchPicImg = searchPicImg;

        this.stages = stages;
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();

        textures = new ArrayList<Texture>();

        FileHandle[] files = Gdx.files.local("Входные/").list();
     //   for(int i=0;i<files.length;i++) {
            // do something interesting here
           // System.out.println(files[i].name());
      //  }

        if(files.length>0){
            for (int i=0;i<files.length;i++){
                Pixmap pixzel;
                pixzel = new Pixmap(Gdx.files.local("Входные/"+files[i].name()));
                pixzel = scale(razmer-1,pixzel);

                textures.add(new Texture(pixzel));

                pixzel.dispose();

            }
            string[0]="Входные/"+files[0].name();
            string[1]="Входные/"+files[1].name();

        }

        texture = new Texture(Gdx.files.local("Data/imgChose/imC.jpg"));

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


    public void render(){
        spriteBatch.begin();
        for (int i=0;i<textures.size();i++)
        spriteBatch.draw(textures.get(i), Gdx.graphics.getWidth()/2-razmer/2, Gdx.graphics.getHeight()-1.5f*razmer-razmer*i);
        spriteBatch.draw(texture, Gdx.graphics.getWidth()/2-razmer/2, Gdx.graphics.getHeight()-razmer*0.75f);
        spriteBatch.end();

        if(Gdx.input.justTouched()){

            if(Gdx.input.getX()>= Gdx.graphics.getWidth()/2-razmer/2&& Gdx.input.getY()<= Gdx.graphics.getHeight()-1.5f*razmer)
                if(Gdx.input.getX()<= Gdx.graphics.getWidth()/2-razmer/2+razmer&& Gdx.input.getY()>=razmer*0.75f){
                searchPicImg = string[0];
                   // System.out.println(searchPicImg);
                stages[4]=true;

                }

            if(Gdx.input.getX()>= Gdx.graphics.getWidth()/2-razmer/2&& Gdx.input.getY()<= Gdx.graphics.getHeight()-1.5f*razmer+razmer)
                if(Gdx.input.getX()<= Gdx.graphics.getWidth()/2-razmer/2+razmer&& Gdx.input.getY()>=razmer*0.75f+razmer){
                searchPicImg = string[1];

                stages[4]=true;

                }
        }
    }
    public String getSearchPicImg(){
        return searchPicImg;
    }
    public void dispose(){
        spriteBatch.dispose();
        shapeRenderer.dispose();
        textures.clear();
        texture.dispose();
    }
}
