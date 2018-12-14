
/* 删除基础数据中的view视图对象 */
DROP VIEW IF EXISTS "public"."vw_cabin_detailinfo";
DROP VIEW IF EXISTS "public"."vw_goodslocation_axisdictionary";
DROP VIEW IF EXISTS "public"."vw_inwarehouse_bill";
DROP VIEW IF EXISTS "public"."vw_outwarehouse_bill";
DROP VIEW IF EXISTS "public"."vw_suitcase_profile";
DROP VIEW IF EXISTS "public"."vw_terminal_category";
DROP VIEW IF EXISTS "public"."vw_terminal_equipment";

/* 删除基础数据中的表 */

drop table Cabin_DetailInfo;

drop table FixShelf_BillInfo;

drop table FixShelf_DetailInfo;

drop table GoodsLocation_AxisDictionary;

drop table MFAGV_GoodsLocation;

drop table OutWarehouse_DetailBill;

drop table OutWarehouse_Bill;

drop table inwarehouse_detailtask;

drop table inwarehouse_task;

drop table InWarehouse_DetailBill;

drop table InWarehouse_Bill;

drop table MoveShelf_Location;

drop table StaticData_Translate;

drop table SuitcaseFlow_Location;

drop table SuitcaseFlow_Region;

drop table Suitcase_AppCategory;

drop table Suitcase_FuncCategory;

drop table Terminal_AppCategory;

drop table Terminal_FuncCategory;

drop table Terminal_Equipment;

drop table Suitcase_Profile;

drop table MFAGV_AxisDictionary;

drop table MaterialFlow_Station;

drop table Terminal_Category;

drop table Bills_Category;

drop table app_menu;