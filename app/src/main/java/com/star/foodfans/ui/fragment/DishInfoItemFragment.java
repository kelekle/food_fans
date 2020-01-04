package com.star.foodfans.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.star.foodfans.R;
import com.star.foodfans.entity.Result;


/**
 * Created by HaPBoy on 5/18/16.
 */
public class DishInfoItemFragment extends Fragment {

    
    private Result dishInfo;
    private Context context;

    private TextView dishNameView;
    private TextView dishIntroductionView;
    private TextView dishRestaurantNameView;
    private TextView dishTasteView;
    private TextView dishPriceView;
    private TextView dishAddressView;

    public static DishInfoItemFragment newInstance(Context context, Result dishInfo) {
        DishInfoItemFragment fragment = new DishInfoItemFragment();
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
        View view = (View)inflater.inflate(R.layout.fragment_dish_info_item_list, container, false);
        dishNameView= (TextView) view.findViewById(R.id.dishName);
        dishIntroductionView= (TextView) view.findViewById(R.id.dish_introduction);
        dishRestaurantNameView= (TextView) view.findViewById(R.id.dish_restaurant_name);
        dishTasteView= (TextView) view.findViewById(R.id.dishTaste);
        dishAddressView= (TextView) view.findViewById(R.id.dishAddress);
        dishPriceView= (TextView) view.findViewById(R.id.dishPrice);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateDishInfo();
    }

    private void updateDishInfo() {
        dishNameView.setText(dishInfo.getName().getTitle());
        dishPriceView.setText("55");
        dishAddressView.setText("北下关");
        dishTasteView.setText("超辣");
        dishRestaurantNameView.setText("上原饭店");
        System.out.println(dishInfo.getName().getContent());
        System.out.println(dishInfo.getName().getTitle());
        dishIntroductionView.setText(dishInfo.getName().getContent());
    }

}
