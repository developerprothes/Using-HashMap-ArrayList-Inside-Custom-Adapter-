package com.prothes.usinghashmapandarraylistwithcustomadapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomBaseAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<HashMap<String, String>> arrayList;
    private HashMap<String, String> hashMap;
    private ImageView imageViews;
    private TextView textViewTitle,textViewDescription;
    private AppCompatButton copyBtn;

    public CustomBaseAdapter(MainActivity mainActivity, ArrayList<HashMap<String, String>> arrayList, HashMap<String, String> hashMap) {
        this.context = mainActivity;
        this.arrayList = arrayList;
        this.hashMap = hashMap;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_views_items_design_layout,parent,false);
        }
        imageViews = convertView.findViewById(R.id.imageViews);
        textViewTitle = convertView.findViewById(R.id.textViewTitle);
        textViewDescription = convertView.findViewById(R.id.textViewDescription);
        copyBtn = convertView.findViewById(R.id.copyBtn);

        hashMap = arrayList.get(position);
        String getImgUrl = hashMap.get("image");
        String getTitle = hashMap.get("title");
        String getDescription = hashMap.get("details");

        // Online Img use kora hoice tar jonnno picasso library implement kore tar help naoya lagce
        Picasso.get().load(getImgUrl).into(imageViews);
        textViewTitle.setText(""+getTitle);
        textViewDescription.setText(""+getDescription);

        // Copy Button Copy Details.....
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Text",getDescription);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, "Copied \n"+getTitle+" Details", Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
}
