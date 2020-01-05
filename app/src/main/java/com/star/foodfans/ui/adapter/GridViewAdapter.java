package com.star.foodfans.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.star.foodfans.R;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Result;
import com.star.foodfans.entity.SearchResultBean;
import com.star.foodfans.ui.activity.DishInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Result> mList;

    public GridViewAdapter(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<>();
    }

    public void resetList(List<Result> list) {
        mList.clear();
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //当前菜类别的名字
        final String foodName = mList.get(position).getName().getTitle();
        //当前菜类别的ID
        final Integer foodId = mList.get(position).getName().getArticleid();
        System.out.println(foodName);
        Glide.with(mContext).load(mList.get(position).getName().getUrls()).into(holder.imageView);
        holder.tv_name.setText(foodName);
        //点击事件
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带参跳转界面
                Intent intent = new Intent(mContext, DishInfoActivity.class);
                //传递数组
                intent.putExtra("name", foodName);
                intent.putExtra("id", foodId);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


    class ViewHolder {
        ImageView imageView;
        TextView tv_name;

        public ViewHolder(View convertView) {
            tv_name = convertView.findViewById(R.id.title_text);
            imageView = convertView.findViewById(R.id.iv_grid);
        }
    }

}
