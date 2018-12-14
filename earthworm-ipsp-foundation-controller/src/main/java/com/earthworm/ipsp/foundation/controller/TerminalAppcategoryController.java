package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.TerminalAppcategory;
import com.earthworm.ipsp.foundation.entity.TerminalCategory;
import com.earthworm.ipsp.foundation.service.interf.ITerminalAppcategoryService;
import com.earthworm.ipsp.foundation.service.interf.ITerminalCategoryService;
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
@RequestMapping("/terminalAppcategory")
public class TerminalAppcategoryController {

    private Logger logger = LoggerFactory.getLogger(TerminalAppcategoryController.class);

    @Autowired
    private ITerminalAppcategoryService terminalAppcategoryService;

    @Autowired
    private ITerminalCategoryService terminalCategoryService;

    // 首页，默认显示7条记录
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<TerminalAppcategory> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<TerminalAppcategory> list = terminalAppcategoryService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "terminalAppcategory/terminalAppcategoryView";
        } catch (Exception e) {
            logger.error("TerminalAppcategoryController.view(): ", e);
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
            TerminalAppcategory terminalAppcategory = FastJsonUtil.toBean(formData, TerminalAppcategory.class);
            Page<TerminalAppcategory> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<TerminalAppcategory> list = terminalAppcategoryService.findLikeInfo(terminalAppcategory);
            int[] pageList = PageHelper.getPageList(page);
            map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("TerminalAppcategoryController.jumpPage: ", e);
            throw e;
        } finally {

        }
    }

    /**
     * 根据Id删除
     *
     * @param appId
     * @return
     */
    @RequestMapping(value = "/delete{appId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("appId") String appId) throws EarthWormException {
        try {
            // 根据appId 进行查询TerminalAppcategory
            TerminalAppcategory terminalAppcategory = terminalAppcategoryService.findById(appId);

            // 根据查询出来terminalAppcategory的appCategory属性，
            // 赋值给TerminalCategory的catAppcategory属性，再进行条件查询
            TerminalCategory terminalCategory = new TerminalCategory();
            terminalCategory.setCatAppcategory(terminalAppcategory.getAppCategory());

            // 条件查询TerminalCategory
            List<TerminalCategory> terminalCategories = terminalCategoryService.find(terminalCategory);

            // 如果条件查询查询不到结果，则可以进行删除操作;
            // 如果在其他表中关联了此TerminalAppcategory表中的数据，则返回数字2。
            return (terminalCategories == null || terminalCategories.isEmpty()) ?
                    terminalAppcategoryService.deleteById(appId) : 2;
        } catch (Exception e) {
            logger.error("TerminalAppcategoryController.deleteById: ", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/update{appId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("appId") String appId, @RequestParam("data") String str) throws EarthWormException {
        try {
            TerminalAppcategory terminalAppcategory = FastJsonUtil.toBean(str, TerminalAppcategory.class);
            terminalAppcategory.setAppId(appId);
            return terminalAppcategoryService.update(terminalAppcategory);
        } catch (Exception e) {
            logger.error("TerminalAppcategoryController.update: ", e);
            throw e;
        } finally {

        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            TerminalAppcategory terminalAppcategory = FastJsonUtil.toBean(str, TerminalAppcategory.class);
            // 后台校验
            Map<String, String> validate = ValidateHelper.validate(terminalAppcategory);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return terminalAppcategoryService.save(terminalAppcategory);
        } catch (Exception e) {
            logger.error("TerminalAppcategoryController.save():", e);
            throw e;
        } finally {
        }
    }

    // 嵌入到增加或者修改页面
    @GetMapping("/edit")
    public String toEditPage() {
        return "terminalAppcategory/terminalAppcategoryEdit";
    }

    // 编辑页面，数据回显
    @RequestMapping("edit/{appId}")
    public String dynamicToAddUpt(@PathVariable("appId") @Nullable String appId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(appId)) {
                TerminalAppcategory terminalAppcategory = terminalAppcategoryService.findById(appId);
                request.setAttribute("terminalAppcategory", terminalAppcategory);
            }
            return "terminalAppcategory/terminalAppcategoryEdit";
        } catch (Exception e) {
            logger.error("TerminalAppcategoryController.dynamicToAddUpt():", e);
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
        List<TerminalAppcategory> list = null;
        try {
            TerminalAppcategory terminalAppcategory = FastJsonUtil.toBean(str, TerminalAppcategory.class);
            list = terminalAppcategoryService.find(terminalAppcategory);
            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("TerminalAppcategoryController.validIsExist():", e);
            throw e;
        } finally {
        }
    }
}