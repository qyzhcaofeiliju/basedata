package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 终端设备应用分类
 */
public class TerminalAppcategory extends BaseEntity {

    private static final long serialVersionUID = -7816498889298216153L;

    private String appId;// 应用分类Id

    @NotBlank
    @Size(max = 64, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String appName;// 应用分类名称


    @NotNull(message = EarthWormException.NOTNULL)
    @Min(1)
    private Short appCategory;// 应用分类类别

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