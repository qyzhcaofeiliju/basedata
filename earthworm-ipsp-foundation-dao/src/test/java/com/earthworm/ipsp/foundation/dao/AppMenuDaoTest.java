package com.earthworm.ipsp.foundation.dao;

import com.earthworm.ipsp.foundation.dao.interf.IAppMenuDao;
import com.earthworm.ipsp.foundation.entity.AppMenu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FoundationApplication.class }, properties = { "application.properties" })
public class AppMenuDaoTest {
    private static Logger logger = LoggerFactory.getLogger(AppMenuDaoTest.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppMenuDao testDao;

    @org.junit.Test
    public void testInsertMenus() {
        List<AppMenu> menus = new ArrayList<>();
        String rootId = UUID.randomUUID().toString();
        String terimalId = UUID.randomUUID().toString();

        AppMenu menu = new AppMenu();
        menu.setMenuName("Fun1");
        menu.setMenuId(rootId);
        menu.setMenuDescription("基础管理");
        menu.setMenuOwnerid("0");
        menu.setMenuLevel(1);
        menu.setMenuIcon("fa fa-dashboard fa-fw");
        // 基础功能Menu
        menus.add(menu);

        //====================================================
        AppMenu subMenu = new AppMenu();
        subMenu.setMenuName("Fun1Ter1");
        subMenu.setMenuId(terimalId);
        subMenu.setMenuDescription("终端管理");
        subMenu.setMenuOwnerid(rootId);
        subMenu.setMenuLevel(2);
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1Ter1Apt1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("应用分类");
        subMenu.setMenuOwnerid(terimalId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/terminalAppcategory/view");
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1Ter1Fut1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("功能分类");
        subMenu.setMenuOwnerid(terimalId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/terminalFunccategory/view");
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1Ter1t1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("设备");
        subMenu.setMenuOwnerid(terimalId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/terminalCategory/view");
        menus.add(subMenu);

//        subMenu = new AppMenu();
//        subMenu.setMenuName("Fun1Ter1te1");
//        subMenu.setMenuId(UUID.randomUUID().toString());
//        subMenu.setMenuDescription("设备");
//        subMenu.setMenuOwnerid(terimalId);
//        subMenu.setMenuLevel(3);
//        subMenu.setMenuUrl("/ipsp/TerminalEquipment/view");
//        menus.add(subMenu);
        //==========================================================

//        String sbId = UUID.randomUUID().toString();
//        subMenu = new AppMenu();
//        subMenu.setMenuName("Fun1SB1");
//        subMenu.setMenuId(sbId);
//        subMenu.setMenuDescription("货架管理");
//        subMenu.setMenuOwnerid(rootId);
//        subMenu.setMenuLevel(2);
//        menus.add(subMenu);
//
//        subMenu = new AppMenu();
//        subMenu.setMenuName("Fun1SB1Mv1");
//        subMenu.setMenuId(UUID.randomUUID().toString());
//        subMenu.setMenuDescription("移动货架");
//        subMenu.setMenuOwnerid(sbId);
//        subMenu.setMenuLevel(3);
//        subMenu.setMenuUrl("/ipsp/moveshelfLocation/view");
//        menus.add(subMenu);
//
//        subMenu = new AppMenu();
//        subMenu.setMenuName("Fun1SB1Fi1");
//        subMenu.setMenuId(UUID.randomUUID().toString());
//        subMenu.setMenuDescription("固定货架");
//        subMenu.setMenuOwnerid(sbId);
//        subMenu.setMenuLevel(3);
//        subMenu.setMenuUrl("/ipsp/fixshelfBillinfo/view");
//        menus.add(subMenu);

//        subMenu = new AppMenu();
//        subMenu.setMenuName("Fun1SB1Ax1");
//        subMenu.setMenuId(UUID.randomUUID().toString());
//        subMenu.setMenuDescription("货架坐标");
//        subMenu.setMenuOwnerid(sbId);
//        subMenu.setMenuLevel(3);
//        subMenu.setMenuUrl("/ipsp/GoodslocationAxisdictionary/view");
//        menus.add(subMenu);
        //==========================================================

        String suprId = UUID.randomUUID().toString();
        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1SuPr1");
        subMenu.setMenuId(suprId);
        subMenu.setMenuDescription("周转箱管理");
        subMenu.setMenuOwnerid(rootId);
        subMenu.setMenuLevel(2);
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1SuPr1Ap1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("应用分类");
        subMenu.setMenuOwnerid(suprId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/suitcaseAppcategory/view");
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1SuPr1Fu1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("功能分类");
        subMenu.setMenuOwnerid(suprId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/suitcaseFunccategory/view");
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1SuPr1Sp1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("特征描述");
        subMenu.setMenuOwnerid(suprId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/suitcaseProfile/view");
        menus.add(subMenu);
        //==========================================================

        String orderId = UUID.randomUUID().toString();
        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1Or1");
        subMenu.setMenuId(orderId);
        subMenu.setMenuDescription("单据管理");
        subMenu.setMenuOwnerid(rootId);
        subMenu.setMenuLevel(2);
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1Or1In1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("入库单");
        subMenu.setMenuOwnerid(orderId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/inWarehouseBill/view");
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1Or1Ou1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("出库单");
        subMenu.setMenuOwnerid(orderId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/outWarehouseBill/view");
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1Or1T1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("单据分类");
        subMenu.setMenuOwnerid(orderId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/billsCategory/view");
        menus.add(subMenu);
        //==========================================================

//        subMenu = new AppMenu();
//        subMenu.setMenuName("Fun1InDeBill1");
//        subMenu.setMenuId(UUID.randomUUID().toString());
//        subMenu.setMenuDescription("入库单详情");
//        subMenu.setMenuOwnerid(rootId);
//        subMenu.setMenuLevel(3);
//        subMenu.setMenuUrl("/ipsp/inWarehouseDetailBill/view");
//        menus.add(subMenu);
//
//        subMenu = new AppMenu();
//        subMenu.setMenuName("Fun1MaSt1");
//        subMenu.setMenuId(UUID.randomUUID().toString());
//        subMenu.setMenuDescription("物料周转站点");
//        subMenu.setMenuOwnerid(rootId);
//        subMenu.setMenuLevel(3);
//        subMenu.setMenuUrl("/ipsp/materialflowStation/view");
//        menus.add(subMenu);
//
//
//        subMenu = new AppMenu();
//        subMenu.setMenuName("Fun1OuDeBi1");
//        subMenu.setMenuId(UUID.randomUUID().toString());
//        subMenu.setMenuDescription("物料出库信息明细表");
//        subMenu.setMenuOwnerid(rootId);
//        subMenu.setMenuLevel(3);
//        subMenu.setMenuUrl("/ipsp/outWarehouseDetailBill/view");
//        menus.add(subMenu);


//==========================================================
        subMenu = new AppMenu();
        subMenu.setMenuName("Fun1StTr1");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("静态数据");
        subMenu.setMenuOwnerid(rootId);
        subMenu.setMenuLevel(2);
        subMenu.setMenuUrl("/ipsp/staticdataTranslate/view");
        menus.add(subMenu);

// =============================================================
        String staManagerId = UUID.randomUUID().toString();
        subMenu = new AppMenu();
        subMenu.setMenuName("StaManager");
        subMenu.setMenuId(staManagerId);
        subMenu.setMenuDescription("站点管理");
        subMenu.setMenuOwnerid(rootId);
        subMenu.setMenuLevel(2);
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("MaterflowSta");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("物流站点");
        subMenu.setMenuOwnerid(staManagerId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/materialflowStation/view");
        menus.add(subMenu);

        subMenu = new AppMenu();
        subMenu.setMenuName("StaTerminEqu");
        subMenu.setMenuId(UUID.randomUUID().toString());
        subMenu.setMenuDescription("站点设备");
        subMenu.setMenuOwnerid(staManagerId);
        subMenu.setMenuLevel(3);
        subMenu.setMenuUrl("/ipsp/terminalEquipment/view");
        menus.add(subMenu);

// ==============================================================





        long rs = testDao.saveBatch(menus);

        Assert.assertTrue("Test failed! Insert number failed!", rs>0L);
        logger.info("Test complete!! Insert number is:{}", rs);
    }

    @org.junit.Test
    public void testInsert() {
        String[] ids = {
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        };
        String twoId = UUID.randomUUID().toString();

        List<AppMenu> menus = new ArrayList<>();
        for (int i=0; i<14; i++) {
            AppMenu menu = new AppMenu();
            if(i<4) {
                menu.setMenuId(ids[i]);
                menu.setMenuLevel(1);
                menu.setMenuName("L1"+i);
                menu.setMenuDescription("L1desc"+(int)(Math.random()*14));
                menu.setMenuOwnerid("0");
            }
            else if(i<8) {
                menu.setMenuId(UUID.randomUUID().toString());
                if(i==4) {
                    menu.setMenuId(twoId);
                }
                menu.setMenuLevel(2);
                menu.setMenuName("L10L2"+i);
                menu.setMenuDescription("L1L2desc"+(int)(Math.random()*14));
                menu.setMenuOwnerid(ids[0]);
            }
            else if(i<11) {
                menu.setMenuId(UUID.randomUUID().toString());
                menu.setMenuLevel(3);
                menu.setMenuName("L10L24L3"+i);
                menu.setMenuDescription("L1L2L3desc"+(int)(Math.random()*14));
                menu.setMenuOwnerid(twoId);
            }
            else {
                menu.setMenuId(UUID.randomUUID().toString());
                menu.setMenuLevel(2);
                menu.setMenuName("L11L2"+i);
                menu.setMenuDescription("L1L2desc"+(int)(Math.random()*14));
                menu.setMenuOwnerid(ids[1]);
            }
            menus.add(menu);
        }

        long rs = testDao.saveBatch(menus);

        Assert.assertSame("Test failed! Insert number failed!", 14L, rs);
        logger.info("Test complete!! Insert number is:{}", rs);
    }

    @Test
    public void testFindByLike() {
        AppMenu appMenu = new AppMenu();
        appMenu.setMenuName("L10%");
        AppMenu exclude = new AppMenu();
        exclude.setMenuName("L10");
        List<AppMenu> menus = testDao.findByLikeQuery(appMenu,exclude);
        Assert.assertNotNull("Test failed!!",menus);
        Assert.assertTrue("Test failed!!", !menus.isEmpty());

        logger.info("*************************Test complete!!*************************");
        menus.forEach(menu ->
                logger.info("id:{},name:{},level:{},ownerid:{},desc:{},icon:{},remark:{}",
                        menu.getMenuId(),menu.getMenuName(),menu.getMenuLevel(),menu.getMenuOwnerid(),menu.getMenuDescription(),menu.getMenuIcon(),menu.getMenuRemark()));
    }

    @Test
    public void deleteByLikeQuery() {
        AppMenu appMenu = new AppMenu();
        appMenu.setMenuName("L11%");
        AppMenu exclude = new AppMenu();
        exclude.setMenuName("L11");
        long rs = testDao.deleteByLikeQuery(appMenu, exclude);
        Assert.assertTrue("Test failed!!", rs>0L);
        logger.info("*************************Test complete!!*************************");
        logger.info("Delete record number:{}",rs);
    }

    @Test
    public void test(){

    }

}