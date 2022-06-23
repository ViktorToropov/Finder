package com.find.by.graphs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.find.by.Finder;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class Graphs {
    Pixmap pixmap;
    Texture texture;
    SpriteBatch spriteBatch;

    int kolStolb=10;
    int[] mas = new int[kolStolb];
    int[] heigh = new int[kolStolb];

    BitmapFont bitmapFont = new BitmapFont();

    ArrayList<String> arrayList = new ArrayList<String>();

    boolean imgPast;

    int i100;
    int i50;
    int i5;

    Button button;
    Stage stage;
    TextField textField;

    boolean openGraph;


    public Graphs(final ArrayList<int[]> outputData, int resultLength) {

        ArrayList<double[]> outputData1 = new ArrayList<double[]>();

        for(int i=0;i<outputData.size();i++){
            outputData1.add(new double[2]);
            outputData1.get(i)[0]=(double)outputData.get(i)[0]/(double)resultLength;
            outputData1.get(i)[1]=outputData.get(i)[1];

            System.out.println(outputData1.get(i)[0]);
        }

        i100=(int)outputData1.get(outputData.size()-1)[0];

        double counter=0;
        for (int i=0;i<outputData1.size();i++){
            counter+=outputData1.get(i)[1];
        }
        double counter1=0;
        for (int i=0;i<outputData1.size();i++){
            counter1+=outputData1.get((outputData1.size()-1)-i)[1];
            if(counter1>=counter/2){
                i50=(int)outputData1.get((outputData1.size()-1)-i)[0];
                break;
            }
        }
        counter1=0;
        for (int i=0;i<outputData1.size();i++){
            counter1+=outputData1.get((outputData1.size()-1)-i)[1];
            if(counter1>=counter*0.95d){
                i5=(int)outputData1.get((outputData1.size()-1)-i)[0];
                break;
            }
        }


        int shag = (int)Math.ceil(outputData1.get(0)[0]/(double)kolStolb);

        for (int i=0;i<mas.length;i++)
        mas[i]=shag*i;
///////////////////////////////////////////////////////////////////
        for (int i=0;i<outputData1.size();i++){
            for(int j=0;j<mas.length-1;j++){
                if(outputData1.get(i)[0]>mas[j]&&outputData1.get(i)[0]<=mas[j+1]){
                    heigh[j]+=outputData1.get(i)[1];
                }
            }
        }

        for (int i=0;i<outputData1.size();i++){
                if(outputData1.get(i)[0]>mas[mas.length-1]){
                    heigh[heigh.length-1]+=outputData1.get(i)[1];
                }
        }
//////////////////////////////////////////////////////////////////////////
        for (int i=0;i<mas.length;i++)
            System.out.println(heigh[i]);



        int max=0;
        for (int i=0;i<heigh.length;i++)
            if(max<=heigh[i])max=heigh[i];


        pixmap = new Pixmap(500,300, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        pixmap.setColor(Color.RED);

        for(int i=0;i<kolStolb;i++){
            for(int k=0;k<20;k++)
            for (int j=0;j<pixmap.getHeight()/max*heigh[i];j++)
                pixmap.drawPixel((pixmap.getWidth()/kolStolb)*i+k,pixmap.getHeight()-j);
        }



        for (int i=1;i<kolStolb+1;i++){
            arrayList.add(new String(String.valueOf(i*shag)));
        }


        texture = new Texture(pixmap);
        spriteBatch = new SpriteBatch();


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        ///////////////////////////////////////////////////////////////////////
        // Button 1
        button = new TextButton("+", Finder.skin,"small");
        button.setSize(100,50);
        button.setPosition(Gdx.graphics.getWidth()/2-100/2+100, Gdx.graphics.getHeight()/2+texture.getHeight()/2+50);
        button.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(!openGraph){
                    pressPlus(Double.valueOf(textField.getText()),i50,outputData);
                    openGraph=true;
                    imgPast = false;
                }

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button);
///////////////////////////////////////////////////////////////////////

        textField = new TextField(String.valueOf(i50),Finder.skin);
        textField.setSize(100,50);
        textField.setPosition(Gdx.graphics.getWidth()/2-100/2-20, Gdx.graphics.getHeight()/2+texture.getHeight()/2+50);

        stage.addActor(textField);

    }
    public void render(){

        stage.act();
        stage.draw();

        spriteBatch.begin();
        spriteBatch.draw(texture, Gdx.graphics.getWidth()/2-texture.getWidth()/2, Gdx.graphics.getHeight()/2-texture.getHeight()/2);
        for (int i=0;i<arrayList.size();i++)
        bitmapFont.draw(spriteBatch,arrayList.get(i), Gdx.graphics.getWidth()/2-texture.getWidth()/2+(pixmap.getWidth()/kolStolb)*i+30, Gdx.graphics.getHeight()/2-texture.getHeight()/2-20);
        for (int i=0;i<arrayList.size();i++)
            bitmapFont.draw(spriteBatch,String.valueOf(heigh[i]), Gdx.graphics.getWidth()/2-texture.getWidth()/2+(pixmap.getWidth()/kolStolb)*i, Gdx.graphics.getHeight()/2-texture.getHeight()/2-40);
        bitmapFont.draw(spriteBatch,"> "+i100+" -100%",Gdx.graphics.getWidth()/2-texture.getWidth()/2+(pixmap.getWidth()/kolStolb), Gdx.graphics.getHeight()/2-texture.getHeight()/2-80);
        bitmapFont.draw(spriteBatch,"> "+i50+" -50%",Gdx.graphics.getWidth()/2-texture.getWidth()/2+(pixmap.getWidth()/kolStolb)+120, Gdx.graphics.getHeight()/2-texture.getHeight()/2-80);
        bitmapFont.draw(spriteBatch,"> "+i5+" -5%",Gdx.graphics.getWidth()/2-texture.getWidth()/2+(pixmap.getWidth()/kolStolb)+240, Gdx.graphics.getHeight()/2-texture.getHeight()/2-80);
        spriteBatch.end();


     /*   if(!imgPast){
            Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            FileHandle fh;
            fh = new FileHandle("Выходные/graph.png");
            PixmapIO.writePNG(fh, pixmap);
            pixmap.dispose();

            imgPast=true;
        }*/


    }

    private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown){
        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);

        if (yDown) {
            // Переворачиваем изображение, так как оно должно храниться в перевернутом формате.
            ByteBuffer pixels = pixmap.getPixels();
            int numBytes = w * h * 4;
            byte[] lines = new byte[numBytes];
            int numBytesPerLine = w * 4;
            for (int i = 0; i < h; i++) {
                pixels.position((h - i - 1) * numBytesPerLine);
                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
            }
            pixels.clear();
            pixels.put(lines);
        }

        return pixmap;
    }

    void pressPlus(double outD,int i50I, ArrayList<int[]> outputData){
        ArrayList<double[]> outputData1 = new ArrayList<double[]>();

        Arrays.fill(heigh,0);


        for(int i=0;i<outputData.size();i++){
            outputData1.add(new double[2]);
            outputData1.get(i)[0]=(double)outputData.get(i)[0]/((double)i50I/outD);
            outputData1.get(i)[1]=outputData.get(i)[1];

        }

        i100=(int)outputData1.get(outputData.size()-1)[0];

        double counter=0;
        for (int i=0;i<outputData1.size();i++){
            counter+=outputData1.get(i)[1];
        }
        double counter1=0;
        for (int i=0;i<outputData1.size();i++){
            counter1+=outputData1.get((outputData1.size()-1)-i)[1];
            if(counter1>=counter/2){
                i50=(int)outputData1.get((outputData1.size()-1)-i)[0];
                break;
            }
        }
        counter1=0;
        for (int i=0;i<outputData1.size();i++){
            counter1+=outputData1.get((outputData1.size()-1)-i)[1];
            if(counter1>=counter*0.95d){
                i5=(int)outputData1.get((outputData1.size()-1)-i)[0];
                break;
            }
        }


        int shag = (int)Math.ceil(outputData1.get(0)[0]/(double)kolStolb);

        for (int i=0;i<mas.length;i++)
            mas[i]=shag*i;
///////////////////////////////////////////////////////////////////
        for (int i=0;i<outputData1.size();i++){
            for(int j=0;j<mas.length-1;j++){
                if(outputData1.get(i)[0]>mas[j]&&outputData1.get(i)[0]<=mas[j+1]){
                    heigh[j]+=outputData1.get(i)[1];
                }
            }
        }

        for (int i=0;i<outputData1.size();i++){
            if(outputData1.get(i)[0]>mas[mas.length-1]){
                heigh[heigh.length-1]+=outputData1.get(i)[1];
            }
        }
//////////////////////////////////////////////////////////////////////////
        for (int i=0;i<mas.length;i++)
            System.out.println(heigh[i]);



        int max=0;
        for (int i=0;i<heigh.length;i++)
            if(max<=heigh[i])max=heigh[i];


        pixmap = new Pixmap(500,300, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        pixmap.setColor(Color.RED);

        for(int i=0;i<kolStolb;i++){
            for(int k=0;k<20;k++)
                for (int j=0;j<pixmap.getHeight()/max*heigh[i];j++)
                    pixmap.drawPixel((pixmap.getWidth()/kolStolb)*i+k,pixmap.getHeight()-j);
        }

        arrayList.clear();
        for (int i=1;i<kolStolb+1;i++){
            arrayList.add(new String(String.valueOf(i*shag)));
        }

        texture.draw(pixmap,0,0);

    }
}

