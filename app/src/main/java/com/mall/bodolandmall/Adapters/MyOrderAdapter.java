package com.mall.bodolandmall.Adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mall.bodolandmall.Activities.OrderDetailsActivity;
import com.mall.bodolandmall.Models.MyOrderItemModel;
import com.mall.bodolandmall.R;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private List<MyOrderItemModel> myOrderItemModelList;
    private int Rating;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_order_items_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder viewHolder, int i) {

        int resources=myOrderItemModelList.get(i).getProductImage();
        int rating=myOrderItemModelList.get(i).getRating();

        String title=myOrderItemModelList.get(i).getProductTitle();
        String  deliverydate= myOrderItemModelList.get(i).getDeleveryStatus();
        viewHolder.setdata(resources,rating,title,deliverydate);


    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productTitle, productDeliveryStatus;
        private ImageView productImage, orderIndicator;
        private LinearLayout rateNowContainer;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title);
            productDeliveryStatus = itemView.findViewById(R.id.order_delivery_date);
            productImage = itemView.findViewById(R.id.product_image);
            orderIndicator = itemView.findViewById(R.id.order_indicator);
            rateNowContainer=itemView.findViewById(R.id.star_rating_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailsIntent= new Intent(itemView.getContext(), OrderDetailsActivity.class);
                    itemView.getContext().startActivity(orderDetailsIntent);
                }
            });
        }

        private void setdata(int resource ,int rating, String title, String deliveryStatus) {
            productImage.setImageResource(resource);
            productTitle.setText(title);

            if (deliveryStatus.equals("Cancelled")) {
                orderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorPrimaryDark)));
            } else {
                orderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.success_green)));
            }
            productDeliveryStatus.setText(deliveryStatus);


            setRating(rating);
            /*rating star*/
            for (int x=0;x<rateNowContainer.getChildCount();x++){
                final  int startPosition=x;
                rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRating(startPosition);
                    }
                });
            }

        }
        /* set rating method*/
        private void setRating(int startPosition) {
            for (int x=0;x<rateNowContainer.getChildCount();x++){
                ImageView startBtn=(ImageView)rateNowContainer.getChildAt(x);
                startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if (x<=startPosition){
                    startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }

        }
    }


}
