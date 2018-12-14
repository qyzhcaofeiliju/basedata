package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.ISuitcaseProfileDao;
import com.earthworm.ipsp.foundation.entity.SuitcaseProfile;
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
public class SuitcaseProfileDaoTest {
    private static Logger logger = LoggerFactory.getLogger(SuitcaseProfileDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ISuitcaseProfileDao testDao;

    @org.junit.Test
    public void testInsert() {
        int rs = 0;
        for (int i = 0; i < 10; i++) {
            SuitcaseProfile test = new SuitcaseProfile();
            test.setProId(UUID.randomUUID().toString());
            test.setProAppcategory((short) 0);
            test.setProCabintotal((short) 0);
            test.setProDescription("Description");
            test.setProFunccategory((short) 0);
            test.setProHorizontalamount((short) 0);
            test.setProIspartition(true);
            test.setProStatus((short) 0);
            test.setProUniquecode("Uniquecode");
            test.setProVerticalamount((short) 0);
            test.setProRfid("proRfid");
            rs += testDao.save(test);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 10, rs);
    }
}
