package com.star.foodfans.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.star.foodfans.R;
import com.star.foodfans.entity.History;
import com.star.foodfans.entity.Userinfo;
import com.star.foodfans.repository.AppRepository;
import com.star.foodfans.ui.activity.EditUserInfoActivity;
import com.star.foodfans.ui.activity.HistoryActivity;
import com.star.foodfans.ui.activity.LoveActivity;
import com.star.foodfans.ui.activity.WebViewActivity;
import com.star.foodfans.util.AppConfig;
import com.star.foodfans.util.Utils;

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout userNotLogin, userLogin, viewFavourite,
            viewHistory, viewWork, viewInformation, viewAboutUs;
    private ImageView userImage;
    private TextView phone, username, achive, email;

    @Override
    protected void setListener() {
        userLogin.setOnClickListener(this);
        userNotLogin.setOnClickListener(this::onClick);
        viewFavourite.setOnClickListener(this::onClick);
        viewAboutUs.setOnClickListener(this::onClick);
//        viewWork.setOnClickListener(this::onClick);
        viewHistory.setOnClickListener(this::onClick);
        viewInformation.setOnClickListener(this::onClick);
    }

    @Override
    protected void initData() {
        generateData();
    }

    public void generateData(){
        if(!Utils.getBooleanValue(getContext(),AppConfig.LOGIN_STATE)){
            userNotLogin.setVisibility(View.VISIBLE);
            userLogin.setVisibility(View.GONE);
        }else {
            userNotLogin.setVisibility(View.GONE);
            userLogin.setVisibility(View.VISIBLE);
            if(AppConfig.CURRENT_USER == null){
                AppRepository.getAppRepository(getContext())
                        .selectUserinfoByEmail(Utils.getValue(getContext(), AppConfig.CURRENT_EMAIL))
                        .observe(getActivity(), new Observer<Userinfo>(){
                            @Override
                            public void onChanged(Userinfo userinfo) {
                                AppConfig.CURRENT_USER = userinfo;
                                setData();
                            }
                        });
            }else {
                setData();
            }
        }
    }

    private void setData(){
        username.setText(AppConfig.CURRENT_USER.getUsername());
        phone.setText(AppConfig.CURRENT_USER.getPhone());
        achive.setText(AppConfig.CURRENT_USER.getAchievement().replace("/",""));
        email.setText(AppConfig.CURRENT_USER.getEmail());
        Glide.with(getContext()).load(AppConfig.CURRENT_USER.getHeadpicture()).into(userImage);
    }

    @Override
    public void onResume(){
        super.onResume();
        generateData();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_me,container, false);
        userLogin = root.findViewById(R.id.user_login);
        userNotLogin = root.findViewById(R.id.user_not_login);
        viewFavourite = root.findViewById(R.id.view_favorite);
        viewHistory = root.findViewById(R.id.view_history);
//        viewWork = root.findViewById(R.id.view_my_work);
        viewInformation = root.findViewById(R.id.view_my_information);
        viewAboutUs = root.findViewById(R.id.view_setting);
        achive = root.findViewById(R.id.me_fans_count);
        email = root.findViewById(R.id.me_follow_count);
        phone = root.findViewById(R.id.me_phone);
        username = root.findViewById(R.id.me_nickname);
        userImage = root.findViewById(R.id.user_image);
        return root;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view_favorite:
                Utils.start_Activity(getActivity(), LoveActivity.class);
                break;
            case R.id.view_history:
                Utils.start_Activity(getActivity(), HistoryActivity.class);
                break;
//            case R.id.view_my_work:
//                break;
            case R.id.view_my_information:
                Utils.start_Activity(getActivity(), EditUserInfoActivity.class);
                break;
            case R.id.view_setting:
                Utils.start_Activity(getActivity(), WebViewActivity.class);
                break;
        }
    }
}
