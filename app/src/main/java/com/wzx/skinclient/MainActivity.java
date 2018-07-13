package com.wzx.skinclient;

import android.Manifest;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wzx.skinmanager.SkinCenter;
import com.wzx.skinmanager.viewconverts.IViewConvert;
import com.wzx.skinmanager.viewconverts.SetBackgroundColor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_image;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        iv_image=findViewById(R.id.iv_image);
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //如果用的小米手机，并且用的是默认的debug签名，即便申请了读写权限也不行，需要手动去设置里改
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), "app-debug.apk");
        boolean exist = file.exists();
        if (!exist){
            return;
        }
        SkinCenter.get().init(this);
        SkinCenter.get().loadSkin(this, file.getAbsolutePath());
//        SkinCenter.get().apply(iv_image, R.color.colorAccent, SetBackgroundColor.class.getSimpleName());
        SkinCenter.get().apply(getWindow(), R.layout.activity_main, new IViewConvert<XmlResourceParser,Window>() {

            @Override
            public void apply(Window view, XmlResourceParser res) {
                if (res !=null) {
                    view.setContentView(LayoutInflater.from(MainActivity.this).inflate(res, null));
                }
            }
        });
        button = findViewById(R.id.button);
        if (button !=null){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"hahaha",Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(MainActivity.this,"插件布局加载失败",Toast.LENGTH_SHORT).show();
        }
    }
}
