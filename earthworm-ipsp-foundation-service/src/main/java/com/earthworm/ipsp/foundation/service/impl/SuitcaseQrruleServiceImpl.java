package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.ipsp.foundation.dao.interf.ISuitcaseQrruleDao;
import com.earthworm.ipsp.foundation.entity.SuitcaseQrrule;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseQrruleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: chenrh@i-earthworm.com
 * @Date: 2018/11/22 23:06
 * @Description:
 */
@Service
public class SuitcaseQrruleServiceImpl implements ISuitcaseQrruleService {

    @Autowired
    private ISuitcaseQrruleDao iSuitcaseQrruleDao;

    @Override
    public int save(SuitcaseQrrule suitcaseQrrule) {
        return iSuitcaseQrruleDao.save(suitcaseQrrule);
    }

    @Override
    public long saveBatch(List<SuitcaseQrrule> list) {
        return iSuitcaseQrruleDao.saveBatch(list);
    }

    @Override
    public int deleteById(String id) {
        return iSuitcaseQrruleDao.deleteById(id);
    }

    @Override
    public long deleteAll() {
        return iSuitcaseQrruleDao.deleteAll();
    }

    @Override
    public long delete(SuitcaseQrrule suitcaseQrrule) {
        return iSuitcaseQrruleDao.delete(suitcaseQrrule);
    }

    @Override
    public long cascadeDelete(SuitcaseQrrule suitcaseQrrule) {
        return iSuitcaseQrruleDao.cascadeDelete(suitcaseQrrule);
    }

    @Override
    public long deleteByCondition(Map<String, Object> condition) {
        return iSuitcaseQrruleDao.deleteByCondition(condition);
    }

    @Override
    public long deleteByLikeQuery(SuitcaseQrrule suitcaseQrrule, SuitcaseQrrule exclude) {
        return iSuitcaseQrruleDao.deleteByLikeQuery(suitcaseQrrule, exclude);
    }

    @Override
    public int update(SuitcaseQrrule suitcaseQrrule) {
        return iSuitcaseQrruleDao.update(suitcaseQrrule);
    }

    @Override
    public int updateBatch(List<SuitcaseQrrule> list) {
        return iSuitcaseQrruleDao.updateBatch(list);
    }

    @Override
    public int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions) {
        return iSuitcaseQrruleDao.updateByCondition(updateParam, conditions);
    }

    @Override
    public SuitcaseQrrule findById(String id) {
        return iSuitcaseQrruleDao.findById(id);
    }

    @Override
    public List<SuitcaseQrrule> findAll() {
        return iSuitcaseQrruleDao.findAll();
    }

    @Override
    public List<SuitcaseQrrule> find(SuitcaseQrrule suitcaseQrrule) {
        return iSuitcaseQrruleDao.find(suitcaseQrrule);
    }

    @Override
    public List<SuitcaseQrrule> findByCondition(Map<String, Object> condition) {
        return iSuitcaseQrruleDao.findByCondition(condition);
    }

    @Override
    public List<SuitcaseQrrule> findByOrCondition(Map<String, Object> condition) {
        return iSuitcaseQrruleDao.findByOrCondition(condition);
    }

    @Override
    public List<SuitcaseQrrule> findByOrQuery(SuitcaseQrrule suitcaseQrrule) {
        return iSuitcaseQrruleDao.findByOrQuery(suitcaseQrrule);
    }

    @Override
    public List<SuitcaseQrrule> findByLikeQuery(SuitcaseQrrule suitcaseQrrule, SuitcaseQrrule exclude) {
        return iSuitcaseQrruleDao.findByLikeQuery(suitcaseQrrule, exclude);
    }

    @Override
    public List<SuitcaseQrrule> findByLikeCondition(Map<String, Object> condition) {
        return iSuitcaseQrruleDao.findByLikeCondition(condition);
    }

    @Override
    public List<SuitcaseQrrule> findByInCondition(Map<String, Object> condition) {
        return iSuitcaseQrruleDao.findByInCondition(condition);
    }

    @Override
    public long countAll() {
        return iSuitcaseQrruleDao.countAll();
    }

    @Override
    public long count(SuitcaseQrrule suitcaseQrrule) {
        return iSuitcaseQrruleDao.count(suitcaseQrrule);
    }

    @Override
    public long countByCondition(Map<String, Object> condition) {
        return iSuitcaseQrruleDao.countByCondition(condition);
    }

    @Override
    public List<SuitcaseQrrule> findLikeInfo(SuitcaseQrrule suitcaseQrrule) {
        return iSuitcaseQrruleDao.findLikeInfo(suitcaseQrrule);
    }






}
