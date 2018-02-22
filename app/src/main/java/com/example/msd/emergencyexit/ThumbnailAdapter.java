package com.example.msd.emergencyexit;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msd on 2/13/18.
 */

public class ThumbnailAdapter extends BaseAdapter {

    private Context mContext;
    List<Cameras> cameras = new ArrayList<>();




    public ThumbnailAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public ThumbnailAdapter(Context mContext, List<Cameras> cameras) {
        this.mContext = mContext;
        this.cameras = cameras;
    }

    public  void addCamera(){

    }

    @Override
    public int getCount() {
        return cameras.size();
    }

    @Override
    public Object getItem(int i) {
        return cameras.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(cameras.get(pos).isStatus()) {
            imageView.setColorFilter(Color.RED, PorterDuff.Mode.OVERLAY);
            imageView.setAlpha(0.3f);
        }
        imageView.setLayoutParams(new GridView.LayoutParams(250,250));
        return imageView;
    }
}
