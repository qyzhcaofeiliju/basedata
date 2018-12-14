package com.earthworm.ipsp.foundation.entity;

import com.earthworm.postgres.entity.BaseEntity;

public class VwTerminalCategory extends BaseEntity {

    private static final long serialVersionUID = -5885780652102594646L;

    private String catId;

    private String catModel;

    private String catName;

    private Short catAppcategory;

    private Short catFunccategory;

    private String catDescription;

    private String appName;

    private String funName;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId == null ? null : catId.trim();
    }

    public String getCatModel() {
        return catModel;
    }

    public void setCatModel(String catModel) {
        this.catModel = catModel == null ? null : catModel.trim();
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public Short getCatAppcategory() {
        return catAppcategory;
    }

    public void setCatAppcategory(Short catAppcategory) {
        this.catAppcategory = catAppcategory;
    }

    public Short getCatFunccategory() {
        return catFunccategory;
    }

    public void setCatFunccategory(Short catFunccategory) {
        this.catFunccategory = catFunccategory;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription == null ? null : catDescription.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }
}