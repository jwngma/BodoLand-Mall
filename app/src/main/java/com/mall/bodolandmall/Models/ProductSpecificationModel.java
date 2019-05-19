package com.mall.bodolandmall.Models;

public class ProductSpecificationModel {

    public static final int SPECIFICATION_TITLE = 0;
    public static final int SPECIFICATION_BODY = 1;


    private int Type;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    /* specifation title*/

    private  String title;

    public ProductSpecificationModel(int type, String title) {
        Type = type;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /* specifation title*/


    /*specifictio title*/

    private String featureName;
    private String featureValue;

    public ProductSpecificationModel(int type, String featureName, String featureValue) {
        Type = type;
        this.featureName = featureName;
        this.featureValue = featureValue;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }

    /*specifictio title*/


}
