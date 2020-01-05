package com.star.foodfans.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.JsonObject;
import com.star.foodfans.R;
import com.star.foodfans.api.ApiClient;
import com.star.foodfans.api.ApiInterface;
import com.star.foodfans.entity.Result;
import com.star.foodfans.entity.SearchResultBean;
import com.star.foodfans.ui.fragment.DishCoverFragment;
import com.star.foodfans.ui.fragment.DishInfoCommentFragment;
import com.star.foodfans.ui.fragment.DishInfoItemFragment;
import com.star.foodfans.ui.fragment.RecommentFragment;
import com.star.foodfans.ui.view.ViewPagerIndicator;
import com.star.foodfans.util.AppConfig;
import com.star.foodfans.util.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by HaPBoy on 5/18/16.
 */
public class DishInfoActivity extends AppCompatActivity {
    private static String TAG = "DishInfoActivity";
    // Context
    private Context context;
    // ViewPager
    private ViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;

    //是否已经收藏
    private boolean isDishFavorite = false;
    //用户是否登录
    private boolean isUserLogin;

    // ViewPagerIndicator
    private ViewPagerIndicator viewPagerIndicator;
    private List<String> titles = Arrays.asList("美食详情", "相关评论");

    // Fragment
    private List<Fragment> fragments = new ArrayList<>();

    private Result dishInfo;

    //收藏按钮图片
    private int iconFavorite[] = {R.drawable.icon_unfavourite, R.drawable.icon_faviroute};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_info);
        // Context
        context = this;
        //获取美食信息
        dishInfo = (Result) getIntent().getSerializableExtra("dishInfo");
        //初始化用户状态
        isUserLogin = getUserState();
        if (isUserLogin) {
            Log.i(TAG, "用户已经登录" + isUserLogin);
            updateDishFavorite();//更新收藏状态
        }
        //初始化toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //初始化界面信息
        setTitle(dishInfo.getName().getTitle());
        // ViewPager
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        // ViewPagerIndicator
        viewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.indicator);
        viewPagerIndicator.setTabItemTitles(titles);
        viewPagerIndicator.setVisibleTabCount(3);

        // 基本信息 Fragment
        fragments.add(DishInfoItemFragment.newInstance(context,dishInfo));

        // 评论 Fragment
        fragments.add(new DishInfoCommentFragment());

         //推荐 Fragment
        fragments.add(new RecommentFragment());

        // PagerAdapter
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };

        // 设置数据适配器
        viewPager.setAdapter(pagerAdapter);
        viewPagerIndicator.setViewPager(viewPager, 0);
        // 封面图
        Fragment dishFragment = DishCoverFragment.newInstance(context,dishInfo);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_dish_cover, dishFragment).commit();
        visit();
    }

    public int getArticleId(){
        return dishInfo.getName().getArticleid();
    }

    public void visit(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JsonObject> call;
        call = apiInterface.queryArticle(dishInfo.getName().getArticleid());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(DishInfoActivity.this, t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean getUserState() {
        if (!Utils.getBooleanValue(this, AppConfig.LOGIN_STATE)) {
            Toast.makeText(DishInfoActivity.this, "请先登录", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean updateDishFavorite() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JsonObject> call;
        call = apiInterface.isCollectArticle(dishInfo.getName().getArticleid());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject object = response.body();
                System.out.println(response.body());
                if(object.get("result").getAsString().equals("true")){
                    isDishFavorite = true;
                }else {
                    isDishFavorite = false;
                }
                invalidateOptionsMenu();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(DishInfoActivity.this, t.toString(),Toast.LENGTH_LONG).show();
            }
        });
        return false;
    }

    private void toggleFavorite() {
        if(isUserLogin && !isDishFavorite){
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<JsonObject> call;
            call = apiInterface.collectArticle(String.valueOf(dishInfo.getName().getArticleid()));
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    JsonObject object = response.body();
                    if(object.get("result").getAsString().equals("success")){
                        isDishFavorite = true;
                        invalidateOptionsMenu();
                        Toast.makeText(DishInfoActivity.this, "已收藏",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(DishInfoActivity.this, t.toString(),Toast.LENGTH_LONG).show();
                }
            });

        }
        if(isUserLogin && isDishFavorite){
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<JsonObject> call;
            call = apiInterface.cancelCollectArticle(dishInfo.getName().getArticleid());
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    JsonObject object = response.body();
                    if(object.get("result").getAsString().equals("success")){
                        isDishFavorite = false;
                        invalidateOptionsMenu();
                        Toast.makeText(DishInfoActivity.this, "已取消收藏",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(DishInfoActivity.this, t.toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_favorite:
                toggleFavorite();
                return true;
            case R.id.action_browser:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dish_info, menu);
        return true;
    }

    //
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_favorite);
        Log.i(TAG, "Pre" + isDishFavorite);
        menuItem.setIcon(isDishFavorite?iconFavorite[1]:iconFavorite[0]);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
