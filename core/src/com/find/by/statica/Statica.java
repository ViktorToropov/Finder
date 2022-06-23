package com.find.by.statica;

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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Statica {
    Texture texture;
    Texture texture1;
    Pixmap imgStart;//test8.jpg;//test8.jpg
    Pixmap imgScale;
    Pixmap imgBlackRed;
    SpriteBatch spriteBatch;

    int []dpp = new int[256];

    Pixmap graph;

    ShapeRenderer shapeRenderer;

    int beginX;

    Stage stage;

    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

    Button button;

    boolean[] stages;

    //String searchPicImg;
    byte[] searchPicImg;

    int blackWhiteS;

    public Statica(final boolean[] stages, byte[] searchPicImg) {


        this.searchPicImg = searchPicImg;

       // imgStart = new Pixmap(Gdx.files.local(searchPicImg));//test8.jpg
        imgStart = new Pixmap(searchPicImg,0,searchPicImg.length);//test8.jpg
        graph = new Pixmap(256,256,imgStart.getFormat());
        this.stages = stages;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        scale(400);
        blackAndWhite();
        peaks();

        beginX = Gdx.graphics.getWidth()/2-texture1.getWidth()/2;



        ///////////////////////////////////////////////////////////////////////
        // Button 1
        button = new TextButton("+",mySkin,"small");
        button.setSize((int)(Gdx.graphics.getWidth()*0.1),(int)(Gdx.graphics.getWidth()*0.1));
        button.setPosition(Gdx.graphics.getWidth()- Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()- Gdx.graphics.getHeight()/4);
        button.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                stages[6]=true;
                blackWhiteS = beginX-(Gdx.graphics.getWidth()/2-texture1.getWidth()/2);


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

        spriteBatch.begin();
        spriteBatch.draw(texture, Gdx.graphics.getWidth()/2-texture.getWidth()/2,50);
        spriteBatch.draw(texture1, Gdx.graphics.getWidth()/2-texture1.getWidth()/2, (Gdx.graphics.getHeight()-(texture1.getHeight()+1))-50);
        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        runner();
        counter();
        curierPic();
        shapeRenderer.rect(beginX,305,30,30);
        shapeRenderer.end();
    }

    void scale(int scalex){
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


    }

    public float parseColor(String hex) {
        float max = 0f;
        if(hex.length()==8) {
            String s1 = hex.substring(0, 2);
            int v1 = Integer.parseInt(s1, 16);
            float f1 = (float) v1 / 255f;
            String s2 = hex.substring(2, 4);
            int v2 = Integer.parseInt(s2, 16);
            float f2 = (float) v2 / 255f;
            String s3 = hex.substring(4, 6);
            int v3 = Integer.parseInt(s3, 16);
            float f3 = (float) v3 / 255f;

            //float max = f1; if(max <= f2){max = f2; if(max <= f3)max = f3;}
     /*   float max = f1;
        if((max <= f2)&&(max <= f3)){max = f2; if(max >= f3)max = f3;}
        if((max >= f2)&&(max >= f3)){max = f2; if(max <= f3)max = f3;}
        */
            max = (f1 + f2 + f3) / 3f;
        }

        return max;
    }

    void blackAndWhite(){
        imgBlackRed = new Pixmap(imgScale.getWidth(),imgScale.getHeight(),imgScale.getFormat());
        imgBlackRed.setColor(255);
        imgBlackRed.fill();

        int shag=1;

        float n=0/255f,k=255/255f;

        for (int i=shag-1;i<imgScale.getWidth();i+=shag){
            for(int j=shag-1;j<imgScale.getHeight();j+=shag){

                for (int l=i;l<i + shag;l++) {
                    for (int m = j; m < j + shag; m++) {

                        float col = parseColor(Integer.toHexString(imgScale.getPixel(i, j)));

                        imgBlackRed.drawPixel(l, m, Color.argb8888(col, col , col, 1));

                        dpp[(int)(col*255f)]++;
                        if ((col >= n && col <= 100/255f))
                        imgBlackRed.drawPixel(l, m, Color.argb8888(1, 0, 0, 150 / 255f));
                    }
                }
            }
        }

        texture = new Texture(imgBlackRed);

    }

    void blackAndWhite1(int stable){

        imgBlackRed.setColor(Color.BLACK);
        imgBlackRed.fill();


        int shag=1;

        float n=0/255f,k=255/255f;

        for (int i=shag-1;i<imgScale.getWidth();i+=shag){
            for(int j=shag-1;j<imgScale.getHeight();j+=shag){

                for (int l=i;l<i + shag;l++) {
                    for (int m = j; m < j + shag; m++) {

                        float col = parseColor(Integer.toHexString(imgScale.getPixel(i, j)));

                        imgBlackRed.drawPixel(l, m, Color.argb8888(col, col , col, 1));

                        if ((col >= n && col <= stable/255f))
                            imgBlackRed.drawPixel(l, m, Color.argb8888(1, 0, 0, 150 / 255f));
                    }
                }
            }
        }

        texture.draw(imgBlackRed,0,0);

    }

    void peaks(){
        int max=0;
        for (int i=0;i<256;i++)
            if(dpp[i]>max)max=dpp[i];

        for(int j=0;j<256;j++){
            int counter = (int)(((double)dpp[j]/(double)max)*255d);
            graph.setColor(j/255f,j/255f,j/255f,1);
            for (int i=0;i<counter;i++){
                graph.drawPixel(j,graph.getHeight()-i);
            }
        }
        texture1 = new Texture(graph);

        for (int i=0;i<dpp.length;i++){
         /*   System.out.print(i);
            System.out.print(" ");
            System.out.println(dpp[i]);*/
        }
    }
    void peaks1(int stable){
        int max=0;
        for (int i=0;i<256;i++)
            if(dpp[i]>max)max=dpp[i];

        for(int j=0;j<256;j++){
            int counter = (int)(((double)dpp[j]/(double)max)*255d);
            graph.setColor(j/255f,j/255f,j/255f,1);
            for (int i=0;i<counter;i++){
                graph.drawPixel(j,graph.getHeight()-i);
                if(j<=stable){
                    graph.setColor(1, 0, 0, 150 / 255f);
                    graph.drawPixel(j,graph.getHeight()-i);
                }
            }
        }
        texture1.draw(graph,0,0);

    }
    void runner(){

        if(Gdx.input.isTouched()){
            if(Gdx.input.getY()> Gdx.graphics.getHeight()/3)
            if(Gdx.input.getX()< Gdx.graphics.getWidth()/2-texture1.getWidth()/2)
                beginX = Gdx.graphics.getWidth()/2-texture1.getWidth()/2;
            else if(Gdx.input.getX()> Gdx.graphics.getWidth()/2+texture1.getWidth()/2-1)
                beginX = Gdx.graphics.getWidth()/2+texture1.getWidth()/2-1;
            else beginX = Gdx.input.getX();
        }
    }
    void counter(){
        int stable = beginX-(Gdx.graphics.getWidth()/2-texture1.getWidth()/2);

        graph.setColor(Color.BLACK);
        graph.fill();
        peaks1(stable);

    }
    void curierPic(){
        int stable = beginX-(Gdx.graphics.getWidth()/2-texture1.getWidth()/2);
        blackAndWhite1(stable);
    }
    public int getwhiteBlack(){
        return blackWhiteS;
    }
    public void dispose(){
        texture.dispose();
        texture1.dispose();
        imgStart.dispose();
        imgScale.dispose();
        imgBlackRed.dispose();
        graph.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
        dpp=null;

        button=null;
        stage.dispose();
        mySkin.dispose();

    }
}
