package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 周转盒可以流转的物流站点范围
 */
public class SuitcaseflowRegion extends BaseEntity {

    private static final long serialVersionUID = -7036803211781694589L;

    private String regId;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String regStaId;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short regStaNumber;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String regProId;

    @Size(max = 50, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String regProUniquecode;

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId == null ? null : regId.trim();
    }

    public String getRegStaId() {
        return regStaId;
    }

    public void setRegStaId(String regStaId) {
        this.regStaId = regStaId == null ? null : regStaId.trim();
    }

    public Short getRegStaNumber() {
        return regStaNumber;
    }

    public void setRegStaNumber(Short regStaNumber) {
        this.regStaNumber = regStaNumber;
    }

    public String getRegProId() {
        return regProId;
    }

    public void setRegProId(String regProId) {
        this.regProId = regProId == null ? null : regProId.trim();
    }

    public String getRegProUniquecode() {
        return regProUniquecode;
    }

    public void setRegProUniquecode(String regProUniquecode) {
        this.regProUniquecode = regProUniquecode == null ? null : regProUniquecode.trim();
    }
}