package com.earthworm.ipsp.foundation.controller;


import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.OutWarehouseBill;
import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;
import com.earthworm.ipsp.foundation.service.interf.IOutWarehouseBillService;
import com.earthworm.ipsp.foundation.service.interf.IOutWarehouseDetailBillService;
import com.earthworm.postgres.helper.PageHelper;
import com.earthworm.postgres.page.Page;
import com.earthworm.postgres.validate.ValidateHelper;
import com.earthworm.utils.fastjson.FastJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 9:24
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Controller
@RequestMapping("/outWarehouseDetailBill")
public class OutWarehouseDetailBillController {

    // sl4j 日志相关
    private Logger logger = LoggerFactory.getLogger(OutWarehouseDetailBillController.class);

    @Autowired
    private IOutWarehouseDetailBillService outWarehouseDetailBillService;

    @Autowired
    private IOutWarehouseBillService outWarehouseBillService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException {
        try {
            Page<OutWarehouseDetailBill> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<OutWarehouseDetailBill> list = outWarehouseDetailBillService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "outWarehouseDetailBill/outWarehouseDetailBillView";
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.findAll():", e);
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
    public Map<String, Object> jumpPage(HttpServletRequest request, @RequestParam("pageData") String pageData, @RequestParam("formData") String formData, @RequestParam("outwBillsnumber") String outwBillsnumber) throws EarthWormException {
        try {
            Page page1 = FastJsonUtil.toBean(pageData, Page.class);
            OutWarehouseDetailBill outWarehouseDetailBill = FastJsonUtil.toBean(formData, OutWarehouseDetailBill.class);
            outWarehouseDetailBill.setOutwBillsnumber(outwBillsnumber);
            Page<OutWarehouseDetailBill> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<OutWarehouseDetailBill> list = outWarehouseDetailBillService.findLikeInfo(outWarehouseDetailBill);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.jumpPage():", e);
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
            return outWarehouseDetailBillService.deleteById(outwId);
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.deleteById():", e);
            throw e;
        } finally {
        }
    }


    @RequestMapping(value = "/update{outwId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("outwId") String outwId, @RequestParam("data") String str) throws EarthWormException {
        try {
            OutWarehouseDetailBill outWarehouseDetailBill = FastJsonUtil.toBean(str, OutWarehouseDetailBill.class);
            outWarehouseDetailBill.setOutwId(outwId);
            return outWarehouseDetailBillService.update(outWarehouseDetailBill);
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.update():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            OutWarehouseDetailBill outWarehouseDetailBill = FastJsonUtil.toBean(str, OutWarehouseDetailBill.class);

            Map<String, String> validate = ValidateHelper.validate(outWarehouseDetailBill);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }

            return outWarehouseDetailBillService.save(outWarehouseDetailBill);
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.save():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 编辑页面，数据回显
     *
     * @param outwBillsnumber
     * @param outwId
     * @param request
     * @return
     */
    @RequestMapping("edit/{outwBillsnumber}/{outwId}")
    public String edit(@PathVariable("outwBillsnumber") @Nullable String outwBillsnumber, @PathVariable("outwId") @Nullable String outwId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(outwId)) {
                OutWarehouseDetailBill outWarehouseDetailBill = outWarehouseDetailBillService.findById(outwId);
                request.setAttribute("outwBillsnumber", outwBillsnumber);
                request.setAttribute("outWarehouseDetailBill", outWarehouseDetailBill);
            }
            return "outWarehouseDetailBill/outWarehouseDetailBillEdit";
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.edit():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 出库单详情添加
     *
     * @param outwBillsnumber
     * @param request
     * @return
     */
    @RequestMapping(value = "/detailAdd/{outwBillsnumber}")
    public String detailAfterAdd(@PathVariable("outwBillsnumber") @Nullable String outwBillsnumber, HttpServletRequest request) throws EarthWormException {
        try {
            request.setAttribute("outwBillsnumber", outwBillsnumber);
            return "outWarehouseDetailBill/outWarehouseDetailBillEdit";
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.detailAfterAdd():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 出库单详情编辑
     *
     * @param outwBillsnumber
     * @param outwId
     * @param request
     * @return
     */
    @RequestMapping(value = "/detailUpt/{outwBillsnumber}/{outwId}")
    public String detailUpt(@PathVariable("outwBillsnumber") @Nullable String outwBillsnumber, @PathVariable("outwId") @Nullable String outwId, HttpServletRequest request) throws EarthWormException {
        try {
            OutWarehouseBill outWarehouseBill = outWarehouseBillService.findById(outwId);
            String outwBillsnumberTemp = outWarehouseBill.getOutwBillsnumber();
            OutWarehouseDetailBill outWarehouseDetailBillTemp = new OutWarehouseDetailBill();
            outWarehouseDetailBillTemp.setOutwBillsnumber(outwBillsnumberTemp);
            List<OutWarehouseDetailBill> outWarehouseDetailBills = outWarehouseDetailBillService.find(outWarehouseDetailBillTemp);

            if (!outWarehouseDetailBills.isEmpty() || outWarehouseDetailBills.size() != 0) {
                OutWarehouseDetailBill outWarehouseDetailBill = outWarehouseDetailBills.get(0);
                request.setAttribute("outwBillsnumber", outwBillsnumber);
                request.setAttribute("outWarehouseDetailBill", outWarehouseDetailBill);
                return "outWarehouseDetailBill/outWarehouseDetailBillEdit";
            } else {
                return "outWarehouseDetailBill/errorMessageRemind";
            }
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.detailUpt():", e);
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
            OutWarehouseDetailBill outWarehouseDetailBill = FastJsonUtil.toBean(str, OutWarehouseDetailBill.class);
            List<OutWarehouseDetailBill> list = outWarehouseDetailBillService.find(outWarehouseDetailBill);

            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("OutWarehouseDetailBillController.validIsExist():", e);
            throw e;
        } finally {
        }
    }
}
