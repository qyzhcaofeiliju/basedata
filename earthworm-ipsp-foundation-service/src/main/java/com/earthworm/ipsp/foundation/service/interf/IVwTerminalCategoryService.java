package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.VwTerminalCategory;

import java.util.List;
import java.util.Map;

public interface IVwTerminalCategoryService {
    int save(VwTerminalCategory vwTerminalCategory);

    long saveBatch(List<VwTerminalCategory> list);

    int deleteById(String id);

    long deleteAll();

    long delete(VwTerminalCategory vwTerminalCategory);

    long cascadeDelete(VwTerminalCategory vwTerminalCategory);

    long deleteByCondition(Map<String, Object> condition);

    long deleteByLikeQuery(VwTerminalCategory vwTerminalCategory, VwTerminalCategory exclude);

    int update(VwTerminalCategory vwTerminalCategory);

    int updateBatch(List<VwTerminalCategory> list);

    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    VwTerminalCategory findById(String id);

    List<VwTerminalCategory> findAll();

    List<VwTerminalCategory> find(VwTerminalCategory vwTerminalCategory);

    List<VwTerminalCategory> findByCondition(Map<String, Object> condition);

    List<VwTerminalCategory> findByOrCondition(Map<String, Object> condition);

    List<VwTerminalCategory> findByOrQuery(VwTerminalCategory vwTerminalCategory);

    List<VwTerminalCategory> findByLikeQuery(VwTerminalCategory vwTerminalCategory, VwTerminalCategory exclude);

    List<VwTerminalCategory> findByLikeCondition(Map<String, Object> condition);

    List<VwTerminalCategory> findByInCondition(Map<String, Object> condition);

    long countAll();

    long count(VwTerminalCategory vwTerminalCategory);

    long countByCondition(Map<String, Object> condition);

    List<VwTerminalCategory> findLikeInfo(VwTerminalCategory vwTerminalCategory);
}
