package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IInWarehouseDetailBillDao;
import com.earthworm.ipsp.foundation.entity.InWarehouseDetailBill;
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
public class InWarehouseDetailBillDaoTest {
    private static Logger logger = LoggerFactory.getLogger(InWarehouseDetailBillDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IInWarehouseDetailBillDao testDao;

    @org.junit.Test
    public void testInsert() {
        int rs = 0;
        InWarehouseDetailBill test = new InWarehouseDetailBill();
        test.setInwId(UUID.randomUUID().toString());
        test.setInwActualcount((short) 0);
        test.setInwAmount((short) 0);
        test.setInwBatch("test");
        test.setInwBillsnumber("test");
        test.setInwCode("InwCode");
        test.setInwEffectivedate(new Date());
        test.setInwManufacturer("test");
        test.setInwModel("Model");
        test.setInwProduceddate(new Date());
        test.setInwUniquecode("Uniquecode");
        test.setInwUnit("Unit");
        test.setInwWarehousenumber("test");
        rs += testDao.save(test);
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 1, rs);
    }
}
