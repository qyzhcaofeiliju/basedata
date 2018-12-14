package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IVwInWarehouseBillDao;
import com.earthworm.ipsp.foundation.entity.VwInWarehouseBill;
import com.earthworm.ipsp.foundation.service.interf.IVwInWarehouseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VwInWarehouseBillServiceImpl implements IVwInWarehouseBillService {

    @Autowired
    private IVwInWarehouseBillDao vwInWarehouseBillDao;

    @Override
    public List<VwInWarehouseBill> findAll() {
        return vwInWarehouseBillDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return vwInWarehouseBillDao.deleteById(id);
    }

    @Override
    public VwInWarehouseBill findById(String id) {
        return vwInWarehouseBillDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(VwInWarehouseBill VwInWarehouseBill) {
        return vwInWarehouseBillDao.update(VwInWarehouseBill);
    }

    @Override
    public List<VwInWarehouseBill> find(VwInWarehouseBill VwInWarehouseBill) {
        return vwInWarehouseBillDao.find(VwInWarehouseBill);
    }

    @Override
    public long countAll() {
        return vwInWarehouseBillDao.countAll();
    }

    public List<VwInWarehouseBill> findLikeInfo(VwInWarehouseBill vwInWarehouseBill){
        return vwInWarehouseBillDao.findLikeInfo(vwInWarehouseBill);
    };
}
