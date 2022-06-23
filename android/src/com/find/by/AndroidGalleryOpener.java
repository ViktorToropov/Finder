package com.find.by;

import android.app.Activity;
import android.content.Intent;


public class AndroidGalleryOpener implements GalleryOpener {

    Activity activity;
    public static final int SELECT_IMAGE_CODE = 1;

    private byte[] bytes;

    public AndroidGalleryOpener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void getGalleryImagePath() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Users Image"), SELECT_IMAGE_CODE);

    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}


