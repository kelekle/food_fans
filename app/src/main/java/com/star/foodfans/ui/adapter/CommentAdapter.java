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
import com.star.foodfans.entity.Commentinfo;
import com.star.foodfans.entity.Userinfo;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Commentinfo> dataSet;
    private ArrayList<Userinfo> userinfoArrayList;

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

    public CommentAdapter(Context context) {
        this.context = context;
        this.dataSet = new ArrayList<>();
        userinfoArrayList = new ArrayList<>();
    }

    public void resetList(List<Commentinfo> items, List<Userinfo> userinfos){
        this.dataSet.clear();
        this.dataSet.addAll(items);
        userinfoArrayList.addAll(userinfos);
        notifyDataSetChanged();
    }

    public Commentinfo getComment(int pos){
        return dataSet.get(pos);
    }

    public Userinfo getUserinfo(int pos){
        return userinfoArrayList.get(pos);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_text_feed, parent, false);
        return new CourseImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ((CourseImageHolder) holder).textView.setText(dataSet.get(position).getContent());
        ((CourseImageHolder) holder).name.setText(userinfoArrayList.get(position).getUsername());
        Glide.with(context).load(userinfoArrayList.get(position).getHeadpicture()).into(((CourseImageHolder)holder).circleImageView);
//        if (onItemClickListener != null) {
//            ((CourseImageHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //在TextView的地方进行监听点击事件，并且实现接口
//                    onItemClickListener.onItemClick(position % dataSet.size());
//                }
//            });
//        }
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

//        private ImageView imageView;
        private TextView textView;
        private TextView name;
        private CircleImageView circleImageView;

        protected CourseImageHolder(@NonNull View itemView) {
            super(itemView);
//            imageView = itemView.findViewById(R.id.title_image);
            textView = itemView.findViewById(R.id.post_content_text);
            circleImageView = itemView.findViewById(R.id.profile_picture);
            name = itemView.findViewById(R.id.profile_name);

        }

    }


}