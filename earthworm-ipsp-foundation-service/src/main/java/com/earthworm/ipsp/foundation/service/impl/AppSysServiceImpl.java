package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.ipsp.foundation.dao.interf.IAppSysDao;
import com.earthworm.ipsp.foundation.dao.interf.IAppSysMenuDao;
import com.earthworm.ipsp.foundation.entity.AppMenu;
import com.earthworm.ipsp.foundation.entity.AppSys;
import com.earthworm.ipsp.foundation.entity.AppSysMenu;
import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.IAppSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service("appSysService")
public class AppSysServiceImpl implements IAppSysService {
    private static Logger logger = LoggerFactory.getLogger(AppSysServiceImpl.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppSysDao appSysDao;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppSysMenuDao appSysMenuDao;

    @Transactional(readOnly = true)
    @Override
    public List<AppSys> find(AppSys appSys) {
        return null;
    }

    @Override
    public boolean addAppSys(AppSys appSys) throws IPSPFoundationServiceException {
        if(appSys!=null) {
            try {
                return appSysDao.save(appSys)>0;
            }
            catch (Exception e){
                IPSPFoundationServiceException ex =
                        new IPSPFoundationServiceException(
                                e,"",IPSPFoundationServiceException.FoundationCode.APPSYS_ADD_ERR);
                logger.error("Save AppSys failed!",ex);
                throw ex;
            }
        }
        return false;
    }

    @Override
    public boolean addAppMenu(AppSys appSys, Set<VwAppSysMenu> vwAppSysMenus) throws IPSPFoundationServiceException {
        //Add application informtaion
        if(addAppSys(appSys)){
            if(vwAppSysMenus !=null&&!vwAppSysMenus.isEmpty()) {
                //遍历，封装中间表对象
                List<AppSysMenu> appSysMenus = new ArrayList<>();
                    vwAppSysMenus.forEach(vwAppSysMenu->{
                        if(vwAppSysMenu!=null&&vwAppSysMenu.getAppMenu()!=null) {
                            AppMenu appMenu = vwAppSysMenu.getAppMenu();
                            AppSysMenu appSysMenu = new AppSysMenu();
                            appSysMenu.setAppMenuId(appMenu.getMenuId());
                            appSysMenu.setAppSysCode(vwAppSysMenu.getAppSysCode());
                            appSysMenu.setAppSysmenuCreatetime(LocalDateTime.now());
                            appSysMenu.setAppSysMenuId(UUID.randomUUID().toString());
                            appSysMenu.setAppSysmenuMenuDescription(appMenu.getMenuDescription());
                            appSysMenu.setAppSysmenuModifytime(appSysMenu.getAppSysmenuCreatetime());
                            appSysMenu.setAppSysmenuSort(vwAppSysMenu.getSortNum());
                            appSysMenus.add(appSysMenu);
                        }

                });

                try {
                    //Add app menu
                    if(appSysMenus!=null&&!appSysMenus.isEmpty())
                        return appSysMenuDao.saveBatch(appSysMenus)>=(long)appSysMenus.size();
                }
                catch (Exception e) {
                    IPSPFoundationServiceException ex =
                            new IPSPFoundationServiceException(
                                    e,"",IPSPFoundationServiceException.FoundationCode.APPSYSMENU_ADD_ERR);
                    logger.error("Save AppSys failed!",ex);
                    throw ex;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateAppSys(AppSys appSys) throws IPSPFoundationServiceException {
        if(appSys!=null) {
            try {
                return appSysDao.update(appSys)>0;
            }
            catch (Exception e){
                IPSPFoundationServiceException ex =
                        new IPSPFoundationServiceException(
                                e,"",IPSPFoundationServiceException.FoundationCode.APPSYS_UPT_ERR);
                logger.error("Update AppSys failed!",ex);
                throw ex;
            }
        }
        return false;
    }
}
