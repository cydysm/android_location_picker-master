package com.zeonic.icity.location_picker;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by IrrElephant on 17/5/8.
 */

public class MyAdapter extends BaseAdapter {

    List<Location> locationList;
    LayoutInflater inflater;

    public MyAdapter(List<Location> locationList, MainActivity mainActivity) {
        this.locationList = locationList;
        inflater = LayoutInflater.from(mainActivity);
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Object getItem(int position) {
        return locationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.item, null);
        TextView textView1 = (TextView) view.findViewById(R.id.txt_lat);
        textView1.setText(locationList.get(position).getLatitude().toString());
        TextView textView2 = (TextView) view.findViewById(R.id.txt_lng);
        textView2.setText(locationList.get(position).getLongitude().toString());
        TextView textView3 = (TextView) view.findViewById(R.id.txt_remark);
        textView3.setText(locationList.get(position).getRemark());
        return view;
    }

}
