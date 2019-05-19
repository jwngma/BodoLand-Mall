package com.mall.bodolandmall.Models;

public class MyOrderItemModel {
    private String productTitle;
    private int productImage;
    private String deleveryStatus;
    private int rating;

    public MyOrderItemModel(String productTitle, int productImage, String deleveryStatus, int rating) {
        this.productTitle = productTitle;
        this.productImage = productImage;
        this.deleveryStatus = deleveryStatus;
        this.rating = rating;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getDeleveryStatus() {
        return deleveryStatus;
    }

    public void setDeleveryStatus(String deleveryStatus) {
        this.deleveryStatus = deleveryStatus;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
