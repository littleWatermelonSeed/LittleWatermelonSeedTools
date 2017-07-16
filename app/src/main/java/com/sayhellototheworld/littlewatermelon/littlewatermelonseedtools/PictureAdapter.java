package com.sayhellototheworld.littlewatermelon.littlewatermelonseedtools;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import little.watermelon.pictureselect.Image;

/**
 * Created by 123 on 2017/7/16.
 */

public class PictureAdapter extends BaseAdapter {

    private Context context;
    private List<Image> imageList;
    private LayoutInflater inflater;

    public PictureAdapter(Context context, List<Image> imageList) {
        this.context = context;
        this.imageList = imageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            Log.i("niyuanjie","PictureAdapter vcreate view");
            convertView = inflater.inflate(R.layout.adapter_picture,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.adapter_picture_imageview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Glide.with(context)
                .load(imageList.get(position).getPath())
                .into(viewHolder.imageView);

        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
    }

}
