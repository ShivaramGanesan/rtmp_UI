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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
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


public class ListViewFragment extends android.app.Fragment {


    public ListViewFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    List<Cameras> list = new ArrayList<>();
    CustomAdapter customAdapter;
    String status;
    Cameras cameras = new Cameras();





    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
 //       getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onViewCreated(view, savedInstanceState);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference();
        //Toast.makeText(getActivity(), "fff", Toast.LENGTH_SHORT).show();

        //mReference.child("ss").setValue("ss");

         //c.setStatus(true);

        list.add(cameras);




        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    status = dataSnapshot.child("exit1").child("status").getValue().toString();


                    if (status.equals("blocked")) {
                        addCamera(false);
                        //c.setStatus(false,getActivity());
                        //Toast.makeText(getActivity(), "change made blocked", Toast.LENGTH_SHORT).show();

                    } else {
                        addCamera(true);
                        //c.setStatus(true,getActivity());
                        //Toast.makeText(getActivity(), "change made not blocked", Toast.LENGTH_SHORT).show();

                    }

                       // Toast.makeText(getActivity(), "" + status, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    //Log.d("tag", e.toString());
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

            }
        });

        //mReference.child("ss").setValue("hello");


        recyclerView = (RecyclerView)getActivity().findViewById(R.id.recycler_view_fragment);
        customAdapter = new CustomAdapter(list,getActivity());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(customAdapter);

       /* for(int i=0;i<10;i++){
            if(i%2 == 0){
                addCamera(true);
            }
            else{
                addCamera(false);
            }
        }
*/


}

private void changeInstance(Cameras cameras, boolean status){
        cameras.setStatus(status);
}
    private void addCamera(boolean bool){

        cameras.setStatus(bool);
        cameras.setLocation("shop");


        customAdapter.notifyDataSetChanged();
    }

    private void addCamera(Cameras c){
        list.add(c);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }




}
