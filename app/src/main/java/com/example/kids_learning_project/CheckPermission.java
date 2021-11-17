package com.example.kids_learning_project;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class CheckPermission {
    private Context context;
    public CheckPermission(Context context){
        this.context=context;
    }

    public boolean checkPermission(){
//        boolean permission1= ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED;
        boolean permission2= ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED;
        if(permission2)
            return true;
        else
            return false;
    }
}
