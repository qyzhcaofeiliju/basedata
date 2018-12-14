/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.254
 Source Server Type    : PostgreSQL
 Source Server Version : 100005
 Source Host           : 192.168.1.254:5432
 Source Catalog        : i_psp
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100005
 File Encoding         : 65001

 Date: 19/11/2018 19:01:43
*/


-- ----------------------------
-- Table structure for app_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."app_menu";
CREATE TABLE "public"."app_menu" (
  "menu_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "menu_name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "menu_description" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "menu_level" int4 NOT NULL,
  "menu_icon" varchar(200) COLLATE "pg_catalog"."default",
  "menu_ownerid" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "menu_url" varchar(200) COLLATE "pg_catalog"."default",
  "menu_remark" varchar(1024) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."app_menu"."menu_id" IS '菜单ID';
COMMENT ON COLUMN "public"."app_menu"."menu_name" IS '菜单名称';
COMMENT ON COLUMN "public"."app_menu"."menu_description" IS '显示名称';
COMMENT ON COLUMN "public"."app_menu"."menu_level" IS '级别';
COMMENT ON COLUMN "public"."app_menu"."menu_icon" IS '图标';
COMMENT ON COLUMN "public"."app_menu"."menu_ownerid" IS '所属ID';
COMMENT ON COLUMN "public"."app_menu"."menu_url" IS '链接';
COMMENT ON COLUMN "public"."app_menu"."menu_remark" IS '备注';
COMMENT ON TABLE "public"."app_menu" IS '应用菜单';

-- ----------------------------
-- Records of app_menu
-- ----------------------------
INSERT INTO "public"."app_menu" VALUES ('e450f632-d628-479d-a778-1874c1484f4b', 'Fun1Ter1Fut1', '功能分类', 3, NULL, '06f5f4e8-85f0-4008-9d4d-704ea23dc1b5', '/ipsp/terminalFunccategory/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('f61dcd07-61da-4a11-98af-79f74e51569b', 'Fun1Ter1t1', '设备', 3, NULL, '06f5f4e8-85f0-4008-9d4d-704ea23dc1b5', '/ipsp/terminalCategory/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('8557b555-3f85-46c0-bc61-754161e6412b', 'Fun1SuPr1', '周转箱管理', 2, NULL, '0cee3751-eb4b-4c55-bfe4-6da0cbcbe72e', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('c9034067-917c-413f-917d-574de4255494', 'Fun1SuPr1Ap1', '应用分类', 3, NULL, '8557b555-3f85-46c0-bc61-754161e6412b', '/ipsp/suitcaseAppcategory/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('38f9db19-a617-493b-822c-d713c801ba33', 'Fun1SuPr1Fu1', '功能分类', 3, NULL, '8557b555-3f85-46c0-bc61-754161e6412b', '/ipsp/suitcaseFunccategory/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('b896500a-fa93-4f5b-a241-0c8f2b2e2034', 'Fun1SuPr1Sp1', '特征描述', 3, NULL, '8557b555-3f85-46c0-bc61-754161e6412b', '/ipsp/suitcaseProfile/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('a2cdbfb2-e3e5-4440-b512-eec83e85192c', 'Fun1Or1', '单据管理', 2, NULL, '0cee3751-eb4b-4c55-bfe4-6da0cbcbe72e', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('dd557853-8ba6-45cc-9a95-6d51ee1b6eba', 'Fun1Or1T1', '单据分类', 3, NULL, 'a2cdbfb2-e3e5-4440-b512-eec83e85192c', '/ipsp/billsCategory/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('75622dcd-5edb-4736-9f86-7264eaf3dba9', 'Fun1StTr1', '静态数据', 2, NULL, '0cee3751-eb4b-4c55-bfe4-6da0cbcbe72e', '/ipsp/staticdataTranslate/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('4b9021fc-60d6-4e9d-93c2-d8ef88add673', 'StaManager', '站点管理', 2, NULL, '0cee3751-eb4b-4c55-bfe4-6da0cbcbe72e', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('6a860797-63b7-41d7-8f8a-803917ddbd08', 'MaterflowSta', '物流站点', 3, NULL, '4b9021fc-60d6-4e9d-93c2-d8ef88add673', '/ipsp/materialflowStation/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('051a2f10-cdd8-4bb6-9a4e-b1606e169b78', 'StaTerminEqu', '站点设备', 3, NULL, '4b9021fc-60d6-4e9d-93c2-d8ef88add673', '/ipsp/terminalEquipment/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('d04d5f0d-c398-4f44-913b-308f2c0b6936', 'Fun1Ter1Apt1', '应用分类', 3, NULL, '06f5f4e8-85f0-4008-9d4d-704ea23dc1b5', '/ipsp/terminalAppcategory/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('06f5f4e8-85f0-4008-9d4d-704ea23dc1b5', 'Fun1Ter1', '终端管理', 2, NULL, '0cee3751-eb4b-4c55-bfe4-6da0cbcbe72e', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('0cee3751-eb4b-4c55-bfe4-6da0cbcbe72e', 'Fun1', '基础数据', 1, 'fa fa-dashboard fa-fw', '0', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('24ec68cb-946f-4331-a846-f7000f410950', 'L10', '配码入库', 1, 'fa fa-gears fa-fw', '0', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('27e96551-3f11-43c1-a249-cadb0c8cf689', 'ProductionReady', '生产准备区', 1, 'fa  fa-suitcase fa-fw', '0', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d23', 'OutWarehouselMission', '出库任务', 2, NULL, '27e96551-3f11-43c1-a249-cadb0c8cf689', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d6-ec77-4a94-b697-e831761e0d40', 'Material_Sort', '出库分拣', 2, NULL, '27e96551-3f11-43c1-a249-cadb0c8cf689', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('24ec68cb-946f-4331-a846-f7000f410958', 'ProductionLineSend', '产线配送', 2, NULL, '27e96551-3f11-43c1-a249-cadb0c8cf689', '/ipsp/productionLineDistribution/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d46', 'inWareMatching', '入库配码', 2, NULL, '24ec68cb-946f-4331-a846-f7000f410950', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d47', 'inWareTask', '入库任务', 2, NULL, '24ec68cb-946f-4331-a846-f7000f410950', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('b9910be0-3149-4e78-9947-4b1nb9876d29', 'L10L26', '一对一入库', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d47', '/ipsp/generalWarehousingTask/homePage', NULL);
INSERT INTO "public"."app_menu" VALUES ('b7710be0-3149-4e78-9947-4b1nb9876b83', 'L10L27', '全自动入库', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d47', '/ipsp/taskTypeWarehousingTask/homePage', NULL);
INSERT INTO "public"."app_menu" VALUES ('051a2f10-cdd8-4bb6-9a4e-b1606e169b79', 'L10L28', '抽屉式入库', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d47', '/ipsp/drawerMaterialMatching/homePage', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d6-ec77-4a94-b697-e831761e0d43', 'ManualDrawerSorting', '抽屉式分拣', 3, NULL, '3fc017d6-ec77-4a94-b697-e831761e0d40', '/ipsp/manualDrawerSorting/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('60574415-ff9e-42ef-b5b2-39cb788b49b4', 'ProductionLine', '产线', 1, 'fa fa-sliders fa-fw', '0', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('863f1d63-eeeb-45c5-b61f-4a333a7733eb', 'L10L24', '盘料配码', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d46', '/ipsp/discMaterialMatching/homePage', NULL);
INSERT INTO "public"."app_menu" VALUES ('b8810be0-3169-4e88-9947-4b0bb8279d29', 'L10L25', '散料配码', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d46', '/ipsp/dispersionMaterialMatching/homePage', NULL);
INSERT INTO "public"."app_menu" VALUES ('b8810be0-3169-4e88-9947-4b0bb8279d30', 'L10L25', '抽屉配码', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d46', '/ipsp/drawerMaterialMatching/home', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d48', 'inWareTask', '入库单', 2, NULL, '24ec68cb-946f-4331-a846-f7000f410950', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d81', 'suitcaseqr', '周转箱唯一码', 2, NULL, '24ec68cb-946f-4331-a846-f7000f410951', '/ipsp/suitcaseQR/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('4345430c-d9d0-4d5d-b7e1-b933a55873fd', 'Process_Management', '工序管理', 2, NULL, '60574415-ff9e-42ef-b5b2-39cb788b49b4', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('f61dcd07-61da-4a11-98af-79f74e51569e', 'L10L29', '查询状态', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d48', '/ipsp/inwareBillStatus/homePage', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d80', 'materialqr', '物料唯一码', 2, NULL, '24ec68cb-946f-4331-a846-f7000f410951', '/ipsp/materialQR/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d82', 'materialCategory', '分类管理', 2, NULL, '24ec68cb-946f-4331-a846-f7000f410951', '/ipsp/materialCategory/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('f61dcd07-61da-4a11-98af-79f74e51569d', 'L10L29', '编辑单据', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d48', '/ipsp/warehousingInWarehouseBill/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d99', 'queryOutwBillStatus', '查看状态', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d28', '/ipsp/outWareBillStatus/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('ee992c93-46a5-45b7-8aec-1f015fec929b', 'queryMaterialStock', '查看库存', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d28', '/ipsp/materialStock/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d6-ec77-4a94-b697-e831761e0d41', 'OneToOneSort', '一对一分拣', 3, NULL, '3fc017d6-ec77-4a94-b697-e831761e0d40', '/ipsp/oneToOneCabinetSort/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('aefca889-8f84-49ca-b2f1-900ac8e4af62', 'EditOutWareBill', '编辑单据', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d28', '/ipsp/outWarehouseBill/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d21', 'OutwMissionForOneToOne', '一对一出库', 3, NULL, '3fc017d5-ec77-4a94-b697-e831761e0d23', '/ipsp/oneToOneOuting/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('27e96551-3f11-43c1-a249-cadb0c8cf690', 'ManualDrawerOuting', '抽屉式出库', 3, '', '3fc017d5-ec77-4a94-b697-e831761e0d23', '/ipsp/manualDrawerOuting/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('8d883983-f7bb-41f4-ab6a-92378c0024b8', 'ProductionLine_Management', '产线管理', 2, NULL, '60574415-ff9e-42ef-b5b2-39cb788b49b4', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('938ddcfd-b8f2-4f6a-80f1-f353b4000f0c', 'AGV_Management', 'AGV管理', 2, NULL, '60574415-ff9e-42ef-b5b2-39cb788b49b4', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('ae927934-dfb3-4ebf-b73c-bf900a78d129', 'Process', '工序', 3, NULL, '4345430c-d9d0-4d5d-b7e1-b933a55873fd', '/ipsp/process/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('788279de-47e9-4d29-80d7-5245836379dc', 'Process_Product', '工序产品', 3, NULL, '4345430c-d9d0-4d5d-b7e1-b933a55873fd', '/ipsp/processProduct/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('631bcb2f-1b19-4116-9578-a22a8eed0ad0', 'Process_Flow', '工序流转', 3, NULL, '4345430c-d9d0-4d5d-b7e1-b933a55873fd', '/ipsp/processFlow/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('3fc017d5-ec77-4a94-b697-e831761e0d28', 'OutwareBill', '出库单', 2, NULL, '27e96551-3f11-43c1-a249-cadb0c8cf689', NULL, NULL);
INSERT INTO "public"."app_menu" VALUES ('d16b6d12-20d8-448c-a5ad-bce90f918bf1', 'Process_Distribution', '工序配送', 3, NULL, '4345430c-d9d0-4d5d-b7e1-b933a55873fd', '/ipsp/processDistribution/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('5a324b25-a4e0-409e-adbe-9259452dbe85', 'Material_Info', '物料信息', 3, NULL, '8d883983-f7bb-41f4-ab6a-92378c0024b8', '/ipsp/materialInfo/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('4624de5e-5abe-49f0-b0ca-1ba74c76293c', 'Schedule_AGV', '调度AGV', 3, NULL, '938ddcfd-b8f2-4f6a-80f1-f353b4000f0c', '/ipsp/schedulingAgv/view', NULL);
INSERT INTO "public"."app_menu" VALUES ('24ec68cb-946f-4331-a846-f7000f410951', 'L10', '码管理', 1, 'fa fa-certificate', '0', NULL, NULL);

-- ----------------------------
-- Table structure for app_sys
-- ----------------------------
DROP TABLE IF EXISTS "public"."app_sys";
CREATE TABLE "public"."app_sys" (
  "sys_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "sys_code" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "sys_name" varchar(15) COLLATE "pg_catalog"."default",
  "sys_address" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "sys_create_time" timestamp(6) NOT NULL,
  "sys_modify_time" timestamp(6) NOT NULL,
  "sys_modify_author" varchar(20) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."app_sys"."sys_id" IS '主键';
COMMENT ON COLUMN "public"."app_sys"."sys_code" IS '系统代码';
COMMENT ON COLUMN "public"."app_sys"."sys_name" IS '系统名称';
COMMENT ON COLUMN "public"."app_sys"."sys_address" IS '系统访问地址';
COMMENT ON COLUMN "public"."app_sys"."sys_create_time" IS '创建时间';
COMMENT ON COLUMN "public"."app_sys"."sys_modify_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."app_sys"."sys_modify_author" IS '修改人';
COMMENT ON TABLE "public"."app_sys" IS '应用信息表，用于描述各应用信息（例：基础数据、生产准备区、仓储、三元权限等系统信息)';

-- ----------------------------
-- Records of app_sys
-- ----------------------------
INSERT INTO "public"."app_sys" VALUES ('5483a1a0-44a4-4d75-949d-2094d2cbd4b0', 'EW.IPSP.PRODUCTIONREADY', '生产准备区', 'http://192.168.1.253:8090', '2018-10-17 17:19:35.571', '2018-10-17 17:19:35.571', NULL);
INSERT INTO "public"."app_sys" VALUES ('2abce14c-a7ff-4d67-879f-8cc958f26e74', 'EW.IPSP.FOUNDATION', '基础数据', 'http://192.168.1.253:8089', '2018-10-17 17:18:54.342', '2018-10-17 17:18:54.342', NULL);
INSERT INTO "public"."app_sys" VALUES ('b48f7f3f-dc0b-4e23-bb85-9f219effb716', 'EW.IPSP.FOUNDATION-LOCALHOST', '基础数据', 'http://192.168.1.20:8089', '2018-10-23 09:45:19.58', '2018-10-23 09:45:19.58', NULL);
INSERT INTO "public"."app_sys" VALUES ('934fcf4b-d54b-4220-ae76-ba283a5ba6dd', 'EW.IPSP.WAREHOUSING', '配码入库', 'http://192.168.1.253:8088', '2018-10-17 17:17:56', '2018-10-17 17:17:56.491', NULL);
INSERT INTO "public"."app_sys" VALUES ('3f89b2b9-58f5-425c-96f3-0f1d1d7bb680', 'EW.IPSP.PRODUCTIONLINE', '产线', 'http://192.168.1.253:8091', '2018-11-17 13:17:46.057', '2018-11-17 13:17:46.057', NULL);

-- ----------------------------
-- Table structure for app_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."app_sys_menu";
CREATE TABLE "public"."app_sys_menu" (
  "app_sys_menu_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "app_menu_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "app_sys_code" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "app_sysmenu_createtime" timestamp(6) NOT NULL,
  "app_sysmenu_modifytime" timestamp(6) NOT NULL,
  "app_sysmenu_modify_author" varchar(20) COLLATE "pg_catalog"."default",
  "app_sysmenu_sort" int2,
  "app_sysmenu_menu_description" varchar(64) COLLATE "pg_catalog"."default",
  "app_sysmenu_access_address" varchar(64) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."app_sys_menu"."app_sys_menu_id" IS '主键';
COMMENT ON COLUMN "public"."app_sys_menu"."app_menu_id" IS 'app_menu id';
COMMENT ON COLUMN "public"."app_sys_menu"."app_sys_code" IS 'app_sys code';
COMMENT ON COLUMN "public"."app_sys_menu"."app_sysmenu_createtime" IS '创建时间';
COMMENT ON COLUMN "public"."app_sys_menu"."app_sysmenu_modifytime" IS '修改时间';
COMMENT ON COLUMN "public"."app_sys_menu"."app_sysmenu_modify_author" IS '编辑人';
COMMENT ON COLUMN "public"."app_sys_menu"."app_sysmenu_sort" IS '排序';
COMMENT ON COLUMN "public"."app_sys_menu"."app_sysmenu_menu_description" IS '菜单定制名称,如果指定，则不使用app_menu中的描述';
COMMENT ON COLUMN "public"."app_sys_menu"."app_sysmenu_access_address" IS '菜单访问根地址，如果指定，则不使用app_sys中的地址';
COMMENT ON TABLE "public"."app_sys_menu" IS 'app_menu与app_sys中间表';

-- ----------------------------
-- Records of app_sys_menu
-- ----------------------------
INSERT INTO "public"."app_sys_menu" VALUES ('2dd537c8-f65a-4dce-916f-359d6bf7f714', '24ec68cb-946f-4331-a846-f7000f410951', 'EW.IPSP.WAREHOUSING', '2018-11-06 17:34:24.62', '2018-11-06 17:34:24.62', NULL, 2, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('0689b47f-b0c0-4f67-bb36-554d579eb0dd', '3fc017d5-ec77-4a94-b697-e831761e0d82', 'EW.IPSP.WAREHOUSING', '2018-11-06 17:34:24.648', '2018-11-06 17:34:24.648', NULL, 21, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('f548818e-9523-43f7-813f-8622cfcc8057', '3fc017d5-ec77-4a94-b697-e831761e0d80', 'EW.IPSP.WAREHOUSING', '2018-11-06 17:34:24.645', '2018-11-06 17:34:24.645', NULL, 22, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('252849fb-7e9c-4964-8378-308d9cf16369', '3fc017d5-ec77-4a94-b697-e831761e0d81', 'EW.IPSP.WAREHOUSING', '2018-11-06 17:34:24.643', '2018-11-06 17:34:24.643', NULL, 23, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('4be37c75-9829-4663-bbad-fc28af09c6c8', '3fc017d5-ec77-4a94-b697-e831761e0d23', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.568', '2018-10-17 17:16:51.568', NULL, 22, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('03574255-46fb-452a-826e-a00ec079e136', '3fc017d6-ec77-4a94-b697-e831761e0d40', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.571', '2018-10-17 17:16:51.571', NULL, 23, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('f660ccf8-4367-4633-b196-3b47ff9733d4', '24ec68cb-946f-4331-a846-f7000f410958', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.573', '2018-10-17 17:16:51.573', NULL, 24, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('bf56ad7f-6385-43dc-8a0c-18375d36346e', 'aefca889-8f84-49ca-b2f1-900ac8e4af62', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:12:17.619', '2018-10-17 17:12:17.619', NULL, 211, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('a42715c2-f305-42de-947a-60297e65b94a', '27e96551-3f11-43c1-a249-cadb0c8cf690', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.585', '2018-10-17 17:16:51.585', NULL, 221, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('437f5db2-0d76-46be-b903-71aaafc5f2ac', '3fc017d5-ec77-4a94-b697-e831761e0d21', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.587', '2018-10-17 17:16:51.587', NULL, 222, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('6177e7f7-93ea-4ed8-9f14-63b0205ffc1e', '0cee3751-eb4b-4c55-bfe4-6da0cbcbe72e', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:15.894', '2018-10-17 17:12:15.894', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('1e637419-cb3c-420f-9064-3cb9e8ac919b', '8557b555-3f85-46c0-bc61-754161e6412b', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:16.04', '2018-10-17 17:12:16.04', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('0b2f677d-37a7-45e1-9da4-e5a144e71a3f', 'a2cdbfb2-e3e5-4440-b512-eec83e85192c', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:16.049', '2018-10-17 17:12:16.049', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('aee9e218-2949-4a69-bda4-d2072aaa21b9', '75622dcd-5edb-4736-9f86-7264eaf3dba9', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:16.088', '2018-10-17 17:12:16.088', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('90ccdcb4-4b2f-4add-8bfb-a9c3b3ca7262', '4b9021fc-60d6-4e9d-93c2-d8ef88add673', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:16.096', '2018-10-17 17:12:16.096', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('8f3f1074-2786-47e8-91f4-88a13cc0851e', '06f5f4e8-85f0-4008-9d4d-704ea23dc1b5', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:16.131', '2018-10-17 17:12:16.131', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('758093f8-24c9-474a-9475-9004e6cd4491', 'c9034067-917c-413f-917d-574de4255494', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:17.004', '2018-10-17 17:12:17.004', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('dee2b006-c15e-4e5a-a86d-443835a61c98', '38f9db19-a617-493b-822c-d713c801ba33', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:17.188', '2018-10-17 17:12:17.189', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('04ab7087-f2d5-490c-b17c-68c73dcf9778', 'b896500a-fa93-4f5b-a241-0c8f2b2e2034', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:17.29', '2018-10-17 17:12:17.29', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('40ff880e-42dd-49d5-845c-31bf08e52d38', 'dd557853-8ba6-45cc-9a95-6d51ee1b6eba', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:18.216', '2018-10-17 17:12:18.216', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('f46507a8-c1bd-46cf-a552-6f19fbbae45d', '6a860797-63b7-41d7-8f8a-803917ddbd08', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:18.321', '2018-10-17 17:12:18.321', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('2c80f153-c329-4045-a744-115d09c34219', '051a2f10-cdd8-4bb6-9a4e-b1606e169b78', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:18.77', '2018-10-17 17:12:18.77', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('ff7a7f02-b97d-4885-981f-9aeede0122ec', 'e450f632-d628-479d-a778-1874c1484f4b', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:18.823', '2018-10-17 17:12:18.823', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('7d87b840-2bc8-4667-8d78-ca4ab59e1ad2', 'f61dcd07-61da-4a11-98af-79f74e51569b', 'EW.IPSP.FOUNDATION', '2018-10-17 17:12:18.836', '2018-10-17 17:12:18.836', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('9c4ba5b7-39e4-4dee-a8f3-45a5ea714c63', '27e96551-3f11-43c1-a249-cadb0c8cf689', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.549', '2018-10-17 17:16:51.549', NULL, 3, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('ed277c95-b251-45ad-abd8-06c08f4af14c', '24ec68cb-946f-4331-a846-f7000f410950', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.208', '2018-10-29 18:04:12.208', NULL, 3, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('2e137fee-8658-4185-b164-e42d280b8a2e', 'f61dcd07-61da-4a11-98af-79f74e51569d', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.286', '2018-10-29 18:04:12.286', NULL, 311, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('eb4d8ded-20a7-48fa-a90a-851988003323', 'f61dcd07-61da-4a11-98af-79f74e51569e', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.289', '2018-10-29 18:04:12.289', NULL, 312, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('cc71496f-bac0-404a-8ec3-c996a09d3a27', '0cee3751-eb4b-4c55-bfe4-6da0cbcbe72e', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:43.994', '2018-10-23 09:48:43.994', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('8001d9da-afff-4f25-8015-678a5561ba77', '8557b555-3f85-46c0-bc61-754161e6412b', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.021', '2018-10-23 09:48:44.021', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('928b6d9a-de30-4d32-acfb-4603afccb784', 'a2cdbfb2-e3e5-4440-b512-eec83e85192c', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.024', '2018-10-23 09:48:44.024', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('f88821aa-fb63-420f-8362-a4097f04b341', '75622dcd-5edb-4736-9f86-7264eaf3dba9', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.028', '2018-10-23 09:48:44.028', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('fd9041e7-e17d-47d5-8b62-0ff599896c63', '4b9021fc-60d6-4e9d-93c2-d8ef88add673', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.03', '2018-10-23 09:48:44.03', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('fc23e65e-e24e-40db-bda7-484c574b9a94', '06f5f4e8-85f0-4008-9d4d-704ea23dc1b5', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.035', '2018-10-23 09:48:44.035', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('e9d95fed-ea3e-46fa-a3ae-cda9cf58d732', 'c9034067-917c-413f-917d-574de4255494', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.062', '2018-10-23 09:48:44.062', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('dbd0624f-1bf2-40fc-bf11-1ab0cc2182a1', '38f9db19-a617-493b-822c-d713c801ba33', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.065', '2018-10-23 09:48:44.065', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('10d63748-61ee-4b18-91d6-f52425e7148a', 'b896500a-fa93-4f5b-a241-0c8f2b2e2034', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.067', '2018-10-23 09:48:44.067', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('e1cdf01c-de8d-4818-a3d3-242500ccc4bd', 'aefca889-8f84-49ca-b2f1-900ac8e4af62', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.072', '2018-10-23 09:48:44.072', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('f913a392-7b57-436c-a490-7c88ec56c228', 'dd557853-8ba6-45cc-9a95-6d51ee1b6eba', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.074', '2018-10-23 09:48:44.075', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('772ebcf6-31f9-4dde-beec-11faefecf631', '6a860797-63b7-41d7-8f8a-803917ddbd08', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.077', '2018-10-23 09:48:44.077', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('3ae179d9-09ab-4532-8064-e28c23a3417c', '051a2f10-cdd8-4bb6-9a4e-b1606e169b78', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.08', '2018-10-23 09:48:44.08', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('66af5381-2c05-4699-b035-7a8a7c66f1b9', 'e450f632-d628-479d-a778-1874c1484f4b', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.082', '2018-10-23 09:48:44.083', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('fc8ebb19-748d-4a06-885f-7225570f120e', 'f61dcd07-61da-4a11-98af-79f74e51569b', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.086', '2018-10-23 09:48:44.087', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('0204ca86-23a1-4182-9db2-b70ff1230b85', 'd04d5f0d-c398-4f44-913b-308f2c0b6936', 'EW.IPSP.FOUNDATION-LOCALHOST', '2018-10-23 09:48:44.092', '2018-10-23 09:48:44.092', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('6e7ffc73-72db-40f7-9839-c8ef1aa3766d', '3fc017d5-ec77-4a94-b697-e831761e0d46', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.237', '2018-10-29 18:04:12.237', NULL, 32, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('4f22bba3-0be1-4a59-9792-c20e6d962421', '3fc017d5-ec77-4a94-b697-e831761e0d47', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.242', '2018-10-29 18:04:12.242', NULL, 33, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('003aa181-b199-4f79-a114-63d308324ab4', '863f1d63-eeeb-45c5-b61f-4a333a7733eb', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.27', '2018-10-29 18:04:12.27', NULL, 321, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('43f6a3eb-8978-4fac-aaa4-12a0c49c07ef', 'b8810be0-3169-4e88-9947-4b0bb8279d29', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.274', '2018-10-29 18:04:12.274', NULL, 322, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('cf2c8afe-efb5-4253-b4e3-77b341f50f25', 'b8810be0-3169-4e88-9947-4b0bb8279d30', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.276', '2018-10-29 18:04:12.276', NULL, 323, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('ef2a09fc-b808-49b1-93b3-5fa17153b58f', 'b9910be0-3149-4e78-9947-4b1nb9876d29', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.279', '2018-10-29 18:04:12.279', NULL, 331, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('bc50cee3-b58a-42b1-8b34-af2e7f542473', 'b7710be0-3149-4e78-9947-4b1nb9876b83', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.282', '2018-10-29 18:04:12.282', NULL, 332, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('ea1d327f-988b-4b93-afaa-719fc5e4c8d4', '051a2f10-cdd8-4bb6-9a4e-b1606e169b79', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.284', '2018-10-29 18:04:12.284', NULL, 333, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('a5bcf623-6524-48ed-a1c4-bbb31d14501e', '3fc017d5-ec77-4a94-b697-e831761e0d48', 'EW.IPSP.WAREHOUSING', '2018-10-29 18:04:12.246', '2018-10-29 18:04:12.246', NULL, 31, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('2c9a0efe-e03d-40dc-be43-04322c49d899', '3fc017d5-ec77-4a94-b697-e831761e0d28', 'EW.IPSP.PRODUCTIONREADY', '2018-11-06 19:16:51', '2018-11-06 19:16:51', NULL, 21, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('fdfc372f-b049-444e-91f7-37f611e84090', 'ee992c93-46a5-45b7-8aec-1f015fec929b', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.592', '2018-10-17 17:16:51.592', NULL, 213, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('81e641b0-e8a9-4d3e-8fa3-d516cd26cfcb', '3fc017d6-ec77-4a94-b697-e831761e0d43', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.591', '2018-10-17 17:16:51.591', NULL, 231, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('fdfc372f-b049-444e-91f7-37f611e8409c', '3fc017d6-ec77-4a94-b697-e831761e0d41', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.592', '2018-10-17 17:16:51.592', NULL, 232, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('8264b538-88fe-43e2-8d2f-66a5e7567e1a', '60574415-ff9e-42ef-b5b2-39cb788b49b4', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.175', '2018-11-17 13:22:16.175', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('e96935da-7aac-4d67-96a8-8b92dfc89ef1', '4345430c-d9d0-4d5d-b7e1-b933a55873fd', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.208', '2018-11-17 13:22:16.208', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('ec0e146e-722d-4170-8e81-c5c02304a7a7', '8d883983-f7bb-41f4-ab6a-92378c0024b8', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.213', '2018-11-17 13:22:16.213', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('5814a651-b7dd-4e7f-b128-67e2391d1aa0', '938ddcfd-b8f2-4f6a-80f1-f353b4000f0c', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.217', '2018-11-17 13:22:16.217', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('2c9a0efe-e03d-40dc-be43-04322c49d8eb', '3fc017d5-ec77-4a94-b697-e831761e0d99', 'EW.IPSP.PRODUCTIONREADY', '2018-10-17 17:16:51.589', '2018-10-17 17:16:51.589', NULL, 212, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('43cd1ce1-f53d-43c5-8223-9834482ee817', 'ae927934-dfb3-4ebf-b73c-bf900a78d129', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.24', '2018-11-17 13:22:16.24', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('0fe9cbc8-794e-4eb4-b1b7-d818ddb29ec3', '788279de-47e9-4d29-80d7-5245836379dc', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.245', '2018-11-17 13:22:16.245', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('eceb58bd-57ce-4f8c-aa65-cbae9eb2b261', '631bcb2f-1b19-4116-9578-a22a8eed0ad0', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.248', '2018-11-17 13:22:16.248', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('e5b7b5a1-117d-42a0-91e0-02af7080e9eb', 'd16b6d12-20d8-448c-a5ad-bce90f918bf1', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.251', '2018-11-17 13:22:16.251', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('72350f16-8dc2-4b14-bd12-f8e4bf2253e1', '5a324b25-a4e0-409e-adbe-9259452dbe85', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.26', '2018-11-17 13:22:16.26', NULL, 1, NULL, NULL);
INSERT INTO "public"."app_sys_menu" VALUES ('dc88a784-9917-4622-908f-a31507e30483', '4624de5e-5abe-49f0-b0ca-1ba74c76293c', 'EW.IPSP.PRODUCTIONLINE', '2018-11-17 13:22:16.264', '2018-11-17 13:22:16.264', NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for bills_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."bills_category";
CREATE TABLE "public"."bills_category" (
  "cat_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "cat_name" varchar(64) COLLATE "pg_catalog"."default",
  "cat_category" int2 NOT NULL,
  "cat_ownercategory" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."bills_category"."cat_id" IS 'Bom单分类ID';
COMMENT ON COLUMN "public"."bills_category"."cat_name" IS 'Bom单分类名称';
COMMENT ON COLUMN "public"."bills_category"."cat_category" IS 'Bom单分类';
COMMENT ON TABLE "public"."bills_category" IS 'Bom表单分类';

-- ----------------------------
-- Table structure for cabin_detailinfo
-- ----------------------------
DROP TABLE IF EXISTS "public"."cabin_detailinfo";
CREATE TABLE "public"."cabin_detailinfo" (
  "det_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "det_suiid" varchar(40) COLLATE "pg_catalog"."default",
  "det_suiuniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "det_materialmodel" varchar(30) COLLATE "pg_catalog"."default",
  "det_materialcode" varchar(40) COLLATE "pg_catalog"."default",
  "det_materialuniquecode" varchar COLLATE "pg_catalog"."default",
  "det_materialmanufacturer" varchar(50) COLLATE "pg_catalog"."default",
  "det_materialunit" varchar(10) COLLATE "pg_catalog"."default",
  "det_materialbatch" varchar(20) COLLATE "pg_catalog"."default",
  "det_materialeffectivedate" date,
  "det_materialproduceddate" date,
  "det_materialamount" int2,
  "det_isreimbursement" int2,
  "det_cabinindex" int2,
  "det_bindtime" timestamp(4),
  "det_billscategory" int2,
  "det_billsnumber" varchar(40) COLLATE "pg_catalog"."default",
  "det_processcode" varchar(40) COLLATE "pg_catalog"."default",
  "det_currentpath" int2,
  "det_currentsite" int2,
  "det_targetsite" int2,
  "det_targetpath" int2,
  "det_bulksuiuniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "det_isbulkmaterial" int2,
  "det_fromsource" int2,
  "det_description" varchar(200) COLLATE "pg_catalog"."default",
  "det_ishalf" bool
)
;
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_id" IS '周转箱料仓ID';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_suiid" IS '料仓关联周转箱ID';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_suiuniquecode" IS '周转箱唯一码';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialmodel" IS '物料规格型号';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialcode" IS '物料编码';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialuniquecode" IS '物料唯一码';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialmanufacturer" IS '物料厂家';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialunit" IS '物料计量单位（颗）';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialbatch" IS '物料批次';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialeffectivedate" IS '有效日期';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialproduceddate" IS '生产日期';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_materialamount" IS '物料数量';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_isreimbursement" IS '是否退料入库（0来料入库、1物料分解后余料入库、2产线退库入料）';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_cabinindex" IS '仓位索引';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_bindtime" IS '绑定时间';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_billscategory" IS '单据分类（1来料入库单、2BOM出料余料退库入库单、3产线退料入库单、3BOM物料派送单）';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_billsnumber" IS 'Bom编号';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_targetsite" IS '目标站点';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_targetpath" IS '目标路径';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_bulksuiuniquecode" IS '如果是散料时，散料对应的包装盒的唯一码';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_isbulkmaterial" IS '是否为散料';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_fromsource" IS '数据来源字段，用于标明数据从那个地方来。
0-排码区
1-库存
2-生产准备区
3-生产区';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_description" IS '描述信息';
COMMENT ON COLUMN "public"."cabin_detailinfo"."det_ishalf" IS '是否为半盘';
COMMENT ON TABLE "public"."cabin_detailinfo" IS '配码完成后生成的 仓位详情信息表，属于基础数据，数据是动态的，只需要查询功能。属于配码区。';

-- ----------------------------
-- Table structure for goodslocation_axisdictionary
-- ----------------------------
DROP TABLE IF EXISTS "public"."goodslocation_axisdictionary";
CREATE TABLE "public"."goodslocation_axisdictionary" (
  "axisd_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "axisd_equ_id" varchar(40) COLLATE "pg_catalog"."default",
  "axisd_number" int2,
  "axisd_status" int2,
  "axisd_rank" int2,
  "axisd_tier" int2,
  "axisd_column" int2,
  "axisd_x" int4,
  "axisd_y" int4,
  "axisd_z" int4,
  "axisd_suitcaseuniquecode" varchar COLLATE "pg_catalog"."default",
  "axisd_suitcasetype" int2,
  "axisd_isautoload" bool
)
;
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_id" IS '货位坐标ID';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_equ_id" IS '设备ID（固定货架、移动货架）';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_number" IS '货位编号';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_status" IS '货位状态（0空闲状态、1占用状态、2故障不可用状态）';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_rank" IS '货位所在的排';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_tier" IS '货位所在的层';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_column" IS '货位所在的列';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_x" IS '机械X坐标值';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_y" IS '机械Y坐标值';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_z" IS '机械Z坐标值';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_suitcaseuniquecode" IS '货位上放置的货盒唯一码';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_suitcasetype" IS '周转箱类型';
COMMENT ON COLUMN "public"."goodslocation_axisdictionary"."axisd_isautoload" IS '是否可以自动装卸周转箱';
COMMENT ON TABLE "public"."goodslocation_axisdictionary" IS '货架（固定、移动）货位坐标状态信息记录表，基础数据表，需要做CRUD功能。';

-- ----------------------------
-- Table structure for inwarehouse_bill
-- ----------------------------
DROP TABLE IF EXISTS "public"."inwarehouse_bill";
CREATE TABLE "public"."inwarehouse_bill" (
  "inw_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "inw_billsnumber" varchar(40) COLLATE "pg_catalog"."default",
  "inw_category" int2 NOT NULL,
  "inw_intime" timestamp(4),
  "inw_status" int2
)
;
COMMENT ON COLUMN "public"."inwarehouse_bill"."inw_id" IS '入库单ID';
COMMENT ON COLUMN "public"."inwarehouse_bill"."inw_billsnumber" IS '入库BOM单编号';
COMMENT ON COLUMN "public"."inwarehouse_bill"."inw_category" IS '入库单分类';
COMMENT ON COLUMN "public"."inwarehouse_bill"."inw_intime" IS '入库时间';
COMMENT ON COLUMN "public"."inwarehouse_bill"."inw_status" IS '入库状态：0代表没有入库（新单）、1代表入库未完成（出了一部分）、2代表入库完成';
COMMENT ON TABLE "public"."inwarehouse_bill" IS '入库单，需要数据的CRUD功能';

-- ----------------------------
-- Table structure for inwarehouse_detailbill
-- ----------------------------
DROP TABLE IF EXISTS "public"."inwarehouse_detailbill";
CREATE TABLE "public"."inwarehouse_detailbill" (
  "inw_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "inw_billsnumber" varchar(40) COLLATE "pg_catalog"."default",
  "inw_model" varchar(30) COLLATE "pg_catalog"."default",
  "inw_code" varchar(40) COLLATE "pg_catalog"."default",
  "inw_uniquecode" varchar(80) COLLATE "pg_catalog"."default" NOT NULL,
  "inw_manufacturer" varchar(50) COLLATE "pg_catalog"."default",
  "inw_unit" varchar(10) COLLATE "pg_catalog"."default",
  "inw_batch" varchar(20) COLLATE "pg_catalog"."default",
  "inw_effectivedate" date,
  "inw_produceddate" date,
  "inw_amount" int2,
  "inw_actualcount" int2,
  "inw_status" int2,
  "inw_warehousenumber" varchar(30) COLLATE "pg_catalog"."default",
  "inw_rank" int2,
  "inw_tier" int2,
  "inw_column" int2,
  "inw_rangeindex" int2,
  "inw_indatetime" timestamp(4)
)
;
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_id" IS '入库单ID';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_billsnumber" IS '入库BOM单编号';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_model" IS '物料规格型号';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_code" IS '物料编码';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_uniquecode" IS '物料唯一码';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_manufacturer" IS '物料厂家';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_unit" IS '物料计量单位';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_batch" IS '物料批次';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_effectivedate" IS '有效日期';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_produceddate" IS '生产日期';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_amount" IS '入库单某一种物料数量';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_actualcount" IS '某一种物料的实际入库数量';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_status" IS '入库状态：0代表没有入库（新单）、1代表入库未完成（出了一部分）、2代表入库完成';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_warehousenumber" IS '仓库编号';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_rank" IS '货位所在的排';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_tier" IS '货位所在的层';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_column" IS '货位所在的列';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_rangeindex" IS '仓位索引';
COMMENT ON COLUMN "public"."inwarehouse_detailbill"."inw_indatetime" IS '入库时间';
COMMENT ON TABLE "public"."inwarehouse_detailbill" IS '入库单详情表，不属于基础数据，需要增删改查功能。配码区入库功能。';

-- ----------------------------
-- Table structure for inwarehouse_detailtask
-- ----------------------------
DROP TABLE IF EXISTS "public"."inwarehouse_detailtask";
CREATE TABLE "public"."inwarehouse_detailtask" (
  "taskdet_id" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "taskdet_suitcase_uniquecode" varchar COLLATE "pg_catalog"."default",
  "taskdet_suitcase_rfid" varchar COLLATE "pg_catalog"."default",
  "taskdet_warehouse_number" varchar COLLATE "pg_catalog"."default",
  "taskdet_status" int2,
  "taskdet_date_time" timestamp(4)
)
;
COMMENT ON COLUMN "public"."inwarehouse_detailtask"."taskdet_suitcase_uniquecode" IS '周转箱的唯一码';
COMMENT ON COLUMN "public"."inwarehouse_detailtask"."taskdet_suitcase_rfid" IS '周转箱的RFID';
COMMENT ON COLUMN "public"."inwarehouse_detailtask"."taskdet_warehouse_number" IS '入库任务编号';
COMMENT ON COLUMN "public"."inwarehouse_detailtask"."taskdet_status" IS '任务执行状态';

-- ----------------------------
-- Table structure for inwarehouse_task
-- ----------------------------
DROP TABLE IF EXISTS "public"."inwarehouse_task";
CREATE TABLE "public"."inwarehouse_task" (
  "task_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "task_number" varchar(40) COLLATE "pg_catalog"."default",
  "task_bills_number" varchar(40) COLLATE "pg_catalog"."default",
  "task_date_time" timestamp(4),
  "task_status" int2,
  "task_target_site" int2
)
;
COMMENT ON COLUMN "public"."inwarehouse_task"."task_number" IS '任务编号';
COMMENT ON COLUMN "public"."inwarehouse_task"."task_bills_number" IS '表单编号';
COMMENT ON COLUMN "public"."inwarehouse_task"."task_date_time" IS '任务创建时间';
COMMENT ON COLUMN "public"."inwarehouse_task"."task_status" IS '任务执行状态';
COMMENT ON COLUMN "public"."inwarehouse_task"."task_target_site" IS '目标站点';
COMMENT ON TABLE "public"."inwarehouse_task" IS '入库任务表';

-- ----------------------------
-- Table structure for material_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."material_category";
CREATE TABLE "public"."material_category" (
  "mcat_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "mcat_number" int2 NOT NULL,
  "mcat_name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "mcat_description" varchar(200) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."material_category"."mcat_id" IS '主键';
COMMENT ON COLUMN "public"."material_category"."mcat_number" IS '物料类型编号';
COMMENT ON COLUMN "public"."material_category"."mcat_name" IS '物料类型名称';
COMMENT ON COLUMN "public"."material_category"."mcat_description" IS '描述信息';
COMMENT ON TABLE "public"."material_category" IS '物料类型表';

-- ----------------------------
-- Table structure for material_qr
-- ----------------------------
DROP TABLE IF EXISTS "public"."material_qr";
CREATE TABLE "public"."material_qr" (
  "mqr_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "mqr_rid" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "mqr_info" varchar(500) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."material_qr"."mqr_id" IS '主键';
COMMENT ON COLUMN "public"."material_qr"."mqr_rid" IS '外键';
COMMENT ON COLUMN "public"."material_qr"."mqr_info" IS '物料唯一码';
COMMENT ON TABLE "public"."material_qr" IS '物料唯一码';

-- ----------------------------
-- Table structure for material_qrrule
-- ----------------------------
DROP TABLE IF EXISTS "public"."material_qrrule";
CREATE TABLE "public"."material_qrrule" (
  "mqrr_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "mqrr_cid" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "mqrr_prefix" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "mqrr_attribute" varchar(255) COLLATE "pg_catalog"."default",
  "mqrr_suffix" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "mqrr_date_time" timestamp(4),
  "mqrr_status" int2
)
;
COMMENT ON COLUMN "public"."material_qrrule"."mqrr_id" IS '主键';
COMMENT ON COLUMN "public"."material_qrrule"."mqrr_cid" IS '外键';
COMMENT ON COLUMN "public"."material_qrrule"."mqrr_prefix" IS '物料最小包装唯一码前缀';
COMMENT ON COLUMN "public"."material_qrrule"."mqrr_attribute" IS '物料最小包装唯一码包含信息，由物料相关属性、业务相关属性组成';
COMMENT ON COLUMN "public"."material_qrrule"."mqrr_suffix" IS '物料最小包装唯一码后缀';
COMMENT ON COLUMN "public"."material_qrrule"."mqrr_date_time" IS '物料最小包装唯一码创建日期';
COMMENT ON COLUMN "public"."material_qrrule"."mqrr_status" IS '最小包装唯一码状态：0未打印，1打印未使用，2打印已使用';
COMMENT ON TABLE "public"."material_qrrule" IS '物料唯一码生成规则';

-- ----------------------------
-- Table structure for materialflow_station
-- ----------------------------
DROP TABLE IF EXISTS "public"."materialflow_station";
CREATE TABLE "public"."materialflow_station" (
  "sta_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "sta_name" varchar(64) COLLATE "pg_catalog"."default",
  "sta_number" int2,
  "sta_description" varchar(200) COLLATE "pg_catalog"."default",
  "sta_x" numeric(10,2),
  "sta_y" numeric(10,2),
  "sta_z" numeric(10,2)
)
;
COMMENT ON COLUMN "public"."materialflow_station"."sta_id" IS '物流站点ID';
COMMENT ON COLUMN "public"."materialflow_station"."sta_name" IS '物流站点名称';
COMMENT ON COLUMN "public"."materialflow_station"."sta_number" IS '物流站点编号';
COMMENT ON COLUMN "public"."materialflow_station"."sta_description" IS '物流站点描述信息';
COMMENT ON TABLE "public"."materialflow_station" IS '物料周转站点，基础数据表，需要做crud';

-- ----------------------------
-- Table structure for mfagv_axisdictionary
-- ----------------------------
DROP TABLE IF EXISTS "public"."mfagv_axisdictionary";
CREATE TABLE "public"."mfagv_axisdictionary" (
  "axisd_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "axisd_number" int2,
  "axisd_status" int2,
  "axisd_rank" int2,
  "axisd_tier" int2,
  "axisd_column" int2,
  "axisd_x" int4,
  "axisd_y" int4,
  "axisd_z" int4
)
;
COMMENT ON COLUMN "public"."mfagv_axisdictionary"."axisd_id" IS '坐标ID';
COMMENT ON COLUMN "public"."mfagv_axisdictionary"."axisd_status" IS '坐标状态（0表示空、1表示占用、2表示冻结上面有货盒、3表示冻结上面没有货盒）';
COMMENT ON COLUMN "public"."mfagv_axisdictionary"."axisd_rank" IS '坐标所在的排';
COMMENT ON COLUMN "public"."mfagv_axisdictionary"."axisd_tier" IS '坐标所在的层';
COMMENT ON COLUMN "public"."mfagv_axisdictionary"."axisd_column" IS '坐标所在的列';
COMMENT ON COLUMN "public"."mfagv_axisdictionary"."axisd_x" IS '机械坐标X值';
COMMENT ON TABLE "public"."mfagv_axisdictionary" IS '多功能AGV货位坐标字典表';

-- ----------------------------
-- Table structure for mfagv_goodslocation
-- ----------------------------
DROP TABLE IF EXISTS "public"."mfagv_goodslocation";
CREATE TABLE "public"."mfagv_goodslocation" (
  "gsl_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "gsl_axisid" varchar(40) COLLATE "pg_catalog"."default",
  "gsl_suitcaseuniquecode" varchar COLLATE "pg_catalog"."default",
  "gsl_billsnumber" varchar COLLATE "pg_catalog"."default",
  "gsl_locationnumber" int2
)
;
COMMENT ON COLUMN "public"."mfagv_goodslocation"."gsl_id" IS '货位ID';
COMMENT ON COLUMN "public"."mfagv_goodslocation"."gsl_axisid" IS '坐标ID';
COMMENT ON COLUMN "public"."mfagv_goodslocation"."gsl_suitcaseuniquecode" IS '货位上周转箱RFID唯一码';
COMMENT ON COLUMN "public"."mfagv_goodslocation"."gsl_billsnumber" IS '周转箱绑定的BOM编号';
COMMENT ON COLUMN "public"."mfagv_goodslocation"."gsl_locationnumber" IS '货位编号';
COMMENT ON TABLE "public"."mfagv_goodslocation" IS '多功能AGV货位';

-- ----------------------------
-- Table structure for moveshelf_location
-- ----------------------------
DROP TABLE IF EXISTS "public"."moveshelf_location";
CREATE TABLE "public"."moveshelf_location" (
  "msl_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "msl_equ_id" varchar(40) COLLATE "pg_catalog"."default",
  "msl_equ_number" int2,
  "msl_cur_station" int2,
  "msl_pre_station" int2
)
;
COMMENT ON COLUMN "public"."moveshelf_location"."msl_id" IS '周转箱当前站点ID';
COMMENT ON COLUMN "public"."moveshelf_location"."msl_equ_id" IS '移动货架的ID号';
COMMENT ON COLUMN "public"."moveshelf_location"."msl_equ_number" IS '移动货架的编号。';
COMMENT ON COLUMN "public"."moveshelf_location"."msl_cur_station" IS '货架当前站点。';
COMMENT ON COLUMN "public"."moveshelf_location"."msl_pre_station" IS '货架上一站点';
COMMENT ON TABLE "public"."moveshelf_location" IS '移动货架转转站点，基础数据表，但不需要增删改，有查询功能。。。。';

-- ----------------------------
-- Table structure for outwarehouse_bill
-- ----------------------------
DROP TABLE IF EXISTS "public"."outwarehouse_bill";
CREATE TABLE "public"."outwarehouse_bill" (
  "outw_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "outw_billsnumber" varchar(40) COLLATE "pg_catalog"."default",
  "outw_category" int2 NOT NULL,
  "outw_outtime" timestamp(4),
  "outw_status" int2
)
;
COMMENT ON COLUMN "public"."outwarehouse_bill"."outw_id" IS '出库单ID';
COMMENT ON COLUMN "public"."outwarehouse_bill"."outw_billsnumber" IS '出库BOM单编号';
COMMENT ON COLUMN "public"."outwarehouse_bill"."outw_category" IS '单据分类';
COMMENT ON COLUMN "public"."outwarehouse_bill"."outw_outtime" IS '出库时间';
COMMENT ON COLUMN "public"."outwarehouse_bill"."outw_status" IS '出库状态：0代表没有出库（新单）、1代表出库未完成（出了一部分）、2代表出库完成';
COMMENT ON TABLE "public"."outwarehouse_bill" IS '出库单表。生产准备区功能。需要CRUD功能。';

-- ----------------------------
-- Table structure for outwarehouse_detailbill
-- ----------------------------
DROP TABLE IF EXISTS "public"."outwarehouse_detailbill";
CREATE TABLE "public"."outwarehouse_detailbill" (
  "outw_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "outw_billsnumber" varchar(40) COLLATE "pg_catalog"."default",
  "outw_model" varchar(30) COLLATE "pg_catalog"."default",
  "outw_code" varchar(40) COLLATE "pg_catalog"."default",
  "outw_uniquecode" varchar(80) COLLATE "pg_catalog"."default" NOT NULL,
  "outw_manufacturer" varchar(50) COLLATE "pg_catalog"."default",
  "outw_unit" varchar(10) COLLATE "pg_catalog"."default",
  "outw_batch" varchar(20) COLLATE "pg_catalog"."default",
  "outw_effectivedate" date,
  "outw_produceddate" date,
  "outw_total" int2,
  "outw_amount" int2,
  "outw_actualcount" int2,
  "outw_status" int2,
  "outw_warehousenumber" int2,
  "outw_ispreout" int2,
  "outw_prebillnumber" varchar(50) COLLATE "pg_catalog"."default",
  "outw_outdatetime" timestamp(6)
)
;
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_id" IS '出库单详细ID';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_billsnumber" IS '出库BOM单编号';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_model" IS '物料规格型号';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_code" IS '物料编码';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_uniquecode" IS '物料唯一码';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_manufacturer" IS '物料厂家';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_unit" IS '物料计量单位';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_batch" IS '物料批次';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_effectivedate" IS '有效日期';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_produceddate" IS '生产日期';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_total" IS '实际出库总数量';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_amount" IS 'BOM单需求量';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_actualcount" IS 'BOM单实际出库量';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_status" IS '#出库状态：0代表没有出库（新单）、1代表出库未完成（出了一部分）、2代表出库完成';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_warehousenumber" IS '仓库编号';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_ispreout" IS '是否预出库，0-否， 1-是，如果是预出库，则数据返回生产准备区';
COMMENT ON COLUMN "public"."outwarehouse_detailbill"."outw_prebillnumber" IS '预出库表单号';
COMMENT ON TABLE "public"."outwarehouse_detailbill" IS '物料出库信息明细表，需要做CRUD功能';


-- ----------------------------
-- Table structure for productionline_billdetailinfo
-- ----------------------------
DROP TABLE IF EXISTS "public"."productionline_billdetailinfo";
CREATE TABLE "public"."productionline_billdetailinfo" (
  "billd_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "billd_suiid" varchar(50) COLLATE "pg_catalog"."default",
  "billd_suiuniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "billd_materialmodel" varchar(30) COLLATE "pg_catalog"."default",
  "billd_materialcode" varchar(40) COLLATE "pg_catalog"."default",
  "billd_materialuniquecode" varchar(40) COLLATE "pg_catalog"."default",
  "billd_materialmanufacturer" varchar(50) COLLATE "pg_catalog"."default",
  "billd_materialunit" varchar(10) COLLATE "pg_catalog"."default",
  "billd_materialbatch" varchar(20) COLLATE "pg_catalog"."default",
  "billd_materialeffectivedate" date,
  "billd_materialproduceddate" date,
  "billd_materialamount" int2,
  "billd_isreimbursement" int2,
  "billd_cabinindex" int2,
  "billd_bindtime" date,
  "billd_billscategory" int2 NOT NULL,
  "billd_billsnumber" varchar(40) COLLATE "pg_catalog"."default",
  "billd_processcode" varchar(40) COLLATE "pg_catalog"."default",
  "billd_currentpath" int2,
  "billd_currentsite" int2,
  "billd_targetsite" int2,
  "billd_targetpath" int2,
  "billd_bulksuiuniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "billd_isbulkmaterial" int2,
  "billd_fromsource" int2,
  "billd_description" varchar(200) COLLATE "pg_catalog"."default",
  "billd_status" int2
)
;
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_id" IS '周转箱料仓ID';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_suiid" IS '料仓关联周转箱ID';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_suiuniquecode" IS '周转箱唯一码';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialmodel" IS '物料规格型号';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialcode" IS '物料编码';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialuniquecode" IS '物料唯一码';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialmanufacturer" IS '物料厂家';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialunit" IS '物料计量单位（颗）';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialbatch" IS '物料批次';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialeffectivedate" IS '有效日期';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialproduceddate" IS '生产日期';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_materialamount" IS '物料数量';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_isreimbursement" IS '是否退料入库（0来料入库、1物料分解后余料入库、2产线退库入料）';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_cabinindex" IS '仓位索引';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_bindtime" IS '到达时间';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_billscategory" IS '单据分类（1来料入库单、2BOM出料余料退库入库单、3产线退料入库单、3BOM物料派送单）';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_billsnumber" IS 'Bom编号';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_targetsite" IS '目标站点';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_targetpath" IS '目标路径';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_bulksuiuniquecode" IS '如果是散料时，散料对应的包装盒的唯一码';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_isbulkmaterial" IS '是否为散料';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_fromsource" IS '数据来源字段，用于标明数据从那个地方来。
0-排码区
1-库存
2-生产准备区
3-生产区';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_description" IS '描述信息';
COMMENT ON COLUMN "public"."productionline_billdetailinfo"."billd_status" IS '状态，0-为生产，1-正生产';
COMMENT ON TABLE "public"."productionline_billdetailinfo" IS '生产区固定货架的物料信息，查看功能。。。产线区。属于业务数据。';

-- ----------------------------
-- Table structure for productionline_billinfo
-- ----------------------------
DROP TABLE IF EXISTS "public"."productionline_billinfo";
CREATE TABLE "public"."productionline_billinfo" (
  "bill_id" varchar(40) COLLATE "pg_catalog"."default",
  "bill_number" varchar(40) COLLATE "pg_catalog"."default",
  "bill_arrivaltime" date,
  "bill_status" int2,
  "bill_site" int2,
  "bill_category" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."productionline_billinfo"."bill_id" IS '出库总单ID';
COMMENT ON COLUMN "public"."productionline_billinfo"."bill_number" IS '单据编号';
COMMENT ON COLUMN "public"."productionline_billinfo"."bill_arrivaltime" IS '送达时间。';
COMMENT ON COLUMN "public"."productionline_billinfo"."bill_status" IS '表单执行状态。0-未执行，1-正执行';
COMMENT ON COLUMN "public"."productionline_billinfo"."bill_site" IS '所在站点';
COMMENT ON TABLE "public"."productionline_billinfo" IS '固定货架的执行表单信息，根据送达的物料生成，需要做CRUD功能。';

-- ----------------------------
-- Table structure for productionline_process
-- ----------------------------
DROP TABLE IF EXISTS "public"."productionline_process";
CREATE TABLE "public"."productionline_process" (
  "pro_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "pro_name" varchar(50) COLLATE "pg_catalog"."default",
  "pro_description" varchar(200) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."productionline_process"."pro_id" IS '工序id，使用 uuid产生';
COMMENT ON COLUMN "public"."productionline_process"."pro_name" IS '工序名称';
COMMENT ON COLUMN "public"."productionline_process"."pro_description" IS '工序说明';
COMMENT ON TABLE "public"."productionline_process" IS '工序表';

-- ----------------------------
-- Table structure for productionline_processflow
-- ----------------------------
DROP TABLE IF EXISTS "public"."productionline_processflow";
CREATE TABLE "public"."productionline_processflow" (
  "prof_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "prof_suiuniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "prof_wipuniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "prof_currentprocessnum" varchar(40) COLLATE "pg_catalog"."default",
  "prof_currentprocessdesc" varchar(200) COLLATE "pg_catalog"."default",
  "prof_preprocessnum" varchar(40) COLLATE "pg_catalog"."default",
  "prof_preprocessdesc" varchar(200) COLLATE "pg_catalog"."default",
  "prof_nextprocessnum" varchar(40) COLLATE "pg_catalog"."default",
  "prof_nextprocessdesc" varchar(200) COLLATE "pg_catalog"."default",
  "prof_isfinishedproduct" int2,
  "prof_productnum" varchar(40) COLLATE "pg_catalog"."default",
  "prof_ordernum" varchar(40) COLLATE "pg_catalog"."default",
  "prof_amount" int2,
  "prof_cabinindex" int2,
  "prof_processstatus" int2
)
;
COMMENT ON COLUMN "public"."productionline_processflow"."prof_id" IS '工序流转表的主键 id，使用 uuid产生';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_suiuniquecode" IS '周转箱唯一码';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_wipuniquecode" IS '在制品唯一码； wip：在制品（Work In Process）。';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_currentprocessnum" IS '当前工序编号(currentpronum:currentprocessnumber)';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_currentprocessdesc" IS '当前工序说明';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_preprocessnum" IS '上道工序编号;   上道工序 :previous process';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_preprocessdesc" IS '上道工序说明';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_nextprocessnum" IS '下道工序编号;   下道工序 :next process';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_nextprocessdesc" IS '下道工序说明';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_isfinishedproduct" IS '是否为成品';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_productnum" IS '产品编号';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_ordernum" IS '订单编号';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_amount" IS '数量';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_cabinindex" IS '仓位索引';
COMMENT ON COLUMN "public"."productionline_processflow"."prof_processstatus" IS '工序状态';
COMMENT ON TABLE "public"."productionline_processflow" IS '工序流转';

-- ----------------------------
-- Table structure for productionline_processproduct
-- ----------------------------
DROP TABLE IF EXISTS "public"."productionline_processproduct";
CREATE TABLE "public"."productionline_processproduct" (
  "prop_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "prop_ordernum" varchar(50) COLLATE "pg_catalog"."default",
  "prop_productnum" varchar(50) COLLATE "pg_catalog"."default",
  "prop_processnum" varchar(50) COLLATE "pg_catalog"."default",
  "prop_processname" varchar(50) COLLATE "pg_catalog"."default",
  "prop_priority" int2,
  "prop_iscomplete" int2
)
;
COMMENT ON COLUMN "public"."productionline_processproduct"."prop_id" IS '工序产品表的主键 id，使用 uuid产生';
COMMENT ON COLUMN "public"."productionline_processproduct"."prop_ordernum" IS '订单编号';
COMMENT ON COLUMN "public"."productionline_processproduct"."prop_productnum" IS '产品编号';
COMMENT ON COLUMN "public"."productionline_processproduct"."prop_processnum" IS '工序编号';
COMMENT ON COLUMN "public"."productionline_processproduct"."prop_processname" IS '工序名称';
COMMENT ON COLUMN "public"."productionline_processproduct"."prop_priority" IS '优先级';
COMMENT ON COLUMN "public"."productionline_processproduct"."prop_iscomplete" IS '是否完成';
COMMENT ON TABLE "public"."productionline_processproduct" IS '工序产品';

-- ----------------------------
-- Table structure for staticdata_translate
-- ----------------------------
DROP TABLE IF EXISTS "public"."staticdata_translate";
CREATE TABLE "public"."staticdata_translate" (
  "tra_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "tra_appcategory" int2,
  "tra_rawvalue" int2,
  "tra_translatevalue" varchar(50) COLLATE "pg_catalog"."default",
  "tra_ownerid" varchar(40) COLLATE "pg_catalog"."default",
  "tra_ownername" varchar(10) COLLATE "pg_catalog"."default",
  "tra_appdescription" varchar(200) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."staticdata_translate"."tra_id" IS '翻译ID ';
COMMENT ON COLUMN "public"."staticdata_translate"."tra_appcategory" IS '应用分类';
COMMENT ON COLUMN "public"."staticdata_translate"."tra_rawvalue" IS '原始值';
COMMENT ON COLUMN "public"."staticdata_translate"."tra_translatevalue" IS '翻译值';
COMMENT ON COLUMN "public"."staticdata_translate"."tra_ownerid" IS '归属ID';
COMMENT ON COLUMN "public"."staticdata_translate"."tra_ownername" IS '翻译归属名称';
COMMENT ON COLUMN "public"."staticdata_translate"."tra_appdescription" IS '应用描述';
COMMENT ON TABLE "public"."staticdata_translate" IS '静态数据翻译表，基础数据表，需要做增删改查功能。';

-- ----------------------------
-- Records of staticdata_translate
-- ----------------------------
INSERT INTO "public"."staticdata_translate" VALUES ('39d2766e-a382-4dd5-9887-024b1bb8de35', 21, 3, 'test', '39d2766e-a382-4dd5-9887-024b1bb8de35', 'test123', NULL);

-- ----------------------------
-- Table structure for suitcase_appcategory
-- ----------------------------
DROP TABLE IF EXISTS "public"."suitcase_appcategory";
CREATE TABLE "public"."suitcase_appcategory" (
  "app_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "app_name" varchar(64) COLLATE "pg_catalog"."default",
  "app_category" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."suitcase_appcategory"."app_id" IS '字典编号';
COMMENT ON COLUMN "public"."suitcase_appcategory"."app_name" IS '字典名称';
COMMENT ON COLUMN "public"."suitcase_appcategory"."app_category" IS '应用分类';
COMMENT ON TABLE "public"."suitcase_appcategory" IS '周转箱应用分类字典表，基础数据表，需要做增删改查页面';

-- ----------------------------
-- Table structure for suitcase_funccategory
-- ----------------------------
DROP TABLE IF EXISTS "public"."suitcase_funccategory";
CREATE TABLE "public"."suitcase_funccategory" (
  "fun_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "fun_name" varchar(64) COLLATE "pg_catalog"."default",
  "fun_category" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."suitcase_funccategory"."fun_id" IS '字典编号';
COMMENT ON COLUMN "public"."suitcase_funccategory"."fun_name" IS '字典名称';
COMMENT ON COLUMN "public"."suitcase_funccategory"."fun_category" IS '功能分类';
COMMENT ON TABLE "public"."suitcase_funccategory" IS '周转箱功能分类字典表，基础数据表，需要做增删改查页面';

-- ----------------------------
-- Table structure for suitcase_profile
-- ----------------------------
DROP TABLE IF EXISTS "public"."suitcase_profile";
CREATE TABLE "public"."suitcase_profile" (
  "pro_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "pro_uniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "pro_cabintotal" int2,
  "pro_funccategory" int2 NOT NULL,
  "pro_appcategory" int2 NOT NULL,
  "pro_description" varchar(200) COLLATE "pg_catalog"."default",
  "pro_status" int2,
  "pro_ispartition" bool,
  "pro_verticalamount" int2,
  "pro_horizontalamount" int2,
  "pro_rfid" varchar(40) COLLATE "pg_catalog"."default",
  "pro_height" int2,
  "pro_width" int2,
  "pro_length" int2,
  "pro_cabinwidth" int2
)
;
COMMENT ON COLUMN "public"."suitcase_profile"."pro_id" IS '周转箱ID';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_uniquecode" IS '周转箱赋的RFID唯一码';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_cabintotal" IS '周转箱内仓位总数';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_funccategory" IS '周转箱功能分类（1表示盘料柜周转箱、2表示抽屉柜周转箱、3表示混合型周转箱）';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_appcategory" IS '周转箱应用分类，描述周转箱的使用范围（1表示配码入库专用周转箱、2、生产准备区与生产区流转周转箱）';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_description" IS '周转箱描述信息';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_status" IS '周转箱当前状态（1表示装有物料状态、2表示空盒状态、3RFID无法识别、4箱子破损）';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_ispartition" IS '是否分区';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_verticalamount" IS '垂直隔板个数';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_horizontalamount" IS '水平隔板个数';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_rfid" IS 'RFID码';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_height" IS '周转箱的高度';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_width" IS '周转箱的宽度';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_length" IS '周转箱的长度';
COMMENT ON COLUMN "public"."suitcase_profile"."pro_cabinwidth" IS '周转箱的仓位厚度';
COMMENT ON TABLE "public"."suitcase_profile" IS '周转箱特征描述数据表，基础数据表，做增删改查页面。';

-- ----------------------------
-- Table structure for suitcase_qr
-- ----------------------------
DROP TABLE IF EXISTS "public"."suitcase_qr";
CREATE TABLE "public"."suitcase_qr" (
  "sqr_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "sqr_rid" varchar(40) COLLATE "pg_catalog"."default",
  "sqr_info" varchar(500) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."suitcase_qr"."sqr_id" IS '主键';
COMMENT ON COLUMN "public"."suitcase_qr"."sqr_rid" IS '外键';
COMMENT ON COLUMN "public"."suitcase_qr"."sqr_info" IS '周装箱唯一码';
COMMENT ON TABLE "public"."suitcase_qr" IS '周转箱唯一码';

-- ----------------------------
-- Table structure for suitcase_qrrule
-- ----------------------------
DROP TABLE IF EXISTS "public"."suitcase_qrrule";
CREATE TABLE "public"."suitcase_qrrule" (
  "sqrr_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "sqrr_number" varchar(10) COLLATE "pg_catalog"."default",
  "sqrr_productmodel" varchar(20) COLLATE "pg_catalog"."default",
  "sqrr_date_time" timestamp(4),
  "sqrr_status" int2
)
;
COMMENT ON COLUMN "public"."suitcase_qrrule"."sqrr_id" IS '主键';
COMMENT ON COLUMN "public"."suitcase_qrrule"."sqrr_number" IS '码编号';
COMMENT ON COLUMN "public"."suitcase_qrrule"."sqrr_productmodel" IS '产品型号';
COMMENT ON COLUMN "public"."suitcase_qrrule"."sqrr_date_time" IS '创建时间';
COMMENT ON COLUMN "public"."suitcase_qrrule"."sqrr_status" IS '码状态：1未打印，2打印';
COMMENT ON TABLE "public"."suitcase_qrrule" IS '周转箱唯一码生成规则';


-- ----------------------------
-- Table structure for suitcaseflow_location
-- ----------------------------
DROP TABLE IF EXISTS "public"."suitcaseflow_location";
CREATE TABLE "public"."suitcaseflow_location" (
  "loc_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "loc_sta_number" int2,
  "loc_isshelf" bool,
  "loc_suitcase_uniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "loc_goodsshelf_number" int2,
  "loc_goodslocation_number" int2
)
;
COMMENT ON COLUMN "public"."suitcaseflow_location"."loc_id" IS '周转箱当前站点ID';
COMMENT ON COLUMN "public"."suitcaseflow_location"."loc_sta_number" IS '物流站点编号';
COMMENT ON COLUMN "public"."suitcaseflow_location"."loc_isshelf" IS '周转箱是否在货架上';
COMMENT ON COLUMN "public"."suitcaseflow_location"."loc_suitcase_uniquecode" IS '周转箱唯一码';
COMMENT ON COLUMN "public"."suitcaseflow_location"."loc_goodsshelf_number" IS '周转箱所在货架编号';
COMMENT ON COLUMN "public"."suitcaseflow_location"."loc_goodslocation_number" IS '周转箱所在货位编号';
COMMENT ON TABLE "public"."suitcaseflow_location" IS '周转箱当前到达的物流站点（每个周转箱该表中同时只能有一条记录）
周转箱随着流转实时更新站点编号、货架编号、货位编号、以及是否在货架上状态标记，
基础数据，不需要做CRUD';

-- ----------------------------
-- Table structure for suitcaseflow_region
-- ----------------------------
DROP TABLE IF EXISTS "public"."suitcaseflow_region";
CREATE TABLE "public"."suitcaseflow_region" (
  "reg_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "reg_sta_id" varchar(40) COLLATE "pg_catalog"."default",
  "reg_sta_number" int2,
  "reg_pro_id" varchar(40) COLLATE "pg_catalog"."default",
  "reg_pro_uniquecode" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."suitcaseflow_region"."reg_id" IS '流转范围ID';
COMMENT ON COLUMN "public"."suitcaseflow_region"."reg_sta_id" IS '物流站点ID';
COMMENT ON COLUMN "public"."suitcaseflow_region"."reg_sta_number" IS '物流站点编号';
COMMENT ON COLUMN "public"."suitcaseflow_region"."reg_pro_id" IS '周转箱ID';
COMMENT ON COLUMN "public"."suitcaseflow_region"."reg_pro_uniquecode" IS '周转箱唯一码';
COMMENT ON TABLE "public"."suitcaseflow_region" IS '周转盒可以流转的物流站点范围，基础数据，不需要做增删改查页面。';

-- ----------------------------
-- Table structure for terminal_appcategory
-- ----------------------------
DROP TABLE IF EXISTS "public"."terminal_appcategory";
CREATE TABLE "public"."terminal_appcategory" (
  "app_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "app_name" varchar(64) COLLATE "pg_catalog"."default",
  "app_category" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."terminal_appcategory"."app_id" IS '字典编号';
COMMENT ON COLUMN "public"."terminal_appcategory"."app_name" IS '字典名称';
COMMENT ON COLUMN "public"."terminal_appcategory"."app_category" IS '应用分类';
COMMENT ON TABLE "public"."terminal_appcategory" IS '基础设备应用分类字典表，也是基础数据，也需要CRUD页面。';

-- ----------------------------
-- Table structure for terminal_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."terminal_category";
CREATE TABLE "public"."terminal_category" (
  "cat_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "cat_model" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "cat_name" varchar(100) COLLATE "pg_catalog"."default",
  "cat_funccategory" int2 NOT NULL,
  "cat_appcategory" int2 NOT NULL,
  "cat_description" varchar(100) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."terminal_category"."cat_id" IS '设备ID';
COMMENT ON COLUMN "public"."terminal_category"."cat_model" IS '设备型号';
COMMENT ON COLUMN "public"."terminal_category"."cat_name" IS '名称';
COMMENT ON COLUMN "public"."terminal_category"."cat_funccategory" IS '设备功能分类（卷料柜、抽屉柜、锡膏柜、立式柜、物料交换机、机器人、批量单元、多功能激光AGV、三轴搬用机器人、磁导航普通AGV、移动货架、固定货架、接驳平台）';
COMMENT ON COLUMN "public"."terminal_category"."cat_appcategory" IS '设备应用分类';
COMMENT ON COLUMN "public"."terminal_category"."cat_description" IS '描述信息';
COMMENT ON TABLE "public"."terminal_category" IS '终端分类，基础数据表，也需要CRUD页面。';

-- ----------------------------
-- Table structure for terminal_equipment
-- ----------------------------
DROP TABLE IF EXISTS "public"."terminal_equipment";
CREATE TABLE "public"."terminal_equipment" (
  "equ_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "equ_cat_id" varchar(40) COLLATE "pg_catalog"."default",
  "equ_sta_id" varchar(40) COLLATE "pg_catalog"."default",
  "equ_uniquecode" varchar(50) COLLATE "pg_catalog"."default",
  "equ_number" int4,
  "equ_currentstatus" int2,
  "equ_ip" varchar(20) COLLATE "pg_catalog"."default",
  "equ_port" int4,
  "equ_description" varchar(100) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."terminal_equipment"."equ_id" IS '设备ID';
COMMENT ON COLUMN "public"."terminal_equipment"."equ_cat_id" IS '设备分类ID';
COMMENT ON COLUMN "public"."terminal_equipment"."equ_sta_id" IS '物流站点ID';
COMMENT ON COLUMN "public"."terminal_equipment"."equ_uniquecode" IS '设备唯一码';
COMMENT ON COLUMN "public"."terminal_equipment"."equ_number" IS '终端设备分类编号用四位数据表示（前两位表示产品分类、后两位表示具体的设备数量编号）';
COMMENT ON COLUMN "public"."terminal_equipment"."equ_currentstatus" IS '设备状态（0停机、1正常、2报警、3故障）';
COMMENT ON COLUMN "public"."terminal_equipment"."equ_ip" IS 'IP地址';
COMMENT ON COLUMN "public"."terminal_equipment"."equ_port" IS '端口';
COMMENT ON COLUMN "public"."terminal_equipment"."equ_description" IS '描述信息';
COMMENT ON TABLE "public"."terminal_equipment" IS '终端设备表，基础数据表，需要做CRUD页面。';

-- ----------------------------
-- Table structure for terminal_funccategory
-- ----------------------------
DROP TABLE IF EXISTS "public"."terminal_funccategory";
CREATE TABLE "public"."terminal_funccategory" (
  "fun_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "fun_name" varchar(64) COLLATE "pg_catalog"."default",
  "fun_category" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."terminal_funccategory"."fun_id" IS '字典编号';
COMMENT ON COLUMN "public"."terminal_funccategory"."fun_name" IS '字典名称';
COMMENT ON COLUMN "public"."terminal_funccategory"."fun_category" IS '功能分类';
COMMENT ON TABLE "public"."terminal_funccategory" IS '基础设备功能分类字典表，基础数据，需要CRUD页面。';

-- ----------------------------
-- View structure for vw_app_sys_menu
-- ----------------------------
DROP VIEW IF EXISTS "public"."vw_app_sys_menu";
CREATE VIEW "public"."vw_app_sys_menu" AS  SELECT menu.menu_url,
    menu.menu_id,
    menu.menu_icon,
    menu.menu_level,
    menu.menu_remark,
    menu.menu_ownerid,
    sysmenu.app_sys_code,
    sysmenu.app_sysmenu_access_address,
        CASE
            WHEN (sysmenu.app_sysmenu_menu_description IS NULL) THEN menu.menu_description
            ELSE sysmenu.app_sysmenu_menu_description
        END AS menu_description,
    sysmenu.app_sysmenu_sort
   FROM (app_menu menu
     LEFT JOIN app_sys_menu sysmenu ON (((sysmenu.app_menu_id)::text = (menu.menu_id)::text)));

-- ----------------------------
-- View structure for vw_goodslocation_axisdictionary
-- ----------------------------
DROP VIEW IF EXISTS "public"."vw_goodslocation_axisdictionary";
CREATE VIEW "public"."vw_goodslocation_axisdictionary" AS  SELECT axisd.axisd_id,
    axisd.axisd_equ_id,
    axisd.axisd_number,
    axisd.axisd_status,
    axisd.axisd_rank,
    axisd.axisd_tier,
    axisd.axisd_column,
    axisd.axisd_x,
    axisd.axisd_y,
    axisd.axisd_z,
    axisd.axisd_suitcaseuniquecode,
    axisd.axisd_suitcasetype,
    axisd.axisd_isautoload,
    equ.equ_uniquecode,
    fun.fun_name
   FROM ((goodslocation_axisdictionary axisd
     LEFT JOIN terminal_equipment equ ON (((axisd.axisd_equ_id)::text = (equ.equ_id)::text)))
     LEFT JOIN suitcase_funccategory fun ON ((axisd.axisd_suitcasetype = fun.fun_category)));

-- ----------------------------
-- View structure for vw_inwarehouse_bill
-- ----------------------------
DROP VIEW IF EXISTS "public"."vw_inwarehouse_bill";
CREATE VIEW "public"."vw_inwarehouse_bill" AS  SELECT inw.inw_id,
    inw.inw_billsnumber,
    inw.inw_category,
    inw.inw_intime,
    inw.inw_status,
    cat.cat_name
   FROM (inwarehouse_bill inw
     LEFT JOIN bills_category cat ON ((inw.inw_category = cat.cat_category)));

-- ----------------------------
-- View structure for vw_outwarehouse_bill
-- ----------------------------
DROP VIEW IF EXISTS "public"."vw_outwarehouse_bill";
CREATE VIEW "public"."vw_outwarehouse_bill" AS  SELECT outw.outw_id,
    outw.outw_billsnumber,
    outw.outw_category,
    outw.outw_outtime,
    outw.outw_status,
    cat.cat_name
   FROM (outwarehouse_bill outw
     LEFT JOIN bills_category cat ON ((outw.outw_category = cat.cat_category)));

-- ----------------------------
-- View structure for vw_suitcase_profile
-- ----------------------------
DROP VIEW IF EXISTS "public"."vw_suitcase_profile";
CREATE VIEW "public"."vw_suitcase_profile" AS  SELECT pro.pro_id,
    pro.pro_uniquecode,
    pro.pro_rfid,
    pro.pro_cabintotal,
    pro.pro_appcategory,
    pro.pro_funccategory,
    pro.pro_status,
    pro.pro_ispartition,
    pro.pro_verticalamount,
    pro.pro_horizontalamount,
    pro.pro_height,
    pro.pro_width,
    pro.pro_length,
    pro.pro_cabinwidth,
    fun.fun_name,
    app.app_name
   FROM ((suitcase_profile pro
     LEFT JOIN suitcase_appcategory app ON ((pro.pro_appcategory = app.app_category)))
     LEFT JOIN suitcase_funccategory fun ON ((pro.pro_funccategory = fun.fun_category)));

-- ----------------------------
-- View structure for vw_terminal_category
-- ----------------------------
DROP VIEW IF EXISTS "public"."vw_terminal_category";
CREATE VIEW "public"."vw_terminal_category" AS  SELECT cat.cat_id,
    cat.cat_model,
    cat.cat_name,
    cat.cat_appcategory,
    cat.cat_funccategory,
    cat.cat_description,
    app.app_name,
    fun.fun_name
   FROM ((terminal_category cat
     LEFT JOIN terminal_appcategory app ON ((cat.cat_appcategory = app.app_category)))
     LEFT JOIN terminal_funccategory fun ON ((cat.cat_funccategory = fun.fun_category)));

-- ----------------------------
-- View structure for vw_terminal_equipment
-- ----------------------------
DROP VIEW IF EXISTS "public"."vw_terminal_equipment";
CREATE VIEW "public"."vw_terminal_equipment" AS  SELECT equ.equ_id,
    equ.equ_cat_id,
    equ.equ_sta_id,
    equ.equ_uniquecode,
    equ.equ_number,
    equ.equ_currentstatus,
    equ.equ_ip,
    equ.equ_port,
    equ.equ_description,
    cat.cat_name,
    sta.sta_name
   FROM ((terminal_equipment equ
     LEFT JOIN terminal_category cat ON (((equ.equ_cat_id)::text = (cat.cat_id)::text)))
     LEFT JOIN materialflow_station sta ON (((equ.equ_sta_id)::text = (sta.sta_id)::text)));

-- ----------------------------
-- View structure for vw_cabin_detailinfo
-- ----------------------------
DROP VIEW IF EXISTS "public"."vw_cabin_detailinfo";
CREATE VIEW "public"."vw_cabin_detailinfo" AS  SELECT det.det_suiuniquecode,
    det.det_billsnumber,
    string_agg((det.det_materialuniquecode)::text, ','::text) AS det_materialuniquecode,
    min(loc.loc_goodsshelf_number) AS loc_goodsshelf_number,
    min(loc.loc_goodslocation_number) AS loc_goodslocation_number,
    det.det_targetsite,
    det.det_isreimbursement
   FROM (cabin_detailinfo det
     LEFT JOIN suitcaseflow_location loc ON (((det.det_suiuniquecode)::text = (loc.loc_suitcase_uniquecode)::text)))
  WHERE (det.det_isreimbursement = 0)
  GROUP BY det.det_suiuniquecode, det.det_billsnumber, det.det_targetsite, det.det_isreimbursement;

-- ----------------------------
-- Primary Key structure for table app_menu
-- ----------------------------
ALTER TABLE "public"."app_menu" ADD CONSTRAINT "pk_app_menu" PRIMARY KEY ("menu_id");

-- ----------------------------
-- Uniques structure for table app_sys
-- ----------------------------
ALTER TABLE "public"."app_sys" ADD CONSTRAINT "ipsp_sys_unique_code" UNIQUE ("sys_code");
COMMENT ON CONSTRAINT "ipsp_sys_unique_code" ON "public"."app_sys" IS '应用代码唯一';

-- ----------------------------
-- Primary Key structure for table app_sys
-- ----------------------------
ALTER TABLE "public"."app_sys" ADD CONSTRAINT "app_sys_pkey" PRIMARY KEY ("sys_id");

-- ----------------------------
-- Indexes structure for table app_sys_menu
-- ----------------------------
CREATE INDEX "ipsp_menu_sys_idx_menuid" ON "public"."app_sys_menu" USING btree (
  "app_menu_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "ipsp_menu_sys_idx_sort" ON "public"."app_sys_menu" USING btree (
  "app_sysmenu_sort" "pg_catalog"."int2_ops" ASC NULLS LAST
);
CREATE INDEX "ipsp_menu_sys_idx_syscode" ON "public"."app_sys_menu" USING btree (
  "app_sys_code" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table app_sys_menu
-- ----------------------------
ALTER TABLE "public"."app_sys_menu" ADD CONSTRAINT "app_sys_menu_pkey" PRIMARY KEY ("app_sys_menu_id");

-- ----------------------------
-- Primary Key structure for table bills_category
-- ----------------------------
ALTER TABLE "public"."bills_category" ADD CONSTRAINT "pk_bills_category" PRIMARY KEY ("cat_id");

-- ----------------------------
-- Primary Key structure for table cabin_detailinfo
-- ----------------------------
ALTER TABLE "public"."cabin_detailinfo" ADD CONSTRAINT "pk_materialdetail" PRIMARY KEY ("det_id");

-- ----------------------------
-- Primary Key structure for table goodslocation_axisdictionary
-- ----------------------------
ALTER TABLE "public"."goodslocation_axisdictionary" ADD CONSTRAINT "pk_goodslocation_axisdictionar" PRIMARY KEY ("axisd_id");

-- ----------------------------
-- Primary Key structure for table inwarehouse_bill
-- ----------------------------
ALTER TABLE "public"."inwarehouse_bill" ADD CONSTRAINT "pk_inwarehouse_bill" PRIMARY KEY ("inw_id");

-- ----------------------------
-- Primary Key structure for table inwarehouse_detailbill
-- ----------------------------
ALTER TABLE "public"."inwarehouse_detailbill" ADD CONSTRAINT "pk_inwarehouse_detailbill" PRIMARY KEY ("inw_id");

-- ----------------------------
-- Primary Key structure for table inwarehouse_detailtask
-- ----------------------------
ALTER TABLE "public"."inwarehouse_detailtask" ADD CONSTRAINT "inwarehouse_detailtask_pkey" PRIMARY KEY ("taskdet_id");

-- ----------------------------
-- Primary Key structure for table inwarehouse_task
-- ----------------------------
ALTER TABLE "public"."inwarehouse_task" ADD CONSTRAINT "inwarehous_task_pkey" PRIMARY KEY ("task_id");

-- ----------------------------
-- Primary Key structure for table material_category
-- ----------------------------
ALTER TABLE "public"."material_category" ADD CONSTRAINT "material_category_pkey" PRIMARY KEY ("mcat_id");

-- ----------------------------
-- Primary Key structure for table material_qr
-- ----------------------------
ALTER TABLE "public"."material_qr" ADD CONSTRAINT "material_qr_pkey" PRIMARY KEY ("mqr_id");

-- ----------------------------
-- Primary Key structure for table material_qrrule
-- ----------------------------
ALTER TABLE "public"."material_qrrule" ADD CONSTRAINT "material_qrrule_pkey" PRIMARY KEY ("mqrr_id");

-- ----------------------------
-- Primary Key structure for table materialflow_station
-- ----------------------------
ALTER TABLE "public"."materialflow_station" ADD CONSTRAINT "pk_materialflow_station" PRIMARY KEY ("sta_id");

-- ----------------------------
-- Primary Key structure for table mfagv_axisdictionary
-- ----------------------------
ALTER TABLE "public"."mfagv_axisdictionary" ADD CONSTRAINT "pk_mfagv_axisdictionary" PRIMARY KEY ("axisd_id");

-- ----------------------------
-- Primary Key structure for table mfagv_goodslocation
-- ----------------------------
ALTER TABLE "public"."mfagv_goodslocation" ADD CONSTRAINT "pk_mfagv_goodslocation" PRIMARY KEY ("gsl_id");

-- ----------------------------
-- Primary Key structure for table moveshelf_location
-- ----------------------------
ALTER TABLE "public"."moveshelf_location" ADD CONSTRAINT "pk_moveshelf_location" PRIMARY KEY ("msl_id");

-- ----------------------------
-- Primary Key structure for table outwarehouse_bill
-- ----------------------------
ALTER TABLE "public"."outwarehouse_bill" ADD CONSTRAINT "pk_outwarehouse_bill" PRIMARY KEY ("outw_id");

-- ----------------------------
-- Primary Key structure for table outwarehouse_detailbill
-- ----------------------------
ALTER TABLE "public"."outwarehouse_detailbill" ADD CONSTRAINT "pk_outwarehouse_detailbill" PRIMARY KEY ("outw_id");

-- ----------------------------
-- Primary Key structure for table productionline_billdetailinfo
-- ----------------------------
ALTER TABLE "public"."productionline_billdetailinfo" ADD CONSTRAINT "pk_fixshelf_detailinfo" PRIMARY KEY ("billd_id");

-- ----------------------------
-- Primary Key structure for table productionline_process
-- ----------------------------
ALTER TABLE "public"."productionline_process" ADD CONSTRAINT "productionline_process_pkey" PRIMARY KEY ("pro_id");

-- ----------------------------
-- Primary Key structure for table productionline_processflow
-- ----------------------------
ALTER TABLE "public"."productionline_processflow" ADD CONSTRAINT "productionline_processflow_pkey" PRIMARY KEY ("prof_id");

-- ----------------------------
-- Primary Key structure for table productionline_processproduct
-- ----------------------------
ALTER TABLE "public"."productionline_processproduct" ADD CONSTRAINT "productionline_processproduct_pkey" PRIMARY KEY ("prop_id");

-- ----------------------------
-- Primary Key structure for table staticdata_translate
-- ----------------------------
ALTER TABLE "public"."staticdata_translate" ADD CONSTRAINT "pk_statictranslate" PRIMARY KEY ("tra_id");

-- ----------------------------
-- Primary Key structure for table suitcase_appcategory
-- ----------------------------
ALTER TABLE "public"."suitcase_appcategory" ADD CONSTRAINT "pk_suitcase_appcategory" PRIMARY KEY ("app_id");

-- ----------------------------
-- Primary Key structure for table suitcase_funccategory
-- ----------------------------
ALTER TABLE "public"."suitcase_funccategory" ADD CONSTRAINT "pk_suitcase_funccategory" PRIMARY KEY ("fun_id");

-- ----------------------------
-- Primary Key structure for table suitcase_profile
-- ----------------------------
ALTER TABLE "public"."suitcase_profile" ADD CONSTRAINT "pk_circulationcase" PRIMARY KEY ("pro_id");

-- ----------------------------
-- Primary Key structure for table suitcase_qr
-- ----------------------------
ALTER TABLE "public"."suitcase_qr" ADD CONSTRAINT "suitcase_qr_pkey" PRIMARY KEY ("sqr_id");

-- ----------------------------
-- Primary Key structure for table suitcase_qrrule
-- ----------------------------
ALTER TABLE "public"."suitcase_qrrule" ADD CONSTRAINT "suitcase_qrrule_pkey" PRIMARY KEY ("sqrr_id");

-- ----------------------------
-- Primary Key structure for table suitcaseflow_location
-- ----------------------------
ALTER TABLE "public"."suitcaseflow_location" ADD CONSTRAINT "pk_suitcaseflow_location" PRIMARY KEY ("loc_id");

-- ----------------------------
-- Primary Key structure for table suitcaseflow_region
-- ----------------------------
ALTER TABLE "public"."suitcaseflow_region" ADD CONSTRAINT "pk_suitcaseflow_region" PRIMARY KEY ("reg_id");

-- ----------------------------
-- Primary Key structure for table terminal_appcategory
-- ----------------------------
ALTER TABLE "public"."terminal_appcategory" ADD CONSTRAINT "pk_terminal_appcategory" PRIMARY KEY ("app_id");

-- ----------------------------
-- Primary Key structure for table terminal_category
-- ----------------------------
ALTER TABLE "public"."terminal_category" ADD CONSTRAINT "pk_category" PRIMARY KEY ("cat_id");

-- ----------------------------
-- Primary Key structure for table terminal_equipment
-- ----------------------------
ALTER TABLE "public"."terminal_equipment" ADD CONSTRAINT "pk_equipment" PRIMARY KEY ("equ_id");

-- ----------------------------
-- Primary Key structure for table terminal_funccategory
-- ----------------------------
ALTER TABLE "public"."terminal_funccategory" ADD CONSTRAINT "pk_terminal_funccategory" PRIMARY KEY ("fun_id");

-- ----------------------------
-- Foreign Keys structure for table cabin_detailinfo
-- ----------------------------
ALTER TABLE "public"."cabin_detailinfo" ADD CONSTRAINT "fk_cabindetail_suitcase" FOREIGN KEY ("det_suiid") REFERENCES "public"."suitcase_profile" ("pro_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table goodslocation_axisdictionary
-- ----------------------------
ALTER TABLE "public"."goodslocation_axisdictionary" ADD CONSTRAINT "fk_goodsloc_reference_terminal" FOREIGN KEY ("axisd_equ_id") REFERENCES "public"."terminal_equipment" ("equ_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table material_qrrule
-- ----------------------------
ALTER TABLE "public"."material_qrrule" ADD CONSTRAINT "mqrr_cid" FOREIGN KEY ("mqrr_cid") REFERENCES "public"."material_category" ("mcat_id") ON DELETE SET NULL ON UPDATE SET NULL;

-- ----------------------------
-- Foreign Keys structure for table mfagv_goodslocation
-- ----------------------------
ALTER TABLE "public"."mfagv_goodslocation" ADD CONSTRAINT "fk_mfagv_go_reference_mfagv_ax" FOREIGN KEY ("gsl_axisid") REFERENCES "public"."mfagv_axisdictionary" ("axisd_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table suitcase_qr
-- ----------------------------
ALTER TABLE "public"."suitcase_qr" ADD CONSTRAINT "sqr_rid" FOREIGN KEY ("sqr_rid") REFERENCES "public"."suitcase_qrrule" ("sqrr_id") ON DELETE SET NULL ON UPDATE SET NULL;

-- ----------------------------
-- Foreign Keys structure for table suitcaseflow_region
-- ----------------------------
ALTER TABLE "public"."suitcaseflow_region" ADD CONSTRAINT "fk_suitcase_reference_material" FOREIGN KEY ("reg_sta_id") REFERENCES "public"."materialflow_station" ("sta_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."suitcaseflow_region" ADD CONSTRAINT "fk_suitcase_reference_suitcase" FOREIGN KEY ("reg_pro_id") REFERENCES "public"."suitcase_profile" ("pro_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table terminal_equipment
-- ----------------------------
ALTER TABLE "public"."terminal_equipment" ADD CONSTRAINT "fk_terminal_reference_material" FOREIGN KEY ("equ_sta_id") REFERENCES "public"."materialflow_station" ("sta_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."terminal_equipment" ADD CONSTRAINT "fk_terminal_reference_terminal" FOREIGN KEY ("equ_cat_id") REFERENCES "public"."terminal_category" ("cat_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
