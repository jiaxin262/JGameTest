package com.jia.jason.jgametest.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.view.JImageDraweeView;

/**
 * Created by xin.jia
 * since 2016/9/28
 */
public class FrescoImageViewActivity extends BaseActivity {

    private SimpleDraweeView simpleDraweeView;
    private ImageView imageView;
    private JImageDraweeView jImageDraweeView;

    String url = "http://resizing.flixster.com/DeLpPTAwX3O2LszOpeaMHjbzuAw=/53x77/dkpu1ddg7pbsk.cloudfront.net/movie/11/16/47/11164719_ori.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawable_animation_layout);

        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.drawee_imv);
        imageView = (ImageView) findViewById(R.id.drawable_animation_iv);
        jImageDraweeView = (JImageDraweeView) findViewById(R.id.j_img_drawee);

        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);
        imageView.setImageURI(uri);
        //jImageDraweeView.setImageURI(uri);
    }
}
