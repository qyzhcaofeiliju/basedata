package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.ITerminalAppcategoryDao;
import com.earthworm.ipsp.foundation.entity.TerminalAppcategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoundationApplication.class}, properties = {"application.properties"})
public class TerminalAppcategoryDaoTest {
    private static Logger logger = LoggerFactory.getLogger(TerminalAppcategoryDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ITerminalAppcategoryDao terminalAppcategoryDao;

    @Test
    public void testInsert() {
        int rs = 0;
        for (int i = 1; i < 13; i++) {
            TerminalAppcategory terminalAppcategory = new TerminalAppcategory();
            terminalAppcategory.setAppId(UUID.randomUUID().toString());
            terminalAppcategory.setAppName("EMT_Terminal_AppName_100" + i);
            terminalAppcategory.setAppCategory((short) i);
            rs += terminalAppcategoryDao.save(terminalAppcategory);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 12, rs);
    }
}
