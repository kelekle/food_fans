package com.star.foodfans.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.star.foodfans.R;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class LoveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Articleinfo> dataSet;

    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个的监听
     * @param itemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        onItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public LoveAdapter(Context context) {
        this.context = context;
        this.dataSet = new ArrayList<>();
    }

    public void addList(List<Articleinfo> items) {
        this.dataSet.addAll(items);
        notifyDataSetChanged();
    }

    public void resetList(List<Articleinfo> items){
        this.dataSet.clear();
        this.dataSet.addAll(items);
        notifyDataSetChanged();
    }

    public Articleinfo getItem(int pos){
        return dataSet.get(pos);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dish, parent, false);
        return new CourseImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ((CourseImageHolder) holder).textView.setText(dataSet.get(position).getTitle());
        Glide.with(context).load(dataSet.get(position).getUrls()).into(((CourseImageHolder)holder).imageView);
        if (onItemClickListener != null) {
            ((CourseImageHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //在TextView的地方进行监听点击事件，并且实现接口
                    onItemClickListener.onItemClick(position % dataSet.size());
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    protected static class CourseImageHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;

        protected CourseImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.title_image);
            textView = itemView.findViewById(R.id.title_text);
            cardView = itemView.findViewById(R.id.card_view);
        }

    }


}
