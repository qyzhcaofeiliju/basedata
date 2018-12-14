package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IStaticdataTranslateDao;
import com.earthworm.ipsp.foundation.entity.StaticdataTranslate;
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
public class StaticdataTranslateDaoTest {
    private static Logger logger = LoggerFactory.getLogger(StaticdataTranslateDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IStaticdataTranslateDao testDao;

    @org.junit.Test
    public void testInsert() {
        int rs = 0;
        for (int i = 0; i < 10; i++) {
            StaticdataTranslate test = new StaticdataTranslate();
            test.setTraId(UUID.randomUUID().toString());
            test.setTraTranslatevalue("Translatevalue");
            test.setTraRawvalue((short) 0);
            test.setTraOwnername("Ownername");
            test.setTraOwnerid("Ownerid");
            test.setTraAppdescription("Appdescription");
            test.setTraAppcategory((short) 0);
            rs += testDao.save(test);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 10, rs);
    }
}
