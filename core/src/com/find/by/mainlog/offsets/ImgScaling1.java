package com.find.by.mainlog.offsets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

public class ImgScaling1 {
    Pixmap imgStart;//test8.jpg
    Pixmap imgScale;
    Pixmap imgBlackRed;
    boolean[] imgCheck;
    public boolean fin;

    int blackWhite;

    public ImgScaling1(int pWidth,int pHeight,byte[] searchPicImg,int blackWhite){
        this.blackWhite = blackWhite;
        imgStart = new Pixmap(searchPicImg,0,searchPicImg.length);

        imgScale = new Pixmap(pWidth,pHeight,imgStart.getFormat());
        imgScale.drawPixmap(imgStart,0,0,imgStart.getWidth(),imgStart.getHeight(),1,1,imgScale.getWidth()-2,imgScale.getHeight()-2);

        imgCheck = new boolean[3];
        imgCheck[0] = true;

    }
    public Pixmap pixRet(){
        return imgBlackRed;
    }

    public void otrisovPic(){

        if(imgCheck[2])fin=true;//3

        if(imgCheck[1]){//2
            imgBlackRed = new Pixmap(imgScale.getWidth(),imgScale.getHeight(),imgScale.getFormat());
            imgBlackRed.setColor(255);
            imgBlackRed.fill();

            int shag=1;

            float n=0/255f,k=255/255f;

            for (int i=shag;i<imgScale.getWidth()-shag;i+=shag){
                for(int j=shag;j<imgScale.getHeight()-shag;j+=shag){

                    for (int l=i;l<i + shag;l++) {
                        for (int m = j; m < j + shag; m++) {

                            float col = parseColor(Integer.toHexString(imgScale.getPixel(i, j)));

                            if(col<=blackWhite/255f)
                          //  if ((col >= n && col <= ramki1(n, k))) //||(col>=ramki2(n,k)&&col<=k)
                                imgBlackRed.drawPixel(l, m, Color.argb8888(1, 0, 0, 150 / 255f));

                        }
                    }
                }
            }
            imgCheck[1]=false;
            imgCheck[2]=true;
        }

        if(imgCheck[0]){//1
            imgBlackRed = imgScale;
            imgCheck[0]=false;
            imgCheck[1]=true;
        }

    }
    public float ramki1(float a, float b){
        float c = b - a;
        c = (float) (c * 0.38) + a;
        return c;
    }
    public float ramki2(float a, float b){
        float c = b - a;
        c = (float) (c * 0.62) + a;
        return c;
    }
    public static float parseColor(String hex) {
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
    public void dispose(){
        imgStart.dispose();
        imgScale.dispose();
        imgBlackRed.dispose();
    }
}
