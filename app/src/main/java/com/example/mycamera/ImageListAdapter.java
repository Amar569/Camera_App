package com.example.mycamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Bitmap> imageList;
    private LayoutInflater inflater;

    public ImageListAdapter(Context context, ArrayList<Bitmap> imageList) {
        this.context = context;
        this.imageList = imageList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.list_item_image);
//        TextView textView = view.findViewById(R.id.list_item_image);
        TextView countView = view.findViewById(R.id.list_item_count);
        //imageView.setImageBitmap(imageList.get(position));

        imageView.setImageBitmap(imageList.get(position));
        //textView.setText("Image ");
        countView.setText("(" + (position + 1) + ")");

        return view;
    }
}
