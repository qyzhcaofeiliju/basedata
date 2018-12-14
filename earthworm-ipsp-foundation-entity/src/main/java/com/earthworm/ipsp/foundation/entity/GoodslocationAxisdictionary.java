package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 货架（固定、移动）货位坐标状态信息记录表
 */
public class GoodslocationAxisdictionary extends BaseEntity {

    private static final long serialVersionUID = -6037152779239772507L;

    // 货位坐标ID
    private String axisdId;

    private String axisdEquId;

    // 货位编号
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdNumber;

    // 货位状态
    // （0空闲状态、1占用状态、2故障不可用状态）
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 0, message = "BEYONDMIN")
    private Short axisdStatus;

    // 货位所在的排
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdRank;

    // 货位所在的层
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdTier;

    // 货位所在的列
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdColumn;

    // 机械X坐标值
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Integer axisdX;

    // 机械Y坐标值
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Integer axisdY;

    // 机械Z坐标值
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Integer axisdZ;

    // 周转箱唯一码
    @Size(max = 50, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String axisdSuitcaseuniquecode;

    // 周转箱类型
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short axisdSuitcasetype;

    // 是否可以自动装卸周转箱
    @NotNull(message = EarthWormException.NOTNULL)
    private Boolean axisdIsautoload;

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
}