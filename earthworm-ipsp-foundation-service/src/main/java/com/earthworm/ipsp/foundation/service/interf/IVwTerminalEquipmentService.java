package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.VwTerminalEquipment;

import java.util.List;
import java.util.Map;

public interface IVwTerminalEquipmentService {
    int save(VwTerminalEquipment vwTerminalEquipment);

    long saveBatch(List<VwTerminalEquipment> list);

    int deleteById(String id);

    long deleteAll();

    long delete(VwTerminalEquipment vwTerminalEquipment);

    long cascadeDelete(VwTerminalEquipment vwTerminalEquipment);

    long deleteByCondition(Map<String, Object> condition);

    long deleteByLikeQuery(VwTerminalEquipment vwTerminalEquipment, VwTerminalEquipment exclude);

    int update(VwTerminalEquipment vwTerminalEquipment);

    int updateBatch(List<VwTerminalEquipment> list);

    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    VwTerminalEquipment findById(String id);

    List<VwTerminalEquipment> findAll();

    List<VwTerminalEquipment> find(VwTerminalEquipment vwTerminalEquipment);

    List<VwTerminalEquipment> findByCondition(Map<String, Object> condition);

    List<VwTerminalEquipment> findByOrCondition(Map<String, Object> condition);

    List<VwTerminalEquipment> findByOrQuery(VwTerminalEquipment vwTerminalEquipment);

    List<VwTerminalEquipment> findByLikeQuery(VwTerminalEquipment vwTerminalEquipment, VwTerminalEquipment exclude);

    List<VwTerminalEquipment> findByLikeCondition(Map<String, Object> condition);

    List<VwTerminalEquipment> findByInCondition(Map<String, Object> condition);

    long countAll();

    long count(VwTerminalEquipment vwTerminalEquipment);

    long countByCondition(Map<String, Object> condition);

    List<VwTerminalEquipment> findLikeInfo(VwTerminalEquipment vwTerminalEquipment);
}
