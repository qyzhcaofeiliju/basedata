package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.VwSuitcaseProfile;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IVwSuitcaseProfileDao extends BaseDao<VwSuitcaseProfile> {
    @Override
    int save(VwSuitcaseProfile vwSuitcaseProfile);

    @Override
    long saveBatch(List<VwSuitcaseProfile> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(VwSuitcaseProfile vwSuitcaseProfile);

    @Override
    long cascadeDelete(VwSuitcaseProfile vwSuitcaseProfile);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(VwSuitcaseProfile vwSuitcaseProfile, VwSuitcaseProfile exclude);

    @Override
    int update(VwSuitcaseProfile vwSuitcaseProfile);

    @Override
    int updateBatch(List<VwSuitcaseProfile> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    VwSuitcaseProfile findById(String id);

    @Override
    List<VwSuitcaseProfile> findAll();

    @Override
    List<VwSuitcaseProfile> find(VwSuitcaseProfile vwSuitcaseProfile);

    @Override
    List<VwSuitcaseProfile> findByCondition(Map<String, Object> condition);

    @Override
    List<VwSuitcaseProfile> findByOrCondition(Map<String, Object> condition);

    @Override
    List<VwSuitcaseProfile> findByOrQuery(VwSuitcaseProfile vwSuitcaseProfile);

    @Override
    List<VwSuitcaseProfile> findByLikeQuery(VwSuitcaseProfile vwSuitcaseProfile, VwSuitcaseProfile exclude);

    @Override
    List<VwSuitcaseProfile> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<VwSuitcaseProfile> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(VwSuitcaseProfile vwSuitcaseProfile);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<VwSuitcaseProfile> findLikeInfo(VwSuitcaseProfile vwSuitcaseProfile);
}
