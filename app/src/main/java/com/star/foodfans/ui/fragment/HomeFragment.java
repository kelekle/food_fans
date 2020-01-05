package com.star.foodfans.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.star.foodfans.R;
import com.star.foodfans.api.ApiClient;
import com.star.foodfans.api.ApiInterface;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Result;
import com.star.foodfans.repository.AppRepository;
import com.star.foodfans.ui.activity.DishInfoActivity;
import com.star.foodfans.ui.adapter.BannerAdapter;
import com.star.foodfans.ui.adapter.DishItemAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment implements View.OnClickListener {

    //    private Kanner kanner;
    private RecyclerView lv;
    private RecyclerView topLv;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage, coursesTitle;
    private Button btnRetry;

    private DishItemAdapter coursesAdapter;
    private BannerAdapter bannerAdapter;

    private RelativeLayout errorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout ll_dot, container;

    private int currentTopItem = 0;

    private List<ImageView> iv_dots;
    private List<Result> topCourses;
    private List<Result> courseList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        topCourses = new ArrayList<>();
        courseList = new ArrayList<>();
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    public void setListener() {
        lv.setOnClickListener(this);
        topLv.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadJson();
            }
        });
    }


    public void LoadJson() {
        errorLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JsonObject> call;
        call = apiInterface.getAllArticle();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, final Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    courseList.clear();
                    topCourses.clear();
                    JsonArray jsonArray = response.body().getAsJsonArray("allArticle");
                    for(JsonElement jsonElement:jsonArray){
                        Result result = new Gson().fromJson(jsonElement, Result.class);
                        courseList.add(result);
                    }
                    topCourses.add(courseList.get(0));
                    topCourses.add(courseList.get(1));
                    topCourses.add(courseList.get(2));
                    bannerAdapter.resetList(topCourses);
                    coursesAdapter.resetList(courseList);
                    resetBanner();
                    // prepare adapter and  attach to recyclerview
                    coursesTitle.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    coursesTitle.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    showErrorMessage(
                            R.mipmap.no_result,
                            "No Result",
                            "Please Try Again!\n");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showErrorMessage(
                        R.mipmap.no_result,
                        "No Result",
                        "Please Try Again!\n");
                System.out.println(t.toString());
            }
        });

    }

    private void showErrorMessage(int imageView, String title, String message) {

        if (errorLayout.getVisibility() == View.GONE) {
            System.out.println("visible");
            errorLayout.setVisibility(View.VISIBLE);
        }

        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadJson();
            }
        });

    }

    @Override
    public void initData() {
        coursesAdapter = new DishItemAdapter(getContext());
        lv.setAdapter(coursesAdapter);
        bannerAdapter = new BannerAdapter(getContext());
//        bannerAdapter.resetList(courses);
        topLv.setAdapter(bannerAdapter);
        coursesAdapter.setOnItemClickListener(new DishItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //带参跳转界面
                Intent intent = new Intent(getContext(), DishInfoActivity.class);
                intent.putExtra("dishInfo", coursesAdapter.getItem(position));
                //传递数组
//                intent.putExtra("name", foodName);
//                intent.putExtra("id", foodId);
                startActivity(intent);
            }
        });
        bannerAdapter.setOnItemClickListener(new BannerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //带参跳转界面
                Intent intent = new Intent(getContext(), DishInfoActivity.class);
                intent.putExtra("dishInfo", bannerAdapter.getItem(position));
                //传递数组
//                intent.putExtra("name", foodName);
//                intent.putExtra("id", foodId);
                startActivity(intent);
            }
        });
        LoadJson();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        this.container = root.findViewById(R.id.container);
        topLv = root.findViewById(R.id.kanner);
        lv = root.findViewById(R.id.rv_courses);
        errorLayout = root.findViewById(R.id.errorLayout);
        errorImage = root.findViewById(R.id.errorImage);
        errorTitle = root.findViewById(R.id.errorTitle);
        errorMessage = root.findViewById(R.id.errorMessage);
        btnRetry = root.findViewById(R.id.btnRetry);
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);
        coursesTitle = root.findViewById(R.id.courses_title);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topLv.setLayoutManager(layoutManager);
        ll_dot = (LinearLayout) root.findViewById(R.id.ll_dot);
        iv_dots = new ArrayList<ImageView>();
        topLv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastTopItem = currentTopItem;
                currentTopItem = layoutManager.findFirstVisibleItemPosition() % 3;
                updateIndicatorStatus(lastTopItem);

            }
        });
        lv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        new PagerSnapHelper().attachToRecyclerView(topLv);
        return root;
    }

    public void updateIndicatorStatus(int lastTopItem) {
        System.out.println("last " + lastTopItem + "  cur " + currentTopItem);
        if (lastTopItem != currentTopItem && iv_dots.size() > 0) {
            iv_dots.get(lastTopItem).setImageResource(R.mipmap.dot_blur);
            iv_dots.get(currentTopItem).setImageResource(R.mipmap.dot_focus);
        }
    }

    public void resetBanner() {
        int len = bannerAdapter.getSize();
        ll_dot.removeAllViews();
        iv_dots.clear();
        for (int i = 0; i < len; i++) {
            ImageView iv_dot = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 6;
            params.rightMargin = 6;
            ll_dot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }
        if (len > 0) {
            iv_dots.get(0).setImageResource(R.mipmap.dot_focus);
        }
        for (int i = 1; i < bannerAdapter.getSize(); i++) {
//            if(i != currentTopItem){
            iv_dots.get(i).setImageResource(R.mipmap.dot_blur);
        }
    }

    /**
     * Refresh
     */
    @Override
    public void refresh() {
        LoadJson();
    }

    /**
     * Called when a fragment will be displayed
     */
    @Override
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (container != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            container.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    @Override
    public void willBeHidden() {
        if (container != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            container.startAnimation(fadeOut);
        }
    }
}


