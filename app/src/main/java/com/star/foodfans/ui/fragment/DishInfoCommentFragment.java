package com.star.foodfans.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.star.foodfans.R;
import com.star.foodfans.api.ApiClient;
import com.star.foodfans.api.ApiInterface;
import com.star.foodfans.entity.Commentinfo;
import com.star.foodfans.entity.Userinfo;
import com.star.foodfans.ui.activity.CommentActivity;
import com.star.foodfans.ui.activity.DishInfoActivity;
import com.star.foodfans.ui.adapter.CommentAdapter;
import com.star.foodfans.util.AppConfig;
import com.star.foodfans.util.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by HaPBoy on 5/18/16.
 */
public class DishInfoCommentFragment extends Fragment {
    
    private List<Commentinfo> commentinfoList;
    private List<Userinfo> userinfoList;
    private RecyclerView recyclerView;
    private FloatingActionButton comment;
    private CommentAdapter commentAdapter;
    private TextView nonComment;
    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View lv = (View)inflater.inflate(R.layout.fragment_dish_comment, container, false);
        recyclerView = lv.findViewById(R.id.comment_rv);
        nonComment = lv.findViewById(R.id.none_comment);
        comment = lv.findViewById(R.id.comment);
        commentinfoList = new ArrayList<>();
        userinfoList = new ArrayList<>();
        commentAdapter = new CommentAdapter(getContext());
        id = ((DishInfoActivity) getActivity()).getArticleId();
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(commentAdapter);
        getComment();
        return lv;
    }

    public void getComment(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JsonObject> call;
        call = apiInterface.queryArticle(id);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject = response.body();
                commentinfoList.clear();
                userinfoList.clear();
                ArrayList<Userinfo> arrayList = new ArrayList<>();
                ArrayList<Commentinfo> commentinfos = new ArrayList<>();
                JsonArray jsonArray = jsonObject.getAsJsonArray("commentList");
                for(JsonElement element:jsonArray){
                    JsonObject object = element.getAsJsonObject();
                    Commentinfo commentinfo = new Gson().fromJson(object.get("comment"), Commentinfo.class);
                    Userinfo userinfo = new Gson().fromJson(object.get("username"), Userinfo.class);
                    arrayList.add(userinfo);
                    commentinfos.add(commentinfo);
                    System.out.println("head  " + userinfo.getHeadpicture());
                }
                commentinfoList = commentinfos;
                userinfoList = arrayList;
                if(commentinfos.size() <= 0){
                    nonComment.setVisibility(View.VISIBLE);
                }else {
                    nonComment.setVisibility(View.GONE);
                }
                commentAdapter.resetList(commentinfoList, userinfoList);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


}
