package com.mall.bodolandmall.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mall.bodolandmall.Models.WishlistModel;
import com.mall.bodolandmall.R;

import java.util.List;

public class WishlistAdapter   extends RecyclerView.Adapter<WishlistAdapter.ViewHolder>{


    private List<WishlistModel> wishlistModelList;

    public WishlistAdapter(List<WishlistModel> wishlistModelList) {
        this.wishlistModelList = wishlistModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productCoupon;
        private ImageView couponIcon;
        private TextView rating;
        private TextView productprice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageButton deletebtn;
        private  View pricecut;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productTitle=itemView.findViewById(R.id.product_title);
            productCoupon=itemView.findViewById(R.id.product_image);
            couponIcon=itemView.findViewById(R.id.product_image);
            rating=itemView.findViewById(R.id.product_image);
            cuttedPrice=itemView.findViewById(R.id.product_image);
            paymentMethod=itemView.findViewById(R.id.product_image);
            productprice=itemView.findViewById(R.id.product_image);
            deletebtn=itemView.findViewById(R.id.product_image);


            
        }
    }
}
