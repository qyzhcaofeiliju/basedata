package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.BillsCategory;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IBillsCategoryDao extends BaseDao<BillsCategory> {
    @Override
    int save(BillsCategory billsCategory);

    @Override
    long saveBatch(List<BillsCategory> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(BillsCategory billsCategory);

    @Override
    long cascadeDelete(BillsCategory billsCategory);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(BillsCategory billsCategory, BillsCategory exclude);

    @Override
    int update(BillsCategory billsCategory);

    @Override
    int updateBatch(List<BillsCategory> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    BillsCategory findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<BillsCategory> findAll();

    @Override
    List<BillsCategory> find(BillsCategory billsCategory);

    @Override
    List<BillsCategory> findByCondition(Map<String, Object> condition);

    @Override
    List<BillsCategory> findByOrCondition(Map<String, Object> condition);

    @Override
    List<BillsCategory> findByOrQuery(BillsCategory billsCategory);

    @Override
    List<BillsCategory> findByLikeQuery(BillsCategory billsCategory, BillsCategory exclude);

    @Override
    List<BillsCategory> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<BillsCategory> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(BillsCategory billsCategory);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<BillsCategory> findLikeInfo(BillsCategory billsCategory);
}
