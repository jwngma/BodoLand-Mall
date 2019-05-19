package com.mall.bodolandmall.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mall.bodolandmall.Adapters.MyOrderAdapter;
import com.mall.bodolandmall.Models.MyOrderItemModel;
import com.mall.bodolandmall.R;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFragment extends Fragment {
    private static final String TAG = "MyOrderFragment";

    private RecyclerView myOrderRecy;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        initOrderRecy(view);

        return view;
    }

    private void initOrderRecy(View view) {

        myOrderRecy=view.findViewById(R.id.my_order_recyclerview);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrderRecy.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList= new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel("Pixel 2",R.drawable.smart_phone,"Delivered on monday, 15TH JAN 2013",3));
        myOrderItemModelList.add(new MyOrderItemModel("Pixel 2",R.drawable.house,"Cancelled",3));
        myOrderItemModelList.add(new MyOrderItemModel("Pixel 2",R.drawable.smart_phone,"Delivered on Stauredar, 15TH JAN 2019",4));
        myOrderItemModelList.add(new MyOrderItemModel("Pixel 2",R.drawable.house,"Cancelled",5));

        MyOrderAdapter myOrderAdapter= new MyOrderAdapter(myOrderItemModelList);
        myOrderRecy.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
    }

}
