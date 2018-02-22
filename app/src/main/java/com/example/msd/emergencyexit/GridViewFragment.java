package com.example.msd.emergencyexit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GridViewFragment extends android.app.Fragment {


    public GridViewFragment() {
        // Required empty public constructor
    }


    GridView gridView;
    List<Cameras> camerasList = new ArrayList<>();


    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = (GridView)getActivity().findViewById(R.id.grid_view_fragment);



        for(int i=0;i<10;i++){
            if(i%2 == 0){
                addCamera(true);
            }
            else{
                addCamera(false);
            }
        }
        gridView.setAdapter(new ThumbnailAdapter(getActivity().getApplicationContext(), camerasList));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), LiveFeed.class));
            }
        });



    }
    private void addCamera(boolean bool){
        Cameras cameras = new Cameras();
        cameras.setStatus(bool);
        cameras.setLocation("shop");
        camerasList.add(cameras);
        //gridView.setAdapter(new ThumbnailAdapter());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_view, container, false);
    }

}
