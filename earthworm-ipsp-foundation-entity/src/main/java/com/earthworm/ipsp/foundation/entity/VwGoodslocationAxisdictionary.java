package com.earthworm.ipsp.foundation.entity;

import com.earthworm.postgres.entity.BaseEntity;

public class VwGoodslocationAxisdictionary extends BaseEntity {

    private static final long serialVersionUID = 3387421374534748824L;

    private String axisdId;

    private String axisdEquId;

    private Short axisdNumber;

    private Short axisdStatus;

    private Short axisdRank;

    private Short axisdTier;

    private Short axisdColumn;

    private Integer axisdX;

    private Integer axisdY;

    private Integer axisdZ;

    private String axisdSuitcaseuniquecode;

    private Short axisdSuitcasetype;

    private Boolean axisdIsautoload;

    private String equUniquecode;

    private String funName;

    public String getAxisdId() {
        return axisdId;
    }

    public void setAxisdId(String axisdId) {
        this.axisdId = axisdId == null ? null : axisdId.trim();
    }

    public String getAxisdEquId() {
        return axisdEquId;
    }

    public void setAxisdEquId(String axisdEquId) {
        this.axisdEquId = axisdEquId == null ? null : axisdEquId.trim();
    }

    public Short getAxisdNumber() {
        return axisdNumber;
    }

    public void setAxisdNumber(Short axisdNumber) {
        this.axisdNumber = axisdNumber;
    }

    public Short getAxisdStatus() {
        return axisdStatus;
    }

    public void setAxisdStatus(Short axisdStatus) {
        this.axisdStatus = axisdStatus;
    }

    public Short getAxisdRank() {
        return axisdRank;
    }

    public void setAxisdRank(Short axisdRank) {
        this.axisdRank = axisdRank;
    }

    public Short getAxisdTier() {
        return axisdTier;
    }

    public void setAxisdTier(Short axisdTier) {
        this.axisdTier = axisdTier;
    }

    public Short getAxisdColumn() {
        return axisdColumn;
    }

    public void setAxisdColumn(Short axisdColumn) {
        this.axisdColumn = axisdColumn;
    }

    public Integer getAxisdX() {
        return axisdX;
    }

    public void setAxisdX(Integer axisdX) {
        this.axisdX = axisdX;
    }

    public Integer getAxisdY() {
        return axisdY;
    }

    public void setAxisdY(Integer axisdY) {
        this.axisdY = axisdY;
    }

    public Integer getAxisdZ() {
        return axisdZ;
    }

    public void setAxisdZ(Integer axisdZ) {
        this.axisdZ = axisdZ;
    }

    public String getAxisdSuitcaseuniquecode() {
        return axisdSuitcaseuniquecode;
    }

    public void setAxisdSuitcaseuniquecode(String axisdSuitcaseuniquecode) {
        this.axisdSuitcaseuniquecode = axisdSuitcaseuniquecode == null ? null : axisdSuitcaseuniquecode.trim();
    }

    public Short getAxisdSuitcasetype() {
        return axisdSuitcasetype;
    }

    public void setAxisdSuitcasetype(Short axisdSuitcasetype) {
        this.axisdSuitcasetype = axisdSuitcasetype;
    }

    public Boolean getAxisdIsautoload() {
        return axisdIsautoload;
    }

    public void setAxisdIsautoload(Boolean axisdIsautoload) {
        this.axisdIsautoload = axisdIsautoload;
    }

    public String getEquUniquecode() {
        return equUniquecode;
    }

    public void setEquUniquecode(String equUniquecode) {
        this.equUniquecode = equUniquecode == null ? null : equUniquecode.trim();
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }
}