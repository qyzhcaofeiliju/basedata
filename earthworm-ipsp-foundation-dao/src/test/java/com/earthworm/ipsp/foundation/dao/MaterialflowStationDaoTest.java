package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IMaterialflowStationDao;
import com.earthworm.ipsp.foundation.entity.MaterialflowStation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/20 9:32
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoundationApplication.class}, properties = {"application.properties"})
public class MaterialflowStationDaoTest {

    private static Logger logger = LoggerFactory.getLogger(MaterialflowStationDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IMaterialflowStationDao materialflowStationDao;

    @Test
    public void testInsert() {
        int rs = 0;
        for (int i = 1; i < 13; i++) {
            MaterialflowStation materialflowStation = new MaterialflowStation();
            materialflowStation.setStaId(UUID.randomUUID().toString());
            materialflowStation.setStaName("EMT_StaName_100" + i);
            materialflowStation.setStaNumber((short) i);
            materialflowStation.setStaX(new BigDecimal(Short.toString((short) (i + 10))));
            materialflowStation.setStaY(new BigDecimal(Short.toString((short) (i + 20))));
            materialflowStation.setStaZ(new BigDecimal(Short.toString((short) (i + 30))));
            materialflowStation.setStaDescription("EMT_StaDescri:" + i);
            rs += materialflowStationDao.save(materialflowStation);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 12, rs);
    }
}
