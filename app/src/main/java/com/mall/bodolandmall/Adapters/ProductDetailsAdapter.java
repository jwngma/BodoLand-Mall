package com.mall.bodolandmall.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mall.bodolandmall.Fragments.ProductDescriptionFragment;
import com.mall.bodolandmall.Fragments.ProductSpecificationFragment;

public class ProductDetailsAdapter extends FragmentPagerAdapter {
    private int totalTabs;

    public ProductDetailsAdapter(FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                ProductDescriptionFragment productDescriptionFragment= new ProductDescriptionFragment();
                return  productDescriptionFragment;
            case 1:
                ProductSpecificationFragment productSpecificationFragment= new ProductSpecificationFragment();
                return  productSpecificationFragment;
            case 2:
                ProductSpecificationFragment productSpecificationFragmen1= new ProductSpecificationFragment();
                return  productSpecificationFragmen1;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
