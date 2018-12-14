package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.ISuitcaseAppcategoryDao;
import com.earthworm.ipsp.foundation.entity.SuitcaseAppcategory;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoundationApplication.class}, properties = {"application.properties"})
public class SuitcaseAppcategoryDaoTest {
    private static Logger logger = LoggerFactory.getLogger(SuitcaseAppcategoryDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ISuitcaseAppcategoryDao testDao;

    @org.junit.Test
    public void testInsert() {
        int rs = 0;
        for (int i = 0; i < 10; i++) {
            SuitcaseAppcategory test = new SuitcaseAppcategory();
            test.setAppId(UUID.randomUUID().toString());
            test.setAppName("appname");
            test.setAppCategory((short) 0);
            rs += testDao.save(test);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 10, rs);
    }
}
