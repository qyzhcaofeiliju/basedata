package com.earthworm.ipsp.foundation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.*;
import com.earthworm.ipsp.foundation.entity.validate.BackValidateHelper;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.*;
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
@RequestMapping("/terminalEquipment")
public class TerminalEquipmentController {

    // sl4j Logger
    private Logger logger = LoggerFactory.getLogger(TerminalEquipmentController.class);

    @Autowired
    private ITerminalEquipmentService terminalEquipmentService;

    @Autowired
    private IVwTerminalEquipmentService vwTerminalEquipmentService;

    @Autowired
    private ITerminalCategoryService terminalCategoryService;

    @Autowired
    private IMaterialflowStationService materialflowStationService;

    @Autowired
    private IVwGoodslocationAxisdictionaryService vwGoodslocationAxisdictionaryService;

    @Autowired
    private IGoodslocationAxisdictionaryService goodslocationAxisdictionaryService;

    /**
     * 首页，默认显示7条记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<VwTerminalEquipment> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<VwTerminalEquipment> list = vwTerminalEquipmentService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "terminalEquipment/terminalEquipmentView";
        } catch (Exception e) {
            logger.error("TerminalEquipmentController.view():", e);
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
            VwTerminalEquipment vwTerminalEquipment = FastJsonUtil.toBean(formData, VwTerminalEquipment.class);
            Page<VwTerminalEquipment> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<VwTerminalEquipment> list = vwTerminalEquipmentService.findLikeInfo(vwTerminalEquipment);
            int[] pageList = PageHelper.getPageList(page);
            map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception e) {
            logger.error("TerminalEquipmentController.jumpPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除
     *
     * @param equId
     * @return
     */
    @RequestMapping(value = "/delete{equId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("equId") String equId) throws EarthWormException {
        int deleteRow = 0;
        try {
            // 实例化GoodslocationAxisdictionary 并setAxisdEquId(equId) ；
            GoodslocationAxisdictionary goodslocationAxisdictionary = new GoodslocationAxisdictionary();
            goodslocationAxisdictionary.setAxisdEquId(equId);

            // 根据goodslocationAxisdictionary 进行查询
            List<GoodslocationAxisdictionary> goodslocationAxisdictionaries =
                    goodslocationAxisdictionaryService.find(goodslocationAxisdictionary);
            if (goodslocationAxisdictionaries != null && !goodslocationAxisdictionaries.isEmpty()) {
                // 证明 站点设备中数据被其他表所关联，此时返回deletRow = 0;
                return deleteRow;
            } else {
                deleteRow = terminalEquipmentService.deleteById(equId);// 如果没有关联，则可以进行删除，且返回值为1；
            }
            return deleteRow;
        } catch (Exception e) {
            logger.error("TerminalEquipmentController.deleteById():", e);
            throw e;
        } finally {
        }
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public TerminalEquipment update(@RequestParam("data") String str) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(str)) {
                // Update data
                TerminalEquipment terminalEquipment = FastJsonUtil.toBean(str, TerminalEquipment.class);
                String equId = terminalEquipment.getEquId();

                // Old data
                TerminalEquipment oldData = terminalEquipmentService.findById(terminalEquipment.getEquId());

                // Duplicate removal
                terminalEquipment = FastJsonUtil.duplicateRemoval(terminalEquipment, oldData);

                if (terminalEquipment!=null) {
                    BackValidateHelper.backValidateForUpdate(terminalEquipment);
                    terminalEquipment.setEquId(equId);
                    if (terminalEquipmentService.update(terminalEquipment)>0) {
                        // Update success. Research
                        terminalEquipment = terminalEquipmentService.findById(equId);

                        return terminalEquipment;
                    }
                }
            }

            return null;
        }
        catch (EWValidationException ex) {
            throw ex;
        }
        catch (Exception e) {
            logger.error("TerminalEquipmentController.update():", e);
            throw new EarthWormException(e, EarthWormException.INTERNAL_ERROR);
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException, EWValidationException {
        try {
            TerminalEquipment terminalEquipment = FastJsonUtil.toBean(str, TerminalEquipment.class);

            // 后台校验
            BackValidateHelper.backValidateForSave(terminalEquipment);
            return terminalEquipmentService.save(terminalEquipment);
        } catch (Exception ew) {
            logger.error("TerminalEquipmentController.save():", ew);
            throw ew;
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
            return "terminalEquipment/terminalEquipmentEdit";
        } catch (Exception e) {
            logger.error("TerminalEquipmentController.toEditPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 编辑页面，数据回显
     *
     * @param equId
     * @param request
     * @return
     */
    @GetMapping("edit/{equId}")
    public String dynamicToAddUpt(@PathVariable("equId") @Nullable String equId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(equId)) {
                TerminalEquipment terminalEquipment = terminalEquipmentService.findById(equId);
                request.setAttribute("terminalEquipment", terminalEquipment);

                // 下拉框数据显示
                selectLabData(request);
            }
            return "terminalEquipment/terminalEquipmentEdit";
        } catch (Exception e) {
            logger.error("TerminalEquipmentController.dynamicToAddUpt():", e);
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

        List<TerminalEquipment> list = null;
        try {
            TerminalEquipment terminalEquipment = FastJsonUtil.toBean(str, TerminalEquipment.class);
            list = terminalEquipmentService.find(terminalEquipment);
            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("TerminalEquipmentController.validIsExist():", e);
            throw e;
        } finally {
        }

    }

    //查询货架货位信息
    @RequestMapping("find/{equId}")
    public String find(@PathVariable("equId") @Nullable String equId, HttpServletRequest request) {
        try {
            Page<OutWarehouseDetailBill> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);

            // 根据equId查询TerminalEquipment
            TerminalEquipment terminalEquipment = terminalEquipmentService.findById(equId);
            String equUniquecode = terminalEquipment.getEquUniquecode();
            if (terminalEquipment != null && StringUtils.isNotBlank(equUniquecode)) {
                VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary = new VwGoodslocationAxisdictionary();
                vwGoodslocationAxisdictionary.setEquUniquecode(equUniquecode);
                List<VwGoodslocationAxisdictionary> list =
                        vwGoodslocationAxisdictionaryService.find(vwGoodslocationAxisdictionary);
                int[] pageList = PageHelper.getPageList(page);
                request.setAttribute("equId", equId);
                request.setAttribute("page", page);
                request.setAttribute("pageList", pageList);
                request.setAttribute("list", list);
            } else {
            }
            return "goodslocationAxisdictionary/goodslocationAxisdictionaryView";
        } catch (Exception e) {
            logger.error("TerminalEquipmentController.find():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/doubleSave", method = RequestMethod.POST)
    @ResponseBody
    public int doubleSave(@RequestParam("data") String data) throws EarthWormException {
        try {
            JSONObject jsonObj = JSON.parseObject(data);
            TerminalEquipment terminalEquipment = jsonObj.getObject("terminalEquipment", TerminalEquipment.class);
            GoodslocationAxisdictionary goodslocationAxisdictionary =
                    jsonObj.getObject("goodslocationAxisdictionary", GoodslocationAxisdictionary.class);
            // 对TerminalEquipment 站点设备做后台验证
            backValidate(terminalEquipment);

            // GoodslocationAxisdictionary 货架货位做后台验证
            backValidate(goodslocationAxisdictionary);
            return terminalEquipmentService.save(terminalEquipment, goodslocationAxisdictionary);
        } catch (IPSPFoundationServiceException e) {
            logger.error("TerminalEquipmentController.doubleSave():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/doubleUpt", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doubleUpt(@RequestParam("data") String data) throws EarthWormException {
        try {
            VwTerminalEquipment vwTerminalEquipment = null;
            JSONObject jsonObj = JSON.parseObject(data);
            TerminalEquipment terminalEquipment = jsonObj.getObject("terminalEquipment", TerminalEquipment.class);
            GoodslocationAxisdictionary goodslocationAxisdictionary =
                    jsonObj.getObject("goodslocationAxisdictionary", GoodslocationAxisdictionary.class);
            if ((terminalEquipment != null || terminalEquipment.getEquId() != null)
                    && (goodslocationAxisdictionary == null || goodslocationAxisdictionary.getAxisdId() == null)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("info", terminalEquipmentService.update(terminalEquipment));
                vwTerminalEquipment = vwTerminalEquipmentService.findById(terminalEquipment.getEquId());
                map.put("vwTerminalEquipment", vwTerminalEquipment);
                return map;
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("info", terminalEquipmentService.update(terminalEquipment, goodslocationAxisdictionary));
                vwTerminalEquipment = vwTerminalEquipmentService.findById(terminalEquipment.getEquId());
                map.put("vwTerminalEquipment", vwTerminalEquipment);
                return map;
            }
        } catch (Exception ex) {
            logger.error("TerminalEquipmentController.doubleUpt:update terminalEquipment failed!", ex);
            throw ex;
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
            // 查找设备分类
            List<TerminalCategory> terminalCategories = terminalCategoryService.findAll();

            // 查找物流站点信息
            List<MaterialflowStation> materialflowStations = materialflowStationService.findAll();

            // 在edit页面做相关的下拉框操作
            request.setAttribute("terminalCategories", terminalCategories);
            request.setAttribute("materialflowStations", materialflowStations);
        } catch (Exception e) {
            logger.error("TerminalEquipmentController.selectLabData():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 后台验证
     *
     * @param object
     */
    private void backValidate(Object object) {
        Map<String, String> validate = ValidateHelper.validate(object);
        if (validate != null && !validate.isEmpty()) {
            throw new EWValidationException(validate);
        }
    }
}
