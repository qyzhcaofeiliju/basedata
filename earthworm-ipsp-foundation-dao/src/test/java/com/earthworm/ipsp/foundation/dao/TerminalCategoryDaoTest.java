package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.ITerminalCategoryDao;
import com.earthworm.ipsp.foundation.entity.TerminalCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class TerminalCategoryDaoTest {

    private static Logger logger = LoggerFactory.getLogger(TerminalCategoryDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ITerminalCategoryDao terminalCategoryDao;

    @Test
    public void testInsert() {
        int rs = 0;
        for (int i = 1; i < 13; i++) {
            TerminalCategory terminalCategory = new TerminalCategory();
            terminalCategory.setCatId(UUID.randomUUID().toString());
            terminalCategory.setCatModel("EMT_Model设备型号：100" + i);
            terminalCategory.setCatName("TerminalCategory名称：EMT00" + i);
            terminalCategory.setCatFunccategory((short) 1);
            terminalCategory.setCatAppcategory((short) 3);
            terminalCategory.setCatDescription("终端分类描述信息：EMT-" + i);
            rs += terminalCategoryDao.save(terminalCategory);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 12, rs);
    }
}
