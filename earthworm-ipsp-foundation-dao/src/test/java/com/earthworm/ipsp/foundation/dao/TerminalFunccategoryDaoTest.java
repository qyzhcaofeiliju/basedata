package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.ITerminalFunccategoryDao;
import com.earthworm.ipsp.foundation.entity.TerminalFunccategory;
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
public class TerminalFunccategoryDaoTest {
    private static Logger logger = LoggerFactory.getLogger(TerminalFunccategoryDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ITerminalFunccategoryDao terminalFunccategoryDao;

    @Test
    public void testInsert() {
        int rs = 0;
        for (int i = 1; i < 13; i++) {
            TerminalFunccategory terminalFunccategory = new TerminalFunccategory();
            terminalFunccategory.setFunId(UUID.randomUUID().toString());
            terminalFunccategory.setFunName("EMT_Terminal_FunName_100" + i);
            terminalFunccategory.setFunCategory((short) i);
            rs += terminalFunccategoryDao.save(terminalFunccategory);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 12, rs);
    }
}
