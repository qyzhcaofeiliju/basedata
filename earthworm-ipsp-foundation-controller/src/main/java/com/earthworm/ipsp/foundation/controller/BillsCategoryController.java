package com.earthworm.ipsp.foundation.controller;


import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.entity.BillsCategory;
import com.earthworm.ipsp.foundation.entity.InWarehouseBill;
import com.earthworm.ipsp.foundation.entity.OutWarehouseBill;
import com.earthworm.ipsp.foundation.service.interf.IBillsCategoryService;
import com.earthworm.ipsp.foundation.service.interf.IInWarehouseBillService;
import com.earthworm.ipsp.foundation.service.interf.IOutWarehouseBillService;
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
@RequestMapping("/billsCategory")
public class BillsCategoryController {
    @Autowired
    private IBillsCategoryService billsCategoryService;

    @Autowired
    private IInWarehouseBillService inWarehouseBillService;

    @Autowired
    private IOutWarehouseBillService outWarehouseBillService;

    private static Logger logger = LoggerFactory.getLogger(BillsCategoryController.class);

    /**
     * 访问首页
     * @param request
     * @return
     * @throws EarthWormException
     */
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String findAll(HttpServletRequest request) throws EarthWormException{
        try {
            Page<BillsCategory> page=new Page<>();
            page.setPageNum(1);
            page.setPageSize(7);
            PageHelper.setPageParam(page);
            List<BillsCategory> list= billsCategoryService.findAll();
            int[] pageList = PageHelper.getPageList(page);
            request.setAttribute("page",page);
            request.setAttribute("pageList",pageList);
            request.setAttribute("list",list);
            return "billsCategory/billsCategoryView";
        }
        catch (Exception ex){
            logger.error("BillsCategoryController.findAll:Find BillsCategory failed!",ex);
            throw ex;
        }
    }

    /**
     * 分页，条件查询
     * @param request
     * @param pageData
     * @param formData
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> jumpPage(HttpServletRequest request,@RequestParam("pageData") String pageData,@RequestParam("formData")String formData) throws EarthWormException {
        try {
            Page page1 = FastJsonUtil.toBean(pageData, Page.class);
            BillsCategory billsCategory = FastJsonUtil.toBean(formData, BillsCategory.class);
            Page<BillsCategory> page=new Page<>();
            page.setPageNum(page1.getPageNum());
            page.setPageSize(page1.getPageSize());
            PageHelper.setPageParam(page);
            List<BillsCategory> list= billsCategoryService.findLikeInfo(billsCategory);
            int[] pageList = PageHelper.getPageList(page);
            Map<String, Object> map = new HashMap<>();
            map.put("page",page);
            map.put("pageList",pageList);
            map.put("list",list);
            return map;
        }
        catch (Exception ex){
            logger.error("BillsCategoryController.jumpPage:Find BillsCategory failed!",ex);
            throw ex;
        }
    }
    /**
     * 根据Id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete{catId}",method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteById(@PathVariable("catId") String id) throws EarthWormException{
        try {
            //根据ID查出对应的信息，并取出其分类
            BillsCategory billsCategory = billsCategoryService.findById(id);
            Short catCategory = billsCategory.getCatCategory();
            InWarehouseBill inWarehouseBill = new InWarehouseBill();
            inWarehouseBill.setInwCategory(catCategory);
            //根据取出的分类查询入库单和出库单，如果有取出值，不执行此操作。如果没有，删除。
            List<InWarehouseBill> inWarehouseBills = inWarehouseBillService.find(inWarehouseBill);
            OutWarehouseBill outWarehouseBill = new OutWarehouseBill();
            outWarehouseBill.setOutwCategory(catCategory);
            List<OutWarehouseBill> outWarehouseBills = outWarehouseBillService.find(outWarehouseBill);
            if ((inWarehouseBills==null||inWarehouseBills.isEmpty())&&(outWarehouseBills==null||outWarehouseBills.isEmpty()))
            {
                return billsCategoryService.deleteById(id);
            }
            return 2;
        }
        catch (Exception ex){
            logger.error("BillsCategoryController.deleteById:Delete BillsCategory failed!",ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/update{catId}",method = RequestMethod.PUT)
    @ResponseBody
    public int update(@PathVariable("catId") String id, @RequestParam("data")String str) throws EarthWormException{
        try {
            BillsCategory billsCategory = FastJsonUtil.toBean(str, BillsCategory.class);
            billsCategory.setCatId(id);
            return billsCategoryService.update(billsCategory);
        }
        catch (Exception ex){
            logger.error("BillsCategoryController.update:Update BillsCategory failed!",ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestParam("data")String str) throws EarthWormException , EWValidationException{
        try {
            BillsCategory billsCategory = FastJsonUtil.toBean(str, BillsCategory.class);
            Map<String, String> validate = ValidateHelper.validate(billsCategory);
            if (validate!=null&&!validate.isEmpty()){
                throw  new EWValidationException(validate);
            }
            return billsCategoryService.save(billsCategory);
        }
        catch (Exception ex){
            logger.error("BillsCategoryController.save:Save BillsCategory failed!",ex);
            throw ex;
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String toEditPage(){
        return "billsCategory/billsCategoryEdit";
    }

    // 编辑页面，数据回显
    @RequestMapping("edit/{catId}")
    public String edit(@PathVariable("catId") @Nullable String catId, HttpServletRequest request) throws EarthWormException{
        try {
            if(StringUtils.isNotBlank(catId)) {
                BillsCategory billsCategory = billsCategoryService.findById(catId);
                request.setAttribute("billsCategory",billsCategory);
            }
            return "billsCategory/billsCategoryEdit";
        }
        catch (Exception ex){
            logger.error("BillsCategoryController.edit:Update BillsCategory failed!",ex);
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
            BillsCategory billsCategory = FastJsonUtil.toBean(str, BillsCategory.class);
            List<BillsCategory> list = billsCategoryService.find(billsCategory);

            return  list==null||list.isEmpty();
        }
        catch (Exception ex){
            logger.error("BillsCategoryController.validIsExist:find BillsCategory failed!",ex);
            throw ex;
        }
    }
}
