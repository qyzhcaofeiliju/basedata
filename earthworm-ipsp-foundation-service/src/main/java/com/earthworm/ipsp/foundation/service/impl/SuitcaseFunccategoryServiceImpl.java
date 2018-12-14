package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.ISuitcaseFunccategoryDao;
import com.earthworm.ipsp.foundation.entity.SuitcaseFunccategory;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseFunccategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SuitcaseFunccategoryServiceImpl implements ISuitcaseFunccategoryService {

    @Autowired
    ISuitcaseFunccategoryDao suitcaseFunccategoryDao;

    @Override

    public List<SuitcaseFunccategory> findAll() {
        return suitcaseFunccategoryDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return suitcaseFunccategoryDao.deleteById(id);
    }

    @Override

    public SuitcaseFunccategory findById(String id) {
        return suitcaseFunccategoryDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(SuitcaseFunccategory suitcaseFunccategory) {
        return suitcaseFunccategoryDao.update(suitcaseFunccategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(SuitcaseFunccategory suitcaseFunccategory) {
        suitcaseFunccategory.setFunId(UUID.randomUUID().toString());
        return suitcaseFunccategoryDao.save(suitcaseFunccategory);
    }

    @Override

    public List<SuitcaseFunccategory> find(SuitcaseFunccategory suitcaseFunccategory) {
        return suitcaseFunccategoryDao.find(suitcaseFunccategory);
    }

    @Override

    public long countAll() {
        return suitcaseFunccategoryDao.countAll();
    }

    public List<SuitcaseFunccategory> findLikeInfo(SuitcaseFunccategory suitcaseFunccategory){
        return suitcaseFunccategoryDao.findLikeInfo(suitcaseFunccategory);
    };
}
