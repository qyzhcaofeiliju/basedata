package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.TerminalCategory;
import com.earthworm.ipsp.foundation.entity.TerminalFunccategory;
import com.earthworm.ipsp.foundation.service.interf.ITerminalCategoryService;
import com.earthworm.ipsp.foundation.service.interf.ITerminalFunccategoryService;
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
@RequestMapping("/terminalFunccategory")
public class TerminalFunccategoryController {

    // sl4j 日志相关
    private Logger logger = LoggerFactory.getLogger(TerminalFunccategoryController.class);

    @Autowired
    private ITerminalFunccategoryService terminalFunccategoryService;

    @Autowired
    private ITerminalCategoryService terminalCategoryService;

    /**
     * 首页view，默认显示7条记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<TerminalFunccategory> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<TerminalFunccategory> list = terminalFunccategoryService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "terminalFunccategory/terminalFunccategoryView";
        } catch (Exception e) {
            logger.error("TerminalFunccategoryController.view(): ", e);
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
            Map<String, Object> map = null;
            Page page1 = FastJsonUtil.toBean(pageData, Page.class);
            TerminalFunccategory terminalFunccategory = FastJsonUtil.toBean(formData, TerminalFunccategory.class);
            Page<TerminalFunccategory> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<TerminalFunccategory> list = terminalFunccategoryService.findLikeInfo(terminalFunccategory);
            int[] pageList = PageHelper.getPageList(page);
            map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("TerminalFunccategoryController.jumpPage(): ", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除,
     * 如果其他表中关联了此数据，则不删除
     *
     * @param funId
     * @return
     */
    @RequestMapping(value = "/delete{funId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("funId") String funId) throws EarthWormException {
        try {
            List<TerminalCategory> terminalCategories = null;
            // 根据funId 进行查询
            TerminalFunccategory terminalFunccategory = terminalFunccategoryService.findById(funId);

            // 实例TerminalCategory，并设置属性CatFunccategory
            TerminalCategory terminalCategory = new TerminalCategory();
            terminalCategory.setCatFunccategory(terminalFunccategory.getFunCategory());

            // 条件查询terminalCategory
            terminalCategories = terminalCategoryService.find(terminalCategory);
            // 做判断，是删除还是返回其他值
            return (terminalCategories == null || terminalCategories.isEmpty()) ?
                    terminalFunccategoryService.deleteById(funId) : 2;
        } catch (Exception e) {
            logger.error("TerminalFunccategoryController.deleteById(): ", e);
            throw e;
        } finally {

        }
    }

    @RequestMapping(value = "/update{funId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("funId") String funId, @RequestParam("data") String str) throws EarthWormException {
        try {
            TerminalFunccategory terminalFunccategory = FastJsonUtil.toBean(str, TerminalFunccategory.class);
            terminalFunccategory.setFunId(funId);

            TerminalFunccategory tmp = new TerminalFunccategory();

            TerminalFunccategory oldData = terminalFunccategoryService.findById(funId);

            if (!oldData.getFunName().equals(terminalFunccategory.getFunName())) {
                tmp.setFunName(terminalFunccategory.getFunName());
            }
            terminalFunccategoryService.find(tmp);
            return terminalFunccategoryService.update(terminalFunccategory);
        } catch (Exception e) {
            logger.error("TerminalFunccategoryController.update(): ", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            TerminalFunccategory terminalFunccategory = FastJsonUtil.toBean(str, TerminalFunccategory.class);
            // 后台校验
            Map<String, String> validate = ValidateHelper.validate(terminalFunccategory);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return terminalFunccategoryService.save(terminalFunccategory);
        } catch (Exception e) {
            logger.error("TerminalFunccategoryController.save()--> EWValidationException: ", e);
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
        return "terminalFunccategory/terminalFunccategoryEdit";
    }

    /**
     * 编辑页面，数据回显
     *
     * @param funId
     * @param request
     * @return
     */
    @GetMapping("edit/{funId}")
    public String dynamicToAddUpt(@PathVariable("funId") @Nullable String funId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(funId)) {
                TerminalFunccategory terminalFunccategory = terminalFunccategoryService.findById(funId);
                request.setAttribute("terminalFunccategory", terminalFunccategory);
            }
            return "terminalFunccategory/terminalFunccategoryEdit";
        } catch (Exception e) {
            logger.error("TerminalFunccategoryController.dynamicToAddUpt(): ", e);
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
        List<TerminalFunccategory> list = null;
        try {
            TerminalFunccategory terminalFunccategory = FastJsonUtil.toBean(str, TerminalFunccategory.class);
            list = terminalFunccategoryService.find(terminalFunccategory);
            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("TerminalFunccategoryController.validIsExist(): ", e);
            throw e;
        } finally {
        }
    }
}