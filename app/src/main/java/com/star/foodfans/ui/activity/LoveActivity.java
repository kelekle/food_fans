package com.star.foodfans.ui.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.star.foodfans.R;
import com.star.foodfans.api.ApiClient;
import com.star.foodfans.api.ApiInterface;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Result;
import com.star.foodfans.ui.adapter.DishItemAdapter;
import com.star.foodfans.ui.adapter.LoveAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.star.foodfans.Application.getContext;

public class LoveActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Articleinfo> articleinfoList;
    private LoveAdapter dishItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        textView = findViewById(R.id.tv_show_none);
        recyclerView = findViewById(R.id.collect_rv);
        swipeRefreshLayout = findViewById(R.id.love_swipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("我的收藏");
        articleinfoList = new ArrayList<>();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJson();
            }
        });
        dishItemAdapter = new LoveAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(dishItemAdapter);
        dishItemAdapter.setOnItemClickListener(new LoveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //带参跳转界面
                Intent intent = new Intent(getContext(), DishInfoActivity.class);
                Result result = new Result();
                result.setName(dishItemAdapter.getItem(position));
                result.setHot(3.5f);
                intent.putExtra("dishInfo",result);
                //传递数组
//                intent.putExtra("name", foodName);
//                intent.putExtra("id", foodId);
                startActivity(intent);
            }
        });
        loadJson();
    }

    public void loadJson(){
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JsonArray> call;
        call = apiInterface.getCollection();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray jsonArray = response.body();
                swipeRefreshLayout.setRefreshing(false);
                articleinfoList.clear();
                ArrayList<Articleinfo> articleinfos = new ArrayList<>();
                for(JsonElement jsonElement:jsonArray){
                    Articleinfo articleinfo = new Gson().fromJson(jsonElement, Articleinfo.class);
                    articleinfos.add(articleinfo);
                }
                articleinfoList = articleinfos;
                if(articleinfoList.size()<=0){
                    textView.setVisibility(View.VISIBLE);
                }else {
                    textView.setVisibility(View.GONE);
                    dishItemAdapter.resetList(articleinfoList);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(LoveActivity.this, t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }


}
