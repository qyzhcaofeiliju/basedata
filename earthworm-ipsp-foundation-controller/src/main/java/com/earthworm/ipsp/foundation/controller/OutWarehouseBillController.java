package com.earthworm.ipsp.foundation.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.BillsCategory;
import com.earthworm.ipsp.foundation.entity.OutWarehouseBill;
import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;
import com.earthworm.ipsp.foundation.entity.VwOutWarehouseBill;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.IBillsCategoryService;
import com.earthworm.ipsp.foundation.service.interf.IOutWarehouseBillService;
import com.earthworm.ipsp.foundation.service.interf.IOutWarehouseDetailBillService;
import com.earthworm.ipsp.foundation.service.interf.IVwOutWarehouseBillService;
import com.earthworm.postgres.helper.PageHelper;
import com.earthworm.postgres.page.Page;
import com.earthworm.postgres.validate.ValidateHelper;
import com.earthworm.utils.fastjson.FastJsonUtil;
import com.earthworm.utils.poi.ExcelUtil;
import freemarker.template.SimpleDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Sheet;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 9:24
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Controller
@RequestMapping("/outWarehouseBill")
public class OutWarehouseBillController {
    // sl4j
    private static Logger logger = LoggerFactory.getLogger(OutWarehouseBillController.class);

    @Autowired
    private IOutWarehouseBillService outWarehouseBillService;

    @Autowired
    private IVwOutWarehouseBillService vwOutWarehouseBillService;

    @Autowired
    private IBillsCategoryService billsCategoryService;

    @Autowired
    private IOutWarehouseDetailBillService outWarehouseDetailBillService;

    /**
     * 首页，默认显示7条记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<VwOutWarehouseBill> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<VwOutWarehouseBill> list = vwOutWarehouseBillService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "outWarehouseBill/outWarehouseBillView";
        } catch (Exception e) {
            logger.error("OutWarehouseBillController.view():", e);
            throw e;
        } finally {
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
            VwOutWarehouseBill vwOutWarehouseBill = FastJsonUtil.toBean(formData, VwOutWarehouseBill.class);
            Page<VwOutWarehouseBill> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<VwOutWarehouseBill> list = vwOutWarehouseBillService.findLikeInfo(vwOutWarehouseBill);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("OutWarehouseBillController.jumpPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除
     *
     * @param outwId
     * @return
     */
    @RequestMapping(value = "/delete{outwId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("outwId") String outwId) throws EarthWormException {
        try {
            return outWarehouseBillService.deleteById(outwId);
        } catch (IPSPFoundationServiceException e) {
            logger.error("OutWarehouseBillController.deleteById():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/update{outwId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("outwId") String outwId, @RequestParam("data") String str) throws EarthWormException {
        try {
            OutWarehouseBill outWarehouseBill = FastJsonUtil.toBean(str, OutWarehouseBill.class);
            outWarehouseBill.setOutwId(outwId);
            return outWarehouseBillService.update(outWarehouseBill);
        } catch (Exception e) {
            logger.error("OutWarehouseBillController.update():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            OutWarehouseBill outWarehouseBill = FastJsonUtil.toBean(str, OutWarehouseBill.class);
            // 对OutWarehouseBill做后台验证
            backValidate(outWarehouseBill);
            return outWarehouseBillService.save(outWarehouseBill);
        } catch (Exception ex) {
            logger.error("OutWarehouseBillController.doubleSave:Add outWarehouse failed!", ex);
            throw ex;
        }
    }

    /**
     * 后台验证
     *
     * @param object
     */
    private void backValidate(Object object) {
        Map<String, String> validate = ValidateHelper.validate(object);
        if (validate != null && !validate.isEmpty()) {
            throw new EWValidationException(validate);
        }
    }

    /**
     * 嵌入到增加或者修改页面
     *
     * @return
     */
    @GetMapping("/edit")
    public String toEditPage(HttpServletRequest request) throws EarthWormException {
        try {
            selectLabData(request);
            return "outWarehouseBill/outWarehouseBillEdit";
        } catch (Exception e) {
            logger.error("OutWarehouseBillController.toEditPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 编辑页面，数据回显
     *
     * @param outwId
     * @param request
     * @return
     */
    @GetMapping("edit/{outwId}")
    public String dynamicToAddUpt(@PathVariable("outwId") @Nullable String outwId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(outwId)) {
                OutWarehouseBill outWarehouseBill = outWarehouseBillService.findById(outwId);
                request.setAttribute("outWarehouseBill", outWarehouseBill);

                // 下拉框数据显示
                selectLabData(request);
            }
            return "outWarehouseBill/outWarehouseBillEdit";
        } catch (Exception e) {
            logger.error("OutWarehouseBillController.dynamicToAddUpt():", e);
            throw e;
        } finally {
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
            OutWarehouseBill outWarehouseBill = FastJsonUtil.toBean(str, OutWarehouseBill.class);
            List<OutWarehouseBill> list = outWarehouseBillService.find(outWarehouseBill);

            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("OutWarehouseBillController.validIsExist():", e);
            throw e;
        } finally {
        }
    }

    /**
     * edit页面中下来框select 标签数据回显
     *
     * @param request
     */
    private void selectLabData(HttpServletRequest request) {
        // 查找与出库相关联的表单分类
        BillsCategory billsCategory = new BillsCategory();
        // CatOwnercategory 为2代表为：出库
        billsCategory.setCatOwnercategory((short) 2);

        // 只查询与出库相关的BillsCategory 的list集合
        List<BillsCategory> billsCategories = billsCategoryService.find(billsCategory);

        // 在edit页面做相关的下拉框操作
        request.setAttribute("billsCategories", billsCategories);
    }

    //查询入库单详情
    @RequestMapping("find/{outwBillsnumber}")
    public String find(@PathVariable("outwBillsnumber") @Nullable String outwBillsnumber, HttpServletRequest request) {
        try {
            Page<OutWarehouseDetailBill> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            OutWarehouseDetailBill outWarehouseDetailBill = new OutWarehouseDetailBill();
            outWarehouseDetailBill.setOutwBillsnumber(outwBillsnumber);
            List<OutWarehouseDetailBill> list = outWarehouseDetailBillService.find(outWarehouseDetailBill);
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("outwBillsnumber", outwBillsnumber);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "outWarehouseDetailBill/outWarehouseDetailBillView";
        } catch (Exception e) {
            logger.error("OutWarehouseBillController.find():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/doubleSave", method = RequestMethod.POST)
    @ResponseBody
    public int doubleSave(@RequestParam("data") String data) throws EarthWormException {
        try {
            JSONObject jsonObj = JSON.parseObject(data);
            OutWarehouseBill outWarehouseBill = jsonObj.getObject("outw", OutWarehouseBill.class);
            OutWarehouseDetailBill outWarehouseDetailBill = jsonObj.getObject("outwDetail", OutWarehouseDetailBill.class);
            // 对OutWarehouseBill 出库单做后台验证
            backValidate(outWarehouseBill);

            // outWarehouseDetailBill 出库详情单做后台验证
            backValidate(outWarehouseDetailBill);
            return outWarehouseBillService.save(outWarehouseBill, outWarehouseDetailBill);
        } catch (IPSPFoundationServiceException e) {
            logger.error("OutWarehouseBillController.doubleSave():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/doubleUpt", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doubleUpt(@RequestParam("data") String data) throws EarthWormException {
        try {
            JSONObject jsonObj = JSON.parseObject(data);
            OutWarehouseBill outWarehouseBill = jsonObj.getObject("outw", OutWarehouseBill.class);
            OutWarehouseDetailBill outWarehouseDetailBill = jsonObj.getObject("outwDetail", OutWarehouseDetailBill.class);
            if ((outWarehouseBill != null || outWarehouseBill.getOutwId() != null)
                    && (outWarehouseDetailBill == null || outWarehouseDetailBill.getOutwId() == null)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("info", outWarehouseBillService.update(outWarehouseBill));
                VwOutWarehouseBill vwOutWarehouseBill = vwOutWarehouseBillService.findById(outWarehouseBill.getOutwId());
                map.put("vwOutWarehouseBill", vwOutWarehouseBill);
                return map;

            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("info", outWarehouseBillService.update(outWarehouseBill, outWarehouseDetailBill));
                VwOutWarehouseBill vwOutWarehouseBill = vwOutWarehouseBillService.findById(outWarehouseBill.getOutwId());
                map.put("vwOutWarehouseBill", vwOutWarehouseBill);
                return map;
            }
        } catch (Exception ex) {
            logger.error("OutWarehouseBillController.doubleUpt:update outWarehouse failed!", ex);
            throw ex;
        } finally {

        }

    }


    //开始解析出库单的excel 并将解析后的数据，返回给页面上
    @RequestMapping(value = "/importOutExcelList", method = {RequestMethod.POST}, consumes = "multipart/form-data")
    @ResponseBody
    public List<OutWarehouseDetailBill> getImportOutWareInfo(@RequestParam("file") MultipartFile file, ServletResponse response) throws Exception {
        List<OutWarehouseDetailBill> outWarehouseDetailBills = outWarehouseBillService.prseExcelMethod(file, response);
        List list = new ArrayList();
        for (OutWarehouseDetailBill outWareList : outWarehouseDetailBills) {
            String outBnumber = outWareList.getOutwBillsnumber();
            list.add(outBnumber);
        }
        return list;
    }

    @RequestMapping(value = "/editOut{outWareDetailInfo}", method = {RequestMethod.GET})
    public String toShowImporOutWareInfoPage(@PathVariable("outWareDetailInfo") String outwBillsnumber, HttpServletRequest request) {
        if (outwBillsnumber != null) {
            request.setAttribute("outwBillsnumber", outwBillsnumber);
        }
        // 选择单据分类，并且只是显示出库单据类的分类；
        BillsCategory billsCategory = new BillsCategory();
        billsCategory.setCatOwnercategory((short) 2);// 2：代表出库
        // 根据查询条件，进行查询相关出库单据分类
        List<BillsCategory> billsCategories = billsCategoryService.find(billsCategory);
        request.setAttribute("billsCategories", billsCategories);
        return "outWarehouseBill/outWarehouseBillInfoEdit";
    }

    //查看出库单详细信息
    @RequestMapping(value = "/showDetailInfo", method = {RequestMethod.POST}, consumes = "multipart/form-data")
    public String showSomeOutWareInfo(@RequestParam("file") MultipartFile file, ServletResponse response, HttpServletRequest request) throws Exception {
        //通过入库单的ID，获得入库单，并将根据ID查找到旗下的详情单
        List<OutWarehouseDetailBill> outWarehouseDetailBill = outWarehouseBillService.prseExcelMethod(file, response);

        request.setAttribute("list", outWarehouseDetailBill);
        return "outWarehouseDetailBill/outWarehouseDetailInfoView";
    }

    //导出出库单详细信息
    @RequestMapping(value = "/exportFile", method = {RequestMethod.POST})
    @ResponseBody
    public void exportFile1(HttpServletResponse response) throws ParseException {
        //查询相应的数据信息记录(数据库内)
        List<OutWarehouseDetailBill> dataList = outWarehouseDetailBillService.findAll();
        try {
            response.reset();
            response.setHeader("content-Type", "application/vnd.ms-excel");// 告诉浏览器用什么软件可以打开此文件
            response.setHeader("Content-Disposition", "attachment;filname=" + URLEncoder.encode("出库详细信息.xlsx", "UTF-8"));//下载文件的默认名称
            ExcelUtil.writeFile(ExcelUtil.exportExcel(
                    new ExportParams(), OutWarehouseDetailBill.class, dataList), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    //导入入单信息
    @RequestMapping(value = "/saveFile", method = {RequestMethod.POST}, consumes = "multipart/form-data")
    @ResponseBody
    public Boolean saveFile(@RequestParam(value = "file") MultipartFile file, @RequestParam String objDate, ServletResponse response) throws Exception {
        //前台传来入库主表的数据,再解析详情表的信息,最后全部save到数据库中
        OutWarehouseBill outWarehouseBill = new OutWarehouseBill();
        JSONObject object = JSONObject.parseObject(objDate);
        outWarehouseBill.setOutwBillsnumber(object.getString("outwBillsnumber"));
        List<OutWarehouseBill> list = outWarehouseBillService.find(outWarehouseBill);
        if (list.size() > 0) {
            return false;
        } else {
            outWarehouseBill.setOutwId(UUID.randomUUID().toString());
            outWarehouseBill.setOutwBillsnumber(object.getString("outwBillsnumber"));
            outWarehouseBill.setOutwCategory(Short.parseShort(object.getString("outwCategory")));
            outWarehouseBill.setOutwStatus(Short.parseShort(object.getString("outwStatus")));
            outWarehouseBill.setOutwOuttime(object.getDate("outwOuttime"));
            outWarehouseBillService.save(outWarehouseBill);

            List<OutWarehouseDetailBill> uploadInWareDetail = outWarehouseBillService.prseExcelMethod(file, response);
            if (uploadInWareDetail == null || uploadInWareDetail.size() == 0) {
                return null;
            }
            OutWarehouseDetailBill outWarehouseDetailBill = new OutWarehouseDetailBill();
            for (OutWarehouseDetailBill entry : uploadInWareDetail) {
                outWarehouseDetailBill.setOutwBillsnumber(entry.getOutwBillsnumber());
                outWarehouseDetailBill.setOutwModel(entry.getOutwModel());
                outWarehouseDetailBill.setOutwCode(entry.getOutwCode());
                outWarehouseDetailBill.setOutwUniquecode(entry.getOutwUniquecode());
                outWarehouseDetailBill.setOutwManufacturer(entry.getOutwManufacturer());
                outWarehouseDetailBill.setOutwUnit(entry.getOutwUnit());
                outWarehouseDetailBill.setOutwBatch(entry.getOutwBatch());
                outWarehouseDetailBill.setOutwEffectivedate(entry.getOutwEffectivedate());
                outWarehouseDetailBill.setOutwProduceddate(entry.getOutwProduceddate());
                outWarehouseDetailBill.setOutwTotal(entry.getOutwTotal());
                outWarehouseDetailBill.setOutwAmount(entry.getOutwAmount());
                outWarehouseDetailBill.setOutwActualcount(entry.getOutwActualcount());
                outWarehouseDetailBill.setOutwStatus(entry.getOutwStatus());
                outWarehouseDetailBill.setOutwWarehousenumber(entry.getOutwWarehousenumber());
                outWarehouseDetailBill.setOutwIspreout(entry.getOutwIspreout());
                outWarehouseDetailBill.setOutwPrebillnumber(entry.getOutwPrebillnumber());
                outWarehouseDetailBill.setOutwOutdatetime(entry.getOutwOutdatetime());
                outWarehouseDetailBillService.save(outWarehouseDetailBill);
            }
        }
        return true;
    }
}
