package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.VwTerminalCategory;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IVwTerminalCategoryDao extends BaseDao<VwTerminalCategory>{
    @Override
    int save(VwTerminalCategory vwTerminalCategory);

    @Override
    long saveBatch(List<VwTerminalCategory> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(VwTerminalCategory vwTerminalCategory);

    @Override
    long cascadeDelete(VwTerminalCategory vwTerminalCategory);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(VwTerminalCategory vwTerminalCategory, VwTerminalCategory exclude);

    @Override
    int update(VwTerminalCategory vwTerminalCategory);

    @Override
    int updateBatch(List<VwTerminalCategory> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    VwTerminalCategory findById(String id);

    @Override
    List<VwTerminalCategory> findAll();

    @Override
    List<VwTerminalCategory> find(VwTerminalCategory vwTerminalCategory);

    @Override
    List<VwTerminalCategory> findByCondition(Map<String, Object> condition);

    @Override
    List<VwTerminalCategory> findByOrCondition(Map<String, Object> condition);

    @Override
    List<VwTerminalCategory> findByOrQuery(VwTerminalCategory vwTerminalCategory);

    @Override
    List<VwTerminalCategory> findByLikeQuery(VwTerminalCategory vwTerminalCategory, VwTerminalCategory exclude);

    @Override
    List<VwTerminalCategory> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<VwTerminalCategory> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(VwTerminalCategory vwTerminalCategory);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<VwTerminalCategory> findLikeInfo(VwTerminalCategory vwTerminalCategory);
}
