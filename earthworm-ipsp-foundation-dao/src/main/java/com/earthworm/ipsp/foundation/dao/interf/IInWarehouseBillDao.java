package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.InWarehouseBill;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IInWarehouseBillDao extends BaseDao<InWarehouseBill> {
    @Override
    int save(InWarehouseBill inWarehouseBill);

    @Override
    long saveBatch(List<InWarehouseBill> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(InWarehouseBill inWarehouseBill);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(InWarehouseBill inWarehouseBill);

    @Override
    int updateBatch(List<InWarehouseBill> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    InWarehouseBill findById(String id);

    @Override
    List<InWarehouseBill> findAll();

    @Override
    List<InWarehouseBill> find(InWarehouseBill inWarehouseBill);

    @Override
    List<InWarehouseBill> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(InWarehouseBill inWarehouseBill);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<InWarehouseBill> findLikeInfo(InWarehouseBill inWarehouseBill);
}
