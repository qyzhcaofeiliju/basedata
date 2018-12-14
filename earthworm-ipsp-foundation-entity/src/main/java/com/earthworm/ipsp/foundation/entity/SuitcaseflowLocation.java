package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 周转箱当前到达的物流站点
 */
public class SuitcaseflowLocation extends BaseEntity {

    private static final long serialVersionUID = -7111657575648603272L;

    private String locId;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short locStaNumber;

    @NotNull(message = EarthWormException.NOTNULL)
    private Boolean locIsshelf;

    @Size(max = 50, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String locSuitcaseUniquecode;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short locGoodsshelfNumber;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short locGoodslocationNumber;

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId == null ? null : locId.trim();
    }

    public Short getLocStaNumber() {
        return locStaNumber;
    }

    public void setLocStaNumber(Short locStaNumber) {
        this.locStaNumber = locStaNumber;
    }

    public Boolean getLocIsshelf() {
        return locIsshelf;
    }

    public void setLocIsshelf(Boolean locIsshelf) {
        this.locIsshelf = locIsshelf;
    }

    public String getLocSuitcaseUniquecode() {
        return locSuitcaseUniquecode;
    }

    public void setLocSuitcaseUniquecode(String locSuitcaseUniquecode) {
        this.locSuitcaseUniquecode = locSuitcaseUniquecode == null ? null : locSuitcaseUniquecode.trim();
    }

    public Short getLocGoodsshelfNumber() {
        return locGoodsshelfNumber;
    }

    public void setLocGoodsshelfNumber(Short locGoodsshelfNumber) {
        this.locGoodsshelfNumber = locGoodsshelfNumber;
    }

    public Short getLocGoodslocationNumber() {
        return locGoodslocationNumber;
    }

    public void setLocGoodslocationNumber(Short locGoodslocationNumber) {
        this.locGoodslocationNumber = locGoodslocationNumber;
    }
}