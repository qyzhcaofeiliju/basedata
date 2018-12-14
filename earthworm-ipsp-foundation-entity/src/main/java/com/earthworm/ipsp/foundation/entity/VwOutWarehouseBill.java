package com.earthworm.ipsp.foundation.entity;

import com.earthworm.postgres.entity.BaseEntity;

import java.util.Date;

public class VwOutWarehouseBill extends BaseEntity {

    private static final long serialVersionUID = -8780119217627127138L;

    private String outwId;

    private String outwBillsnumber;

    private Short outwCategory;

    private Date outwOuttime;

    private Short outwStatus;

    private String catName;

    public String getOutwId() {
        return outwId;
    }

    public void setOutwId(String outwId) {
        this.outwId = outwId == null ? null : outwId.trim();
    }

    public String getOutwBillsnumber() {
        return outwBillsnumber;
    }

    public void setOutwBillsnumber(String outwBillsnumber) {
        this.outwBillsnumber = outwBillsnumber == null ? null : outwBillsnumber.trim();
    }

    public Short getOutwCategory() {
        return outwCategory;
    }

    public void setOutwCategory(Short outwCategory) {
        this.outwCategory = outwCategory;
    }

    public Date getOutwOuttime() {
        return outwOuttime;
    }

    public void setOutwOuttime(Date outwOuttime) {
        this.outwOuttime = outwOuttime;
    }

    public Short getOutwStatus() {
        return outwStatus;
    }

    public void setOutwStatus(Short outwStatus) {
        this.outwStatus = outwStatus;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }
}