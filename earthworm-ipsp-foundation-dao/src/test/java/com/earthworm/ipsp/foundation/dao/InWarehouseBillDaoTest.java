package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IInWarehouseBillDao;
import com.earthworm.ipsp.foundation.entity.InWarehouseBill;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoundationApplication.class}, properties = {"application.properties"})
public class InWarehouseBillDaoTest {
    private static Logger logger = LoggerFactory.getLogger(InWarehouseBillDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IInWarehouseBillDao testDao;

    @org.junit.Test
    public void testInsert() {
        int rs = 0;
        for (int i = 0; i < 10; i++) {
            InWarehouseBill test = new InWarehouseBill();
            test.setInwId(UUID.randomUUID().toString());
            test.setInwBillsnumber("BillSnumber");
            test.setInwCategory((short) 0);
            test.setInwIntime(new Date());
            test.setInwStatus((short) 0);
            rs += testDao.save(test);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 10, rs);
    }
}
