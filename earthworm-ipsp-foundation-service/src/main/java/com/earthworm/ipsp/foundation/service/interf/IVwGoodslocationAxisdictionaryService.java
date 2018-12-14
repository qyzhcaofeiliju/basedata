package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.VwGoodslocationAxisdictionary;

import java.util.List;
import java.util.Map;

public interface IVwGoodslocationAxisdictionaryService {
    int save(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    long saveBatch(List<VwGoodslocationAxisdictionary> list);

    int deleteById(String id);

    long deleteAll();

    long delete(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    long cascadeDelete(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    long deleteByCondition(Map<String, Object> condition);

    long deleteByLikeQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary, VwGoodslocationAxisdictionary exclude);

    int update(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    int updateBatch(List<VwGoodslocationAxisdictionary> list);

    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    VwGoodslocationAxisdictionary findById(String id);

    List<VwGoodslocationAxisdictionary> findAll();

    List<VwGoodslocationAxisdictionary> find(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    List<VwGoodslocationAxisdictionary> findByCondition(Map<String, Object> condition);

    List<VwGoodslocationAxisdictionary> findByOrCondition(Map<String, Object> condition);

    List<VwGoodslocationAxisdictionary> findByOrQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    List<VwGoodslocationAxisdictionary> findByLikeQuery(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary, VwGoodslocationAxisdictionary exclude);

    List<VwGoodslocationAxisdictionary> findByLikeCondition(Map<String, Object> condition);

    List<VwGoodslocationAxisdictionary> findByInCondition(Map<String, Object> condition);

    long countAll();

    long count(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);

    long countByCondition(Map<String, Object> condition);

    List<VwGoodslocationAxisdictionary> findLikeInfo(VwGoodslocationAxisdictionary vwGoodslocationAxisdictionary);
}
