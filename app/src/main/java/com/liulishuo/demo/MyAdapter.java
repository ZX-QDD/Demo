package com.liulishuo.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    private String[] data;
    private Context mContext;

    public MyAdapter(Context mContext, String[] data) {
        super();
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
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

        View  view =LayoutInflater.from(mContext).inflate(R.layout.item_my,null);
//            LayoutInflater inflater = LayoutInflater.from(mContext);
//            View view = inflater.inflate(R.layout.item_my,null);
            final TextView textView = (TextView) view.findViewById(R.id.textView);
            Button button = (Button) view.findViewById(R.id.button);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            imageView.setImageResource(R.mipmap.ic_launcher);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.append("!");
                }
            });
            textView.setText(data[position]);
            return view;

    }
}
