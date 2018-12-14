package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IOutWarehouseBillDao;
import com.earthworm.ipsp.foundation.entity.OutWarehouseBill;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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
public class OutWarehouseBillDaoTest {

    private static Logger logger = LoggerFactory.getLogger(OutWarehouseBillDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IOutWarehouseBillDao outWarehouseDetailBillDao;

    @Test
    public void testInsert() {
        int rs = 0;
        for (int i = 9; i < 13; i++) {
            OutWarehouseBill outWarehouseBill = new OutWarehouseBill();
            outWarehouseBill.setOutwId(UUID.randomUUID().toString());
            outWarehouseBill.setOutwBillsnumber("EMT_OutBillName_100" + i);
            outWarehouseBill.setOutwCategory((short) i);
            outWarehouseBill.setOutwOuttime(new Date());
            outWarehouseBill.setOutwStatus((short) 2);
            rs += outWarehouseDetailBillDao.save(outWarehouseBill);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 4, rs);
    }
}
