package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.TerminalAppcategory;
import com.earthworm.ipsp.foundation.entity.TerminalCategory;
import com.earthworm.ipsp.foundation.entity.TerminalFunccategory;
import com.earthworm.ipsp.foundation.entity.VwTerminalCategory;
import com.earthworm.ipsp.foundation.service.interf.ITerminalAppcategoryService;
import com.earthworm.ipsp.foundation.service.interf.ITerminalCategoryService;
import com.earthworm.ipsp.foundation.service.interf.ITerminalFunccategoryService;
import com.earthworm.ipsp.foundation.service.interf.IVwTerminalCategoryService;
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
@RequestMapping("/terminalCategory")
public class TerminalCategoryController {
    private Logger logger = LoggerFactory.getLogger(TerminalCategoryController.class);

    @Autowired
    private ITerminalCategoryService terminalCategoryService;

    @Autowired
    private IVwTerminalCategoryService vwTerminalCategoryService;

    @Autowired
    private ITerminalFunccategoryService terminalFunccategoryService;

    @Autowired
    private ITerminalAppcategoryService terminalAppcategoryService;

    /**
     * 首页，默认显示7条记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<VwTerminalCategory> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<VwTerminalCategory> list = vwTerminalCategoryService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "terminalCategory/terminalCategoryView";
        } catch (Exception e) {
            logger.error("TerminalCategoryController.view():", e);
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
        Map<String, Object> map = null;
        try {
            Page page1 = FastJsonUtil.toBean(pageData, Page.class);
            VwTerminalCategory vwTerminalCategory = FastJsonUtil.toBean(formData, VwTerminalCategory.class);
            Page<VwTerminalCategory> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<VwTerminalCategory> list = vwTerminalCategoryService.findLikeInfo(vwTerminalCategory);
            int[] pageList = PageHelper.getPageList(page);
            map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("TerminalCategoryController.jumpPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除
     *
     * @param catId
     * @return
     */
    @RequestMapping(value = "/delete{catId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("catId") String catId) throws EarthWormException {
        try {
            return terminalCategoryService.deleteById(catId);
        } catch (Exception e) {
            logger.error("TerminalCategoryController.deleteById():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/update{catId}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(@PathVariable("catId") String catId, @RequestParam("data") String str) throws EarthWormException {
        Map<String, Object> map = null;
        try {
            TerminalCategory terminalCategory = FastJsonUtil.toBean(str, TerminalCategory.class);
            terminalCategory.setCatId(catId);
            map = new HashMap<String, Object>();
            map.put("info", terminalCategoryService.update(terminalCategory));
            VwTerminalCategory vwTerminalCategory = vwTerminalCategoryService.findById(catId);
            map.put("vwTerminalCategory", vwTerminalCategory);
            return map;
        } catch (Exception e) {
            logger.error("TerminalCategoryController.update():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            TerminalCategory terminalCategory = FastJsonUtil.toBean(str, TerminalCategory.class);
            Map<String, String> validate = ValidateHelper.validate(terminalCategory);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return terminalCategoryService.save(terminalCategory);
        } catch (Exception e) {
            logger.error("TerminalCategoryController.save():", e);
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
    public String toEditPage(HttpServletRequest request) throws EarthWormException {
        try {
            selectLabData(request);
            return "terminalCategory/terminalCategoryEdit";
        } catch (Exception e) {
            logger.error("TerminalCategoryController.toEditPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 编辑页面，数据回显
     *
     * @param catId
     * @param request
     * @return
     */
    @GetMapping("edit/{catId}")
    public String dynamicToAddUpt(@PathVariable("catId") @Nullable String catId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(catId)) {
                TerminalCategory terminalCategory = terminalCategoryService.findById(catId);
                request.setAttribute("terminalCategory", terminalCategory);

                // 下拉框数据显示
                selectLabData(request);
            }
            return "terminalCategory/terminalCategoryEdit";
        } catch (Exception e) {
            logger.error("TerminalCategoryController.dynamicToAddUpt():", e);
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
    public boolean validIsExist(@RequestParam("data") String str) {
        List<TerminalCategory> list = null;
        try {
            TerminalCategory terminalCategory = FastJsonUtil.toBean(str, TerminalCategory.class);
            list = terminalCategoryService.find(terminalCategory);
            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("TerminalCategoryController.validIsExist():", e);
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
        try {
            // 查找功能分类
            List<TerminalFunccategory> terminalFunccategories = terminalFunccategoryService.findAll();

            // 查找应用分类
            List<TerminalAppcategory> terminalAppcategories = terminalAppcategoryService.findAll();

            // 在edit页面做相关的下拉框操作
            request.setAttribute("terminalFunccategories", terminalFunccategories);
            request.setAttribute("terminalAppcategories", terminalAppcategories);
        } catch (Exception e) {
            logger.error("TerminalCategoryController.selectLabData():", e);
        } finally {
        }
    }

}
