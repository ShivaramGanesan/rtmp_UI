package com.example.msd.emergencyexit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ListViewFragment extends android.app.Fragment {


    public ListViewFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    List<Cameras> list = new ArrayList<>();
    CustomAdapter customAdapter;

    Animation blinkAnim;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
     //   getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onViewCreated(view, savedInstanceState);



        recyclerView = (RecyclerView)getActivity().findViewById(R.id.recycler_view_fragment);
        customAdapter = new CustomAdapter(list,getActivity());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(customAdapter);

        for(int i=0;i<10;i++){
            if(i%2 == 0){
                addCamera(true);
            }
            else{
                addCamera(false);
            }
        }



}
    private void addCamera(boolean bool){
        Cameras cameras = new Cameras();
        cameras.setStatus(bool);
        cameras.setLocation("shop");
        list.add(cameras);
        Collections.sort(Arrays.asList(cameras), new Comparator<Cameras>() {
            @Override
            public int compare(Cameras camera1, Cameras camera2) {
                return Boolean.compare(camera1.isStatus(), camera2.isStatus());
            }
        });
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }




}
