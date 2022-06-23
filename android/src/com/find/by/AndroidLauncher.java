package com.find.by;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.find.by.Finder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AndroidLauncher extends AndroidApplication {
	String userImagePath = null;
	AndroidGalleryOpener opener;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		opener = new AndroidGalleryOpener(this);
		initialize(new Finder(opener), config);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == AndroidGalleryOpener.SELECT_IMAGE_CODE) {
			Uri imageUri = data.getData();
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				opener.setBytes(stream.toByteArray());
				bitmap.recycle();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//super.onActivityResult(requestCode, resultCode, data);
	}
}
