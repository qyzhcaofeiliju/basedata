package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IGoodslocationAxisdictionaryDao;
import com.earthworm.ipsp.foundation.entity.GoodslocationAxisdictionary;
import com.earthworm.ipsp.foundation.service.interf.IGoodslocationAxisdictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 9:29
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class GoodslocationAxisdictionaryServiceImpl implements IGoodslocationAxisdictionaryService {

    @Autowired
    private IGoodslocationAxisdictionaryDao goodslocationAxisdictionaryDao;


    @Override
    public List<GoodslocationAxisdictionary> findAll() {
        return goodslocationAxisdictionaryDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String axisdId) {
        return goodslocationAxisdictionaryDao.deleteById(axisdId);
    }

    @Override
    public GoodslocationAxisdictionary findById(String axisdId) {
        return goodslocationAxisdictionaryDao.findById(axisdId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(GoodslocationAxisdictionary goodslocationAxisdictionary) {
        return goodslocationAxisdictionaryDao.update(goodslocationAxisdictionary);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(GoodslocationAxisdictionary goodslocationAxisdictionary) {
        goodslocationAxisdictionary.setAxisdId(UUID.randomUUID().toString());
        return goodslocationAxisdictionaryDao.save(goodslocationAxisdictionary);
    }

    @Override
    public List<GoodslocationAxisdictionary> find(GoodslocationAxisdictionary goodslocationAxisdictionary) {
        return goodslocationAxisdictionaryDao.find(goodslocationAxisdictionary);
    }

    @Override
    public long countAll() {
        return goodslocationAxisdictionaryDao.countAll();
    }
}
