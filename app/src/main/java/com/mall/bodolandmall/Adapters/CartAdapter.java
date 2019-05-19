package com.mall.bodolandmall.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mall.bodolandmall.Models.CartItemModel;
import com.mall.bodolandmall.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CartItemModel.CART_ITEM;

            case 1:
                return CartItemModel.TOTLA_AMOUNT;
            default:
                return -1;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout, viewGroup, false);
                return new CartItemViewHolder(cartItemView);

            case CartItemModel.TOTLA_AMOUNT:
                View cartTotalAmountView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_total_amount_layout, viewGroup, false);
                return new CartTotalAmount(cartTotalAmountView);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (cartItemModelList.get(position).getType()) {
            case CartItemModel.CART_ITEM:
                int image_resource=cartItemModelList.get(position).getProductImage();
                String product_title=cartItemModelList.get(position).getProductTitle();
                String product_price=cartItemModelList.get(position).getProductPrice();
                String product_cutted_price=cartItemModelList.get(position).getCuttedPrice();
                int freecoupons=cartItemModelList.get(position).getCoupon_applied();
                int offersApplied=cartItemModelList.get(position).getOfferApplied();

                ((CartItemViewHolder)viewHolder).setCartItemDetails(image_resource,product_title,freecoupons,product_price,product_cutted_price,offersApplied);
                break;

            case CartItemModel.TOTLA_AMOUNT:
                String total_items=cartItemModelList.get(position).getTotlaItems();
                String total_item_price=cartItemModelList.get(position).getTotalItemsPrice();
                String total_delivery_price=cartItemModelList.get(position).getDeliveryPrice();
                String total_Amount=cartItemModelList.get(position).getTotalAmount();
                String total_saved_amount=cartItemModelList.get(position).getSavedAmount();
                ((CartTotalAmount)viewHolder).setCartTotalAmount(total_items,total_item_price,total_delivery_price,total_Amount,total_saved_amount);
                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView product_image, product_couponIcon;
        private TextView productTitle, product_free_coupon, product_price,
                cutted_price, offers_applied, coupons_applied, product_quantity;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            product_couponIcon = itemView.findViewById(R.id.free_coupon_icon);
            product_free_coupon = itemView.findViewById(R.id.free_coupon_text);
            product_price = itemView.findViewById(R.id.product_price);
            cutted_price = itemView.findViewById(R.id.cutted_price);
            offers_applied = itemView.findViewById(R.id.offer_applied);
            coupons_applied = itemView.findViewById(R.id.coupon_applied);
            product_quantity = itemView.findViewById(R.id.tv_quantity);
        }

        private void setCartItemDetails(int resource, String title, int freecouponNo,
                                        String productprice, String cuttedprice, int offersappliedNo) {
            product_image.setImageResource(resource);
            productTitle.setText(title);
            product_price.setText("Rs. "+productprice);
            cutted_price.setText("Rs. "+cuttedprice);
            if (freecouponNo > 0) {
                product_free_coupon.setVisibility(View.VISIBLE);
                product_couponIcon.setVisibility(View.VISIBLE);

                if (freecouponNo == 1) {
                    product_free_coupon.setText("free " + freecouponNo + " coupon");
                } else {
                    product_free_coupon.setText("free " + freecouponNo + " coupons");
                }

            } else {
                product_free_coupon.setVisibility(View.INVISIBLE);
                product_couponIcon.setVisibility(View.INVISIBLE);
            }

            if (offersappliedNo > 0) {
                offers_applied.setVisibility(View.VISIBLE);
                offers_applied.setText(offersappliedNo + " offers Applied");
            } else {
                offers_applied.setVisibility(View.INVISIBLE);
            }
        }
    }


    class CartTotalAmount extends RecyclerView.ViewHolder {

        private TextView total_items, totalItemPrice, totalItemDeliveryPrice, TotalAmount, SavedAmount;

        public CartTotalAmount(@NonNull View itemView) {
            super(itemView);

            total_items = itemView.findViewById(R.id.total_items);
            totalItemPrice = itemView.findViewById(R.id.total_item_price);
            totalItemDeliveryPrice = itemView.findViewById(R.id.total_delivery_charge);
            TotalAmount = itemView.findViewById(R.id.total_amount);
            SavedAmount = itemView.findViewById(R.id.saved_amount);
        }


        private void setCartTotalAmount(String total_item_text, String total_item_price_text, String total_item_delivery_charge_text,
                                        String total_amount_text, String saved_amount_text) {

            total_items.setText(total_item_text);
            totalItemPrice.setText("Rs. "+total_item_price_text);
            totalItemDeliveryPrice.setText(total_item_delivery_charge_text);
            TotalAmount.setText("Rs. "+total_amount_text);
            SavedAmount.setText("You Will Saved Rs." +saved_amount_text+" from this Order");

        }
    }
}
