package com.mall.bodolandmall.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mall.bodolandmall.Adapters.CartAdapter;
import com.mall.bodolandmall.Models.CartItemModel;
import com.mall.bodolandmall.R;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {
    public MyCartFragment() {

    }


    private RecyclerView cartItemRecyclerview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        initRecyclerview(view);
        return view;
    }

    private void initRecyclerview(View view) {
        cartItemRecyclerview=view.findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerview.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList=new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.girl_default_profile,"This is Title One","65555","5999",2,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.boy_default_profile,"Pixel (2)","65555","5999",2,2,2));
        cartItemModelList.add(new CartItemModel(0,R.drawable.girl_default_profile,"This is Title One","65555","5999",2,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.boy_default_profile,"Pixel (2)","65555","5999",2,2,2));

        cartItemModelList.add(new CartItemModel(1,"2","150000","free","10000","1400000"));
        CartAdapter adapter= new CartAdapter(cartItemModelList);
        cartItemRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
