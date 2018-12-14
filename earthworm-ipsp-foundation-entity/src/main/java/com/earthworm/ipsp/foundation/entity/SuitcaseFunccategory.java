package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 周转箱功能分类字典表
 */
public class SuitcaseFunccategory extends BaseEntity {

    private static final long serialVersionUID = -4707395642459181711L;

    private String funId;

    @Size(max = 64, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String funName;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short funCategory;

    public String getFunId() {
        return funId;
    }

    public void setFunId(String funId) {
        this.funId = funId == null ? null : funId.trim();
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }

    public Short getFunCategory() {
        return funCategory;
    }

    public void setFunCategory(Short funCategory) {
        this.funCategory = funCategory;
    }
}