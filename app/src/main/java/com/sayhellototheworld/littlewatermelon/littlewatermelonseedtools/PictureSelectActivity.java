package com.sayhellototheworld.littlewatermelon.littlewatermelonseedtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

import little.watermelon.pictureselect.Image;
import little.watermelon.pictureselect.ShowPictureActivity;

public class PictureSelectActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private GridView gridView;
    private List<Image> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_select);
        init();
    }

    private void init(){
        button = (Button)findViewById(R.id.activity_picture_select_button);
        gridView = (GridView)findViewById(R.id.activity_picture_select_gridView);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ShowPictureActivity.startShowPictureActivityForResult(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ShowPictureActivity.SHOWPICTURE_REQUESTCODE){
            if (resultCode == RESULT_OK){
                int state = data.getIntExtra(ShowPictureActivity.RESULT_STATE_KEY,-1);
                if(state == ShowPictureActivity.RESULT_STATE_OK){
                    imageList = (List<Image>) data.getSerializableExtra(ShowPictureActivity.RESULT_DATA_KEY);
                    Log.i("niyuanjie","返回的Image长度为：" + imageList.size());
                    PictureAdapter adapter = new PictureAdapter(this,imageList);
                    gridView.setAdapter(adapter);
                }
            }
        }
    }

}
