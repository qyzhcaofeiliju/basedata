package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.SuitcaseProfile;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface ISuitcaseProfileDao extends BaseDao<SuitcaseProfile> {
    @Override
    int save(SuitcaseProfile suitcaseProfile);

    @Override
    long saveBatch(List<SuitcaseProfile> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(SuitcaseProfile suitcaseProfile);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(SuitcaseProfile suitcaseProfile);

    @Override
    int updateBatch(List<SuitcaseProfile> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    SuitcaseProfile findById(String id);

    @Override
    List<SuitcaseProfile> findAll();

    @Override
    List<SuitcaseProfile> find(SuitcaseProfile suitcaseProfile);

    @Override
    List<SuitcaseProfile> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(SuitcaseProfile suitcaseProfile);

    @Override
    long countByCondition(Map<String, Object> condition);
}
