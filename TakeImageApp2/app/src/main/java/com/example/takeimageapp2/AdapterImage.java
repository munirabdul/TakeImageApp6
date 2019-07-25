package com.example.takeimageapp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class AdapterImage implements getCount, getItem, getItemId, getView {
    private Context mcontent;
    ArrayList<String> itemList = new ArrayList<>();

    public AdapterImage(Context context){
        mcontent = context;
    }
    void add(String path) {itemList.add(path);}

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {return i;}

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null){
            imageView = new ImageView(mcontent);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
    }else {
            imageView = (ImageView)view;
        }
        Bitmap bitmap = decodeSampleBitmapFromUri(itemList.get(i), 350, 350);
        imageView.setImageBitmap(bitmap);
        return imageView;
}

    private Bitmap decodeSampleBitmapFromUri(String s, int i, int i1) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bm = null;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(s, options);


        options.inSampleSize = calculateInsampleSize(options, i, i1);

        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(s, options);
        return bm;
    }
    private int calculateInsampleSize(BitmapFactory.Options options, int i, int i1) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSimpleSize = 1;

        if (height > i1 || width >i){
            if (width > height){
                inSimpleSize = Math.round((float) height / (float) i1);
            }else {
                inSimpleSize = Math.round((float) width / (float) i);

            }
        }
        return inSimpleSize;
    }
}