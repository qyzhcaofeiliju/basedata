package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 周转箱应用分类字典表
 */
public class SuitcaseAppcategory extends BaseEntity {

    private static final long serialVersionUID = -4535600062033226551L;

    private String appId;

    @Size(max = 64, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String appName;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short appCategory;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public Short getAppCategory() {
        return appCategory;
    }

    public void setAppCategory(Short appCategory) {
        this.appCategory = appCategory;
    }
}