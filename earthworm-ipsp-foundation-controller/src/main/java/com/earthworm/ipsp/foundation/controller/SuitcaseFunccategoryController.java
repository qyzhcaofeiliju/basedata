package com.earthworm.ipsp.foundation.controller;


import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.SuitcaseFunccategory;
import com.earthworm.ipsp.foundation.entity.SuitcaseProfile;
import com.earthworm.ipsp.foundation.entity.SuitcaseQrrule;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseFunccategoryService;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseProfileService;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseQrruleService;
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
@RequestMapping("/suitcaseFunccategory")
public class SuitcaseFunccategoryController {

    @Autowired
    private ISuitcaseFunccategoryService suitcaseFunccategoryService;

    @Autowired
    private ISuitcaseProfileService suitcaseProfileService;

    @Autowired
    private ISuitcaseQrruleService iSuitcaseQrruleService;

    private static Logger logger = LoggerFactory.getLogger(SuitcaseFunccategoryController.class);

    /**
     * 首页列表显示
     *
     * @param request
     * @return
     * @throws EarthWormException
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException {
        try {
            Page<SuitcaseFunccategory> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<SuitcaseFunccategory> list = suitcaseFunccategoryService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "suitcaseFunccategory/suitcaseFunccategoryView";
        } catch (Exception ex) {
            logger.error("SuitcaseFunccategoryController.findAll:Find SuitcaseFunccategory failed!", ex);
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
            SuitcaseFunccategory suitcaseFunccategory = FastJsonUtil.toBean(formData, SuitcaseFunccategory.class);
            Page<SuitcaseFunccategory> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<SuitcaseFunccategory> list = suitcaseFunccategoryService.findLikeInfo(suitcaseFunccategory);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception ex) {
            logger.error("SuitcaseFunccategoryController.jumpPage:Find SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete{funId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("funId") String id) throws EarthWormException {
        try {
            //根据Id查询对应的消息
            SuitcaseFunccategory suitcaseFunccategory = suitcaseFunccategoryService.findById(id);
            //根据分类查出使用该分类的周转箱特征描述，如果没有执行删除，如果有不执行此操作
            SuitcaseProfile suitcaseProfile = new SuitcaseProfile();
            suitcaseProfile.setProFunccategory(suitcaseFunccategory.getFunCategory());
            List<SuitcaseProfile> suitcaseProfiles = suitcaseProfileService.find(suitcaseProfile);
            //根据名称查对应的物料唯一码规则，如果没有执行删除，如果有不执行此操作
            SuitcaseQrrule suitcaseQrrule =new SuitcaseQrrule();
            suitcaseQrrule.setSqrrProductmodel(suitcaseFunccategory.getFunName());
            List<SuitcaseQrrule> suitcaseQrrules = iSuitcaseQrruleService.find(suitcaseQrrule);
            if (suitcaseProfiles.size()==0 && suitcaseQrrules.size()==0) {
                return suitcaseFunccategoryService.deleteById(id);
            }
            return 2;
        } catch (Exception ex) {
            logger.error("SuitcaseFunccategoryController.deleteById:Delete SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/update{funId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("funId") String id, @RequestParam("data") String str) throws EarthWormException {
        try {
            SuitcaseFunccategory suitcaseFunccategory = FastJsonUtil.toBean(str, SuitcaseFunccategory.class);
            suitcaseFunccategory.setFunId(id);
            return suitcaseFunccategoryService.update(suitcaseFunccategory);
        } catch (Exception ex) {
            logger.error("SuitcaseFunccategoryController.update:Update SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            SuitcaseFunccategory suitcaseFunccategory = FastJsonUtil.toBean(str, SuitcaseFunccategory.class);
            Map<String, String> validate = ValidateHelper.validate(suitcaseFunccategory);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return suitcaseFunccategoryService.save(suitcaseFunccategory);
        } catch (Exception ex) {
            logger.error("SuitcaseFunccategoryController.save:Save SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEditPage() {
        return "suitcaseFunccategory/suitcaseFunccategoryEdit";
    }

    // 编辑页面，数据回显
    @RequestMapping("edit/{funId}")
    public String dynamicToAddUpt(@PathVariable("funId") @Nullable String funId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(funId)) {
                SuitcaseFunccategory suitcaseFunccategory = suitcaseFunccategoryService.findById(funId);
                request.setAttribute("suitcaseFunccategory", suitcaseFunccategory);
            }
            return "suitcaseFunccategory/suitcaseFunccategoryEdit";
        } catch (Exception ex) {
            logger.error("SuitcaseFunccategoryController.dynamicToAddUpt:FindById SuitcaseFunccategory failed!", ex);
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
            SuitcaseFunccategory suitcaseFunccategory = FastJsonUtil.toBean(str, SuitcaseFunccategory.class);
            List<SuitcaseFunccategory> list = suitcaseFunccategoryService.find(suitcaseFunccategory);

            return list == null || list.isEmpty();
        } catch (Exception ex) {
            logger.error("SuitcaseFunccategoryController.validIsExist:Find SuitcaseFunccategory failed!", ex);
            throw ex;
        }
    }
}
