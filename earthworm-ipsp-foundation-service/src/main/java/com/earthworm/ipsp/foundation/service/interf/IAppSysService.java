package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.AppSys;
import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;

import java.util.List;
import java.util.Set;

public interface IAppSysService {

    /**
     * Find application system information
     * @param appSys
     * @return
     */
    List<AppSys> find(AppSys appSys);

    /**
     * Add application system information
     * @param appSys
     * @return
     * @throws IPSPFoundationServiceException
     */
    boolean addAppSys(AppSys appSys) throws IPSPFoundationServiceException;

    /**
     * Add menu to application
     * @param appSys
     * @param vwAppSysMenus
     * @return
     * @throws IPSPFoundationServiceException
     */
    boolean addAppMenu(AppSys appSys, Set<VwAppSysMenu> vwAppSysMenus) throws IPSPFoundationServiceException;

    /**
     * Update application system information
     * @param appSys
     * @return
     * @throws IPSPFoundationServiceException
     */
    boolean updateAppSys(AppSys appSys) throws IPSPFoundationServiceException;
}
