package com.sayhellototheworld.littlewatermelon.littlewatermelonseedtools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.customwidget.www.LiTopBar;

public class MainActivity extends AppCompatActivity {

    private LiTopBar liTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
