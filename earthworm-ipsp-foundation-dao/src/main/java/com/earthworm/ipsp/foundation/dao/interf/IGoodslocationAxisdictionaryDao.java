package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.GoodslocationAxisdictionary;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 9:00
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface IGoodslocationAxisdictionaryDao extends BaseDao<GoodslocationAxisdictionary> {
    @Override
    int save(GoodslocationAxisdictionary goodslocationAxisdictionary);

    @Override
    long saveBatch(List<GoodslocationAxisdictionary> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(GoodslocationAxisdictionary goodslocationAxisdictionary);

    @Override
    long cascadeDelete(GoodslocationAxisdictionary goodslocationAxisdictionary);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(GoodslocationAxisdictionary goodslocationAxisdictionary, GoodslocationAxisdictionary exclude);

    @Override
    int update(GoodslocationAxisdictionary goodslocationAxisdictionary);

    @Override
    int updateBatch(List<GoodslocationAxisdictionary> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    GoodslocationAxisdictionary findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<GoodslocationAxisdictionary> findAll();

    @Override
    List<GoodslocationAxisdictionary> find(GoodslocationAxisdictionary goodslocationAxisdictionary);

    @Override
    List<GoodslocationAxisdictionary> findByCondition(Map<String, Object> condition);

    @Override
    List<GoodslocationAxisdictionary> findByOrCondition(Map<String, Object> condition);

    @Override
    List<GoodslocationAxisdictionary> findByOrQuery(GoodslocationAxisdictionary goodslocationAxisdictionary);

    @Override
    List<GoodslocationAxisdictionary> findByLikeQuery(GoodslocationAxisdictionary goodslocationAxisdictionary, GoodslocationAxisdictionary exclude);

    @Override
    List<GoodslocationAxisdictionary> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<GoodslocationAxisdictionary> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(GoodslocationAxisdictionary goodslocationAxisdictionary);

    @Override
    long countByCondition(Map<String, Object> condition);
}
