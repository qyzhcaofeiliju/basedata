package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IInWarehouseDetailBillDao;
import com.earthworm.ipsp.foundation.entity.InWarehouseDetailBill;
import com.earthworm.ipsp.foundation.service.interf.IInWarehouseDetailBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class InWarehouseDetailBillServiceImpl implements IInWarehouseDetailBillService {

    @Autowired
    private IInWarehouseDetailBillDao inWarehouseDetailBillDao;

    @Override

    public List<InWarehouseDetailBill> findAll() {
        return inWarehouseDetailBillDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return inWarehouseDetailBillDao.deleteById(id);
    }

    @Override

    public InWarehouseDetailBill findById(String id) {
        return inWarehouseDetailBillDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(InWarehouseDetailBill inWarehouseDetailBill) {
        return inWarehouseDetailBillDao.update(inWarehouseDetailBill);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(InWarehouseDetailBill inWarehouseDetailBill) {
        inWarehouseDetailBill.setInwId(UUID.randomUUID().toString());
        return inWarehouseDetailBillDao.save(inWarehouseDetailBill);
    }

    @Override

    public List<InWarehouseDetailBill> find(InWarehouseDetailBill inWarehouseDetailBill) {
        return inWarehouseDetailBillDao.find(inWarehouseDetailBill);
    }

    @Override

    public long countAll() {
        return inWarehouseDetailBillDao.countAll();
    }

    public List<InWarehouseDetailBill> findLikeInfo(InWarehouseDetailBill inWarehouseDetailBill){
        return inWarehouseDetailBillDao.findLikeInfo(inWarehouseDetailBill);
    };
}
