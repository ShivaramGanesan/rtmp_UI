package com.example.msd.emergencyexit;

import android.Manifest;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Cameras> list = new ArrayList<>();
    CustomAdapter customAdapter;

    Animation blinkAnim;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())   {
            case R.id.list:
                Toast.makeText(this, "list", Toast.LENGTH_SHORT).show();
                break;
            case R.id.grid:
                Toast.makeText(this, "Grid", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        int internetPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);







       recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
       customAdapter = new CustomAdapter(list,this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
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


}
