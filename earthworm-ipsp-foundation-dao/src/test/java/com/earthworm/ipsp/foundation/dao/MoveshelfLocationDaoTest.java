package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IMoveshelfLocationDao;
import com.earthworm.ipsp.foundation.entity.MoveshelfLocation;
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
public class MoveshelfLocationDaoTest {
    private static Logger logger = LoggerFactory.getLogger(MoveshelfLocationDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IMoveshelfLocationDao moveshelfLocationDao;

    @Test
    public void testInsert() {
        int rs = 0;
        for (int i = 1; i < 2; i++) {
            MoveshelfLocation moveshelfLocation = new MoveshelfLocation();
            moveshelfLocation.setMslId(UUID.randomUUID().toString());
            moveshelfLocation.setMslEquId("3ceafc34-5bc3-4442-a879-cf8cf7df4db9");
            moveshelfLocation.setMslEquNumber((short) 1012);
            moveshelfLocation.setMslPreStation((short) 11);
            moveshelfLocation.setMslCurStation((short) 12);

            rs += moveshelfLocationDao.save(moveshelfLocation);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 1, rs);
    }
}
