package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IAppMenuDao;
import com.earthworm.ipsp.foundation.entity.AppMenu;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.IAppMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class AppMenuServiceImpl implements IAppMenuService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppMenuDao appMenuDao;

    @Override

    public List<AppMenu> findAll() {
        return appMenuDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public boolean addMenu(AppMenu menu) throws IPSPFoundationServiceException {
        if (appMenuDao.save(menu) == 1) {
            return true;
        }
        IPSPFoundationServiceException ex = new IPSPFoundationServiceException("Add AppMenu failed!", IPSPFoundationServiceException.FoundationCode.APPMENU_ADD_ERR);
        throw ex;
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public boolean updateMenu(AppMenu menu) throws IPSPFoundationServiceException {
        if (appMenuDao.update(menu) == 1) {
            return true;
        }
        IPSPFoundationServiceException ex = new IPSPFoundationServiceException(
                "Update AppMenu failed!", IPSPFoundationServiceException.FoundationCode.APPMENU_UPT_ERR);
        throw ex;
    }

    @Override
    public List<AppMenu> findAllChildren(AppMenu menu, boolean includeSelf) {
        List<AppMenu> list = null;
        if(menu!=null){
            if(StringUtils.isNotBlank(menu.getMenuName()))
                list = findAllChildrenByName(menu.getMenuName(),includeSelf);
            else if(StringUtils.isNotBlank(menu.getMenuId()))
                list = findAllChildrenByID(menu.getMenuId(), includeSelf);
        }

        return list;
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public boolean cascadeDelete(AppMenu menu, boolean includeSelf) throws IPSPFoundationServiceException {
        boolean bool = false;
        if (menu != null) {
            try {
                bool = StringUtils.isNotBlank(menu.getMenuName()) ?
                        deleteByLikeQuery(menu, includeSelf) :
                        StringUtils.isNotBlank(menu.getMenuId()) ? deleteAllChildren(menu, includeSelf) : false;
            }
            catch (Exception e) {
                IPSPFoundationServiceException ex = new IPSPFoundationServiceException(
                        e, "", IPSPFoundationServiceException.FoundationCode.APPMENU_CASCDEL_ERR);
                throw ex;
            }

        }

        return bool;
    }

    /**
     * Delete by like query
     *
     * @param menu        condition
     * @param includeSelf Include oneself
     * @return boolean
     */
    private boolean deleteByLikeQuery(AppMenu menu, boolean includeSelf) {
        String name = menu.getMenuName();
        AppMenu appMenu = new AppMenu();
        appMenu.setMenuName(name + "%");

        AppMenu exclude = null;
        if (!includeSelf) {
            exclude = new AppMenu();
            exclude.setMenuName(name);
        }

        return appMenuDao.deleteByLikeQuery(appMenu, exclude) > 0L;
    }

    /**
     * Delete menu and submenus
     *
     * @param menu
     * @param includeSelf
     * @return
     */
    private boolean deleteAllChildren(AppMenu menu, boolean includeSelf) {
        String id = menu.getMenuId();
        List<AppMenu> menus = findAllChildrenByID(id, false);
        Set<String> ownerIdSet = new HashSet<>();
        boolean rs = false;

        if (menus != null) {
            ownerIdSet = menus.stream().map(menu1 -> menu1.getMenuOwnerid())
                    .collect(Collectors.toSet());

            rs = appMenuDao.deleteByOwnerId(ownerIdSet) > 0L;
        }

        if (includeSelf) {
            rs = appMenuDao.deleteById(id) == 1L;
        }

        return rs;
    }

    /**
     * Get all child elements by name
     *
     * @param name
     * @param includeSelf
     * @return List
     */
    private List<AppMenu> findAllChildrenByName(String name, boolean includeSelf) {
        AppMenu menu = new AppMenu();
        menu.setMenuName(name + "%");
        AppMenu exclude = null;
        if (!includeSelf) {
            exclude = new AppMenu();
            exclude.setMenuName(name);
        }
        return appMenuDao.findByLikeQuery(menu, exclude);
    }

    /**
     * Get all child elements by id
     *
     * @param id
     * @param includeSelf
     * @return
     */
    private List<AppMenu> findAllChildrenByID(String id, final boolean includeSelf) {
        List<AppMenu> all = findAll();
        // 保存结果
        List<AppMenu> rs = new ArrayList<>();
        // 记录要查找的id
        Set<String> findIdSet = new LinkedHashSet<>();
        // 记录剩余记录
        Map<String, List<AppMenu>> surplusMap = new LinkedHashMap<>();
        boolean[] incl = {includeSelf};

        all.forEach(appMenu -> {
            String ownerId = appMenu.getMenuOwnerid();
            if (incl[0] && id.equals(appMenu.getMenuId())) {
                rs.add(appMenu);
                incl[0] = false;
            }
            if (id.equals(ownerId)) {
                // 保存结果
                rs.add(appMenu);
                // 记录要查找的id
                findIdSet.add(appMenu.getMenuId());
            } else {
                // 记录剩余记录
                List<AppMenu> list = surplusMap.get(ownerId);
                if (list == null) {
                    list = new ArrayList<>();
                    surplusMap.put(ownerId, list);
                }

                list.add(appMenu);
            }
        });

        return recursiveFindByID(findIdSet, surplusMap, rs);
    }

    /**
     * Find using recursive method
     *
     * @param findIdSet  Set
     * @param surplusMap
     * @param rs
     * @return
     */
    private List<AppMenu> recursiveFindByID(Set<String> findIdSet,
                                            Map<String, List<AppMenu>> surplusMap, List<AppMenu> rs) {

        // 如果没有要查找的ID了，则返回结果
        if (findIdSet.isEmpty()) {
            return rs;
        }

        // 否则记录要查找的ID
        Set<String> newFindIdSet = new LinkedHashSet<>();
        // 记录剩余记录
        Map<String, List<AppMenu>> newSurplusMap = new LinkedHashMap<>();

        surplusMap.forEach((ownerId, appMenus) -> {
            if (findIdSet.contains(ownerId)) {
                rs.addAll(appMenus);
                appMenus.forEach(appMenu -> newFindIdSet.add(appMenu.getMenuId()));
            } else {
                newSurplusMap.put(ownerId, appMenus);
            }
        });

        return recursiveFindByID(newFindIdSet, newSurplusMap, rs);
    }
}
