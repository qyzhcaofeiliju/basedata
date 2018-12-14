package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IVwTerminalCategoryDao;
import com.earthworm.ipsp.foundation.entity.VwTerminalCategory;
import com.earthworm.ipsp.foundation.service.interf.IVwTerminalCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class VwTerminalCategoryServiceImpl implements IVwTerminalCategoryService {

    @Autowired
    private IVwTerminalCategoryDao vwTerminalCategoryDao;

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(VwTerminalCategory vwTerminalCategory) {
        return vwTerminalCategoryDao.save(vwTerminalCategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long saveBatch(List<VwTerminalCategory> list) {
        return vwTerminalCategoryDao.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return vwTerminalCategoryDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteAll() {
        return vwTerminalCategoryDao.deleteAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long delete(VwTerminalCategory vwTerminalCategory) {
        return vwTerminalCategoryDao.delete(vwTerminalCategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long cascadeDelete(VwTerminalCategory vwTerminalCategory) {
        return vwTerminalCategoryDao.cascadeDelete(vwTerminalCategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteByCondition(Map<String, Object> condition) {
        return vwTerminalCategoryDao.deleteByCondition(condition);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteByLikeQuery(VwTerminalCategory vwTerminalCategory, VwTerminalCategory exclude) {
        return vwTerminalCategoryDao.deleteByLikeQuery(vwTerminalCategory, exclude);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(VwTerminalCategory vwTerminalCategory) {
        return vwTerminalCategoryDao.update(vwTerminalCategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int updateBatch(List<VwTerminalCategory> list) {
        return vwTerminalCategoryDao.updateBatch(list);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions) {
        return vwTerminalCategoryDao.updateByCondition(updateParam, conditions);
    }

    @Override
    public VwTerminalCategory findById(String id) {
        return vwTerminalCategoryDao.findById(id);
    }

    @Override
    public List<VwTerminalCategory> findAll() {
        return vwTerminalCategoryDao.findAll();
    }

    @Override
    public List<VwTerminalCategory> find(VwTerminalCategory vwTerminalCategory) {
        return vwTerminalCategoryDao.find(vwTerminalCategory);
    }

    @Override
    public List<VwTerminalCategory> findByCondition(Map<String, Object> condition) {
        return vwTerminalCategoryDao.findByCondition(condition);
    }

    @Override
    public List<VwTerminalCategory> findByOrCondition(Map<String, Object> condition) {
        return vwTerminalCategoryDao.findByOrCondition(condition);
    }

    @Override
    public List<VwTerminalCategory> findByOrQuery(VwTerminalCategory vwTerminalCategory) {
        return vwTerminalCategoryDao.findByOrQuery(vwTerminalCategory);
    }

    @Override
    public List<VwTerminalCategory> findByLikeQuery(VwTerminalCategory vwTerminalCategory, VwTerminalCategory exclude) {
        return vwTerminalCategoryDao.findByLikeQuery(vwTerminalCategory, exclude);
    }

    @Override
    public List<VwTerminalCategory> findByLikeCondition(Map<String, Object> condition) {
        return vwTerminalCategoryDao.findByOrCondition(condition);
    }

    @Override
    public List<VwTerminalCategory> findByInCondition(Map<String, Object> condition) {
        return vwTerminalCategoryDao.findByInCondition(condition);
    }

    @Override
    public long countAll() {
        return vwTerminalCategoryDao.countAll();
    }

    @Override
    public long count(VwTerminalCategory vwTerminalCategory) {
        return vwTerminalCategoryDao.count(vwTerminalCategory);
    }

    @Override
    public long countByCondition(Map<String, Object> condition) {
        return vwTerminalCategoryDao.countByCondition(condition);
    }

    public List<VwTerminalCategory> findLikeInfo(VwTerminalCategory vwTerminalCategory){
        return vwTerminalCategoryDao.findLikeInfo(vwTerminalCategory);
    };
}
