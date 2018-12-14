package com.earthworm.ipsp.foundation.entity;

import com.earthworm.postgres.entity.BaseEntity;

import java.util.Date;

/**
 * @Auther: chenrh@i-earthworm.com
 * @Date: 2018/11/7 10:08
 * @Description:周转箱唯一码规则实体
 */
public class SuitcaseQrrule extends BaseEntity{

    private static final long serialVersionUID = -5292955254737361467L;

    private String sqrrId;

    private String sqrrNumber;

    private String sqrrProductmodel;

    private Date sqrrDateTime;

    private Short sqrrStatus;

    public String getSqrrId() {
        return sqrrId;
    }

    public void setSqrrId(String sqrrId) {
        this.sqrrId = sqrrId == null ? null : sqrrId.trim();
    }

    public String getSqrrNumber() {
        return sqrrNumber;
    }

    public void setSqrrNumber(String sqrrNumber) {
        this.sqrrNumber = sqrrNumber;
    }

    public String getSqrrProductmodel() {
        return sqrrProductmodel;
    }

    public void setSqrrProductmodel(String sqrrProductmodel) {
        this.sqrrProductmodel = sqrrProductmodel == null ? null : sqrrProductmodel.trim();
    }

    public Date getSqrrDateTime() {
        return sqrrDateTime;
    }

    public void setSqrrDateTime(Date sqrrDateTime) {
        this.sqrrDateTime = sqrrDateTime;
    }

    public Short getSqrrStatus() {
        return sqrrStatus;
    }

    public void setSqrrStatus(Short sqrrStatus) {
        this.sqrrStatus = sqrrStatus;
    }
}