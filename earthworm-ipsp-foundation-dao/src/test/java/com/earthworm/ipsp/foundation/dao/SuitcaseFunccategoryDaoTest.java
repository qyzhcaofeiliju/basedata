package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.ISuitcaseFunccategoryDao;
import com.earthworm.ipsp.foundation.entity.SuitcaseFunccategory;
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
public class SuitcaseFunccategoryDaoTest {
    private static Logger logger = LoggerFactory.getLogger(SuitcaseFunccategoryDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ISuitcaseFunccategoryDao testDao;

    @org.junit.Test
    public void testInsert() {
        int rs = 0;
        for (int i = 0; i < 5; i++) {
            SuitcaseFunccategory test = new SuitcaseFunccategory();
            test.setFunId(UUID.randomUUID().toString());
            test.setFunName("funname");
            test.setFunCategory((short) 0);
            rs += testDao.save(test);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 5, rs);
    }
}
