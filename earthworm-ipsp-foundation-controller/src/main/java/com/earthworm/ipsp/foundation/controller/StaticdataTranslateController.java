package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.StaticdataTranslate;
import com.earthworm.ipsp.foundation.service.interf.IStaticdataTranslateService;
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
@RequestMapping("/staticdataTranslate")
public class StaticdataTranslateController {
    @Autowired
    private IStaticdataTranslateService staticdataTranslateService;

    private static Logger logger = LoggerFactory.getLogger(StaticdataTranslateController.class);


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException {
        try {
            Page<StaticdataTranslate> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<StaticdataTranslate> list = staticdataTranslateService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "staticdataTranslate/staticdataTranslateView";
        } catch (Exception ex) {
            logger.error("StaticdataTranslateController.findAll:Find StaticdataTranslate failed!", ex);
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
            StaticdataTranslate staticdataTranslate = FastJsonUtil.toBean(formData, StaticdataTranslate.class);
            Page<StaticdataTranslate> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<StaticdataTranslate> list = staticdataTranslateService.findLikeInfo(staticdataTranslate);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception ex) {
            logger.error("StaticdataTranslateController.jumpPage:Find StaticdataTranslate failed!", ex);
            throw ex;
        }
    }

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete{traId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("traId") String id) throws EarthWormException {
        try {
            return staticdataTranslateService.deleteById(id);
        } catch (Exception ex) {
            logger.error("StaticdataTranslateController.deleteById:Delete StaticdataTranslate failed!", ex);
            throw ex;
        }
    }


    @RequestMapping(value = "/update{traId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("traId") String id, @RequestParam("data") String str) throws EarthWormException {
        try {
            StaticdataTranslate staticdataTranslate = FastJsonUtil.toBean(str, StaticdataTranslate.class);
            staticdataTranslate.setTraId(id);
            return staticdataTranslateService.update(staticdataTranslate);
        } catch (Exception ex) {
            logger.error("StaticdataTranslateController.update:Update StaticdataTranslate failed!", ex);
            throw ex;
        }
    }

    /**
     * 增加
     *
     * @param str
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EWValidationException, EarthWormException {
        try {
            StaticdataTranslate staticdataTranslate = FastJsonUtil.toBean(str, StaticdataTranslate.class);
            Map<String, String> validate = ValidateHelper.validate(staticdataTranslate);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return staticdataTranslateService.save(staticdataTranslate);
        } catch (Exception ex) {
            logger.error("StaticdataTranslateController.save:Save StaticdataTranslate failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEditPage(HttpServletRequest request) throws EarthWormException {
        try {
            List<StaticdataTranslate> staticdataTranslates = staticdataTranslateService.findAll();
            request.setAttribute("staticdataTranslates", staticdataTranslates);
            return "staticdataTranslate/staticdataTranslateEdit";
        } catch (Exception ex) {
            logger.error("StaticdataTranslateController.toEditPage:Find StaticdataTranslate failed!", ex);
            throw ex;
        }
    }

    // 编辑页面，数据回显
    @RequestMapping("edit/{funId}")
    public String dynamicToAddUpt(@PathVariable("funId") @Nullable String funId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(funId)) {
                StaticdataTranslate staticdataTranslate = staticdataTranslateService.findById(funId);
                request.setAttribute("staticdataTranslate", staticdataTranslate);
            }
            List<StaticdataTranslate> staticdataTranslates = staticdataTranslateService.findAll();
            request.setAttribute("staticdataTranslates", staticdataTranslates);
            return "staticdataTranslate/staticdataTranslateEdit";
        } catch (Exception ex) {
            logger.error("StaticdataTranslateController.dynamicToAddUpt:Find StaticdataTranslate failed!", ex);
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
            StaticdataTranslate staticdataTranslate = FastJsonUtil.toBean(str, StaticdataTranslate.class);
            List<StaticdataTranslate> list = staticdataTranslateService.find(staticdataTranslate);

            return list == null || list.isEmpty();
        } catch (Exception ex) {
            logger.error("StaticdataTranslateController.validIsExist:Find StaticdataTranslate failed!", ex);
            throw ex;
        }
    }
}
