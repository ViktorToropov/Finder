package com.find.by.drawline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.find.by.office.Camera1;


/**
 * Created by julienvillegas on 17/01/2017.
 */
public class DrawLine implements Screen {

    private Stage gameStage;
    private Stage UIStage;

    private OrthographicCamera camera;

    private Slider slider;
    private InputMultiplexer multiplexer;

    static public Skin skin;

    private Button button;

    Image dot = new Image(new Texture("Data/imgChose/dot.jpg"));

    Image map;

    int counter=0;
    int[] counterMas = new int[4];
    boolean counterCheck;

    int resultLength=1;

    Camera1 camera1;

    ShapeRenderer shapeRenderer;

    Pixmap changeMap;

    boolean[] stages;

   // public DrawLine(Camera1 cam,String lentPicImg,boolean[] stages) {
    public DrawLine(Camera1 cam,byte[] lentPicImg,boolean[] stages) {

        this.stages = stages;

        map = new Image(new Texture(new Pixmap(lentPicImg,0,lentPicImg.length)));
        changeMap = new Pixmap(lentPicImg,0,lentPicImg.length);
        changeMap.setColor(Color.RED);


        camera1 = cam;


        gameStage = new Stage(cam.getViewport());


        gameStage.addActor(map);
        gameStage.addActor(dot);


        camera = camera1.getCamera();
        camera.translate(0,0);
        camera.zoom = 3.55f;

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.RED);

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        gameStage.act();
        gameStage.draw();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(100,0,100, Gdx.graphics.getHeight());
        shapeRenderer.line(Gdx.graphics.getWidth()-100,0, Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight());
        shapeRenderer.line(0, Gdx.graphics.getHeight()-100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-100);
        shapeRenderer.line(0,100, Gdx.graphics.getWidth(),100);

        shapeRenderer.rect(Gdx.graphics.getWidth()-100-70, Gdx.graphics.getHeight()-100-70,50,50);
        shapeRenderer.rect(Gdx.graphics.getWidth()-100-140, Gdx.graphics.getHeight()-100-70,50,50);

        shapeRenderer.line(Gdx.graphics.getWidth()-100-55, Gdx.graphics.getHeight()-100-45, Gdx.graphics.getWidth()-100-35, Gdx.graphics.getHeight()-100-45);


        shapeRenderer.line(Gdx.graphics.getWidth()-100-125, Gdx.graphics.getHeight()-100-45, Gdx.graphics.getWidth()-100-105, Gdx.graphics.getHeight()-100-45);
        shapeRenderer.line(Gdx.graphics.getWidth()-100-115, Gdx.graphics.getHeight()-100-35, Gdx.graphics.getWidth()-100-115, Gdx.graphics.getHeight()-100-55);

        shapeRenderer.rect(Gdx.graphics.getWidth()-100-70, Gdx.graphics.getHeight()-100-140,50,50);
        shapeRenderer.rect(Gdx.graphics.getWidth()-100-55, Gdx.graphics.getHeight()-100-125,20,20);
        shapeRenderer.end();


        if(Gdx.input.isTouched()){
          //  camera.zoom-=0.01;
          //  System.out.println(camera.zoom);
            if(Gdx.input.getX()> Gdx.graphics.getWidth()-100)
                camera.position.x += 8f*camera.zoom;
            if(Gdx.input.getX()<100)
                camera.position.x -= 8f*camera.zoom;
            if(Gdx.input.getY()<100)
                camera.position.y += 8f*camera.zoom;
            if(Gdx.input.getY()> Gdx.graphics.getHeight()-100)
                camera.position.y -= 8f*camera.zoom;


            if(Gdx.input.getX()> Gdx.graphics.getWidth()-100-70&& Gdx.input.getY()<170)
                if(Gdx.input.getX()< Gdx.graphics.getWidth()-100-20&& Gdx.input.getY()>120)
                        camera.zoom+=0.01f;
            if(Gdx.input.getX()> Gdx.graphics.getWidth()-100-140&& Gdx.input.getY()<170)
                if(Gdx.input.getX()< Gdx.graphics.getWidth()-100-90&& Gdx.input.getY()>120)
                    if(camera.zoom>0.04)
                        camera.zoom-=0.01f;
            if(Gdx.input.getX()> Gdx.graphics.getWidth()-100-70&& Gdx.input.getY()<240)
                if(Gdx.input.getX()< Gdx.graphics.getWidth()-100-20&& Gdx.input.getY()>190)
                    if(counter<2)
                if(Gdx.input.justTouched()){

                    changeMap.drawPixel((int)camera.position.x,changeMap.getHeight()-(int)camera.position.y-1);
                    map = new Image(new Texture(changeMap));
                    gameStage.clear();

                    gameStage.addActor(map);
                    gameStage.addActor(dot);

                   // System.out.println((int)camera.position.x);
                   // System.out.println((int)camera.position.y);

                    if(counter==0){
                        counterMas[0]=(int)camera.position.x;
                        counterMas[2]=(int)camera.position.y;
                    }
                    if(counter==1){
                        counterMas[1]=(int)camera.position.x;
                        counterMas[3]=(int)camera.position.y;
                    }

                    if(counter==1)counterCheck=true;
                    counter++;
                }




            dot.setPosition((int)camera.position.x,(int)camera.position.y);

        }

        if(counterCheck){

            resultLength=(int)Math.sqrt((counterMas[1]-counterMas[0])*(counterMas[1]-counterMas[0])+(counterMas[3]-counterMas[2])*(counterMas[3]-counterMas[2]))+1;
         //   System.out.println(resultLength);
            counterCheck=false;
            stages[11]=true;
        }



    }


    @Override
    public void resize(int width, int height) {
        camera1.rezize(width,height);
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
        gameStage.dispose();
        shapeRenderer.dispose();
        changeMap.dispose();
    }

    public int getResultLength(){
        return resultLength;
    }


}