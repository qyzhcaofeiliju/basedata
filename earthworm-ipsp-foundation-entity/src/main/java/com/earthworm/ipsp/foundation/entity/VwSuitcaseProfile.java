package com.earthworm.ipsp.foundation.entity;

import com.earthworm.postgres.entity.BaseEntity;

public class VwSuitcaseProfile extends BaseEntity {

    private static final long serialVersionUID = 5058514220929444573L;

    private String proId;

    private String proUniquecode;

    private String proRfid;

    private Short proCabintotal;

    private Short proAppcategory;

    private Short proFunccategory;

    private Short proStatus;

    private Boolean proIspartition;

    private Short proVerticalamount;

    private Short proHorizontalamount;

    private String funName;

    private String appName;

    private Short proHeight;

    private Short proWidth;

    private Short proLength;

    private Short proCabinwidth;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getProUniquecode() {
        return proUniquecode;
    }

    public void setProUniquecode(String proUniquecode) {
        this.proUniquecode = proUniquecode == null ? null : proUniquecode.trim();
    }

    public String getProRfid() {
        return proRfid;
    }

    public void setProRfid(String proRfid) {
        this.proRfid = proRfid == null ? null : proRfid.trim();
    }

    public Short getProCabintotal() {
        return proCabintotal;
    }

    public void setProCabintotal(Short proCabintotal) {
        this.proCabintotal = proCabintotal;
    }

    public Short getProAppcategory() {
        return proAppcategory;
    }

    public void setProAppcategory(Short proAppcategory) {
        this.proAppcategory = proAppcategory;
    }

    public Short getProFunccategory() {
        return proFunccategory;
    }

    public void setProFunccategory(Short proFunccategory) {
        this.proFunccategory = proFunccategory;
    }

    public Short getProStatus() {
        return proStatus;
    }

    public void setProStatus(Short proStatus) {
        this.proStatus = proStatus;
    }

    public Boolean getProIspartition() {
        return proIspartition;
    }

    public void setProIspartition(Boolean proIspartition) {
        this.proIspartition = proIspartition;
    }

    public Short getProVerticalamount() {
        return proVerticalamount;
    }

    public void setProVerticalamount(Short proVerticalamount) {
        this.proVerticalamount = proVerticalamount;
    }

    public Short getProHorizontalamount() {
        return proHorizontalamount;
    }

    public void setProHorizontalamount(Short proHorizontalamount) {
        this.proHorizontalamount = proHorizontalamount;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public Short getProHeight() {
        return proHeight;
    }

    public void setProHeight(Short proHeight) {
        this.proHeight = proHeight;
    }

    public Short getProWidth() {
        return proWidth;
    }

    public void setProWidth(Short proWidth) {
        this.proWidth = proWidth;
    }

    public Short getProLength() {
        return proLength;
    }

    public void setProLength(Short proLength) {
        this.proLength = proLength;
    }

    public Short getProCabinwidth() {
        return proCabinwidth;
    }

    public void setProCabinwidth(Short proCabinwidth) {
        this.proCabinwidth = proCabinwidth;
    }
}