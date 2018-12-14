package com.earthworm.ipsp.foundation.controller;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.MaterialflowStation;
import com.earthworm.ipsp.foundation.entity.MoveshelfLocation;
import com.earthworm.ipsp.foundation.entity.TerminalEquipment;
import com.earthworm.ipsp.foundation.service.interf.IMaterialflowStationService;
import com.earthworm.ipsp.foundation.service.interf.IMoveshelfLocationService;
import com.earthworm.ipsp.foundation.service.interf.ITerminalEquipmentService;
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
@RequestMapping("/moveshelfLocation")
public class MoveshelfLocationController {

    // sl4j 日志
    private Logger logger = LoggerFactory.getLogger(MoveshelfLocationController.class);

    @Autowired
    private IMoveshelfLocationService moveshelfLocationService;

    @Autowired
    private IMaterialflowStationService materialflowStationService;

    @Autowired
    private ITerminalEquipmentService terminalEquipmentService;

    /**
     * 首页，默认显示7条记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<MoveshelfLocation> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<MoveshelfLocation> list = moveshelfLocationService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "moveshelfLocation/moveshelfLocationView";
        } catch (Exception e) {
            logger.error("MoveshelfLocationController.view():", e);
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
            MoveshelfLocation moveshelfLocation = FastJsonUtil.toBean(formData, MoveshelfLocation.class);
            Page<MoveshelfLocation> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<MoveshelfLocation> list = moveshelfLocationService.findLikeInfo(moveshelfLocation);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("MoveshelfLocationController.jumpPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除
     *
     * @param mslId
     * @return
     */
    @RequestMapping(value = "/delete{mslId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("mslId") String mslId) throws EarthWormException {
        try {
            return moveshelfLocationService.deleteById(mslId);
        } catch (Exception e) {
            logger.error("MoveshelfLocationController.deleteById():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/update{mslId}", method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("mslId") String mslId, @RequestParam("data") String str) throws EarthWormException {
        try {
            MoveshelfLocation moveshelfLocation = FastJsonUtil.toBean(str, MoveshelfLocation.class);
            moveshelfLocation.setMslId(mslId);
            return moveshelfLocationService.update(moveshelfLocation);
        } catch (Exception e) {
            logger.error("MoveshelfLocationController.update():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            MoveshelfLocation moveshelfLocation = FastJsonUtil.toBean(str, MoveshelfLocation.class);
            Map<String, String> validate = ValidateHelper.validate(moveshelfLocation);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return moveshelfLocationService.save(moveshelfLocation);
        } catch (Exception e) {
            logger.error("MoveshelfLocationController.save():", e);
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
            return "moveshelfLocation/moveshelfLocationEdit";
        } catch (Exception e) {
            logger.error("MoveshelfLocationController.toEditPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 编辑页面，数据回显
     *
     * @param mslId
     * @param request
     * @return
     */
    @GetMapping("edit/{mslId}")
    public String dynamicToAddUpt(@PathVariable("mslId") @Nullable String mslId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(mslId)) {
                MoveshelfLocation moveshelfLocation = moveshelfLocationService.findById(mslId);
                request.setAttribute("moveshelfLocation", moveshelfLocation);

                // 下拉框数据显示
                selectLabData(request);
            }
            return "moveshelfLocation/moveshelfLocationEdit";
        } catch (Exception e) {
            logger.error("MoveshelfLocationController.dynamicToAddUpt():", e);
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
            MoveshelfLocation moveshelfLocation = FastJsonUtil.toBean(str, MoveshelfLocation.class);
            List<MoveshelfLocation> list = moveshelfLocationService.find(moveshelfLocation);

            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("MoveshelfLocationController.validIsExist():", e);
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
        // 查找功能分类
        List<TerminalEquipment> terminalEquipments = terminalEquipmentService.findAll();

        // 查找应用分类
        List<MaterialflowStation> materialflowStations = materialflowStationService.findAll();

        // 在edit页面做相关的下拉框操作
        request.setAttribute("terminalEquipments", terminalEquipments);
        request.setAttribute("materialflowStations", materialflowStations);
    }
}
