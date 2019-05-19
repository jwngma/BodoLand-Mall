package com.mall.bodolandmall.Models;

import java.util.List;

public class HomePageModel {

    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_ADS_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;
    private int Type;

    /* banner slider*/
    List<SliderModel> sliderModelList;

    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        Type = type;
        this.sliderModelList = sliderModelList;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    /* banner slider*/

    /* strip ads*/
    private int resources;
    private String backgroundColor;

    public HomePageModel(int type, int resources, String backgroundColor) {
        Type = type;
        this.resources = resources;
        this.backgroundColor = backgroundColor;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /* strip ads*/

    /* HORIZONTAL PRODUCT VIEW*/
    private String title;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HomePageModel(int type, String title, List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        Type = type;
        this.title = title;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return horizontalProductScrollModelList;
    }

    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    /* HORIZONTAL PRODUCT VIEW*/


}
