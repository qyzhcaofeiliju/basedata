package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 多功能AGV货位坐标字典表
 */
public class MfagvAxisdictionary extends BaseEntity {

    private static final long serialVersionUID = -1100385685057895134L;

    private String axisdId;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdNumber;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 0, message = "BEYONDMIN")
    private Short axisdStatus;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdRank;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdTier;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdColumn;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Integer axisdX;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Integer axisdY;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Integer axisdZ;

    public String getAxisdId() {
        return axisdId;
    }

    public void setAxisdId(String axisdId) {
        this.axisdId = axisdId == null ? null : axisdId.trim();
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
}