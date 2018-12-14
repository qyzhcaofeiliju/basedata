package com.earthworm.ipsp.foundation.service;

import com.earthworm.ipsp.foundation.entity.AppMenu;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.IAppMenuService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FoundationApplication.class }, properties = { "application.properties" })
public class AppMenuServiceTest {
    private static Logger logger = LoggerFactory.getLogger(AppMenuServiceTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppMenuService testService;

    @org.junit.Test
    public void addMenu() throws IPSPFoundationServiceException {
        AppMenu menu = new AppMenu();
        menu.setMenuId(UUID.randomUUID().toString());
        menu.setMenuName("Fun1Test1");
        menu.setMenuLevel(2);
        menu.setMenuOwnerid("9719e6ed-d953-464f-87e2-f0adcdda5a1a");
        menu.setMenuDescription("Fun1Test1");
        menu.setMenuUrl("abceefg");
        Assert.assertTrue("Test failed! addMenu failed!", testService.addMenu(menu));
        logger.info("*************************Test complete!!*************************");
        logger.info("id:{},name:{},level:{},ownerid:{},desc:{},icon:{},remark:{}",
                menu.getMenuId(),menu.getMenuName(),menu.getMenuLevel(),menu.getMenuOwnerid(),menu.getMenuDescription(),menu.getMenuIcon(),menu.getMenuRemark());
    }

    @org.junit.Test
    public void findAll() throws IPSPFoundationServiceException {
        List<AppMenu> all = testService.findAll();
        Assert.assertNotNull("Test failed! findAll failed!",all);
        Assert.assertSame("Test failed! findAll failed!", all.size(),15);
        logger.info("*************************Test complete!!*************************");
        all.forEach(menu ->
                logger.info("id:{},name:{},level:{},ownerid:{},desc:{},icon:{},remark:{}",
                        menu.getMenuId(),menu.getMenuName(),menu.getMenuLevel(),menu.getMenuOwnerid(),menu.getMenuDescription(),menu.getMenuIcon(),menu.getMenuRemark()));
    }

    @org.junit.Test
    public void findAllChildren() throws IPSPFoundationServiceException {
        AppMenu appMenu = new AppMenu();
//        appMenu.setName("L10");
        appMenu.setMenuId("7e512799-f412-45de-8ada-da7ad2f43d93");
        List<AppMenu> all = testService.findAllChildren(appMenu,true);
        Assert.assertNotNull("Test failed! findAllChildren failed!",all);
        Assert.assertTrue("Test failed! findAllChildren failed!", !all.isEmpty());
        logger.info("*************************Test complete!!*************************");
        all.forEach(menu ->
                logger.info("id:{},name:{},level:{},ownerid:{},desc:{},icon:{},remark:{}",
                        menu.getMenuId(),menu.getMenuName(),menu.getMenuLevel(),menu.getMenuOwnerid(),menu.getMenuDescription(),menu.getMenuIcon(),menu.getMenuRemark()));
    }

    @org.junit.Test
    public void cascadeDelete() throws IPSPFoundationServiceException {
        AppMenu appMenu = new AppMenu();
        appMenu.setMenuName("L11");
//        appMenu.setId("6d3cfc49-17b0-498f-a574-4385581c0574");
        Assert.assertTrue("Test failed! cascadeDelete failed!", testService.cascadeDelete(appMenu,true));
        logger.info("*************************Test complete!!*************************");
    }
}