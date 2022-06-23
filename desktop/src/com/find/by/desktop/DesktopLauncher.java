package com.find.by.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.find.by.Finder;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Finder.WIDTH;
		config.height = Finder.HEIGHT;
		new LwjglApplication(new Finder(null), config);
	}
}
