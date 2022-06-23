package com.find.by;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.find.by.office.MainScreen;

public class Finder extends Game {
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	static public Skin skin;

	GalleryOpener galleryOpener;

	public Finder(GalleryOpener opener){
		this.galleryOpener = opener;
	}

	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		this.setScreen(new MainScreen(this,galleryOpener));
	}


	@Override
	public void render () {
		super.render();

	}

	public void dispose () {
		skin.dispose();

	}



}
