package com.jia.jason.jgametest.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jia.jason.jgametest.R;

import java.io.File;

/**
 * Created by jiaxin on 16/10/23.
 */

public class ImageCaptureTestActivity extends BaseActivity {

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final String CAMERA_ACTION = "android.media.action.IMAGE_CAPTURE";
    public static final String CROP_PHOTO_ACTION = "com.android.camera.action.CROP";
    private Button btnTakePicture;
    private ImageView imgPhoto;
    private Uri imagUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_capture_layout);

        btnTakePicture = (Button) findViewById(R.id.j_capture_image_btn);
        imgPhoto = (ImageView) findViewById(R.id.j_image_capture_img);
        btnTakePicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.j_capture_image_btn:
                openCamera();
                break;
        }
    }

    private void openCamera() {
        File outputImage = new File(Environment.getExternalStorageDirectory(), "j_output_img.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imagUri = Uri.fromFile(outputImage);
        Intent intent = new Intent(CAMERA_ACTION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imagUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
//                    Intent intent = new Intent(CROP_PHOTO_ACTION);
//                    intent.putExtra("scale", true);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imagUri);
//                    startActivityForResult(intent, CROP_PHOTO);
                    try {
                        Bitmap  bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imagUri));
                        imgPhoto.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap  bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imagUri));
                        imgPhoto.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
