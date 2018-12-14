package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.InWarehouseDetailBill;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IInWarehouseDetailBillDao extends BaseDao<InWarehouseDetailBill> {
    @Override
    int save(InWarehouseDetailBill inWarehouseDetailBill);

    @Override
    long saveBatch(List<InWarehouseDetailBill> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(InWarehouseDetailBill inWarehouseDetailBill);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(InWarehouseDetailBill inWarehouseDetailBill);

    @Override
    int updateBatch(List<InWarehouseDetailBill> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    InWarehouseDetailBill findById(String id);

    @Override
    List<InWarehouseDetailBill> findAll();

    @Override
    List<InWarehouseDetailBill> find(InWarehouseDetailBill inWarehouseDetailBill);

    @Override
    List<InWarehouseDetailBill> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(InWarehouseDetailBill inWarehouseDetailBill);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<InWarehouseDetailBill> findLikeInfo(InWarehouseDetailBill inWarehouseDetailBill);
}
