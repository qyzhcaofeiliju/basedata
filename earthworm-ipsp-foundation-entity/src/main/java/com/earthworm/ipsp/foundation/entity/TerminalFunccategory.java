package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 终端设备功能分类
 */
public class TerminalFunccategory extends BaseEntity {

    private static final long serialVersionUID = -4133830558581192958L;

    private String funId;// 功能分类ID

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 64, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String funName;// 功能分类名称

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(1)
    private Short funCategory;// 功能分类类别

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