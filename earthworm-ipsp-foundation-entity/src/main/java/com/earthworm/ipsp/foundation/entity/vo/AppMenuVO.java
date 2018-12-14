package com.earthworm.ipsp.foundation.entity.vo;

import com.earthworm.ipsp.foundation.entity.AppMenu;

import java.util.ArrayList;
import java.util.List;

public class AppMenuVO {
    private AppMenu menu;
    private boolean isActive;
    private List<AppMenuVO> subMenus = new ArrayList<>();
    private boolean isRoot;
    private boolean hasChildren;

    public AppMenu getMenu() {
        return menu;
    }

    public void setMenu(AppMenu menu) {
        this.menu = menu;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<AppMenuVO> getSubMenus() {
        return subMenus;
    }

    public void addSubMenu(AppMenuVO subMenu) {
        this.subMenus.add(subMenu);
    }

    public void addSubMenus(List<AppMenuVO> subMenus) {
        this.subMenus.addAll(subMenus);
    }

    public void setSubMenus(List<AppMenuVO> subMenus) {
        this.subMenus = subMenus;
    }
}
