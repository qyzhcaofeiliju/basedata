package com.earthworm.ipsp.foundation.controller;


import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.BillsCategory;
import com.earthworm.ipsp.foundation.entity.FixshelfBillinfo;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.IBillsCategoryService;
import com.earthworm.ipsp.foundation.service.interf.IFixshelfBillinfoService;
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
@RequestMapping("/fixshelfBillinfo")
public class FixshelfBillinfoController {

    //sl4j日志相关
    private Logger logger = LoggerFactory.getLogger(FixshelfBillinfoController.class);

    @Autowired
    private IFixshelfBillinfoService fixshelfBillinfoService;

    @Autowired
    private IBillsCategoryService billsCategoryService;

    /**
     * 首页，默认显示7条记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<FixshelfBillinfo> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<FixshelfBillinfo> list = fixshelfBillinfoService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "fixshelfBillinfo/fixshelfBillinfoView";
        } catch (Exception e) {
            logger.error("FixshelfBillinfoController.view():", e);
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
            FixshelfBillinfo fixshelfBillinfo = FastJsonUtil.toBean(formData, FixshelfBillinfo.class);
            Page<FixshelfBillinfo> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<FixshelfBillinfo> list = fixshelfBillinfoService.findLikeInfo(fixshelfBillinfo);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("FixshelfBillinfoController.jumpPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除
     *
     * @param billId
     * @return
     */
    @RequestMapping(value = "/delete{billId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("billId") String billId) throws EarthWormException {
        try {
            return fixshelfBillinfoService.deleteById(billId);
        } catch (IPSPFoundationServiceException e) {
            logger.error("FixshelfBillinfoController.deleteById():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/update{billId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("billId") String billId, @RequestParam("data") String str) throws EarthWormException {
        try {
            FixshelfBillinfo fixshelfBillinfo = FastJsonUtil.toBean(str, FixshelfBillinfo.class);
            fixshelfBillinfo.setBillId(billId);
            return fixshelfBillinfoService.update(fixshelfBillinfo);
        } catch (Exception e) {
            logger.error("FixshelfBillinfoController.update():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            FixshelfBillinfo fixshelfBillinfo = FastJsonUtil.toBean(str, FixshelfBillinfo.class);
            Map<String, String> validate = ValidateHelper.validate(fixshelfBillinfo);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return fixshelfBillinfoService.save(fixshelfBillinfo);
        } catch (Exception e) {
            logger.error("FixshelfBillinfoController.save():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 嵌入到增加或者修改页面
     *
     * @return
     */
    @GetMapping("/edit")
    public String toEditPage() {
        return "fixshelfBillinfo/fixshelfBillinfoEdit";
    }

    /**
     * 编辑页面，数据回显
     *
     * @param billId
     * @param request
     * @return
     */
    @GetMapping("edit/{billId}")
    public String dynamicToAddUpt(@PathVariable("billId") @Nullable String billId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(billId)) {
                FixshelfBillinfo fixshelfBillinfo = fixshelfBillinfoService.findById(billId);
                request.setAttribute("fixshelfBillinfo", fixshelfBillinfo);
            }
            List<BillsCategory> billsCategories = billsCategoryService.findAll();
            request.setAttribute("billsCategories", billsCategories);
            return "fixshelfBillinfo/fixshelfBillinfoEdit";
        } catch (Exception e) {
            logger.error("FixshelfBillinfoController.dynamicToAddUpt():", e);
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
            FixshelfBillinfo fixshelfBillinfo = FastJsonUtil.toBean(str, FixshelfBillinfo.class);
            List<FixshelfBillinfo> list = fixshelfBillinfoService.find(fixshelfBillinfo);

            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("FixshelfBillinfoController.validIsExist():", e);
            throw e;
        } finally {
        }
    }

}
