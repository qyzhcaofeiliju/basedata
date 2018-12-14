package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IOutWarehouseDetailBillDao;
import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;
import com.earthworm.ipsp.foundation.service.interf.IOutWarehouseDetailBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 14:40
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class OutWarehouseDetailBillServiceImpl implements IOutWarehouseDetailBillService {

    @Autowired
    private IOutWarehouseDetailBillDao outWarehouseDetailBillDao;

    @Override
    public List<OutWarehouseDetailBill> findAll() {
        return outWarehouseDetailBillDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String outwId) {
        return outWarehouseDetailBillDao.deleteById(outwId);
    }

    @Override
    public OutWarehouseDetailBill findById(String outwId) {
        return outWarehouseDetailBillDao.findById(outwId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(OutWarehouseDetailBill outWarehouseDetailBill) {
        return outWarehouseDetailBillDao.update(outWarehouseDetailBill);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(OutWarehouseDetailBill outWarehouseDetailBill) {
        outWarehouseDetailBill.setOutwId(UUID.randomUUID().toString());
        return outWarehouseDetailBillDao.save(outWarehouseDetailBill);
    }

    @Override
    public List<OutWarehouseDetailBill> find(OutWarehouseDetailBill outWarehouseDetailBill) {
        return outWarehouseDetailBillDao.find(outWarehouseDetailBill);
    }

    @Override
    public long countAll() {
        return outWarehouseDetailBillDao.countAll();
    }

    public List<OutWarehouseDetailBill> findLikeInfo(OutWarehouseDetailBill outWarehouseDetailBill){
        return outWarehouseDetailBillDao.findLikeInfo(outWarehouseDetailBill);
    };
}
