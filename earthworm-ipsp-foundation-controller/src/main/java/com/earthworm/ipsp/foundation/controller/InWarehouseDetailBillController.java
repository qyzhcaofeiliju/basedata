package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.InWarehouseBill;
import com.earthworm.ipsp.foundation.entity.InWarehouseDetailBill;
import com.earthworm.ipsp.foundation.service.interf.IInWarehouseBillService;
import com.earthworm.ipsp.foundation.service.interf.IInWarehouseDetailBillService;
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

@Controller
@RequestMapping("/inWarehouseDetailBill")
public class InWarehouseDetailBillController {
    @Autowired
    private IInWarehouseDetailBillService inWarehouseDetailBillService;

    @Autowired
    private IInWarehouseBillService inWarehouseBillService;

    private static Logger logger = LoggerFactory.getLogger(InWarehouseDetailBillController.class);

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException {
        try {
            Page<InWarehouseDetailBill> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<InWarehouseDetailBill> list = inWarehouseDetailBillService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "inWarehouseDetailBill/inWarehouseDetailBillView";
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.findAll:Find SuitcaseFunccategory failed!", ex);
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
    public Map<String, Object> jumpPage(HttpServletRequest request, @RequestParam("pageData") String pageData, @RequestParam("formData") String formData, @RequestParam("inwBillsnumber") String inwBillsnumber) throws EarthWormException {
        try {
            Page page1 = FastJsonUtil.toBean(pageData, Page.class);
            InWarehouseDetailBill inWarehouseDetailBill = FastJsonUtil.toBean(formData, InWarehouseDetailBill.class);
            inWarehouseDetailBill.setInwBillsnumber(inwBillsnumber);
            Page<InWarehouseDetailBill> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<InWarehouseDetailBill> list = inWarehouseDetailBillService.findLikeInfo(inWarehouseDetailBill);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.jumpPage:Find SuitcaseFunccategory failed!", ex);
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
            return inWarehouseDetailBillService.deleteById(id);
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.deleteById:Delete SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }


    @RequestMapping(value = "/update{inwId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("inwId") String id, @RequestParam("data") String str) throws EarthWormException {
        try {
            InWarehouseDetailBill inWarehouseDetailBill = FastJsonUtil.toBean(str, InWarehouseDetailBill.class);
            inWarehouseDetailBill.setInwId(id);
            return inWarehouseDetailBillService.update(inWarehouseDetailBill);
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.update:update SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException {
        try {
            InWarehouseDetailBill inWarehouseDetailBill = FastJsonUtil.toBean(str, InWarehouseDetailBill.class);
            Map<String, String> validate = ValidateHelper.validate(inWarehouseDetailBill);
            if (!validate.isEmpty() && validate != null) {
                throw new EWValidationException(validate);
            }
            return inWarehouseDetailBillService.save(inWarehouseDetailBill);
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.save:save SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }

    // 编辑页面，数据回显
    @RequestMapping("edit/{inwBillsnumber}/{inwId}")
    public String edit(@PathVariable("inwBillsnumber") @Nullable String inwBillsnumber, @PathVariable("inwId") @Nullable String inwId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(inwId)) {
                InWarehouseDetailBill inWarehouseDetailBill = inWarehouseDetailBillService.findById(inwId);
                request.setAttribute("inwBillsnumber", inwBillsnumber);
                request.setAttribute("inWarehouseDetailBill", inWarehouseDetailBill);
            }
            return "inWarehouseDetailBill/inWarehouseDetailBillEdit";
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.edit:edit SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }

    //入库单详情添加
    @RequestMapping(value = "/detailAdd/{inwBillsnumber}")
    public String detailAfterAdd(@PathVariable("inwBillsnumber") @Nullable String inwBillsnumber, HttpServletRequest request) throws EarthWormException {
        try {
            request.setAttribute("inwBillsnumber", inwBillsnumber);
            return "inWarehouseDetailBill/inWarehouseDetailBillEdit";
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.detailAfterAdd:detailAfterAdd SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }

    //入库单详情编辑
    @RequestMapping(value = "/detailUpt/{inwBillsnumber}/{inwId}")
    public String detailUpt(@PathVariable("inwBillsnumber") @Nullable String inwBillsnumber, @PathVariable("inwId") @Nullable String inwId, HttpServletRequest request) throws EarthWormException {
        try {
            InWarehouseBill inWarehouseBill = inWarehouseBillService.findById(inwId);
            String inwBillsnumberTemp = inWarehouseBill.getInwBillsnumber();
            InWarehouseDetailBill inWarehouseDetailBillTemp = new InWarehouseDetailBill();
            inWarehouseDetailBillTemp.setInwBillsnumber(inwBillsnumberTemp);
            List<InWarehouseDetailBill> inWarehouseDetailBills = inWarehouseDetailBillService.find(inWarehouseDetailBillTemp);
            if (!inWarehouseDetailBills.isEmpty() || inWarehouseDetailBills.size() != 0) {
                InWarehouseDetailBill inWarehouseDetailBill = inWarehouseDetailBills.get(0);
                request.setAttribute("inwBillsnumber", inwBillsnumber);
                request.setAttribute("inWarehouseDetailBill", inWarehouseDetailBill);
                return "inWarehouseDetailBill/inWarehouseDetailBillEdit";
            } else {
                return "inWarehouseDetailBill/errorMessageRemind";
            }
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.detailUpt:Update SuitcaseFunccategory failed!", ex);
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
            InWarehouseDetailBill inWarehouseDetailBill = FastJsonUtil.toBean(str, InWarehouseDetailBill.class);
            List<InWarehouseDetailBill> list = inWarehouseDetailBillService.find(inWarehouseDetailBill);

            return list == null || list.isEmpty();
        } catch (Exception ex) {
            logger.error("InWarehouseDetailBillController.validIsExist:Find SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }
}
