package com.earthworm.ipsp.foundation.controller;


import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.FixshelfBillinfo;
import com.earthworm.ipsp.foundation.entity.FixshelfDetailinfo;
import com.earthworm.ipsp.foundation.service.interf.IFixshelfBillinfoService;
import com.earthworm.ipsp.foundation.service.interf.IFixshelfDetailinfoService;
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
@RequestMapping("/fixshelfDetailinfo")
public class FixshelfDetailinfoController {

    // sl4j日志相关
    private Logger logger = LoggerFactory.getLogger(FixshelfDetailinfoController.class);

    @Autowired
    private IFixshelfDetailinfoService fixshelfDetailinfoService;

    @Autowired
    private IFixshelfBillinfoService fixshelfBillinfoService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException {
        try {
            Page<FixshelfDetailinfo> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<FixshelfDetailinfo> list = fixshelfDetailinfoService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "fixshelfDetailinfo/fixshelfDetailinfoView";
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.findAll():", e);
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
    public Map<String, Object> jumpPage(HttpServletRequest request, @RequestParam("pageData") String pageData, @RequestParam("formData") String formData, @RequestParam("fixBillsnumber") String fixBillsnumber) throws EarthWormException {
        try {
            Page page1 = FastJsonUtil.toBean(pageData, Page.class);
            FixshelfDetailinfo fixshelfDetailinfo = FastJsonUtil.toBean(formData, FixshelfDetailinfo.class);
            fixshelfDetailinfo.setFixBillsnumber(fixBillsnumber);
            Page<FixshelfDetailinfo> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<FixshelfDetailinfo> list = fixshelfDetailinfoService.findLikeInfo(fixshelfDetailinfo);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.jumpPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete{fixId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("fixId") String id) throws EarthWormException {
        try {
            return fixshelfDetailinfoService.deleteById(id);
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.deleteById():", e);
            throw e;
        } finally {
        }
    }


    @RequestMapping(value = "/update{fixId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("fixId") String id, @RequestParam("data") String str) throws EarthWormException {
        try {
            FixshelfDetailinfo fixshelfDetailinfo = FastJsonUtil.toBean(str, FixshelfDetailinfo.class);
            fixshelfDetailinfo.setFixId(id);
            return fixshelfDetailinfoService.update(fixshelfDetailinfo);
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.update():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            FixshelfDetailinfo fixshelfDetailinfo = FastJsonUtil.toBean(str, FixshelfDetailinfo.class);
            Map<String, String> validate = ValidateHelper.validate(fixshelfDetailinfo);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return fixshelfDetailinfoService.save(fixshelfDetailinfo);
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.save():", e);
            throw e;
        } finally {
        }
    }


    /**
     * 编辑页面，数据回显
     *
     * @param fixBillsnumber
     * @param fixId
     * @param request
     * @return
     */
    @GetMapping("edit/{fixBillsnumber}/{fixId}")
    public String edit(@PathVariable("fixBillsnumber") @Nullable String fixBillsnumber, @PathVariable("fixId") @Nullable String fixId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(fixId)) {
                FixshelfDetailinfo fixshelfDetailinfo = fixshelfDetailinfoService.findById(fixId);
                request.setAttribute("fixBillsnumber", fixBillsnumber);
                request.setAttribute("fixshelfDetailinfo", fixshelfDetailinfo);
            }
            return "fixshelfDetailinfo/fixshelfDetailinfoEdit";
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.edit():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 详情添加
     *
     * @param fixBillsnumber
     * @param request
     * @return
     */
    @RequestMapping(value = "/detailAdd/{fixBillsnumber}")
    public String detailAfterAdd(@PathVariable("fixBillsnumber") @Nullable String fixBillsnumber, HttpServletRequest request) throws EarthWormException {
        try {
            request.setAttribute("fixBillsnumber", fixBillsnumber);
            return "fixshelfDetailinfo/fixshelfDetailinfoEdit";
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.detailAfterAdd():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 详情编辑
     *
     * @param fixBillsnumber
     * @param fixId
     * @param request
     * @return
     */
    @RequestMapping(value = "/detailUpt/{fixBillsnumber}/{fixId}")
    public String detailUpt(@PathVariable("fixBillsnumber") @Nullable String fixBillsnumber, @PathVariable("fixId") @Nullable String fixId, HttpServletRequest request) throws EarthWormException {
        try {
            FixshelfBillinfo fixshelfBillinfo = fixshelfBillinfoService.findById(fixId);
            String fixBillsnumberTemp = fixshelfBillinfo.getBillNumber();
            FixshelfDetailinfo fixshelfDetailinfoTemp = new FixshelfDetailinfo();
            fixshelfDetailinfoTemp.setFixBillsnumber(fixBillsnumberTemp);
            List<FixshelfDetailinfo> fixshelfDetailinfos = fixshelfDetailinfoService.find(fixshelfDetailinfoTemp);
            FixshelfDetailinfo fixshelfDetailinfo = fixshelfDetailinfos.get(0);
            request.setAttribute("fixBillsnumber", fixBillsnumber);
            request.setAttribute("fixshelfDetailinfo", fixshelfDetailinfo);
            return "fixshelfDetailinfo/fixshelfDetailinfoEdit";
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.detailUpt():", e);
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
            FixshelfDetailinfo fixshelfDetailinfo = FastJsonUtil.toBean(str, FixshelfDetailinfo.class);
            List<FixshelfDetailinfo> list = fixshelfDetailinfoService.find(fixshelfDetailinfo);

            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("FixshelfDetailinfoController.validIsExist():", e);
            throw e;
        } finally {
        }
    }
}
