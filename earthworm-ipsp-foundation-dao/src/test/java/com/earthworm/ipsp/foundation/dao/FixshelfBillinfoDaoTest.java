package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IFixshelfBillinfoDao;
import com.earthworm.ipsp.foundation.entity.FixshelfBillinfo;
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
public class FixshelfBillinfoDaoTest {

    private static Logger logger = LoggerFactory.getLogger(FixshelfBillinfoDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IFixshelfBillinfoDao fixshelfBillinfoDao;

    @Test
    public void testInsert() {
        int rs = 0;
        for (int i = 1; i < 13; i++) {
            FixshelfBillinfo fixshelfBillinfo = new FixshelfBillinfo();
            fixshelfBillinfo.setBillId(UUID.randomUUID().toString());
            fixshelfBillinfo.setBillNumber("单据编号：100" + i);
            // 到达时间
            fixshelfBillinfo.setBillArrivaltime(new Date());
            // 表单执行状态。0-未执行，1-正执行
            fixshelfBillinfo.setBillStatus((short) 1);
            // 所在站点
            fixshelfBillinfo.setBillSite((short) 1);
            rs += fixshelfBillinfoDao.save(fixshelfBillinfo);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 12, rs);
    }
}
