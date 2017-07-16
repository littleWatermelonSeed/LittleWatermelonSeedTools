package little.watermelon.pictureselect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sayhellototheworld.littlewatermelon.watermelonseedtools.R;

import java.io.File;
import java.util.List;

/**
 * Created by 123 on 2017/7/11.
 */

public class GridAdapter extends BaseAdapter {

    private Context context;
    private int folderPosition;
    private TextView textView;

    private List<Image> images;
    private LayoutInflater inflater;
    private static int choosed = 0;
    private int maxPictureNum = 9;
    private int removeChooseID = -1;
    private static int maxNum;
    private PictureDataManager manager;
    private SelectLayoutListener listener;


    public GridAdapter(Context context,TextView textView,int folderPosition) {
        manager = PictureDataManager.getManagerInstance();
        this.context = context;
        this.folderPosition = folderPosition;
        this.textView = textView;
        inflater = LayoutInflater.from(context);
        maxNum = maxPictureNum;
        images = manager.getFolderList().get(folderPosition).getImages();
        setTextView();
    }

    public GridAdapter(Context context, TextView textView,int folderPosition, int maxPictureNum) {
        manager = PictureDataManager.getManagerInstance();
        this.context = context;
        this.folderPosition = folderPosition;
        this.maxPictureNum = maxPictureNum;
        this.textView = textView;
        inflater = LayoutInflater.from(context);
        maxNum = maxPictureNum;
        images = manager.getFolderList().get(folderPosition).getImages();
        setTextView();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_image_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.adapter_image_item_imageview);
            viewHolder.relativeLayout_chooseLayout = (RelativeLayout) convertView.findViewById(R.id.adapter_image_item_imageview_chooseLayout);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.adapter_image_item_imageview_chooserLayoutTextView);
            viewHolder.imageView_masking = (ImageView) convertView.findViewById(R.id.adapter_image_item_imageview_masking);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initItemView(viewHolder,images.get(position).getID());
        listener = new SelectLayoutListener(viewHolder,position,images.get(position).getID());
        viewHolder.relativeLayout_chooseLayout.setOnClickListener(listener);
        Glide.with(context)
                .load(new File(images.get(position).getPath()))
                .into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        ImageView imageView_masking;
        RelativeLayout relativeLayout_chooseLayout;
        TextView textView;
    }

    private void initItemView(ViewHolder viewHolder,int id){
        if(manager.isExistChoosedID(id)){
            manager.doImageSelect(context,viewHolder,id);
        }else {
            manager.doImageDisSelect(context,viewHolder);
        }
    }

    class SelectLayoutListener implements View.OnClickListener{

        private int position;
        private ViewHolder viewHolder;
        private int imageID;

        public SelectLayoutListener(ViewHolder viewHolder,int position,int imageID) {
            this.viewHolder = viewHolder;
            this.position = position;
            this.imageID = imageID;
        }

        @Override
        public void onClick(View v) {
            if(manager.isExistChoosedID(images.get(position).getID())){
                choosed--;
                manager.changeImageChoosedID(GridAdapter.this,images.get(position).getChooseID());
                manager.disSelectImage(folderPosition,position);
                manager.removeCallbackImage(images.get(position).getID());
                manager.removeChoosedID(images.get(position).getID());
                manager.doImageDisSelect(context,viewHolder);
                setTextView();
//                Log.i("niyuanjie","disSelect chooedID = " + images.get(position).getChooseID());
            }else {
                if (choosed >= maxPictureNum){
                    Toast.makeText(context,"最多只能选择" + maxPictureNum + "图片",Toast.LENGTH_SHORT).show();
                    return;
                }
                choosed++;
                manager.addChoosedID(images.get(position).getID());
                manager.selectImage(folderPosition,position,choosed);
                manager.addCallbackImage(images.get(position));
                manager.doImageSelect(context,viewHolder,imageID);
                setTextView();
//                Log.i("niyuanjie","select chooedID = " + images.get(position).getChooseID());
            }
        }
    }

    private void setTextView(){
        int selectedNum = manager.getCallbackImageSize();
        if(selectedNum == 0){
            textView.setText("选择");
            return;
        }
        textView.setText("选择(" + selectedNum + ")");
    }

    public static void initChoosed(){
        choosed = 0;
    }


}
