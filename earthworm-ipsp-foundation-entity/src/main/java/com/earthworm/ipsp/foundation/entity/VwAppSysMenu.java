package com.earthworm.ipsp.foundation.entity;

import com.earthworm.postgres.entity.BaseEntity;

import javax.validation.constraints.NotNull;

public class VwAppSysMenu extends BaseEntity {
    private AppMenu appMenu;
    private Short sortNum;
    private String appSysCode;
    //菜单访问根路径
    private String menuAccessAddress;

    public AppMenu getAppMenu() {
        return appMenu;
    }

    public void setAppMenu(AppMenu appMenu) {
        this.appMenu = appMenu;
    }

    public Short getSortNum() {
        return sortNum;
    }

    public void setSortNum(Short sortNum) {
        this.sortNum = sortNum;
    }

    public String getAppSysCode() {
        return appSysCode;
    }

    public void setAppSysCode(String appSysCode) {
        this.appSysCode = appSysCode;
    }

    public String getMenuAccessAddress() {
        return menuAccessAddress;
    }

    public void setMenuAccessAddress(String menuAccessAddress) {
        this.menuAccessAddress = menuAccessAddress;
    }
}
