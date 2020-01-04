package com.star.foodfans.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.star.foodfans.R;
import com.star.foodfans.api.ApiClient;
import com.star.foodfans.api.ApiInterface;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Result;
import com.star.foodfans.entity.SearchResultBean;
import com.star.foodfans.ui.activity.DishInfoActivity;
import com.star.foodfans.ui.adapter.FoodCategoryListViewAdapter;
import com.star.foodfans.ui.adapter.GridViewAdapter;
import com.star.foodfans.util.AppConfig;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverFragment extends BaseFragment implements View.OnClickListener {

    List<Result> articleinfoList;

    ListView mListView;
    GridView mGridView;

    private GridViewAdapter gridViewAdapter;

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discover,container, false);
        mListView = root.findViewById(R.id.foodcategory_lv_left);
        mGridView = root.findViewById(R.id.foodcategory_gv_right);
        articleinfoList = new ArrayList<>();
        final FoodCategoryListViewAdapter listViewAdapter = new FoodCategoryListViewAdapter(mActivity);
        mListView.setAdapter(listViewAdapter);
        listViewAdapter.resetList(Arrays.asList(AppConfig.TAGS));
        //GridView适配器 默认加载第一个
        gridViewAdapter = new GridViewAdapter(mActivity);
        mGridView.setAdapter(gridViewAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //带参跳转界面
                Intent intent = new Intent(getContext(), DishInfoActivity.class);
                intent.putExtra("dishInfo", articleinfoList.get(i));
                startActivity(intent);
            }
        });
        //ListView item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //改变GridView中需要显示的数据
               getData(AppConfig.TAGS[position]);
                //改变CurrentItem值，用于改变 字体颜色
                listViewAdapter.setCurrentItem(position);
                //通知改变
                listViewAdapter.notifyDataSetChanged();
            }
        });
        getData(AppConfig.TAGS[0]);
        return root;
    }

    private void getData(String dish){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JsonArray> call;
        call = apiInterface.searchArticle("", dish);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray jsonArray = response.body();
                System.out.println(response.body().toString());
                articleinfoList.clear();
                ArrayList<Result> arrayList = new ArrayList<>();
                for(JsonElement jsonElement:jsonArray){
                    System.out.println("ccc");
                    SearchResultBean result = new Gson().fromJson(jsonElement, SearchResultBean.class);
                    Result result1 = new Result();
                    Articleinfo articleinfo = new Articleinfo();
                    articleinfo.setArticleid(Integer.valueOf(result.getArticleId()));
                    articleinfo.setAvgscore(result.getAvgScore());
                    articleinfo.setCommentnum(Integer.valueOf(result.getCommentNum()));
                    articleinfo.setContent(result.getContent());
                    articleinfo.setTitle(result.getTitle());
                    articleinfo.setUrls(result.getUrls());
                    result1.setName(articleinfo);
                    result1.setHot(Float.valueOf(result.getHot()));
                    result1.setUsername(result.getUsername());
                    result1.setTags(result.getTag().split("/"));
                    System.out.println(result1.getUsername());
                    arrayList.add(result1);
                }
                articleinfoList = arrayList;
                gridViewAdapter.resetList(articleinfoList);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                System.out.println("bbb" + t.toString());
            }
        });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void willBeDisplayed() {

    }

    @Override
    public void willBeHidden() {

    }
}
