package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

public class AppSysMenu extends BaseEntity {
    private String appSysMenuId;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    private String appMenuId;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    private String appSysCode;

    @NotNull(message = EarthWormException.NOTNULL)
    private LocalDateTime appSysmenuCreatetime;

    @NotNull(message = EarthWormException.NOTNULL)
    private LocalDateTime appSysmenuModifytime;

    @Size(max = 20, message = "BEYONDSIZE")
    private String appSysmenuModifyAuthor;

    private Short appSysmenuSort;

    @Size(max = 64, message = "BEYONDSIZE")
    private String appSysmenuMenuDescription;

    public String getAppSysMenuId() {
        return appSysMenuId;
    }

    public void setAppSysMenuId(String appSysMenuId) {
        this.appSysMenuId = appSysMenuId == null ? null : appSysMenuId.trim();
    }

    public String getAppMenuId() {
        return appMenuId;
    }

    public void setAppMenuId(String appMenuId) {
        this.appMenuId = appMenuId == null ? null : appMenuId.trim();
    }

    public String getAppSysCode() {
        return appSysCode;
    }

    public void setAppSysCode(String appSysCode) {
        this.appSysCode = appSysCode == null ? null : appSysCode.trim();
    }

    public LocalDateTime getAppSysmenuCreatetime() {
        return appSysmenuCreatetime;
    }

    public void setAppSysmenuCreatetime(LocalDateTime appSysmenuCreatetime) {
        this.appSysmenuCreatetime = appSysmenuCreatetime;
    }

    public LocalDateTime getAppSysmenuModifytime() {
        return appSysmenuModifytime;
    }

    public void setAppSysmenuModifytime(LocalDateTime appSysmenuModifytime) {
        this.appSysmenuModifytime = appSysmenuModifytime;
    }

    public String getAppSysmenuModifyAuthor() {
        return appSysmenuModifyAuthor;
    }

    public void setAppSysmenuModifyAuthor(String appSysmenuModifyAuthor) {
        this.appSysmenuModifyAuthor = appSysmenuModifyAuthor == null ? null : appSysmenuModifyAuthor.trim();
    }

    public Short getAppSysmenuSort() {
        return appSysmenuSort;
    }

    public void setAppSysmenuSort(Short appSysmenuSort) {
        this.appSysmenuSort = appSysmenuSort;
    }

    public String getAppSysmenuMenuDescription() {
        return appSysmenuMenuDescription;
    }

    public void setAppSysmenuMenuDescription(String appSysmenuMenuDescription) {
        this.appSysmenuMenuDescription = appSysmenuMenuDescription == null ? null : appSysmenuMenuDescription.trim();
    }
}