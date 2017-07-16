package com.sayhellototheworld.littlewatermelon.littlewatermelonseedtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import little.watermelon.customwidget.LiTopBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LiTopBar liTopBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        button = (Button)findViewById(R.id.activity_main_pictureSelect);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_pictureSelect:
                startActivity(new Intent(this,PictureSelectActivity.class));
                break;
        }
    }
}
