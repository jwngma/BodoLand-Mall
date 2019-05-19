package com.mall.bodolandmall.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mall.bodolandmall.Adapters.ProductSpecificationAdapter;
import com.mall.bodolandmall.Models.ProductSpecificationModel;
import com.mall.bodolandmall.R;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationFragment extends Fragment {
    private static final String TAG = "ProductSpecificationFra";

    private RecyclerView product_specification_recyclerview;


    public ProductSpecificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_product_specification, container, false);

        initSpecifications(view);


        return  view;
    }

    private void initSpecifications(View view) {

        product_specification_recyclerview=view.findViewById(R.id.product_specification_recyclerview);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        product_specification_recyclerview.setLayoutManager(layoutManager);


        List<ProductSpecificationModel>productSpecificationModelList= new ArrayList<>();
        productSpecificationModelList.add(new ProductSpecificationModel(0,"General"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"Display"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"Result"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","4GB"));

        ProductSpecificationAdapter adapter=new ProductSpecificationAdapter(productSpecificationModelList);
        product_specification_recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
