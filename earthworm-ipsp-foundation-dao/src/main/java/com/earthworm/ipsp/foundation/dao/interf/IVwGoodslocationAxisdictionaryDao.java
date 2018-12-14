package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.VwGoodslocationAxisdictionary;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IVwGoodslocationAxisdictionaryDao extends BaseDao<VwGoodslocationAxisdictionary> {
    @Override
    int save(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    @Override
    long saveBatch(List<VwGoodslocationAxisdictionary> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    @Override
    long cascadeDelete(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary, VwGoodslocationAxisdictionary exclude);

    @Override
    int update(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    @Override
    int updateBatch(List<VwGoodslocationAxisdictionary> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    VwGoodslocationAxisdictionary findById(String id);

    @Override
    List<VwGoodslocationAxisdictionary> findAll();

    @Override
    List<VwGoodslocationAxisdictionary> find(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    @Override
    List<VwGoodslocationAxisdictionary> findByCondition(Map<String, Object> condition);

    @Override
    List<VwGoodslocationAxisdictionary> findByOrCondition(Map<String, Object> condition);

    @Override
    List<VwGoodslocationAxisdictionary> findByOrQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    @Override
    List<VwGoodslocationAxisdictionary> findByLikeQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary, VwGoodslocationAxisdictionary exclude);

    @Override
    List<VwGoodslocationAxisdictionary> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<VwGoodslocationAxisdictionary> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<VwGoodslocationAxisdictionary> findLikeInfo(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);
}
