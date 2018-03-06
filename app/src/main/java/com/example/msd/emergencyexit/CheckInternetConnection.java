package com.example.msd.emergencyexit;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by msd on 3/6/18.
 */

public class CheckInternetConnection extends BroadcastReceiver {

    public CheckInternetConnection(Context c, Intent intent) {
        onReceive(c, intent);
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {

        boolean isConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
        if(isConnected){
            Toast.makeText(context, "Internet Connection Lost", Toast.LENGTH_LONG).show();
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FragmentsInflater f = new FragmentsInflater();
                    f.finish();
                    //context.finish();
                }
            }).setNegativeButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));;
                   // startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            }).setTitle("Couldn't establish connection.").setMessage("Check your connection and try again.").setCancelable(false);
            builder.show();
        }
        else{
            Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show();
        }
    }
}
