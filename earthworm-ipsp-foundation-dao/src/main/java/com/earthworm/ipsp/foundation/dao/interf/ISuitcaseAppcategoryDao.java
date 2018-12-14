package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.SuitcaseAppcategory;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface ISuitcaseAppcategoryDao extends BaseDao<SuitcaseAppcategory> {
    @Override
    int save(SuitcaseAppcategory suitcaseAppcategory);

    @Override
    long saveBatch(List<SuitcaseAppcategory> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(SuitcaseAppcategory suitcaseAppcategory);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(SuitcaseAppcategory suitcaseAppcategory);

    @Override
    int updateBatch(List<SuitcaseAppcategory> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    SuitcaseAppcategory findById(String id);

    @Override
    List<SuitcaseAppcategory> findAll();

    @Override
    List<SuitcaseAppcategory> find(SuitcaseAppcategory suitcaseAppcategory);

    @Override
    List<SuitcaseAppcategory> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(SuitcaseAppcategory suitcaseAppcategory);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<SuitcaseAppcategory> findLikeInfo(SuitcaseAppcategory suitcaseAppcategory);
}
