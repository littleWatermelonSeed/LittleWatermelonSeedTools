package com.sayhellototheworld.littlewatermelon.littlewatermelonseedtools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.customwidget.www.LiTopBar;
import util.DensityUtil;

public class MainActivity extends AppCompatActivity {

    private LiTopBar liTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liTopBar = (LiTopBar)findViewById(R.id.activity_liTopBar);
        ImageView imageView = new ImageView(this);
        ImageView imageView2 = new ImageView(this);
        imageView.setImageResource(R.drawable.addition);
        imageView2.setImageResource(R.drawable.sousuo);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(DensityUtil.dip2px(this,25), ViewGroup.LayoutParams.WRAP_CONTENT);
        liTopBar.addViewToMiddleDevelopment(imageView,params);
        liTopBar.addViewToRightDevelopment(imageView2,params);
        liTopBar.setMiddleDevelopmentListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"middle development",Toast.LENGTH_SHORT).show();
            }
        });
        liTopBar.setRightDevelopmentListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"right development",Toast.LENGTH_SHORT).show();
            }
        });
        liTopBar.setLeftContainerListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"left container",Toast.LENGTH_SHORT).show();
            }
        });
        liTopBar.setRightImageViewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"right image",Toast.LENGTH_SHORT).show();
            }
        });
        liTopBar.setMiddleTextViewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"middle container",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
