package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.AppMenu;
import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;
import com.earthworm.ipsp.foundation.entity.vo.AppMenuVO;
import com.earthworm.ipsp.foundation.helper.RequestHelper;
import com.earthworm.ipsp.foundation.service.impl.FoundationConfigurationService;
import com.earthworm.ipsp.foundation.service.interf.IVwAppSysMenuService;
import com.earthworm.postgres.helper.SortHelper;
import com.earthworm.postgres.page.Sort;
import com.earthworm.utils.regex.RegexHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller("foundationIdxController")
@RequestMapping("/foundation")
public class IndexController {
    @Autowired
    private IVwAppSysMenuService appSysMenuService;
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(@Value("${ew.ipsp.foundation.sys.code}") String sysCode) throws EarthWormException {
        try {
            // GET app address
            String appAddress = FoundationConfigurationService.getInstance().getSysAddress();

            Sort sort = new Sort();
            LinkedHashMap<Sort.SortType, String> sortMap = new LinkedHashMap<>();
            sortMap.put(Sort.SortType.ASC, "app_sysmenu_sort");
            sort.addSort(sortMap);
            SortHelper.setSort(sort);

            VwAppSysMenu appSysMenu = new VwAppSysMenu();
            appSysMenu.setAppSysCode(sysCode);

            List<VwAppSysMenu> allMenus = appSysMenuService.findSysMenu(appSysMenu);
            List<AppMenuVO> allMenuVOs = appMenuToAppMenuVO(appAddress, allMenus);

            RequestHelper.getHttpServletRequest().setAttribute("menus", allMenuVOs);

            return "index";
        }
        catch (Exception ex){
            logger.error("IndexController.index:Find AppMenu failed!",ex);
            throw ex;
        }
    }

    /**
     * AppMenu object convert to AppMenuVO object
     * @param appAddress
     * @param appMenus
     * @return List
     */
    private List<AppMenuVO> appMenuToAppMenuVO(String appAddress, List<VwAppSysMenu> appMenus) throws EarthWormException {
        try {
            List<AppMenuVO> appMenuVOList = new ArrayList<>();
            // 记录最大级别
            AtomicInteger maxLevel = new AtomicInteger(Integer.MAX_VALUE);
            // 记录菜单
            Map<String, AppMenuVO> appMenuVOMap = new LinkedHashMap<>();

            if(appMenus!=null && !appMenus.isEmpty()) {
                Pattern absPattern = Pattern.compile(RegexHelper.ABSOLUTE_PATH_PATTERN);
                Pattern httpPattern = Pattern.compile(RegexHelper.HTTP_PATTERN);
                appMenus.forEach(sysMenu -> {
                    AppMenu menu = sysMenu.getAppMenu();
                    int level = menu.getMenuLevel();
                    if(maxLevel.get()>level) {
                        maxLevel.set(level);
                    }

                    // Get menu
                    String id = menu.getMenuId();
                    AppMenuVO appMenuVO = appMenuVOMap.get(id);
                    if(appMenuVO==null) {
                        appMenuVO = new AppMenuVO();
                        appMenuVOMap.put(id,appMenuVO);
                    }

                    String menuUrl = menu.getMenuUrl();
                    if(StringUtils.isNotBlank(menuUrl)) {
                        if(menuUrl.startsWith("/")||
                                !absPattern.matcher(menuUrl).matches()&&!httpPattern.matcher(menuUrl).matches()) {
                            menu.setMenuUrl(new StringBuilder(StringUtils.isBlank(
                                    sysMenu.getMenuAccessAddress())?appAddress : sysMenu.getMenuAccessAddress()).append(menuUrl)
                                    .toString());
                        }
                    }
                    appMenuVO.setMenu(menu);

                    // Get parent menu
                    String ownerId = menu.getMenuOwnerid();
                    AppMenuVO parent = appMenuVOMap.get(ownerId);
                    if(parent == null) {
                        parent = new AppMenuVO();
                        appMenuVOMap.put(ownerId,parent);
                    }
                    parent.addSubMenu(appMenuVO);
                    parent.setHasChildren(true);
                });

                appMenuVOList = appMenuVOMap.entrySet().stream().filter(entry -> {
                    AppMenuVO appMenuVO = entry.getValue();
                    AppMenu appMenu = appMenuVO.getMenu();
                    return appMenu!=null && appMenu.getMenuLevel()==maxLevel.get();
                }).map(entry -> entry.getValue()).collect(Collectors.toList());
            }


            return appMenuVOList;
        }
        catch (Exception ex){
            logger.error("IndexController.appMenuToAppMenuVO:Find AppMenu failed!",ex);
            throw ex;
        }
    }
}
