package com.wzx.skinclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.wzx.skinmanager.SkinCenter;
import com.wzx.skinmanager.viewconverts.SetBackgroundColor;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_image=findViewById(R.id.iv_image);
        SkinCenter.get().apply(iv_image, R.color.colorAccent, SetBackgroundColor.class.getSimpleName());
    }
}
