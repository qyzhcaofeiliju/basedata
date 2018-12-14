package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IVwSuitcaseProfileDao;
import com.earthworm.ipsp.foundation.entity.VwSuitcaseProfile;
import com.earthworm.ipsp.foundation.service.interf.IVwSuitcaseProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class VwSuitcaseProfileServiceImpl implements IVwSuitcaseProfileService {

    @Autowired
    private IVwSuitcaseProfileDao vwSuitcaseProfileDao;

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(VwSuitcaseProfile vwSuitcaseProfile) {
        return vwSuitcaseProfileDao.save(vwSuitcaseProfile);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long saveBatch(List<VwSuitcaseProfile> list) {
        return vwSuitcaseProfileDao.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return vwSuitcaseProfileDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteAll() {
        return vwSuitcaseProfileDao.deleteAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long delete(VwSuitcaseProfile vwSuitcaseProfile) {
        return vwSuitcaseProfileDao.delete(vwSuitcaseProfile);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long cascadeDelete(VwSuitcaseProfile vwSuitcaseProfile) {
        return vwSuitcaseProfileDao.cascadeDelete(vwSuitcaseProfile);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteByCondition(Map<String, Object> condition) {
        return vwSuitcaseProfileDao.deleteByCondition(condition);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteByLikeQuery(VwSuitcaseProfile vwSuitcaseProfile, VwSuitcaseProfile exclude) {
        return vwSuitcaseProfileDao.deleteByLikeQuery(vwSuitcaseProfile, exclude);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(VwSuitcaseProfile vwSuitcaseProfile) {
        return vwSuitcaseProfileDao.update(vwSuitcaseProfile);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int updateBatch(List<VwSuitcaseProfile> list) {
        return vwSuitcaseProfileDao.updateBatch(list);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions) {
        return 0;
    }

    @Override
    public VwSuitcaseProfile findById(String id) {
        return vwSuitcaseProfileDao.findById(id);
    }

    @Override
    public List<VwSuitcaseProfile> findAll() {
        return vwSuitcaseProfileDao.findAll();
    }

    @Override
    public List<VwSuitcaseProfile> find(VwSuitcaseProfile vwSuitcaseProfile) {
        return vwSuitcaseProfileDao.find(vwSuitcaseProfile);
    }

    @Override
    public List<VwSuitcaseProfile> findByCondition(Map<String, Object> condition) {
        return vwSuitcaseProfileDao.findByOrCondition(condition);
    }

    @Override
    public List<VwSuitcaseProfile> findByOrCondition(Map<String, Object> condition) {
        return vwSuitcaseProfileDao.findByOrCondition(condition);
    }

    @Override
    public List<VwSuitcaseProfile> findByOrQuery(VwSuitcaseProfile vwSuitcaseProfile) {
        return vwSuitcaseProfileDao.findByOrQuery(vwSuitcaseProfile);
    }

    @Override
    public List<VwSuitcaseProfile> findByLikeQuery(VwSuitcaseProfile vwSuitcaseProfile, VwSuitcaseProfile exclude) {
        return vwSuitcaseProfileDao.findByLikeQuery(vwSuitcaseProfile, exclude);
    }

    @Override
    public List<VwSuitcaseProfile> findByLikeCondition(Map<String, Object> condition) {
        return vwSuitcaseProfileDao.findByLikeCondition(condition);
    }

    @Override
    public List<VwSuitcaseProfile> findByInCondition(Map<String, Object> condition) {
        return vwSuitcaseProfileDao.findByInCondition(condition);
    }

    @Override
    public long countAll() {
        return vwSuitcaseProfileDao.countAll();
    }

    @Override
    public long count(VwSuitcaseProfile vwSuitcaseProfile) {
        return vwSuitcaseProfileDao.count(vwSuitcaseProfile);
    }

    @Override
    public long countByCondition(Map<String, Object> condition) {
        return vwSuitcaseProfileDao.countByCondition(condition);
    }

    public List<VwSuitcaseProfile> findLikeInfo(VwSuitcaseProfile vwSuitcaseProfile){
        return vwSuitcaseProfileDao.findLikeInfo(vwSuitcaseProfile);
    };
}
