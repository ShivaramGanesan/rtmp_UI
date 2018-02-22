package com.example.msd.emergencyexit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msd on 1/23/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Cameras> list;
    Context mContext;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Cameras cameras = list.get(position);
        holder.img.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        holder.locationText.setText("garage");
        if(cameras.isStatus()){
            holder.statusImage.setImageResource(R.drawable.green);
            holder.statusImage.setColorFilter(Color.GREEN);

        }
        else {
            holder.statusImage.setImageResource(R.drawable.alarm);
            holder.statusImage.setColorFilter(Color.RED);
            Animation blinkAnim = AnimationUtils.loadAnimation(mContext, R.anim.blink);
            holder.statusImage.startAnimation(blinkAnim);
        }

        holder.statusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, LiveFeed.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        ImageView statusImage;
        TextView locationText;

        public MyViewHolder(View view){
            super(view);
            locationText = (TextView)view.findViewById(R.id.location_camera);
            statusImage = (ImageView)view.findViewById(R.id.status_image);
            img = (ImageView)view.findViewById(R.id.camera_image_view);
        }
    }

    public CustomAdapter(List<Cameras> list, Context mContext){
        this.list = list;
        this.mContext = mContext;
    }



}
