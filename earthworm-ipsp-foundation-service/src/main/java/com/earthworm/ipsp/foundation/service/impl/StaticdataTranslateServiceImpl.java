package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IStaticdataTranslateDao;
import com.earthworm.ipsp.foundation.entity.StaticdataTranslate;
import com.earthworm.ipsp.foundation.service.interf.IStaticdataTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StaticdataTranslateServiceImpl implements IStaticdataTranslateService {
    @Autowired
    private IStaticdataTranslateDao staticdataTranslateDao;

    @Override

    public List<StaticdataTranslate> findAll() {
        return staticdataTranslateDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return staticdataTranslateDao.deleteById(id);
    }

    @Override

    public StaticdataTranslate findById(String id) {
        return staticdataTranslateDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(StaticdataTranslate staticdataTranslate) {
        return staticdataTranslateDao.update(staticdataTranslate);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(StaticdataTranslate staticdataTranslate) {
        staticdataTranslate.setTraId(UUID.randomUUID().toString());
        return staticdataTranslateDao.save(staticdataTranslate);
    }

    @Override

    public List<StaticdataTranslate> find(StaticdataTranslate staticdataTranslate) {
        return staticdataTranslateDao.find(staticdataTranslate);
    }

    @Override

    public long countAll() {
        return staticdataTranslateDao.countAll();
    }

    public List<StaticdataTranslate> findLikeInfo(StaticdataTranslate staticdataTranslate){
        return staticdataTranslateDao.findLikeInfo(staticdataTranslate);
    };

}
