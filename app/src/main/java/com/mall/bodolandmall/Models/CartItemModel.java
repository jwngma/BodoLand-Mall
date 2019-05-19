package com.mall.bodolandmall.Models;

public class CartItemModel {
    public static final int CART_ITEM = 0;
    public static final int TOTLA_AMOUNT = 1;

    private int Type;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
    /* cart item*/

    private int productImage;
    private String productTitle;
    private String productPrice;
    private String cuttedPrice;
    private int productQuantity;
    private int offerApplied;
    private int coupon_applied;

    public CartItemModel(int type, int productImage, String productTitle, String productPrice, String cuttedPrice, int productQuantity, int offerApplied, int coupon_applied) {
        Type = type;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.productQuantity = productQuantity;
        this.offerApplied = offerApplied;
        this.coupon_applied = coupon_applied;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getOfferApplied() {
        return offerApplied;
    }

    public void setOfferApplied(int offerApplied) {
        this.offerApplied = offerApplied;
    }

    public int getCoupon_applied() {
        return coupon_applied;
    }

    public void setCoupon_applied(int coupon_applied) {
        this.coupon_applied = coupon_applied;
    }
    /* cart item*/
    /* cart total*/

    private String totlaItems;
    private String totalItemsPrice;
    private String deliveryPrice;
    private String savedAmount;
    private String totalAmount;



    public CartItemModel(int type, String totlaItems, String totalItemsPrice, String deliveryPrice, String savedAmount, String totalAmount) {
        Type = type;
        this.totlaItems = totlaItems;
        this.totalItemsPrice = totalItemsPrice;
        this.deliveryPrice = deliveryPrice;
        this.savedAmount = savedAmount;
        this.totalAmount=totalAmount;
    }

    public String getTotlaItems() {
        return totlaItems;
    }

    public void setTotlaItems(String totlaItems) {
        this.totlaItems = totlaItems;
    }

    public String getTotalItemsPrice() {
        return totalItemsPrice;
    }

    public void setTotalItemsPrice(String totalItemsPrice) {
        this.totalItemsPrice = totalItemsPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }
    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /* cart total*/


}
