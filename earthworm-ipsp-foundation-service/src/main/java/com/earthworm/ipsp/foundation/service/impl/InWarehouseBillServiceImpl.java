package com.earthworm.ipsp.foundation.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IInWarehouseBillDao;
import com.earthworm.ipsp.foundation.dao.interf.IInWarehouseDetailBillDao;
import com.earthworm.ipsp.foundation.entity.InWarehouseBill;
import com.earthworm.ipsp.foundation.entity.InWarehouseDetailBill;
import com.earthworm.ipsp.foundation.service.InWHandlerService;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.IInWarehouseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InWarehouseBillServiceImpl implements IInWarehouseBillService {

    @Autowired
    private IInWarehouseBillDao inWarehouseBillDao;
    @Autowired
    private IInWarehouseDetailBillDao inWarehouseDetailBillDao;

    @Override
    @Transactional(readOnly = true)
    public List<InWarehouseBill> findAll() {
        return inWarehouseBillDao.findAll();
    }

    /**
     * @param id
     * @return
     * @throws IPSPFoundationServiceException
     */
    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) throws IPSPFoundationServiceException {
        //根据ID获取入库单的信息，取出其编号
        InWarehouseBill inWarehouseBill = inWarehouseBillDao.findById(id);
        String inwBillsnumber = inWarehouseBill.getInwBillsnumber();
        InWarehouseDetailBill inWarehouseDetailBill = new InWarehouseDetailBill();
        inWarehouseDetailBill.setInwBillsnumber(inwBillsnumber);
        //根据取出的编号查询此订单编号的入库单详情信息
        List<InWarehouseDetailBill> inWarehouseDetailBills = inWarehouseDetailBillDao.find(inWarehouseDetailBill);
        //删除入库单信息
        int inwTemp = inWarehouseBillDao.deleteById(id);
        int detailInwIdTemp = 0;
        //删除入库单详情
        for (int i = 0; i < inWarehouseDetailBills.size(); i++) {
            detailInwIdTemp += inWarehouseDetailBillDao.deleteById(inWarehouseDetailBills.get(i).getInwId());
        }
        if (detailInwIdTemp == 0 && inwTemp == 0) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("InWarehouseBillService.deleteById:DELETE inWarehouse failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_DEL_ERR);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    public InWarehouseBill findById(String id) {
        return inWarehouseBillDao.findById(id);
    }

    @Override
    public int update(InWarehouseBill inWarehouseBill) {
        return inWarehouseBillDao.update(inWarehouseBill);
    }

    /**
     * @param inWarehouseBill
     * @param inWarehouseDetailBill
     * @return
     * @throws IPSPFoundationServiceException
     */
    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(InWarehouseBill inWarehouseBill, InWarehouseDetailBill inWarehouseDetailBill) throws IPSPFoundationServiceException {
        //修改入库单
        int update = inWarehouseBillDao.update(inWarehouseBill);
        //修改入库单详情
        int update1 = inWarehouseDetailBillDao.update(inWarehouseDetailBill);
        //任意一个修改失败抛异常进行事务回滚
        if (update != 1 || update1 != 1) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("InWarehouseBillService.update:update inWarehouse failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_UPT_ERR);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(InWarehouseBill inWarehouseBill) {
        inWarehouseBill.setInwId(UUID.randomUUID().toString());
        return inWarehouseBillDao.save(inWarehouseBill);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(InWarehouseBill inWarehouseBill, InWarehouseDetailBill inWarehouseDetailBill) throws IPSPFoundationServiceException {
        //增加入库单
        inWarehouseBill.setInwId(UUID.randomUUID().toString());
        int save = inWarehouseBillDao.save(inWarehouseBill);
        //增加入库单详情
        inWarehouseDetailBill.setInwId(UUID.randomUUID().toString());
        int save1 = inWarehouseDetailBillDao.save(inWarehouseDetailBill);
        //任意一个增加失败抛异常进行事务回滚
        if (save != 1 || save1 != 1) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("InWarehouseBillService.save:Add inWarehouse failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_ADD_ERR);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    public List<InWarehouseBill> find(InWarehouseBill inWarehouseBill) {
        return inWarehouseBillDao.find(inWarehouseBill);
    }

    @Override
    public long countAll() {
        return inWarehouseBillDao.countAll();
    }

    //解析Excel方法
    @Override
    @Transactional
    public List prseExcelMethod(MultipartFile file, ServletResponse response) {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        List<InWarehouseDetailBill> inWarehouseDetailBill = new ArrayList<>();
        ImportParams params = new ImportParams();
        IExcelDataHandler<InWarehouseDetailBill> handler = new InWHandlerService<>();
        handler.setNeedHandlerFields(new String[]{"入库数量", "实际入库数量", "入库状态", "货位排", "货位层", "货位列", "仓库索引"});
        params.setDataHanlder(handler);
        try {
            inWarehouseDetailBill = ExcelImportUtil.importExcel(file.getInputStream(), InWarehouseDetailBill.class, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inWarehouseDetailBill;

    }
}
