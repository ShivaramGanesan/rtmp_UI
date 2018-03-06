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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    ThumbnailAdapter thumbnailAdapter;
    FirebaseDatabase mDatabase;
    String status;
    //Cameras c;
    Cameras cameras = new Cameras();


    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView)getActivity().findViewById(R.id.grid_view_fragment);

        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference();

        //c = new Cameras();
        //c.setStatus(true);
        camerasList.add(cameras);
        //camerasList.add(new Cameras(true, "shop"));
        //addCamera(c);
        //addCamera(true);
        Toast.makeText(getActivity(), "grid activity", Toast.LENGTH_SHORT).show();

       mReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               try {


                   status = dataSnapshot.child("exit1").child("status").getValue().toString();

                   Toast.makeText(getActivity(), "" + status, Toast.LENGTH_SHORT).show();
                   if (status.equals("blocked")) {
                       addCamera(false);
                       Toast.makeText(getActivity(), "camera status false "+cameras.isStatus(), Toast.LENGTH_SHORT).show();

                   } else {
                       addCamera(true);
                       Toast.makeText(getActivity(), "camera status true "+cameras.isStatus(), Toast.LENGTH_SHORT).show();

                   }
               }
               catch (Exception e){
                   Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                   //startActivity(getActivity().getIntent());
               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
               Toast.makeText(getActivity(), "error.", Toast.LENGTH_SHORT).show();
               //startActivity(getActivity().getIntent());
           }
       });

        Toast.makeText(getActivity(), "camera status outside "+cameras.isStatus(), Toast.LENGTH_SHORT).show();


        /*for(int i=0;i<10;i++){
            if(i%2 == 0){
                addCamera(true);
            }
            else{
                addCamera(false);
            }
        }*/


            //addCamera(true);

        gridView.setAdapter(new ThumbnailAdapter(getActivity().getApplicationContext(), camerasList));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), LiveFeed.class));
            }
        });



    }
    private void addCamera(boolean bool){

        cameras.setStatus(bool);
        cameras.setLocation("shop");
       // Toast.makeText(getActivity(), "camera status in function"+cameras.isStatus(), Toast.LENGTH_SHORT).show();

        //gridView.setAdapter(new ThumbnailAdapter());

    }
    private void addCamera(Cameras c){
        camerasList.add(c);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_view, container, false);
    }

}
