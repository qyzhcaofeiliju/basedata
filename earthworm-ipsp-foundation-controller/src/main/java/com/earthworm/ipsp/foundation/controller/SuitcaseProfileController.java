package com.earthworm.ipsp.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.*;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseAppcategoryService;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseFunccategoryService;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseProfileService;
import com.earthworm.ipsp.foundation.service.interf.IVwSuitcaseProfileService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/suitcaseProfile")
public class SuitcaseProfileController {

    @Autowired
    private ISuitcaseProfileService suitcaseProfileService;
    @Autowired
    private IVwSuitcaseProfileService vwSuitcaseProfileService;
    @Autowired
    private ISuitcaseAppcategoryService suitcaseAppcategoryService;
    @Autowired
    private ISuitcaseFunccategoryService suitcaseFunccategoryService;

    private static Logger logger = LoggerFactory.getLogger(SuitcaseProfileController.class);

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException {
        try {
            Page<VwSuitcaseProfile> page = new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<VwSuitcaseProfile> list = vwSuitcaseProfileService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page", page);
            request.setAttribute("pageList", pageList);
            request.setAttribute("list", list);
            return "suitcaseProfile/suitcaseProfileView";
        } catch (Exception ex) {
            logger.error("SuitcaseProfileController.findAll:Find SuitcaseProfile failed!", ex);
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
            VwSuitcaseProfile vwSuitcaseProfile = FastJsonUtil.toBean(formData, VwSuitcaseProfile.class);
            Page<VwSuitcaseProfile> page = new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<VwSuitcaseProfile> list = vwSuitcaseProfileService.findLikeInfo(vwSuitcaseProfile);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("pageList", pageList);
            map.put("list", list);
            return map;
        } catch (Exception ex) {
            logger.error("SuitcaseProfileController.jumpPage:Find SuitcaseProfile failed!", ex);
            throw ex;
        }
    }

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete{proId}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("proId") String id) throws EarthWormException {
        try {
            return suitcaseProfileService.deleteById(id);
        } catch (Exception ex) {
            logger.error("SuitcaseProfileController.deleteById:Delete SuitcaseProfile failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/update{proId}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(@PathVariable("proId") String id, @RequestParam("data") String str) throws EarthWormException {
        try {
            SuitcaseProfile suitcaseProfile = FastJsonUtil.toBean(str, SuitcaseProfile.class);
            suitcaseProfile.setProId(id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("info", suitcaseProfileService.update(suitcaseProfile));
            VwSuitcaseProfile vwSuitcaseProfile = vwSuitcaseProfileService.findById(id);
            map.put("vwSuitcaseProfile", vwSuitcaseProfile);
            return map;
        } catch (Exception ex) {
            logger.error("SuitcaseProfileController.update:Update SuitcaseProfile failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data") String str) throws EarthWormException {
        try {
            SuitcaseProfile suitcaseProfile = FastJsonUtil.toBean(str, SuitcaseProfile.class);
            Map<String, String> validate = ValidateHelper.validate(suitcaseProfile);
            if (validate != null && !validate.isEmpty()) {
                throw new EWValidationException(validate);
            }
            return suitcaseProfileService.save(suitcaseProfile);
        } catch (Exception ex) {
            logger.error("SuitcaseProfileController.save:Save SuitcaseProfile failed!", ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEditPage(HttpServletRequest request) throws EarthWormException {
        try {
            List<SuitcaseAppcategory> suitcaseAppcategories = suitcaseAppcategoryService.findAll();
            List<SuitcaseFunccategory> suitcaseFunccategories = suitcaseFunccategoryService.findAll();
            request.setAttribute("suitcaseAppcategories", suitcaseAppcategories);
            request.setAttribute("suitcaseFunccategories", suitcaseFunccategories);
            return "suitcaseProfile/suitcaseProfileEdit";
        } catch (Exception ex) {
            logger.error("SuitcaseProfileController.toEditPage:Find SuitcaseProfile failed!", ex);
            throw ex;
        }
    }

    // 编辑页面，数据回显
    @RequestMapping("edit/{funId}")
    public String dynamicToAddUpt(@PathVariable("funId") @Nullable String funId, HttpServletRequest request) throws EarthWormException {
        try {
            if (StringUtils.isNotBlank(funId)) {
                SuitcaseProfile suitcaseProfile = suitcaseProfileService.findById(funId);
                request.setAttribute("suitcaseProfile", suitcaseProfile);
            }
            List<SuitcaseAppcategory> suitcaseAppcategories = suitcaseAppcategoryService.findAll();
            List<SuitcaseFunccategory> suitcaseFunccategories = suitcaseFunccategoryService.findAll();
            request.setAttribute("suitcaseAppcategories", suitcaseAppcategories);
            request.setAttribute("suitcaseFunccategories", suitcaseFunccategories);
            return "suitcaseProfile/suitcaseProfileEdit";
        } catch (Exception ex) {
            logger.error("SuitcaseProfileController.dynamicToAddUpt:Find SuitcaseProfile failed!", ex);
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
            SuitcaseProfile suitcaseProfile = FastJsonUtil.toBean(str, SuitcaseProfile.class);
            List<SuitcaseProfile> list = suitcaseProfileService.find(suitcaseProfile);

            return list == null || list.isEmpty();
        } catch (Exception ex) {
            logger.error("SuitcaseProfileController.validIsExist:Find SuitcaseProfile failed!", ex);
            throw ex;
        }
    }

    /**
     * 查询应用和功能分类
     * @param request
     * @return
     */
    @GetMapping("/funAndAppFind")
    public String getImportInWareInfo(HttpServletRequest request) {
        List<SuitcaseAppcategory> suitcaseAppcategories = suitcaseAppcategoryService.findAll();
        List<SuitcaseFunccategory> suitcaseFunccategories = suitcaseFunccategoryService.findAll();
        request.setAttribute("suitcaseAppcategories", suitcaseAppcategories);
        request.setAttribute("suitcaseFunccategories", suitcaseFunccategories);
        return "suitcaseProfile/suitcaseProfileImport";
    }

    /**
     * 开始解析excel，并将解析后的数据，返回到页面上
     * @param file
     * @param response
     * @return
     */
    @PostMapping(value = "/showImportData",consumes = "multipart/form-data")
    public String showImportData(@RequestParam("file") MultipartFile file,ServletResponse response , HttpServletRequest request) {
        //通过入库单的ID，获得入库单，并将根据ID查找到旗下的详情单
        List<SuitcaseProfile> suitcaseProfiles= suitcaseProfileService.prseExcelMethod(file, response);
        request.setAttribute("list", suitcaseProfiles);
        return "suitcaseProfile/suitcaseProfileInfoView";
    }

    //导入入单信息，并显示
    @RequestMapping(value = "/saveImportData", method = {RequestMethod.POST}, consumes = "multipart/form-data")
    @ResponseBody
    public Boolean saveFile(@RequestParam(value = "file") MultipartFile file, @RequestParam String objDate, ServletResponse response) {
        //前台传来入库主表的数据,再解析详情表的信息,最后全部save到数据库中
        JSONObject object = JSONObject.parseObject(objDate);
        List<SuitcaseProfile> suitcaseProfiles = suitcaseProfileService.prseExcelMethod(file, response);
        if (suitcaseProfiles == null || suitcaseProfiles.size() == 0) {
            return null;
        }
        SuitcaseProfile suitcaseProfile = new SuitcaseProfile();
        for (SuitcaseProfile item : suitcaseProfiles) {
            suitcaseProfile.setProFunccategory(Short.parseShort(object.getString("value1")));
            suitcaseProfile.setProAppcategory(Short.parseShort(object.getString("value2")));
            suitcaseProfile.setProIspartition(object.getBoolean("value3"));
            suitcaseProfile.setProVerticalamount(Short.parseShort(object.getString("value4")));
            suitcaseProfile.setProHorizontalamount(Short.parseShort(object.getString("value2")));

            suitcaseProfile.setProUniquecode(item.getProUniquecode());
            suitcaseProfile.setProCabintotal(item.getProCabintotal());
            suitcaseProfile.setProDescription(item.getProDescription());
            suitcaseProfile.setProStatus(item.getProStatus());
            suitcaseProfile.setProRfid(item.getProRfid());
            suitcaseProfile.setProHeight(item.getProHeight());
            suitcaseProfile.setProWidth(item.getProWidth());
            suitcaseProfile.setProLength(item.getProLength());
            suitcaseProfile.setProCabinwidth(item.getProCabinwidth());
            suitcaseProfileService.save(suitcaseProfile);
        }
        return true;
    }
}
