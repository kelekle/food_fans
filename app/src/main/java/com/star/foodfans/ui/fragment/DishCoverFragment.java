package com.star.foodfans.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.star.foodfans.R;
import com.star.foodfans.entity.Result;


/**
 * Created by HaPBoy on 5/22/16.
 */
public class DishCoverFragment extends Fragment {

    private Context context;
    private Result dishInfo;

    public static DishCoverFragment newInstance(Context context, Result dishInfo) {
        DishCoverFragment fragment = new DishCoverFragment();
        fragment.context=context;
        fragment.dishInfo=dishInfo;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish_cover, container, false);

        ImageView ivDishCover = (ImageView) view.findViewById(R.id.dish_cover);

        TextView tvRate = (TextView) view.findViewById(R.id.tv_cover_rate);
        RatingBar rbRate = (RatingBar) view.findViewById(R.id.rb_cover_rate);

        View viewRate = view.findViewById(R.id.dish_rate);

        ivDishCover.setImageResource(R.drawable.recipe);

        Glide.with(getContext()).load(dishInfo.getName().getUrls()).into(ivDishCover);

        // 评分
        tvRate.setText(String.valueOf(dishInfo.getName().getAvgscore()));
        rbRate.setRating(dishInfo.getName().getAvgscore());

        // 封面入场动画
        Animation cover_an = AnimationUtils.loadAnimation(getContext(), R.anim.book_cover_anim);
        ivDishCover.startAnimation(cover_an);
        // 评分入场动画
        Animation rate_an = AnimationUtils.loadAnimation(getContext(), R.anim.book_cover_rate_anim);
        viewRate.startAnimation(rate_an);
        return view;
    }
}
