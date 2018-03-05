package com.example.msd.emergencyexit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class FragmentsInflater extends AppCompatActivity {

    FragmentManager fm;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())   {
            case R.id.list: {
                Toast.makeText(this, "list", Toast.LENGTH_SHORT).show();
                Fragment listViewFragment = new ListViewFragment();
                fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_container, listViewFragment);
                ft.commit();
                break;
            }
            case R.id.grid: {
                Toast.makeText(this, "Grid", Toast.LENGTH_SHORT).show();
                Fragment gridViewFragment = new GridViewFragment();
                fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_container, gridViewFragment);
                ft.commit();
                break;
            }

        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_fragments_inflater);


        /*Fragment gridViewFragment = new GridViewFragment();
        fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container, gridViewFragment);
        ft.commit();
        */

        Fragment listViewFragment = new ListViewFragment();
        fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container, listViewFragment);
        ft.commit();
    }
}
