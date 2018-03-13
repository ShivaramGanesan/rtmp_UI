package com.example.msd.emergencyexit;

import android.content.Context;
import android.graphics.Camera;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by msd on 1/23/18.
 */

public class Cameras implements Serializable{

    boolean status;
    String location;

    public Cameras() {

    }


    public Cameras(boolean status, String location) {
        this.status = status;
        this.location = location;
    }

    public Cameras(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
