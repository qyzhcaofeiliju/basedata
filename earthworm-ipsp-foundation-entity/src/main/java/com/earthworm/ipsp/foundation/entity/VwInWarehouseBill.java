package com.earthworm.ipsp.foundation.entity;

import com.earthworm.postgres.entity.BaseEntity;

import java.util.Date;

public class VwInWarehouseBill extends BaseEntity {

    private static final long serialVersionUID = 1481623558752096267L;

    private String inwId;

    private String inwBillsnumber;

    private Short inwCategory;

    private Date inwIntime;

    private Short inwStatus;

    private String catName;

    public String getInwId() {
        return inwId;
    }

    public void setInwId(String inwId) {
        this.inwId = inwId == null ? null : inwId.trim();
    }

    public String getInwBillsnumber() {
        return inwBillsnumber;
    }

    public void setInwBillsnumber(String inwBillsnumber) {
        this.inwBillsnumber = inwBillsnumber == null ? null : inwBillsnumber.trim();
    }

    public Short getInwCategory() {
        return inwCategory;
    }

    public void setInwCategory(Short inwCategory) {
        this.inwCategory = inwCategory;
    }

    public Date getInwIntime() {
        return inwIntime;
    }

    public void setInwIntime(Date inwIntime) {
        this.inwIntime = inwIntime;
    }

    public Short getInwStatus() {
        return inwStatus;
    }

    public void setInwStatus(Short inwStatus) {
        this.inwStatus = inwStatus;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }
}