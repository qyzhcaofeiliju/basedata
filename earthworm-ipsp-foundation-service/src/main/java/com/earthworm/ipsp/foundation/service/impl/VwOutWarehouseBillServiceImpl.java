package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IVwOutWarehouseBillDao;
import com.earthworm.ipsp.foundation.entity.VwOutWarehouseBill;
import com.earthworm.ipsp.foundation.service.interf.IVwOutWarehouseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VwOutWarehouseBillServiceImpl implements IVwOutWarehouseBillService {

    @Autowired
    private IVwOutWarehouseBillDao vwOutWarehouseBillDao;

    @Override
    public List<VwOutWarehouseBill> findAll() {
        return vwOutWarehouseBillDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return vwOutWarehouseBillDao.deleteById(id);
    }

    @Override
    public VwOutWarehouseBill findById(String id) {
        return vwOutWarehouseBillDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(VwOutWarehouseBill vwOutWarehouseBill) {
        return vwOutWarehouseBillDao.update(vwOutWarehouseBill);
    }

    @Override
    public List<VwOutWarehouseBill> find(VwOutWarehouseBill vwOutWarehouseBill) {
        return vwOutWarehouseBillDao.find(vwOutWarehouseBill);
    }

    @Override
    public long countAll() {
        return vwOutWarehouseBillDao.countAll();
    }

    public List<VwOutWarehouseBill> findLikeInfo(VwOutWarehouseBill vwOutWarehouseBill){
        return vwOutWarehouseBillDao.findLikeInfo(vwOutWarehouseBill);
    };
}
