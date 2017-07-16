package little.watermelon.pictureselect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sayhellototheworld.littlewatermelon.watermelonseedtools.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowPictureActivity extends AppCompatActivity {

    private LinearLayout backLayout;
    private TextView textView_cancle;
    private TextView textView_preview;
    private TextView textView_choose;
    private GridView gridView;

    private TextClickListener listener;
    private PictureDataManager manager;

    private boolean chooseAndBack = false;
    private int folderPosition;
    private GridAdapter adapter;

    public final static int SHOWPICTURE_REQUESTCODE = 0;
    public final static int SHOWPICTURE_RESULT = 1;
    public final static int RESULT_STATE_OK = 1;
    public final static int RESULT_STATE_NO = 0;
    public final static String RESULT_STATE_KEY = "resultState";
    public final static String POSITION_KEY = "position";
    public final static String RESULT_DATA_KEY = "result_imagePath";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        init();
    }

    private void init() {
        manager = PictureDataManager.getManagerInstance();
        getWidget();
        setWidgetListener();
        setGridViewAdapter();
    }

    private void getWidget() {
        backLayout = (LinearLayout) findViewById(R.id.activity_show_picture_backLayout);
        textView_cancle = (TextView) findViewById(R.id.activity_show_picture_cancle);
        textView_preview = (TextView) findViewById(R.id.activity_show_picture_preview);
        textView_choose = (TextView) findViewById(R.id.activity_show_picture_choose);
        gridView = (GridView) findViewById(R.id.activity_show_picture_gridView);
    }

    private void setWidgetListener() {
        listener = new TextClickListener();
        backLayout.setOnClickListener(listener);
        textView_cancle.setOnClickListener(listener);
        textView_preview.setOnClickListener(listener);
        textView_choose.setOnClickListener(listener);
    }

    private void setGridViewAdapter(){
        adapter = new GridAdapter(this,textView_choose,0);
        gridView.setAdapter(adapter);
    }

    public static void startShowPictureActivityForResult(final Context context) {
        GetImageFromSDcard.loadImageForSDCard(context, new GetImageFromSDcard.ImageCallBack() {
            @Override
            public void onSuccess(List<Folder> folderList, List<Image> images) {
                PictureDataManager manager = PictureDataManager.getManagerInstance();
                manager.setFolderList(folderList);
                Intent intent = new Intent(context, ShowPictureActivity.class);
                ((FragmentActivity) context).startActivityForResult(intent, SHOWPICTURE_REQUESTCODE);
            }
        });
    }

    public static void startShowPictureActivityFromFolderList(Context context,int folderPosition) {
        Intent intent = new Intent();
        intent.putExtra(POSITION_KEY,folderPosition);
        ((Activity)context).setResult(RESULT_OK,intent);
        ((Activity)context).finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doCancle();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == FolderListviewActivity.FOLDER_REQUESTCODE){
            folderPosition = data.getIntExtra(POSITION_KEY,-1);
            if(folderPosition == -1){
                return;
            }else if(folderPosition == -2){
                doCancle();
                return;
            }
            adapter = new GridAdapter(this,textView_choose,folderPosition);
            gridView.setAdapter(adapter);
        }
    }

    class TextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.activity_show_picture_backLayout) {
                doBack();
            } else if (i == R.id.activity_show_picture_cancle) {
                doCancle();
            } else if (i == R.id.activity_show_picture_choose) {
                doChoose();
            } else if (i == R.id.activity_show_picture_preview) {
                doPreview();
            }
        }
    }

    private void doBack() {
        FolderListviewActivity.startFolderListviewActivityForResult(this);
    }

    private void doCancle() {

        clearPictureDataManager();

        Intent intent = new Intent();
        intent.putExtra(RESULT_STATE_KEY,RESULT_STATE_NO);
        setResult(RESULT_OK,intent);

        finish();
    }

    private void doChoose() {

        if(manager.getCallbackImageSize() == 0){
            Toast.makeText(this,"你还没有选择图片",Toast.LENGTH_SHORT).show();
            return;
        }

        chooseAndBack = true;
        manager.setCallback(chooseAndBack);

        List<Image> images = manager.getCallbackImageList();
        List<Image> reslut = new ArrayList<Image>();
        for(Image image:images){
            reslut.add(image);
        }

        Intent intent = new Intent();
        intent.putExtra(RESULT_STATE_KEY,RESULT_STATE_OK);
        intent.putExtra(RESULT_DATA_KEY, (Serializable) reslut);
        setResult(RESULT_OK,intent);
        clearPictureDataManager();
        finish();
    }

    private void doPreview() {

    }

    private void clearPictureDataManager(){
        manager.clear();
        manager = null;
    }

}
