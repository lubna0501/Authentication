package com.example.authentication;

import android.content.Context;
import android.widget.Toast;

public class CommonFunction {
  Context context;

    public CommonFunction(Context context) {
        this.context = context;
    }
    public void notifyuser(String tittle){
        Toast.makeText(context, tittle, Toast.LENGTH_SHORT).show();
    }
}
