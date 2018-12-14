package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;

import java.util.List;

public interface IVwAppSysMenuService {
    /**
     * Find application system menus
     * @param appSysMenu
     * @return
     */
    List<VwAppSysMenu> findSysMenu(VwAppSysMenu appSysMenu);

    /**
     * Find application system menus
     * @param sysCode
     * @return
     */
    List<VwAppSysMenu> findSysMenu(String sysCode);


    List<VwAppSysMenu> findSysMenu(String... sysCode);
}
