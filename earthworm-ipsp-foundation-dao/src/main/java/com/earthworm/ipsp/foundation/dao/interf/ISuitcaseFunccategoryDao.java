package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.SuitcaseFunccategory;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface ISuitcaseFunccategoryDao extends BaseDao<SuitcaseFunccategory> {
    @Override
    int save(SuitcaseFunccategory suitcaseFunccategory);

    @Override
    long saveBatch(List<SuitcaseFunccategory> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(SuitcaseFunccategory suitcaseFunccategory);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(SuitcaseFunccategory suitcaseFunccategory);

    @Override
    int updateBatch(List<SuitcaseFunccategory> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    SuitcaseFunccategory findById(String id);

    @Override
    List<SuitcaseFunccategory> findAll();

    @Override
    List<SuitcaseFunccategory> find(SuitcaseFunccategory suitcaseFunccategory);

    @Override
    List<SuitcaseFunccategory> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(SuitcaseFunccategory suitcaseFunccategory);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<SuitcaseFunccategory> findLikeInfo(SuitcaseFunccategory suitcaseFunccategory);
}
