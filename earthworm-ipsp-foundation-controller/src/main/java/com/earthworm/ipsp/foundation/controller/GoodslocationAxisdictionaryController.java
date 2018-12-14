package com.earthworm.ipsp.foundation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.*;
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
@RequestMapping("/goodslocationAxisdictionary")
public class GoodslocationAxisdictionaryController {

    // sl4j 日志相关
    private Logger logger = LoggerFactory.getLogger(GoodslocationAxisdictionaryController.class);

    @Autowired
    private IGoodslocationAxisdictionaryService goodslocationAxisdictionaryService;

    @Autowired
    private IVwGoodslocationAxisdictionaryService vwGoodslocationAxisdictionaryService;


    @Autowired
    private ITerminalEquipmentService terminalEquipmentService;

    @Autowired
    private ISuitcaseProfileService suitcaseProfileService;

    @Autowired
    private ISuitcaseFunccategoryService suitcaseFunccategoryService;

    // 用于下拉框中选择周转箱
    @Autowired
    private IVwSuitcaseProfileService vwSuitcaseProfileService;

    /**
     * 首页，默认显示7条记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request) throws EarthWormException {
        try {
            Page<VwGoodslocationAxisdictionary> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<VwGoodslocationAxisdictionary> list = vwGoodslocationAxisdictionaryService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "goodslocationAxisdictionary/goodslocationAxisdictionaryView";
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.view():", e);
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
    public Map<String, Object> jumpPage(HttpServletRequest request, @RequestParam("pageData") String pageData, @RequestParam("formData") String formData, @RequestParam("equId") String equId) throws EarthWormException {
        try {
            Map<String, Object> map = null;
            Page page1 = FastJsonUtil.toBean(pageData, Page.class);
            VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary = FastJsonUtil.toBean(formData, VwGoodslocationAxisdictionary.class);

            // 根据equId查询站点设备
            TerminalEquipment terminalEquipment = terminalEquipmentService.findById(equId);
            String equUniquecode = terminalEquipment.getEquUniquecode();
            if (terminalEquipment != null && StringUtils.isNotBlank(equUniquecode)) {
                vwGoodslocationAxisdictionary.setEquUniquecode(equUniquecode);
                Page<VwGoodslocationAxisdictionary> page = new Page<>();
                page.setPageNum(page1.getPageNum());
                page.setPageSize(page1.getPageSize());
                PageHelper.setPageParam(page);
                List<VwGoodslocationAxisdictionary> list =
                        vwGoodslocationAxisdictionaryService.findLikeInfo(vwGoodslocationAxisdictionary);
                int[] pageList = PageHelper.getPageList(page);
                map = new HashMap<>();
                map.put("page", page);
                map.put("pageList", pageList);
                map.put("list", list);
            }
            return map;

        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.jumpPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 根据Id删除
     *
     * @param axisdId
     * @return
     */
    @RequestMapping(value = "/delete{axisdId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("axisdId") String axisdId) throws EarthWormException {
        try {
            return goodslocationAxisdictionaryService.deleteById(axisdId);
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.deleteById():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/update{axisdId}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(@PathVariable("axisdId") String axisdId, @RequestParam("data") String str,
                                      HttpServletRequest request) throws EarthWormException {
        try {
            GoodslocationAxisdictionary goodslocationAxisdictionary = FastJsonUtil.toBean(str, GoodslocationAxisdictionary.class);
            goodslocationAxisdictionary.setAxisdId(axisdId);
            // 根据axisdId 查询出axisdEquId ，再赋值给需要更新的对象。
            String axisdEquId = goodslocationAxisdictionaryService.findById(axisdId).getAxisdEquId();
            goodslocationAxisdictionary.setAxisdEquId(axisdEquId);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("info", goodslocationAxisdictionaryService.update(goodslocationAxisdictionary));

            // 根据axisdId 查询VwGoodslocationAxisdictionary视图对象
            VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary =
                    vwGoodslocationAxisdictionaryService.findById(axisdId);
            map.put("vwGoodslocationAxisdictionary", vwGoodslocationAxisdictionary);
            selectLabData(request);
            return map;
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.update():", e);
            throw e;
        } finally {
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String data) throws EarthWormException, EWValidationException {
        try {
            JSONObject jsonObj = JSON.parseObject(data);
            String equId = jsonObj.getObject("equId", String.class);
            GoodslocationAxisdictionary goodslocationAxisdictionary =
                    jsonObj.getObject("goodslocationAxisdictionary", GoodslocationAxisdictionary.class);

            // 为货架货位设置属性AxisdEquId
            goodslocationAxisdictionary.setAxisdEquId(equId);
            Map<String, String> validate = ValidateHelper.validate(goodslocationAxisdictionary);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return goodslocationAxisdictionaryService.save(goodslocationAxisdictionary);
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.save():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 编辑页面，数据回显
     *
     * @param equId
     * @param axisdId
     * @param request
     * @return
     */
    @GetMapping("edit/{equId}/{axisdId}")
    public String edit(@PathVariable("equId") @Nullable String equId, @PathVariable("axisdId") @Nullable String axisdId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(axisdId)) {
                GoodslocationAxisdictionary goodslocationAxisdictionary
                        = goodslocationAxisdictionaryService.findById(axisdId);

                // 实例化vwSuitcaseProfileVO, 并设置ProFunccategory和ProUniquecode两个属性，
                // 查找 vwSuitcaseProfileVO 视图页面对象
                VwSuitcaseProfile vwSuitcaseProfileVO = new VwSuitcaseProfile();
                vwSuitcaseProfileVO.setProFunccategory(goodslocationAxisdictionary.getAxisdSuitcasetype());
                vwSuitcaseProfileVO.setProUniquecode(goodslocationAxisdictionary.getAxisdSuitcaseuniquecode());
                List<VwSuitcaseProfile> vwSuitcaseProfiles = vwSuitcaseProfileService.find(vwSuitcaseProfileVO);
                if (vwSuitcaseProfiles != null && vwSuitcaseProfiles.size() == 1) {
                    vwSuitcaseProfileVO = vwSuitcaseProfiles.get(0); // 取出list集合中的元素，有且只有一个;
                }
                request.setAttribute("vwSuitcaseProfileVO", vwSuitcaseProfileVO);
                request.setAttribute("equId", equId);
                request.setAttribute("goodslocationAxisdictionary", goodslocationAxisdictionary);
            }
            selectLabData(request);
            return "goodslocationAxisdictionary/goodslocationAxisdictionaryEdit";
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.edit():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 添加完站点设备之后，点击到下一步，跳转到新增货架货位edit页面
     * 货架货位信息添加页面
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/addPage")
    public String jump2AddPage(HttpServletRequest request) throws EarthWormException {
        try {
            selectLabData(request);
            return "goodslocationAxisdictionary/goodslocationAxisdictionaryEdit";
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.jump2AddPage():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 在货架货位view页面中点击新增按钮
     * 加载goodslocationAxisdictionaryEdit页面
     * 货架货位信息编辑
     *
     * @param equId
     * @param request
     * @return
     */
    @RequestMapping(value = "/detailAdd/{equId}")
    public String detailAdd(@PathVariable("equId") @Nullable String equId, HttpServletRequest request) throws EarthWormException {
        try {
            request.setAttribute("equId", equId);
            selectLabData(request);
            return "goodslocationAxisdictionary/goodslocationAxisdictionaryEdit";
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.detailAdd():", e);
            throw e;
        } finally {
        }
    }

    /**
     * 货架货位信息编辑
     *
     * @param equId
     * @param request
     * @return
     */
    @RequestMapping(value = "/detailUpt/{equId}")
    public String detailUpt(@PathVariable("equId") @Nullable String equId, HttpServletRequest request) throws EarthWormException {
        try {
            TerminalEquipment terminalEquipment = terminalEquipmentService.findById(equId);
            GoodslocationAxisdictionary goodslocationAxisdictionaryTemp = new GoodslocationAxisdictionary();
            goodslocationAxisdictionaryTemp.setAxisdEquId(equId);

            List<GoodslocationAxisdictionary> goodslocationAxisdictionaries = goodslocationAxisdictionaryService.find(goodslocationAxisdictionaryTemp);
            if (!goodslocationAxisdictionaries.isEmpty() || goodslocationAxisdictionaries.size() != 0) {
                GoodslocationAxisdictionary goodslocationAxisdictionary = goodslocationAxisdictionaries.get(0);


                // 根据axisdEquId,查找站点设备视图对象
                // 查询vwSuitcaseProfileDO视图对象
                VwSuitcaseProfile vwSuitcaseProfileVO = new VwSuitcaseProfile();
                vwSuitcaseProfileVO.setProUniquecode(goodslocationAxisdictionary.getAxisdSuitcaseuniquecode());
                vwSuitcaseProfileVO.setProFunccategory(goodslocationAxisdictionary.getAxisdSuitcasetype());

                List<VwSuitcaseProfile> vwSuitcaseProfileDOs = vwSuitcaseProfileService.find(vwSuitcaseProfileVO);
                if (vwSuitcaseProfileDOs != null && vwSuitcaseProfileDOs.size() == 1) {
                    vwSuitcaseProfileVO = vwSuitcaseProfileDOs.get(0);
                }
                request.setAttribute("equId", equId);
                request.setAttribute("vwSuitcaseProfileVO", vwSuitcaseProfileVO);
                request.setAttribute("goodslocationAxisdictionary", goodslocationAxisdictionary);
                selectLabData(request);
                return "goodslocationAxisdictionary/goodslocationAxisdictionaryEdit";
            } else {
                return "goodslocationAxisdictionary/errorMessageRemind";
            }
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.detailUpt():", e);
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
            GoodslocationAxisdictionary goodslocationAxisdictionary = FastJsonUtil.toBean(str, GoodslocationAxisdictionary.class);
            List<GoodslocationAxisdictionary> list = goodslocationAxisdictionaryService.find(goodslocationAxisdictionary);

            return list == null || list.isEmpty();
        } catch (Exception e) {
            logger.error("GoodslocationAxisdictionaryController.validIsExist():", e);
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
        // 查找终端设备
        List<TerminalEquipment> terminalEquipments = terminalEquipmentService.findAll();
        // 查找周装箱详情
        List<SuitcaseProfile> suitcaseProfiles = suitcaseProfileService.findAll();

        // 周转箱视图
        List<VwSuitcaseProfile> vwSuitcaseProfiles = vwSuitcaseProfileService.findAll();

        // 查找周转箱功能分类
        List<SuitcaseFunccategory> suitcaseFunccategories = suitcaseFunccategoryService.findAll();

        // 在edit页面做相关的下拉框操作
        request.setAttribute("terminalEquipments", terminalEquipments);
        request.setAttribute("suitcaseProfiles", suitcaseProfiles);
        request.setAttribute("vwSuitcaseProfiles", vwSuitcaseProfiles);
        request.setAttribute("suitcaseFunccategories", suitcaseFunccategories);
    }

}
