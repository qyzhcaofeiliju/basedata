package com.earthworm.ipsp.foundation.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IOutWarehouseBillDao;
import com.earthworm.ipsp.foundation.dao.interf.IOutWarehouseDetailBillDao;
import com.earthworm.ipsp.foundation.entity.OutWarehouseBill;
import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;
import com.earthworm.ipsp.foundation.service.OutWHandlerService;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.IOutWarehouseBillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 11:39
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class OutWarehouseBillServiceImpl implements IOutWarehouseBillService {


    private static Logger logger = LoggerFactory.getLogger(OutWarehouseBillServiceImpl.class);

    @Autowired
    private IOutWarehouseBillDao outWarehouseBillDao;

    @Autowired
    private IOutWarehouseDetailBillDao outWarehouseDetailBillDao;

    @Override
    public List<OutWarehouseBill> findAll() {
        return outWarehouseBillDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String outwId) throws IPSPFoundationServiceException {
        OutWarehouseBill outWarehouseBill = outWarehouseBillDao.findById(outwId);
        String outwBillsnumber = outWarehouseBill.getOutwBillsnumber();
        OutWarehouseDetailBill outWarehouseDetailBill = new OutWarehouseDetailBill();
        outWarehouseDetailBill.setOutwBillsnumber(outwBillsnumber);
        List<OutWarehouseDetailBill> outWarehouseDetailBills = outWarehouseDetailBillDao.find(outWarehouseDetailBill);
        int detailOutwIdTemp = 0;
        for (int i = 0; i < outWarehouseDetailBills.size(); i++) {
            detailOutwIdTemp = outWarehouseDetailBillDao.deleteById(outWarehouseDetailBills.get(i).getOutwId());
        }
        int outwTemp = outWarehouseBillDao.deleteById(outwId);
        if (detailOutwIdTemp == 0 && outwTemp == 0) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("DELETE outWarehouse failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_DEL_ERR);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    public OutWarehouseBill findById(String outwId) {
        return outWarehouseBillDao.findById(outwId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(OutWarehouseBill outWarehouseBill) {
        return outWarehouseBillDao.update(outWarehouseBill);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(OutWarehouseBill outWarehouseBill, OutWarehouseDetailBill outWarehouseDetailBill) throws IPSPFoundationServiceException {
        int update = outWarehouseBillDao.update(outWarehouseBill);
        int update1 = outWarehouseDetailBillDao.update(outWarehouseDetailBill);
        if (update != 1 || update1 != 1) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("update outWarehouse failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_UPT_ERR);
            logger.error("Upt outWarehouse failed!", ex);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(OutWarehouseBill outWarehouseBill) {
        outWarehouseBill.setOutwId(UUID.randomUUID().toString());
        return outWarehouseBillDao.save(outWarehouseBill);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(OutWarehouseBill outWarehouseBill, OutWarehouseDetailBill outWarehouseDetailBill) throws IPSPFoundationServiceException {
        outWarehouseBill.setOutwId(UUID.randomUUID().toString());
        int save = outWarehouseBillDao.save(outWarehouseBill);
        outWarehouseDetailBill.setOutwId(UUID.randomUUID().toString());
        int save1 = outWarehouseDetailBillDao.save(outWarehouseDetailBill);
        if (save != 1 || save1 != 1) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("Add outWarehouse failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_ADD_ERR);
            logger.error("Add outWarehouse failed!", ex);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    public List<OutWarehouseBill> find(OutWarehouseBill outWarehouseBill) {
        return outWarehouseBillDao.find(outWarehouseBill);
    }

    @Override
    public long countAll() {
        return outWarehouseBillDao.countAll();
    }

    @Override
    public List prseExcelMethod(MultipartFile file, ServletResponse response) {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        List<OutWarehouseDetailBill> outWarehouseDetailBill = new ArrayList<>();
        ImportParams params = new ImportParams();
        IExcelDataHandler<OutWarehouseDetailBill> handler = new OutWHandlerService();
        handler.setNeedHandlerFields(new String[]{"Bom单实际总数量", "Bom单需求量", "Bom单实际出库量", "出库状态", "厂库编号", "是否预出库"});
        params.setDataHanlder(handler);
        //数据处理
        try {
            outWarehouseDetailBill = ExcelImportUtil.importExcel(file.getInputStream(), OutWarehouseDetailBill.class, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outWarehouseDetailBill;
    }
}
