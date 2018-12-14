package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.ISuitcaseAppcategoryDao;
import com.earthworm.ipsp.foundation.entity.SuitcaseAppcategory;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseAppcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SuitcaseAppcategoryServiceImpl implements ISuitcaseAppcategoryService {

    @Autowired
    private ISuitcaseAppcategoryDao suitcaseAppcategoryDao;

    @Override

    public List<SuitcaseAppcategory> findAll() {
        return suitcaseAppcategoryDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return suitcaseAppcategoryDao.deleteById(id);
    }

    @Override

    public SuitcaseAppcategory findById(String id) {
        return suitcaseAppcategoryDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(SuitcaseAppcategory suitcaseAppcategory) {
        return suitcaseAppcategoryDao.update(suitcaseAppcategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(SuitcaseAppcategory suitcaseAppcategory) {
        suitcaseAppcategory.setAppId(UUID.randomUUID().toString());
        return suitcaseAppcategoryDao.save(suitcaseAppcategory);
    }

    @Override

    public List<SuitcaseAppcategory> find(SuitcaseAppcategory suitcaseAppcategory) {
        return suitcaseAppcategoryDao.find(suitcaseAppcategory);
    }

    @Override

    public long countAll() {
        return suitcaseAppcategoryDao.countAll();
    }

    public List<SuitcaseAppcategory> findLikeInfo(SuitcaseAppcategory suitcaseAppcategory){
        return suitcaseAppcategoryDao.findLikeInfo(suitcaseAppcategory);
    };
}
