package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 物流站点
 */
public class MaterialflowStation extends BaseEntity {

    private static final long serialVersionUID = -2306364135284138982L;

    private String staId;// 站点ID

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 64, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String staName;// 站点名称

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(1)
    private Short staNumber;// 站点编号

    // X坐标
    @NotNull(message = EarthWormException.NOTNULL)
    @DecimalMin("0")
    private BigDecimal staX;

    // Y坐标
    @NotNull(message = EarthWormException.NOTNULL)
    @DecimalMin("0")
    private BigDecimal staY;

    // Z坐标
    @NotNull(message = EarthWormException.NOTNULL)
    @DecimalMin("0")
    private BigDecimal staZ;

    // 站点描述
    @Size(max = 200, message = "OUTLENGTH")
    private String staDescription;

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId == null ? null : staId.trim();
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName == null ? null : staName.trim();
    }

    public Short getStaNumber() {
        return staNumber;
    }

    public void setStaNumber(Short staNumber) {
        this.staNumber = staNumber;
    }

    public String getStaDescription() {
        return staDescription;
    }

    public void setStaDescription(String staDescription) {
        this.staDescription = staDescription == null ? null : staDescription.trim();
    }

    public BigDecimal getStaX() {
        return staX;
    }

    public void setStaX(BigDecimal staX) {
        this.staX = staX;
    }

    public BigDecimal getStaY() {
        return staY;
    }

    public void setStaY(BigDecimal staY) {
        this.staY = staY;
    }

    public BigDecimal getStaZ() {
        return staZ;
    }

    public void setStaZ(BigDecimal staZ) {
        this.staZ = staZ;
    }
}