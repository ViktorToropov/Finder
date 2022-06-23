package com.find.by.office;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.find.by.Finder;


/**
 * Created by ADMIN on 06.05.2018.
 */

public class Camera1 {
    OrthographicCamera camera;
    Viewport viewport;
    public Camera1() {
        camera = new OrthographicCamera();
        camera.position.set(Finder.WIDTH/2, Finder.HEIGHT/2,0);
        camera.update();
        viewport = new FitViewport(Finder.WIDTH,Finder.HEIGHT,camera);
    }
    public void render(){

    }
    public void rezize(int width,int height){
        viewport.update(width, height);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }
}
