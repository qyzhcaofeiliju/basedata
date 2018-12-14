package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.SuitcaseAppcategory;
import com.earthworm.ipsp.foundation.entity.SuitcaseProfile;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseAppcategoryService;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseProfileService;
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
@RequestMapping("/suitcaseAppcategory")
public class SuitcaseAppcategoryController {

    @Autowired
    private ISuitcaseAppcategoryService suitcaseAppcategoryService;

    @Autowired
    private ISuitcaseProfileService suitcaseProfileService;

    private static Logger logger = LoggerFactory.getLogger(SuitcaseAppcategoryController.class);

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException {
        try {
            Page<SuitcaseAppcategory> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<SuitcaseAppcategory> list = suitcaseAppcategoryService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "suitcaseAppcategory/suitcaseAppcategoryView";
        } catch (Exception ex) {
            logger.error("SuitcaseAppcategoryController.findAll:Find SuitcaseAppcategory failed!", ex);
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
            SuitcaseAppcategory suitcaseAppcategory = FastJsonUtil.toBean(formData, SuitcaseAppcategory.class);
            Page<SuitcaseAppcategory> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<SuitcaseAppcategory> list = suitcaseAppcategoryService.findLikeInfo(suitcaseAppcategory);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception ex) {
            logger.error("SuitcaseAppcategoryController.jumpPage:Find SuitcaseAppcategory failed!", ex);
            throw ex;
        }
    }

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete{appId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("appId") String id) throws EarthWormException {
        try {
            //根据Id查询对应的消息,取出对应的分类
            SuitcaseAppcategory suitcaseAppcategory = suitcaseAppcategoryService.findById(id);
            Short appCategory = suitcaseAppcategory.getAppCategory();
            SuitcaseProfile suitcaseProfile = new SuitcaseProfile();
            suitcaseProfile.setProAppcategory(appCategory);
            //根据分类查出使用该分类的周转箱特征描述，如果没有执行删除，如果有不执行此操作
            List<SuitcaseProfile> suitcaseProfiles = suitcaseProfileService.find(suitcaseProfile);
            if (suitcaseProfiles == null || suitcaseProfiles.isEmpty()) {
                return suitcaseAppcategoryService.deleteById(id);
            }
            return 2;
        } catch (Exception ex) {
            logger.error("SuitcaseAppcategoryController.deleteById:delete SuitcaseAppcategory failed!", ex);
            throw ex;
        }
    }


    @RequestMapping(value = "/update{appId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("appId") String id, @RequestParam("data") String str) throws EarthWormException {
        try {
            SuitcaseAppcategory suitcaseAppcategory = FastJsonUtil.toBean(str, SuitcaseAppcategory.class);
            suitcaseAppcategory.setAppId(id);
            return suitcaseAppcategoryService.update(suitcaseAppcategory);
        } catch (Exception ex) {
            logger.error("SuitcaseAppcategoryController.update:Update SuitcaseAppcategory failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            SuitcaseAppcategory suitcaseAppcategory = FastJsonUtil.toBean(str, SuitcaseAppcategory.class);
            Map<String, String> validate = ValidateHelper.validate(suitcaseAppcategory);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return suitcaseAppcategoryService.save(suitcaseAppcategory);
        } catch (Exception ex) {
            logger.error("SuitcaseAppcategoryController.save:Save SuitcaseAppcategory failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEditPage() {
        return "suitcaseAppcategory/suitcaseAppcategoryEdit";
    }

    // 编辑页面，数据回显
    @RequestMapping("edit/{funId}")
    public String dynamicToAddUpt(@PathVariable("funId") @Nullable String funId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(funId)) {
                SuitcaseAppcategory suitcaseAppcategory = suitcaseAppcategoryService.findById(funId);
                request.setAttribute("suitcaseAppcategory", suitcaseAppcategory);
            }
            return "suitcaseAppcategory/suitcaseAppcategoryEdit";
        } catch (Exception ex) {
            logger.error("SuitcaseAppcategoryController.dynamicToAddUpt:Update SuitcaseAppcategory failed!", ex);
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
            SuitcaseAppcategory suitcaseAppcategory = FastJsonUtil.toBean(str, SuitcaseAppcategory.class);
            List<SuitcaseAppcategory> list = suitcaseAppcategoryService.find(suitcaseAppcategory);
            return list == null || list.isEmpty();
        } catch (Exception ex) {
            logger.error("SuitcaseAppcategoryController.validIsExist:Find SuitcaseAppcategory failed!", ex);
            throw ex;
        }
    }
}
