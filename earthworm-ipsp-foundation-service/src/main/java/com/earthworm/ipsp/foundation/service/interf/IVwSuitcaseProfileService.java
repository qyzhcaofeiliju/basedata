package com.earthworm.ipsp.foundation.service.interf;


import com.earthworm.ipsp.foundation.entity.VwSuitcaseProfile;

import java.util.List;
import java.util.Map;

public interface IVwSuitcaseProfileService {

    int save(VwSuitcaseProfile vwSuitcaseProfile);

    long saveBatch(List<VwSuitcaseProfile> list);

    int deleteById(String id);

    long deleteAll();

    long delete(VwSuitcaseProfile vwSuitcaseProfile);

    long cascadeDelete(VwSuitcaseProfile vwSuitcaseProfile);

    long deleteByCondition(Map<String, Object> condition);

    long deleteByLikeQuery(VwSuitcaseProfile vwSuitcaseProfile, VwSuitcaseProfile exclude);

    int update(VwSuitcaseProfile vwSuitcaseProfile);

    int updateBatch(List<VwSuitcaseProfile> list);

    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    VwSuitcaseProfile findById(String id);

    List<VwSuitcaseProfile> findAll();

    List<VwSuitcaseProfile> find(VwSuitcaseProfile vwSuitcaseProfile);

    List<VwSuitcaseProfile> findByCondition(Map<String, Object> condition);

    List<VwSuitcaseProfile> findByOrCondition(Map<String, Object> condition);

    List<VwSuitcaseProfile> findByOrQuery(VwSuitcaseProfile vwSuitcaseProfile);

    List<VwSuitcaseProfile> findByLikeQuery(VwSuitcaseProfile vwSuitcaseProfile, VwSuitcaseProfile exclude);

    List<VwSuitcaseProfile> findByLikeCondition(Map<String, Object> condition);

    List<VwSuitcaseProfile> findByInCondition(Map<String, Object> condition);

    long countAll();

    long count(VwSuitcaseProfile vwSuitcaseProfile);

    long countByCondition(Map<String, Object> condition);

    List<VwSuitcaseProfile> findLikeInfo(VwSuitcaseProfile vwSuitcaseProfile);
}
