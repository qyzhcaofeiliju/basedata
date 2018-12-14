package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.MaterialflowStation;
import com.earthworm.ipsp.foundation.service.interf.IMaterialflowStationService;
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
@RequestMapping("/materialflowStation")
public class MaterialflowStationController {
    // sl4j
    private Logger logger = LoggerFactory.getLogger(MaterialflowStationController.class);

    @Autowired
    private IMaterialflowStationService materialflowStationService;

    /**
     * 首页，默认显示7条记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<MaterialflowStation> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<MaterialflowStation> list = materialflowStationService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "materialflowStation/materialflowStationView";
        } catch (Exception e) {
            logger.error("MaterialflowStationController.view():", e);
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
            MaterialflowStation materialflowStation = FastJsonUtil.toBean(formData, MaterialflowStation.class);
            Page<MaterialflowStation> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<MaterialflowStation> list = materialflowStationService.findLikeInfo(materialflowStation);
            int[] pageList = PageHelper.getPageList(page);
            map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("MaterialflowStationController.jumpPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除
     *
     * @param staId
     * @return
     */
    @RequestMapping(value = "/delete{staId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("staId") String staId) throws EarthWormException {
        try {
            return materialflowStationService.deleteById(staId);
        } catch (Exception e) {
            logger.error("MaterialflowStationController.deleteById():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/update{staId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("staId") String staId, @RequestParam("data") String str) throws EarthWormException {
        MaterialflowStation materialflowStation = null;
        try {
            materialflowStation = FastJsonUtil.toBean(str, MaterialflowStation.class);
            materialflowStation.setStaId(staId);
            return materialflowStationService.update(materialflowStation);
        } catch (Exception e) {
            logger.error("MaterialflowStationController.update():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        MaterialflowStation materialflowStation = null;
        try {
            materialflowStation = FastJsonUtil.toBean(str, MaterialflowStation.class);

            Map<String, String> validate = ValidateHelper.validate(materialflowStation);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return materialflowStationService.save(materialflowStation);
        } catch (EWValidationException e) {
            logger.error("MaterialflowStationController.save():", e);
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
    public String toEditPage(HttpServletRequest request) {
        return "materialflowStation/materialflowStationEdit";
    }

    /**
     * 编辑页面，数据回显
     *
     * @param staId
     * @param request
     * @return
     */
    @RequestMapping(value = "edit/{staId}", method = RequestMethod.GET)
    public String dynamicToAddUpt(@PathVariable("staId") @Nullable String staId, HttpServletRequest request) {
        try {
            if (StringUtils.isNotBlank(staId)) {
                MaterialflowStation materialflowStation = materialflowStationService.findById(staId);
                request.setAttribute("materialflowStation", materialflowStation);
            }
            return "materialflowStation/materialflowStationEdit";
        } catch (Exception e) {
            logger.error("MaterialflowStationController.dynamicToAddUpt():", e);
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

        List<MaterialflowStation> list = null;
        try {
            MaterialflowStation materialflowStation = FastJsonUtil.toBean(str, MaterialflowStation.class);
            list = materialflowStationService.find(materialflowStation);
            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("MaterialflowStationController.validIsExist():", e);
            throw e;
        } finally {
        }
    }
}
