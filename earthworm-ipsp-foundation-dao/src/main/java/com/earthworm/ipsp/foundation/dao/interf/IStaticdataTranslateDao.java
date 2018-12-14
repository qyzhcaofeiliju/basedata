package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.StaticdataTranslate;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IStaticdataTranslateDao extends BaseDao<StaticdataTranslate> {
    @Override
    int save(StaticdataTranslate staticdataTranslate);

    @Override
    long saveBatch(List<StaticdataTranslate> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(StaticdataTranslate staticdataTranslate);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(StaticdataTranslate staticdataTranslate);

    @Override
    int updateBatch(List<StaticdataTranslate> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    StaticdataTranslate findById(String id);

    @Override
    List<StaticdataTranslate> findAll();

    @Override
    List<StaticdataTranslate> find(StaticdataTranslate staticdataTranslate);

    @Override
    List<StaticdataTranslate> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(StaticdataTranslate staticdataTranslate);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<StaticdataTranslate> findLikeInfo(StaticdataTranslate staticdataTranslate);
}
