package cn.customwidget.www;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sayhellototheworld.littlewatermelon.watermelonseedtools.R;

/**
 * Created by 123 on 2017/5/11.
 */

public class LiTopBar extends RelativeLayout{

    private RelativeLayout relativeLayout_content;
    private RelativeLayout relativeLayout_leftContainer;
    private LinearLayout linearLayout_rightContainer;
    private RelativeLayout relativeLayout_middleContainer;

    private LinearLayout linearLayout_middleDevelopment;
    private LinearLayout linearLayout_rightDevelopment;

    private ImageView imageView_left;
    private ImageView imageView_right;

    private TextView textView_left;
    private TextView textView_right;
    private TextView textView_middle;

    private Drawable bodyBackground;

    private Drawable leftImage;
    private String leftText;
    private int leftTextColor;

    private String middleText;
    private int middleTextColor;

    private String rightText;
    private int rightTextColor;
    private Drawable rightImage;

    private TypedArray typedArray;

    public LiTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.li_top_bar,this);
        typedArray = context.obtainStyledAttributes(attrs,R.styleable.LiTopBar);
        init();
    }

    private void init(){
        initWidget();
        getAttrs();
        setAttrs();
    }

    private void initWidget(){
        relativeLayout_content = (RelativeLayout)findViewById(R.id.li_top_bar_content);
        relativeLayout_leftContainer = (RelativeLayout)findViewById(R.id.li_top_bar_leftContainer);
        linearLayout_rightContainer = (LinearLayout) findViewById(R.id.li_top_bar_rightContainer);
        relativeLayout_middleContainer = (RelativeLayout)findViewById(R.id.li_top_bar_middleContainer);

        linearLayout_middleDevelopment = (LinearLayout)findViewById(R.id.li_top_bar_middleDevelopment);
        linearLayout_rightDevelopment = (LinearLayout)findViewById(R.id.li_top_bar_rightDevelopment);

        imageView_left = (ImageView)findViewById(R.id.li_top_bar_leftImage);
        imageView_right = (ImageView)findViewById(R.id.li_top_bar_rightImage);

        textView_left = (TextView)findViewById(R.id.li_top_bar_leftTextview);
        textView_right = (TextView)findViewById(R.id.li_top_bar_rightTextview);
        textView_middle = (TextView)findViewById(R.id.li_top_bar_middleTextview);

    }

    private void getAttrs(){
        bodyBackground = typedArray.getDrawable(R.styleable.LiTopBar_bodyBackground);

        leftImage = typedArray.getDrawable(R.styleable.LiTopBar_leftImage);
        leftText = typedArray.getString(R.styleable.LiTopBar_leftText);
        leftTextColor = typedArray.getColor(R.styleable.LiTopBar_leftTextColor, getResources().getColor(R.color.black));

        middleText = typedArray.getString(R.styleable.LiTopBar_middleText);
        middleTextColor = typedArray.getColor(R.styleable.LiTopBar_middleTextColor,getResources().getColor(R.color.black));

        rightText = typedArray.getString(R.styleable.LiTopBar_rightText);
        rightImage = typedArray.getDrawable(R.styleable.LiTopBar_rightImage);
        rightTextColor = typedArray.getColor(R.styleable.LiTopBar_rightTextColor,getResources().getColor(R.color.black));
        typedArray.recycle();
    }

    private void setAttrs(){
        relativeLayout_content.setBackground(bodyBackground);

        imageView_left.setBackground(leftImage);
        textView_left.setText(leftText);
        textView_left.setTextColor(leftTextColor);

        textView_middle.setText(middleText);
        textView_middle.setTextColor(middleTextColor);

        textView_right.setText(rightText);
        textView_right.setTextColor(rightTextColor);
        imageView_right.setBackground(rightImage);
    }

}
