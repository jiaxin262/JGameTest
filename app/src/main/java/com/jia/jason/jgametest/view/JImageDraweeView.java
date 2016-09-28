package com.jia.jason.jgametest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jia.jason.jgametest.util.CheckUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xin.jia
 * since 2016/9/28
 */
public class JImageDraweeView extends SimpleDraweeView {
    private static final Map<ImageView.ScaleType, ScalingUtils.ScaleType> scaleTypeMappings = new HashMap<ImageView.ScaleType, ScalingUtils.ScaleType>();
    static {
        scaleTypeMappings.put(ImageView.ScaleType.CENTER, ScalingUtils.ScaleType.CENTER);
        scaleTypeMappings.put(ImageView.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP);
        scaleTypeMappings.put(ImageView.ScaleType.CENTER_INSIDE, ScalingUtils.ScaleType.CENTER_INSIDE);
        scaleTypeMappings.put(ImageView.ScaleType.FIT_CENTER, ScalingUtils.ScaleType.FIT_CENTER);
        scaleTypeMappings.put(ImageView.ScaleType.FIT_END, ScalingUtils.ScaleType.FIT_END);
        scaleTypeMappings.put(ImageView.ScaleType.FIT_START, ScalingUtils.ScaleType.FIT_START);
        scaleTypeMappings.put(ImageView.ScaleType.FIT_XY, ScalingUtils.ScaleType.FIT_XY);
    }

    public JImageDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public JImageDraweeView(Context context) {
        super(context);
    }

    public JImageDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        testAttrs(attrs);
        //applyAttrs(attrs);
    }

    @Override
    public void setScaleType(ImageView.ScaleType scaleType) {
        try {
            super.setScaleType(scaleType);
            ScalingUtils.ScaleType actualScaleType = scaleTypeMappings.get(scaleType);
            if(CheckUtils.isExist(actualScaleType)&&CheckUtils.isExist(getHierarchy())){
                getHierarchy().setActualImageScaleType(actualScaleType);
            }
        } catch (Exception ex) {
        }
    }

    public JImageDraweeView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyAttrs(attrs);
    }

    private void applyAttrs(AttributeSet attrs) {
        try {
//            Class<?> klass = Class.forName("com.android.internal.R$styleable");
//            int[] imgFieldResIds = (int[]) klass.getField("ImageView").get(null);
//            Integer imgSrcFieldId = (Integer) klass.getField("ImageView_src").get(null);
//            TypedArray a = getContext().obtainStyledAttributes(attrs, imgFieldResIds, 0, 0);
//            Drawable d = a.getDrawable(imgSrcFieldId);

            Drawable d = null;
            int attributeSize = attrs.getAttributeCount();
            for (int i = 0; i < attributeSize; i++) {
                if ("src".equals(attrs.getAttributeName(i))) {
                    int imgSrcId = attrs.getAttributeResourceValue(i, 0);
                    if (imgSrcId != 0) {
                        d = getContext().getResources().getDrawable(imgSrcId);
                    }
                    if (d != null) {
                        GenericDraweeHierarchy hierarchy = getHierarchy();
                        hierarchy.setPlaceholderImage(d);
                    }
                    break;
                }
            }
            //a.recycle();
        } catch (Exception ex) {
            Log.e("ERROR",ex+"");
        }
    }

    public void setLocalImageResource(int resourceId) {
        Uri uri =  Uri.parse("res://drawable/"+resourceId);
//        Uri uri = new Uri.Builder()
//                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
//                .path("com.mqunar.atom.flight@drawable")
//                .path(String.valueOf(resourceId))
//                .build();
        Log.d("local-image-uri",uri.toString());
        this.setImageURI(uri);
    }

    public void testAttrs(AttributeSet attrs) {
        int attributeSize = attrs.getAttributeCount();
        for (int i = 0; i < attributeSize; i++) {
            Log.d(i+"attrs.AttributeName", attrs.getAttributeName(i)+"");
            Log.d(i+"attrs.AttributeValue", attrs.getAttributeValue(i)+"");
            Log.d(i+"attrs.AttrNameResource", attrs.getAttributeNameResource(i)+"");
            Log.d(i+"attrs.AttrResourceValue", attrs.getAttributeResourceValue(i, 0)+"");
        }
        Log.d("-----attributeSize----", attributeSize+"");
        Log.d("attrs.AttrClassAttr", attrs.getClassAttribute()+"");
        Log.d("attrs.IdAttr", attrs.getIdAttribute()+"");
        Log.d("attrs.PositionDesc", attrs.getPositionDescription()+"");
        Log.d("attrs.IdAttrResValue", attrs.getIdAttributeResourceValue(0)+"");
        Log.d("attrs.StyleAttribute", attrs.getStyleAttribute()+"");
        Log.d("attrs.StyleAttribute", attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0)+"");


    }

}
