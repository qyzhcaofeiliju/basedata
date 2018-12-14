package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.ITerminalEquipmentDao;
import com.earthworm.ipsp.foundation.entity.TerminalEquipment;
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
public class TerminalEquipmentDaoTest {
    private static Logger logger = LoggerFactory.getLogger(TerminalEquipmentDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ITerminalEquipmentDao terminalEquipmentDao;

    @Test
    public void testInsert() {
        int rs = 0;
        for (int i = 6; i < 13; i++) {
            TerminalEquipment terminalEquipment = new TerminalEquipment();
            terminalEquipment.setEquId(UUID.randomUUID().toString());
//            terminalEquipment.setEquCatId("0b65a0a8-44e1-45ea-9224-d87a36dc7eeb");
            terminalEquipment.setEquCatId("838aa532-cb4c-43e2-85d8-e73919382a87");
//            terminalEquipment.setEquStaId("c13a39ed-1c19-4540-ab23-ebc84bf5cbbc");
            terminalEquipment.setEquStaId("fdf2f45b-302c-4560-bf57-9de6e9bd1093");
            terminalEquipment.setEquUniquecode("EMT_TerEqUniq_100" + i);
            terminalEquipment.setEquNumber(i + 1000);
            terminalEquipment.setEquCurrentstatus((short) i);
            terminalEquipment.setEquIp("EMT_TerEqIp_100" + i);
            terminalEquipment.setEquPort(i + 2000);
            terminalEquipment.setEquDescription("EMT_TerEqDesc_4000" + i);
            rs += terminalEquipmentDao.save(terminalEquipment);
        }
        logger.info("Test complete!! Insert number is:{}", rs);
        Assert.assertSame("Test failed! Insert number failed!", 5, rs);
    }
}
