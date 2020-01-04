package com.star.foodfans.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.star.foodfans.R;
import com.star.foodfans.entity.Result;


/**
 * Created by HaPBoy on 5/18/16.
 */
public class RecommentFragment extends Fragment {
    
    private Result restaurantInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View lv = (View)inflater.inflate(R.layout.fragment_recommend, container, false);
        return lv;
    }
}
