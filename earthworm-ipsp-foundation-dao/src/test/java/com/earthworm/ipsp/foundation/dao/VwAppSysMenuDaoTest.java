package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IAppSysDao;
import com.earthworm.ipsp.foundation.dao.interf.IVwAppSysMenuDao;
import com.earthworm.ipsp.foundation.entity.AppSys;
import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FoundationApplication.class }, properties = { "application.properties" })
public class VwAppSysMenuDaoTest {
    private static Logger logger = LoggerFactory.getLogger(VwAppSysMenuDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IVwAppSysMenuDao testDao;

    @Test
    public void testFindByCondition() {
        Map<String, Object> param = new HashMap<>();
        param.put("app_sys_code","EW.IPSP.FOUNDATION-LOCALHOST");
        List<VwAppSysMenu> vwAppSysMenus = testDao.findByCondition(param);
        long size = vwAppSysMenus!=null?vwAppSysMenus.size():0;
        Assert.assertTrue("No records!",size>0L);
        logger.info("Test complete!! VwAppSysMenu size is:{}", size);
        vwAppSysMenus.forEach(o->logger.info("menuid:{},sortnum:{},syscode:{},description:{},url:{}",
                o.getAppMenu().getMenuId(),
                o.getSortNum(),
                o.getAppSysCode(),
                o.getAppMenu().getMenuDescription(),
                o.getAppMenu().getMenuUrl()));
    }
}