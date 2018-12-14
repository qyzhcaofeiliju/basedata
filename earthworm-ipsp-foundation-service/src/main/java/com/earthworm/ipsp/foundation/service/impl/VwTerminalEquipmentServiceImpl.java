package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IVwTerminalEquipmentDao;
import com.earthworm.ipsp.foundation.entity.VwTerminalEquipment;
import com.earthworm.ipsp.foundation.service.interf.IVwTerminalEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class VwTerminalEquipmentServiceImpl implements IVwTerminalEquipmentService {

    @Autowired
    private IVwTerminalEquipmentDao vwTerminalEquipmentDao;

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(VwTerminalEquipment vwTerminalEquipment) {
        return vwTerminalEquipmentDao.save(vwTerminalEquipment);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long saveBatch(List<VwTerminalEquipment> list) {
        return vwTerminalEquipmentDao.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return vwTerminalEquipmentDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteAll() {
        return vwTerminalEquipmentDao.deleteAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long delete(VwTerminalEquipment vwTerminalEquipment) {
        return vwTerminalEquipmentDao.delete(vwTerminalEquipment);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long cascadeDelete(VwTerminalEquipment vwTerminalEquipment) {
        return vwTerminalEquipmentDao.cascadeDelete(vwTerminalEquipment);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteByCondition(Map<String, Object> condition) {
        return vwTerminalEquipmentDao.deleteByCondition(condition);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public long deleteByLikeQuery(VwTerminalEquipment vwTerminalEquipment, VwTerminalEquipment exclude) {
        return vwTerminalEquipmentDao.deleteByLikeQuery(vwTerminalEquipment, exclude);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(VwTerminalEquipment vwTerminalEquipment) {
        return vwTerminalEquipmentDao.update(vwTerminalEquipment);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int updateBatch(List<VwTerminalEquipment> list) {
        return vwTerminalEquipmentDao.updateBatch(list);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions) {
        return vwTerminalEquipmentDao.updateByCondition(updateParam, conditions);
    }

    @Override
    public VwTerminalEquipment findById(String id) {
        return vwTerminalEquipmentDao.findById(id);
    }

    @Override
    public List<VwTerminalEquipment> findAll() {
        return vwTerminalEquipmentDao.findAll();
    }

    @Override
    public List<VwTerminalEquipment> find(VwTerminalEquipment vwTerminalEquipment) {
        return vwTerminalEquipmentDao.find(vwTerminalEquipment);
    }

    @Override
    public List<VwTerminalEquipment> findByCondition(Map<String, Object> condition) {
        return vwTerminalEquipmentDao.findByCondition(condition);
    }

    @Override
    public List<VwTerminalEquipment> findByOrCondition(Map<String, Object> condition) {
        return vwTerminalEquipmentDao.findByCondition(condition);
    }

    @Override
    public List<VwTerminalEquipment> findByOrQuery(VwTerminalEquipment vwTerminalEquipment) {
        return vwTerminalEquipmentDao.findByOrQuery(vwTerminalEquipment);
    }

    @Override
    public List<VwTerminalEquipment> findByLikeQuery(VwTerminalEquipment vwTerminalEquipment, VwTerminalEquipment exclude) {
        return vwTerminalEquipmentDao.findByLikeQuery(vwTerminalEquipment, exclude);
    }

    @Override
    public List<VwTerminalEquipment> findByLikeCondition(Map<String, Object> condition) {
        return vwTerminalEquipmentDao.findByLikeCondition(condition);
    }

    @Override
    public List<VwTerminalEquipment> findByInCondition(Map<String, Object> condition) {
        return vwTerminalEquipmentDao.findByInCondition(condition);
    }

    @Override
    public long countAll() {
        return vwTerminalEquipmentDao.countAll();
    }

    @Override
    public long count(VwTerminalEquipment vwTerminalEquipment) {
        return vwTerminalEquipmentDao.count(vwTerminalEquipment);
    }

    @Override
    public long countByCondition(Map<String, Object> condition) {
        return vwTerminalEquipmentDao.countByCondition(condition);
    }

    public List<VwTerminalEquipment> findLikeInfo(VwTerminalEquipment vwTerminalEquipment){
        return vwTerminalEquipmentDao.findLikeInfo(vwTerminalEquipment);
    };
}
