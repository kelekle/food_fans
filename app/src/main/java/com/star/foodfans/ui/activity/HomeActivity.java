package com.star.foodfans.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.star.foodfans.R;
import com.star.foodfans.ui.adapter.MyPagerAdapter;
import com.star.foodfans.ui.fragment.BaseFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private BaseFragment currentFragment;
    private MyPagerAdapter adapter;
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
//        setListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }


    public void initUI(){
        viewPager = findViewById(R.id.view_pager);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("主页", R.mipmap.ic_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("发现", R.mipmap.table_search_normal);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("食光轴", R.mipmap.ic_message);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("我的", R.mipmap.ic_account);


// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item4);

        bottomNavigation.setTranslucentNavigationEnabled(true);

// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

// Set current item programmatically
//        bottomNavigation.setCurrentItem(1);

// Add or remove notification for each item
//        bottomNavigation.setNotification("1", 3);
// OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.color_notification_back))
//                .setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.color_notification_text))
//                .build();
//        bottomNavigation.setNotification(notification, 1);

// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (currentFragment == null) {
                    currentFragment = (BaseFragment) adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    currentFragment.refresh();
                    return true;
                }

                if (currentFragment != null) {
                    currentFragment.willBeHidden();
                }

                viewPager.setCurrentItem(position, false);

                if (currentFragment == null) {
                    return true;
                }

                currentFragment = (BaseFragment) adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();
                return true;
            }
        });

		/*
		bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
			@Override public void onPositionChange(int y) {
				Log.d("DemoActivity", "BottomNavigation Position: " + y);
			}
		});
		*/

        viewPager.setOffscreenPageLimit(4);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = (BaseFragment) adapter.getCurrentFragment();

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Setting custom colors for notification
//                AHNotification notification = new AHNotification.Builder()
//                        .setText(":)")
//                        .setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.color_notification_back))
//                        .setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.color_notification_text))
//                        .build();
//                bottomNavigation.setNotification(notification, 1);
//                Snackbar.make(bottomNavigation, "Snackbar with bottom navigation",
//                        Snackbar.LENGTH_SHORT).show();
//            }
//        }, 3000);

    }

}
