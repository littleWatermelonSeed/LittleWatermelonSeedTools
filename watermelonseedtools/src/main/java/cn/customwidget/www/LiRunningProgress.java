package cn.customwidget.www;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.sayhellototheworld.littlewatermelon.watermelonseedtools.R;

/**
 * Created by 123 on 2017/5/12.
 */

public class LiRunningProgress extends ProgressBar{

    public LiRunningProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setIndeterminateDrawable(getResources().getDrawable(R.drawable.zz_running_progressbar));
    }

}