package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IVwGoodslocationAxisdictionaryDao;
import com.earthworm.ipsp.foundation.entity.VwGoodslocationAxisdictionary;
import com.earthworm.ipsp.foundation.service.interf.IVwGoodslocationAxisdictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class VwGoodslocationAxisdictionaryServiceImpl implements IVwGoodslocationAxisdictionaryService {

    @Autowired
    private IVwGoodslocationAxisdictionaryDao vwGoodslocationAxisdictionaryDao;


    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary) {
        return vwGoodslocationAxisdictionaryDao.save(vwGoodslocationAxisdictionary);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long saveBatch(List<VwGoodslocationAxisdictionary> list) {
        return vwGoodslocationAxisdictionaryDao.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return vwGoodslocationAxisdictionaryDao.deleteById(id);
    }

    @Override
    public long deleteAll() {
        return vwGoodslocationAxisdictionaryDao.deleteAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long delete(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary) {
        return vwGoodslocationAxisdictionaryDao.delete(vwGoodslocationAxisdictionary);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long cascadeDelete(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary) {
        return vwGoodslocationAxisdictionaryDao.cascadeDelete(vwGoodslocationAxisdictionary);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteByCondition(Map<String, Object> condition) {
        return vwGoodslocationAxisdictionaryDao.deleteByCondition(condition);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteByLikeQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary, VwGoodslocationAxisdictionary exclude) {
        return vwGoodslocationAxisdictionaryDao.deleteByLikeQuery(vwGoodslocationAxisdictionary, exclude);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary) {
        return vwGoodslocationAxisdictionaryDao.update(vwGoodslocationAxisdictionary);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int updateBatch(List<VwGoodslocationAxisdictionary> list) {
        return vwGoodslocationAxisdictionaryDao.updateBatch(list);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions) {
        return vwGoodslocationAxisdictionaryDao.updateByCondition(updateParam, conditions);
    }

    @Override
    public VwGoodslocationAxisdictionary findById(String id) {
        return vwGoodslocationAxisdictionaryDao.findById(id);
    }

    @Override
    public List<VwGoodslocationAxisdictionary> findAll() {
        return vwGoodslocationAxisdictionaryDao.findAll();
    }

    @Override
    public List<VwGoodslocationAxisdictionary> find(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary) {
        return vwGoodslocationAxisdictionaryDao.find(vwGoodslocationAxisdictionary);
    }

    @Override
    public List<VwGoodslocationAxisdictionary> findByCondition(Map<String, Object> condition) {
        return vwGoodslocationAxisdictionaryDao.findByCondition(condition);
    }

    @Override
    public List<VwGoodslocationAxisdictionary> findByOrCondition(Map<String, Object> condition) {
        return vwGoodslocationAxisdictionaryDao.findByOrCondition(condition);
    }

    @Override
    public List<VwGoodslocationAxisdictionary> findByOrQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary) {
        return vwGoodslocationAxisdictionaryDao.findByOrQuery(vwGoodslocationAxisdictionary);
    }

    @Override
    public List<VwGoodslocationAxisdictionary> findByLikeQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary, VwGoodslocationAxisdictionary exclude) {
        return vwGoodslocationAxisdictionaryDao.findByLikeQuery(vwGoodslocationAxisdictionary, exclude);
    }

    @Override
    public List<VwGoodslocationAxisdictionary> findByLikeCondition(Map<String, Object> condition) {
        return vwGoodslocationAxisdictionaryDao.findByLikeCondition(condition);
    }

    @Override
    public List<VwGoodslocationAxisdictionary> findByInCondition(Map<String, Object> condition) {
        return vwGoodslocationAxisdictionaryDao.findByInCondition(condition);
    }

    @Override
    public long countAll() {
        return vwGoodslocationAxisdictionaryDao.countAll();
    }

    @Override
    public long count(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary) {
        return vwGoodslocationAxisdictionaryDao.count(vwGoodslocationAxisdictionary);
    }

    @Override
    public long countByCondition(Map<String, Object> condition) {
        return vwGoodslocationAxisdictionaryDao.countByCondition(condition);
    }

    public List<VwGoodslocationAxisdictionary> findLikeInfo(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary){
        return vwGoodslocationAxisdictionaryDao.findLikeInfo(vwGoodslocationAxisdictionary);
    };
}
