package com.star.foodfans.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.star.foodfans.R;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private List<Result> topCoursesEntities;
    private Context context;
    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public BannerAdapter(Context context){
        this.context = context;
        topCoursesEntities = new ArrayList<>();
    }

    public void resetList(List<Result> items){
        topCoursesEntities.clear();
        topCoursesEntities.addAll(items);
        notifyDataSetChanged();
    }

    public int getSize(){
        return topCoursesEntities.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kanner_content_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public Result getItem(int pos){
        return topCoursesEntities.get(pos);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if(getSize() <= 0)
            return;
        final int pos = position % getSize();
        Articleinfo course = topCoursesEntities.get(pos).getName();
        holder.tv_title.setText(course.getTitle());
        Glide.with(context).load(course.getUrls()).into(holder.iv_title);
            if (onItemClickListener != null) {
                holder.iv_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //在TextView的地方进行监听点击事件，并且实现接口
                        onItemClickListener.onItemClick(pos % topCoursesEntities.size());
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_title;
        private TextView tv_title;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            iv_title = itemView.findViewById(R.id.iv_title);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }

}
