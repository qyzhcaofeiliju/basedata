package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 移动货架位置
 */
public class MoveshelfLocation extends BaseEntity {

    private static final long serialVersionUID = -3459631031467712220L;

    // 周转箱当前站点ID
    private String mslId;

    // 移动货架的ID号
    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String mslEquId;

    // 移动货架的编号
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short mslEquNumber;

    // 货架当前站点
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short mslCurStation;

    // 货架上一站点
    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short mslPreStation;

    public String getMslId() {
        return mslId;
    }

    public void setMslId(String mslId) {
        this.mslId = mslId == null ? null : mslId.trim();
    }

    public String getMslEquId() {
        return mslEquId;
    }

    public void setMslEquId(String mslEquId) {
        this.mslEquId = mslEquId == null ? null : mslEquId.trim();
    }

    public Short getMslEquNumber() {
        return mslEquNumber;
    }

    public void setMslEquNumber(Short mslEquNumber) {
        this.mslEquNumber = mslEquNumber;
    }

    public Short getMslCurStation() {
        return mslCurStation;
    }

    public void setMslCurStation(Short mslCurStation) {
        this.mslCurStation = mslCurStation;
    }

    public Short getMslPreStation() {
        return mslPreStation;
    }

    public void setMslPreStation(Short mslPreStation) {
        this.mslPreStation = mslPreStation;
    }
}