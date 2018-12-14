package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IOutWarehouseDetailBillDao;
import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/20 9:32
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoundationApplication.class}, properties = {"application.properties"})
public class OutWarehouseDetailBillDaoTest {

    private static Logger logger = LoggerFactory.getLogger(OutWarehouseDetailBillDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IOutWarehouseDetailBillDao outWarehouseDetailBillDao;

    @Test
    public void testInsert() {
        int rs = 0;
        OutWarehouseDetailBill outWarehouseDetailBill = new OutWarehouseDetailBill();
//        '10012','CMPP01','物料码','唯一码','EMT','kg','1批','2018/12/18','2018/12/18'
        outWarehouseDetailBill.setOutwId("123-3e4-23434-23");
        outWarehouseDetailBill.setOutwBillsnumber("详情单号1001");
        outWarehouseDetailBill.setOutwModel("物料规格型号");
        outWarehouseDetailBill.setOutwCode("物料码");
        outWarehouseDetailBill.setOutwUniquecode("物料唯一码");
        outWarehouseDetailBill.setOutwManufacturer("EMT东械科技");
        outWarehouseDetailBill.setOutwUnit("千克（kg）");
        outWarehouseDetailBill.setOutwBatch("第一批");
        outWarehouseDetailBill.setOutwProduceddate(new Date());
        outWarehouseDetailBill.setOutwEffectivedate(new Date());
        outWarehouseDetailBill.setOutwOutdatetime(new Date());

        rs = outWarehouseDetailBillDao.save(outWarehouseDetailBill);

        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 1, rs);
    }
}
