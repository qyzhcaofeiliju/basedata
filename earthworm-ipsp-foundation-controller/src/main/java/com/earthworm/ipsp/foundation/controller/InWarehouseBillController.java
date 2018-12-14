package com.earthworm.ipsp.foundation.controller;


import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.BillsCategory;
import com.earthworm.ipsp.foundation.entity.InWarehouseBill;
import com.earthworm.ipsp.foundation.entity.InWarehouseDetailBill;
import com.earthworm.ipsp.foundation.entity.VwInWarehouseBill;
import com.earthworm.ipsp.foundation.service.interf.IBillsCategoryService;
import com.earthworm.ipsp.foundation.service.interf.IInWarehouseBillService;
import com.earthworm.ipsp.foundation.service.interf.IInWarehouseDetailBillService;
import com.earthworm.ipsp.foundation.service.interf.IVwInWarehouseBillService;
import com.earthworm.postgres.helper.PageHelper;
import com.earthworm.postgres.page.Page;
import com.earthworm.postgres.validate.ValidateHelper;
import com.earthworm.utils.fastjson.FastJsonUtil;
import com.earthworm.utils.poi.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inWarehouseBill")
public class InWarehouseBillController {
    @Autowired
    private IInWarehouseBillService inWarehouseBillService;
    @Autowired
    private IVwInWarehouseBillService vwInWarehouseBillService;
    @Autowired
    private IBillsCategoryService billsCategoryService;
    @Autowired
    private IInWarehouseDetailBillService inWarehouseDetailBillService;

    private static Logger logger = LoggerFactory.getLogger(InWarehouseBillController.class);

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException {
        try {
            Page<VwInWarehouseBill> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<VwInWarehouseBill> list = vwInWarehouseBillService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "inWarehouseBill/inWarehouseBillView";
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.findAll:Find InWarehouseBill failed!", ex);
            throw ex;
        }
    }

    /**
     * 分页，条件查询
     *
     * @param request
     * @param pageData
     * @param formData
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> jumpPage(HttpServletRequest request, @RequestParam("pageData") String pageData, @RequestParam("formData") String formData) throws EarthWormException {
        try {
            Page page1 = FastJsonUtil.toBean(pageData, Page.class);
            VwInWarehouseBill vwInWarehouseBill = FastJsonUtil.toBean(formData, VwInWarehouseBill.class);
            Page<VwInWarehouseBill> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<VwInWarehouseBill> list = vwInWarehouseBillService.findLikeInfo(vwInWarehouseBill);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.jumpPage:Find InWarehouseBill failed!", ex);
            throw ex;
        }
    }

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete{inwId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("inwId") String id) throws EarthWormException {
        try {
            return inWarehouseBillService.deleteById(id);
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.deleteById:DELETE inWarehouse failed!", ex);
            throw ex;
        }
    }


    @RequestMapping(value = "/update{inwId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("inwId") String id, @RequestParam("data") String str) throws EarthWormException {
        try {
            InWarehouseBill inWarehouseBill = FastJsonUtil.toBean(str, InWarehouseBill.class);
            inWarehouseBill.setInwId(id);
            return inWarehouseBillService.update(inWarehouseBill);
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.update:Update InWarehouseBill failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            InWarehouseBill inWarehouseBill = FastJsonUtil.toBean(str, InWarehouseBill.class);
            Map<String, String> validate = ValidateHelper.validate(inWarehouseBill);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return inWarehouseBillService.save(inWarehouseBill);
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.save:Save InWarehouseBill failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEditPage(HttpServletRequest request) throws EarthWormException {
        try {
            BillsCategory billsCategory = new BillsCategory();
            billsCategory.setCatOwnercategory((short) 1);
            List<BillsCategory> billsCategories = billsCategoryService.find(billsCategory);
            request.setAttribute("billsCategories", billsCategories);
            return "inWarehouseBill/inWarehouseBillEdit";
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.toEditPage:Find InWarehouseBill failed!", ex);
            throw ex;
        }
    }

    // 编辑页面，数据回显
    @RequestMapping("edit/{funId}")
    public String dynamicToAddUpt(@PathVariable("funId") @Nullable String funId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(funId)) {
                InWarehouseBill inWarehouseBill = inWarehouseBillService.findById(funId);
                request.setAttribute("inWarehouseBill", inWarehouseBill);
            }
            BillsCategory billsCategory = new BillsCategory();
            billsCategory.setCatOwnercategory((short) 1);
            List<BillsCategory> billsCategories = billsCategoryService.find(billsCategory);
            request.setAttribute("billsCategories", billsCategories);
            return "inWarehouseBill/inWarehouseBillEdit";
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.dynamicToAddUpt:Find InWarehouseBill failed!", ex);
            throw ex;
        }
    }

    //查询入库单详情
    @RequestMapping("find/{inwBillsnumber}")
    public String find(@PathVariable("inwBillsnumber") @Nullable String inwBillsnumber, HttpServletRequest request) throws EarthWormException {
        try {
            Page<InWarehouseDetailBill> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            InWarehouseDetailBill inWarehouseDetailBill = new InWarehouseDetailBill();
            inWarehouseDetailBill.setInwBillsnumber(inwBillsnumber);
            List<InWarehouseDetailBill> list = inWarehouseDetailBillService.find(inWarehouseDetailBill);
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("inwBillsnumber", inwBillsnumber);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "inWarehouseDetailBill/inWarehouseDetailBillView";
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.find:Find InWarehouseBill failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/doubleSave", method = RequestMethod.POST)
    @ResponseBody
    public int doubleSave(@RequestParam("data") String data) throws EWValidationException, EarthWormException {
        try {
            JSONObject jsonObj = JSON.parseObject(data);
            InWarehouseBill inWarehouseBill = jsonObj.getObject("inw", InWarehouseBill.class);
            InWarehouseDetailBill inWarehouseDetailBill = jsonObj.getObject("inwDetail", InWarehouseDetailBill.class);
            Map<String, String> validate = ValidateHelper.validate(inWarehouseBill);
            Map<String, String> validate1 = ValidateHelper.validate(inWarehouseDetailBill);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            } else {
                if (validate1 != null && !validate1.isEmpty()) {
                    throw new EWValidationException(validate1);
                }
                try {
                    return inWarehouseBillService.save(inWarehouseBill, inWarehouseDetailBill);
                } catch (Exception ex) {
                    logger.error("InWarehouseBillController.doubleSave:Add inWarehouse failed!", ex);
                    throw ex;
                }
            }
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.doubleSave:Save InWarehouseBill failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/doubleUpt", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doubleUpt(@RequestParam("data") String data) throws EarthWormException {
        try {
            JSONObject jsonObj = JSON.parseObject(data);
            InWarehouseBill inWarehouseBill = jsonObj.getObject("inw", InWarehouseBill.class);
            InWarehouseDetailBill inWarehouseDetailBill = jsonObj.getObject("inwDetail", InWarehouseDetailBill.class);
            if ((inWarehouseBill != null || inWarehouseBill.getInwId() != null) && (inWarehouseDetailBill == null || inWarehouseDetailBill.getInwId() == null)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("info", inWarehouseBillService.update(inWarehouseBill));
                VwInWarehouseBill vwInWarehouseBill = vwInWarehouseBillService.findById(inWarehouseBill.getInwId());
                map.put("vwInWarehouseBill", vwInWarehouseBill);
                return map;

            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("info", inWarehouseBillService.update(inWarehouseBill, inWarehouseDetailBill));
                VwInWarehouseBill vwInWarehouseBill = vwInWarehouseBillService.findById(inWarehouseBill.getInwId());
                map.put("vwInWarehouseBill", vwInWarehouseBill);
                return map;
            }
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.doubleUpt:update inWarehouse failed!", ex);
            throw ex;
        }
    }

    /**
     * 验证是否存在
     *
     * @return
     */
    @RequestMapping(value = "/validIsExist", method = RequestMethod.GET)
    @ResponseBody
    public boolean validIsExist(@RequestParam("data") String str) throws EarthWormException {
        try {
            InWarehouseBill inWarehouseBill = FastJsonUtil.toBean(str, InWarehouseBill.class);
            List<InWarehouseBill> list = inWarehouseBillService.find(inWarehouseBill);

            return list == null || list.isEmpty();
        } catch (Exception ex) {
            logger.error("InWarehouseBillController.validIsExist:find inWarehouse failed!", ex);
            throw ex;
        }
    }

    //开始解析excel，并将解析后的数据，返回到页面上
    @RequestMapping(value = "/importExcelList", method = {RequestMethod.POST}, consumes = "multipart/form-data")
    @ResponseBody
    public List<InWarehouseDetailBill> getImportInWareInfo(@RequestParam("file") MultipartFile file, ServletResponse response) {
        List<InWarehouseDetailBill> inWarehouseDetailBill = inWarehouseBillService.prseExcelMethod(file, response);
        List list = new ArrayList();
        for (InWarehouseDetailBill inWareList : inWarehouseDetailBill) {
            String inBnumber = inWareList.getInwBillsnumber();
            list.add(inBnumber);
        }
        return list;
    }

    //数据回显到入库单查看页面上
    @RequestMapping(value = "/edit{inWareDetailInfo}", method = {RequestMethod.GET})
    public String toShowImportInWareInfoPage(@PathVariable("inWareDetailInfo") String inwBillsnumber, HttpServletRequest request) {
        if (inwBillsnumber != null) {
            request.setAttribute("inwBillsnumber", inwBillsnumber);
        }
        BillsCategory billsCategory = new BillsCategory();
        billsCategory.setCatOwnercategory((short) 1);
        List<BillsCategory> billsCategories = billsCategoryService.find(billsCategory);
        request.setAttribute("billsCategories", billsCategories);
        return "importAndExport/importAndExportEdit";
    }


    //查看入库单详细信息
    @RequestMapping(value = "/showDetailInfo", method = {RequestMethod.POST}, consumes = "multipart/form-data")
    public String showSomeInWareInfo(@RequestParam("file") MultipartFile file, ServletResponse response, HttpServletRequest request) {
        //通过入库单的ID，获得入库单，并将根据ID查找到旗下的详情单
        List<InWarehouseDetailBill> inWarehouseDetailBill = inWarehouseBillService.prseExcelMethod(file, response);
        request.setAttribute("list", inWarehouseDetailBill);
        return "importAndExport/inWarehouseDetailInfoView";
    }

    //导出入库单详细信息
    @RequestMapping(value = "/exportFile", method = {RequestMethod.POST})
    @ResponseBody
    public void exportFile(HttpServletResponse response) {
        //查询相应的数据信息记录(数据库内)
        List<InWarehouseDetailBill> dataList = inWarehouseDetailBillService.findAll();
        try {
            response.reset();
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filname=" + URLEncoder.encode("入库详细信息.xlsx", "UTF-8"));
            ExcelUtil.writeFile(ExcelUtil.exportExcel(
                    new ExportParams(), InWarehouseDetailBill.class, dataList), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


    //导入入单信息，并显示
    @RequestMapping(value = "/saveFile", method = {RequestMethod.POST}, consumes = "multipart/form-data")
    @ResponseBody
    public Boolean saveFile(@RequestParam(value = "file") MultipartFile file, @RequestParam String objDate, ServletResponse response) {
        //前台传来入库主表的数据,再解析详情表的信息,最后全部save到数据库中
        InWarehouseBill inWarehouseBill = new InWarehouseBill();
        JSONObject object = JSONObject.parseObject(objDate);
        inWarehouseBill.setInwBillsnumber(object.getString("value1"));
        List<InWarehouseBill> list = inWarehouseBillService.find(inWarehouseBill);
        if (list.size() > 0) {
            return false;
        } else {
            inWarehouseBill.setInwCategory(Short.parseShort(object.getString("value3")));
            inWarehouseBill.setInwStatus(Short.parseShort(object.getString("value4")));
            inWarehouseBill.setInwIntime(object.getDate("value2"));
            inWarehouseBillService.save(inWarehouseBill);


            List<InWarehouseDetailBill> uploadInWareDetail = inWarehouseBillService.prseExcelMethod(file, response);
            if (uploadInWareDetail == null || uploadInWareDetail.size() == 0) {
                return null;
            }
            InWarehouseDetailBill inWarehouseDetailBill = new InWarehouseDetailBill();
            for (InWarehouseDetailBill item : uploadInWareDetail) {
                inWarehouseDetailBill.setInwBillsnumber(item.getInwBillsnumber());
                inWarehouseDetailBill.setInwModel(item.getInwModel());
                inWarehouseDetailBill.setInwCode(item.getInwCode());
                inWarehouseDetailBill.setInwUniquecode(item.getInwUniquecode());
                inWarehouseDetailBill.setInwManufacturer(item.getInwBillsnumber());
                inWarehouseDetailBill.setInwUnit(item.getInwUnit());
                inWarehouseDetailBill.setInwBatch(item.getInwBatch());
                inWarehouseDetailBill.setInwEffectivedate(item.getInwEffectivedate());
                inWarehouseDetailBill.setInwProduceddate(item.getInwProduceddate());
                inWarehouseDetailBill.setInwAmount(item.getInwAmount());
                inWarehouseDetailBill.setInwActualcount(item.getInwActualcount());
                inWarehouseDetailBill.setInwStatus(item.getInwStatus());
                inWarehouseDetailBill.setInwWarehousenumber(item.getInwWarehousenumber());
                inWarehouseDetailBill.setInwRank(item.getInwRank());
                inWarehouseDetailBill.setInwTier(item.getInwTier());
                inWarehouseDetailBill.setInwColumn(item.getInwColumn());
                inWarehouseDetailBill.setInwRangeindex(item.getInwRangeindex());
                inWarehouseDetailBill.setInwIndatetime(item.getInwIndatetime());
                inWarehouseDetailBillService.save(inWarehouseDetailBill);
            }
        }
        return true;
    }
}
