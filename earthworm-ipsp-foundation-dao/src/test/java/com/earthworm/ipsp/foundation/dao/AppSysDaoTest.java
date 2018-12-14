package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IAppMenuDao;
import com.earthworm.ipsp.foundation.dao.interf.IAppSysDao;
import com.earthworm.ipsp.foundation.entity.AppMenu;
import com.earthworm.ipsp.foundation.entity.AppSys;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FoundationApplication.class }, properties = { "application.properties" })
public class AppSysDaoTest {
    private static Logger logger = LoggerFactory.getLogger(AppSysDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppSysDao testDao;


    @Test
    public void testInsert() {
        AppSys appSys = new AppSys();
        appSys.setSysId(UUID.randomUUID().toString());
        appSys.setSysAddress("http://localhost:8089");
        appSys.setSysCode("EW.IPSP.FOUNDATION-LOCALHOST");
        appSys.setSysCreateTime(LocalDateTime.now());
        appSys.setSysModifyTime(LocalDateTime.now());
        appSys.setSysName("生产准备区");
        long rs = testDao.save(appSys);

        Assert.assertSame("Test failed! Insert number failed!", 1L, rs);
        logger.info("Test complete!! Insert number is:{}", rs);
    }


}