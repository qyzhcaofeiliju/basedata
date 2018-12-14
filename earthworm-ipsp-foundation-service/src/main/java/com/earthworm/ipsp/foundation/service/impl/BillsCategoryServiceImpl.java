package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IBillsCategoryDao;
import com.earthworm.ipsp.foundation.entity.BillsCategory;
import com.earthworm.ipsp.foundation.service.interf.IBillsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BillsCategoryServiceImpl implements IBillsCategoryService {

    @Autowired
    private IBillsCategoryDao billsCategoryDao;

    @Override

    public List<BillsCategory> findAll() {
        return billsCategoryDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return billsCategoryDao.deleteById(id);
    }

    @Override

    public BillsCategory findById(String id) {
        return billsCategoryDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(BillsCategory billsCategory) {
        return billsCategoryDao.update(billsCategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(BillsCategory billsCategory) {
        billsCategory.setCatId(UUID.randomUUID().toString());
        return billsCategoryDao.save(billsCategory);
    }

    @Override

    public List<BillsCategory> find(BillsCategory billsCategory) {
        return billsCategoryDao.find(billsCategory);
    }

    @Override
    public long countAll() {
        return billsCategoryDao.countAll();
    }

    public List<BillsCategory> findLikeInfo(BillsCategory billsCategory){
        return billsCategoryDao.findLikeInfo(billsCategory);
    };
}
