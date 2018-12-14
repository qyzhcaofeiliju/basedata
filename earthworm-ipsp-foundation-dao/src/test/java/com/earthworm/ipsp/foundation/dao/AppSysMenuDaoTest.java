package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IAppMenuDao;
import com.earthworm.ipsp.foundation.dao.interf.IAppSysDao;
import com.earthworm.ipsp.foundation.dao.interf.IAppSysMenuDao;
import com.earthworm.ipsp.foundation.dao.interf.IVwAppSysMenuDao;
import com.earthworm.ipsp.foundation.entity.AppMenu;
import com.earthworm.ipsp.foundation.entity.AppSys;
import com.earthworm.ipsp.foundation.entity.AppSysMenu;
import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;
import com.earthworm.utils.regex.RegexHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.FaultAction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FoundationApplication.class }, properties = { "application.properties" })
public class AppSysMenuDaoTest {
    private static Logger logger = LoggerFactory.getLogger(AppSysMenuDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppMenuDao menuDao;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppSysMenuDao sysMenuDao;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IVwAppSysMenuDao vwAppSysMenuDao;

    @Test
    public void testInsert() {
        AppMenu menuQuery = new AppMenu();
        menuQuery.setMenuDescription("基础数据");
        List<AppMenu> menus = menuDao.find(menuQuery);
        long rs=0l;
        int except=0;
        while(menus!=null&&!menus.isEmpty()){
            except+=menus.size();
            List<AppSysMenu> sysMenus = new ArrayList<>();
            List<AppMenu> subMenus = new ArrayList<>();
            for(AppMenu appMenu : menus) {
                AppSysMenu sysMenu = new AppSysMenu();
                sysMenu.setAppMenuId(appMenu.getMenuId());
                sysMenu.setAppSysCode("EW.IPSP.FOUNDATION-LOCALHOST");
                sysMenu.setAppSysMenuId(UUID.randomUUID().toString());
                sysMenu.setAppSysmenuCreatetime(LocalDateTime.now());
                sysMenu.setAppSysmenuModifytime(LocalDateTime.now());
                sysMenu.setAppSysmenuSort((short)1);
                sysMenus.add(sysMenu);

                menuQuery = new AppMenu();
                menuQuery.setMenuOwnerid(appMenu.getMenuId());
                List<AppMenu> list = menuDao.find(menuQuery);
                if(list!=null&&!list.isEmpty())
                    subMenus.addAll(list);
            }
            rs+=sysMenuDao.saveBatch(sysMenus);
            menus=subMenus;
        }

        Assert.assertSame("Test failed! Insert number failed!", (long)except, rs);
        logger.info("Test complete!! Insert number is:{}", rs);
    }

    @Test
    public void testFindAppSysMenus() {
        List<VwAppSysMenu> list = vwAppSysMenuDao.findBySysCodes(
                Stream.of("EW.IPSP.FOUNDATION-LOCALHOST","EW.IPSP.PRODUCTIONREADY-LOCALHOST").collect(Collectors.toSet()));
        Assert.assertTrue("vwAppSysMenuDao.findBySysCodes failed!",list!=null&&!list.isEmpty());
        list.forEach(o->logger.info("syscode:{},description:{}",o.getAppSysCode(),o.getAppMenu().getMenuDescription()));
    }
}