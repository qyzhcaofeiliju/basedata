package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.AppSys;
import com.earthworm.ipsp.foundation.entity.AppSysMenu;
import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;

import java.util.List;
import java.util.Set;

public interface IAppSysMenuService {
    /**
     * Add app sys menu
     * @param appSysMenu
     * @return
     * @throws IPSPFoundationServiceException
     */
    boolean addAppSysMenu(AppSysMenu appSysMenu) throws IPSPFoundationServiceException;

    /**
     * Update app sys menu
     * @param appSysMenu
     * @return
     * @throws IPSPFoundationServiceException
     */
    boolean updateAppSysMenu(AppSysMenu appSysMenu) throws IPSPFoundationServiceException;
}
