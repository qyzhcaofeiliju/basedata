package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IBillsCategoryDao;
import com.earthworm.ipsp.foundation.entity.BillsCategory;
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
public class BillsCategoryDaoTest {
    private static Logger logger = LoggerFactory.getLogger(BillsCategoryDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IBillsCategoryDao testDao;

    @org.junit.Test
    public void testInsert() {
        int rs = 0;

        //入库的测试数据
//        for (int i = 1; i < 11; i++) {
//            BillsCategory test = new BillsCategory();
//            test.setCatId(UUID.randomUUID().toString());
//            test.setCatName("EMT_BillCategory_"+i);
//            test.setCatCategory((short) i);
//            test.setCatOwnercategory((short)1);
//            rs += testDao.save(test);
//        }

        //出库的测试数据
        for (int i = 21; i < 31; i++) {
            BillsCategory test = new BillsCategory();
            test.setCatId(UUID.randomUUID().toString());
            test.setCatName("EMT_BillCategory_" + i);
            test.setCatCategory((short) i);
            test.setCatOwnercategory((short) 2);
            rs += testDao.save(test);
        }


        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 10, rs);
    }

    @Test
    public void testInsertOutBill() {
        int rs = 0;
        for (int i = 0; i < 13; i++) {
            BillsCategory test = new BillsCategory();
            test.setCatId(UUID.randomUUID().toString());
            test.setCatName("EMT_OutBillName_100" + i);
            test.setCatCategory((short) i);
            rs += testDao.save(test);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 1, rs);
    }

}
