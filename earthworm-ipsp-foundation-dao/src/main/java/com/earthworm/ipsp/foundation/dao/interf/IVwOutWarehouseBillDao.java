package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.VwOutWarehouseBill;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IVwOutWarehouseBillDao extends BaseDao<VwOutWarehouseBill>{
    @Override
    int save(VwOutWarehouseBill vwOutWarehouseBill);

    @Override
    long saveBatch(List<VwOutWarehouseBill> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(VwOutWarehouseBill vwOutWarehouseBill);

    @Override
    long cascadeDelete(VwOutWarehouseBill vwOutWarehouseBill);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(VwOutWarehouseBill vwOutWarehouseBill, VwOutWarehouseBill exclude);

    @Override
    int update(VwOutWarehouseBill vwOutWarehouseBill);

    @Override
    int updateBatch(List<VwOutWarehouseBill> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    VwOutWarehouseBill findById(String id);

    @Override
    List<VwOutWarehouseBill> findAll();

    @Override
    List<VwOutWarehouseBill> find(VwOutWarehouseBill vwOutWarehouseBill);

    @Override
    List<VwOutWarehouseBill> findByCondition(Map<String, Object> condition);

    @Override
    List<VwOutWarehouseBill> findByOrCondition(Map<String, Object> condition);

    @Override
    List<VwOutWarehouseBill> findByOrQuery(VwOutWarehouseBill vwOutWarehouseBill);

    @Override
    List<VwOutWarehouseBill> findByLikeQuery(VwOutWarehouseBill vwOutWarehouseBill, VwOutWarehouseBill exclude);

    @Override
    List<VwOutWarehouseBill> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<VwOutWarehouseBill> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(VwOutWarehouseBill vwOutWarehouseBill);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<VwOutWarehouseBill> findLikeInfo(VwOutWarehouseBill vwOutWarehouseBill);
}
