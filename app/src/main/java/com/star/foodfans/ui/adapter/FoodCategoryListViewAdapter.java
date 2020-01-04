package com.star.foodfans.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.star.foodfans.R;

import java.util.ArrayList;
import java.util.List;


public class FoodCategoryListViewAdapter extends BaseAdapter {
    //当前Item被点击的位置
    private int currentItem;
    private Context mContext;
    private List<String> mList;

    public FoodCategoryListViewAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<String>();
    }

    public void resetList(List<String> list){
        mList.clear();
        mList = list;
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置文字
        holder.foodcategory.setText(mList.get(position));
        if (currentItem == position) {
            holder.foodcategory.setSelected(true);
        } else {
            holder.foodcategory.setSelected(false);
        }
        return convertView;
    }

    class ViewHolder {
        TextView foodcategory;

        public ViewHolder(View convertView) {
            foodcategory = convertView.findViewById(R.id.tv_foodcategory_left);
        }
    }
}
