package com.mall.bodolandmall.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mall.bodolandmall.Models.ProductSpecificationModel;
import com.mall.bodolandmall.R;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {

    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }


    @Override
    public int getItemViewType(int position) {
        switch (productSpecificationModelList.get(position).getType()) {
            case 0:
                return ProductSpecificationModel.SPECIFICATION_TITLE;

            case 1:
                return ProductSpecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:

                TextView title = new TextView(viewGroup.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16, viewGroup.getContext()),
                        setDp(16, viewGroup.getContext()),
                        setDp(16, viewGroup.getContext()),
                        setDp(16, viewGroup.getContext()));
                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);


            case ProductSpecificationModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_specification_item_layout, viewGroup, false);
                return new ViewHolder(view);

            default:
                return null;

        }


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        switch (productSpecificationModelList.get(i).getType()) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:
                viewHolder.setTitle(productSpecificationModelList.get(i).getTitle());
                break;
            case ProductSpecificationModel.SPECIFICATION_BODY:
                String featureName = productSpecificationModelList.get(i).getFeatureName();
                String featureValue = productSpecificationModelList.get(i).getFeatureValue();
                viewHolder.setFeatures(featureName, featureValue);
                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView featureName;
        private TextView featureValue;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        private void setTitle(String title_text) {
            title = (TextView) itemView;
            title.setText(title_text);
        }

        public void setFeatures(String featureNamee, String featureValuee) {
            featureName = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);
            featureValue.setText(featureValuee);
            featureName.setText(featureNamee);
        }
    }

    private int setDp(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
