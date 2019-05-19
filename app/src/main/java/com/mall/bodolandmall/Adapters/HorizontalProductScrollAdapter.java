package com.mall.bodolandmall.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mall.bodolandmall.Activities.ProductDetailsActivity;
import com.mall.bodolandmall.Models.HorizontalProductScrollModel;
import com.mall.bodolandmall.R;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewHolder, int i) {

        int resource = horizontalProductScrollModelList.get(i).getProductImage();
        String title = horizontalProductScrollModelList.get(i).getProductTitle();
        String desc = horizontalProductScrollModelList.get(i).getProductDescription();
        String price = horizontalProductScrollModelList.get(i).getProductPrice();

        viewHolder.productImage.setImageResource(resource);
        viewHolder.productTitle.setText(title);
        viewHolder.productDescription.setText(desc);
        viewHolder.productPrice.setText(price);


    }

    @Override
    public int getItemCount() {

        if (horizontalProductScrollModelList.size()>8){
            return  8;
        }
        else {
            return horizontalProductScrollModelList.size();
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle, productDescription, productPrice;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_description);
            productPrice = itemView.findViewById(R.id.h_s_product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent= new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }

        private void setProductImage(int resource) {
            productImage.setImageResource(resource);

        }

        private void setProductTitle(String title) {
            productTitle.setText(title);

        }

        private void setProductDescription(String description) {
            productDescription.setText(description);

        }

        private void setProductPrice(String price) {
            productPrice.setText(price);

        }
    }
}
