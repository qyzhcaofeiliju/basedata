package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.VwTerminalEquipment;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IVwTerminalEquipmentDao extends BaseDao<VwTerminalEquipment>{
    @Override
    int save(VwTerminalEquipment vwTerminalEquipment);

    @Override
    long saveBatch(List<VwTerminalEquipment> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(VwTerminalEquipment vwTerminalEquipment);

    @Override
    long cascadeDelete(VwTerminalEquipment vwTerminalEquipment);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(VwTerminalEquipment vwTerminalEquipment, VwTerminalEquipment exclude);

    @Override
    int update(VwTerminalEquipment vwTerminalEquipment);

    @Override
    int updateBatch(List<VwTerminalEquipment> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    VwTerminalEquipment findById(String id);

    @Override
    List<VwTerminalEquipment> findAll();

    @Override
    List<VwTerminalEquipment> find(VwTerminalEquipment vwTerminalEquipment);

    @Override
    List<VwTerminalEquipment> findByCondition(Map<String, Object> condition);

    @Override
    List<VwTerminalEquipment> findByOrCondition(Map<String, Object> condition);

    @Override
    List<VwTerminalEquipment> findByOrQuery(VwTerminalEquipment vwTerminalEquipment);

    @Override
    List<VwTerminalEquipment> findByLikeQuery(VwTerminalEquipment vwTerminalEquipment, VwTerminalEquipment exclude);

    @Override
    List<VwTerminalEquipment> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<VwTerminalEquipment> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(VwTerminalEquipment vwTerminalEquipment);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<VwTerminalEquipment> findLikeInfo(VwTerminalEquipment vwTerminalEquipment);
}
