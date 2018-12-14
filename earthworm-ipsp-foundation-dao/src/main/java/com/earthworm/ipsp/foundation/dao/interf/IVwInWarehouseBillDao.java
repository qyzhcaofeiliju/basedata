package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.VwInWarehouseBill;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

@MapperScanner
public interface IVwInWarehouseBillDao extends BaseDao<VwInWarehouseBill>{
    @Override
    int save(VwInWarehouseBill vwInWarehouseBill);

    @Override
    long saveBatch(List<VwInWarehouseBill> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(VwInWarehouseBill vwInWarehouseBill);

    @Override
    long cascadeDelete(VwInWarehouseBill vwInWarehouseBill);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(VwInWarehouseBill vwInWarehouseBill, VwInWarehouseBill exclude);

    @Override
    int update(VwInWarehouseBill vwInWarehouseBill);

    @Override
    int updateBatch(List<VwInWarehouseBill> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    VwInWarehouseBill findById(String id);

    @Override
    List<VwInWarehouseBill> findAll();

    @Override
    List<VwInWarehouseBill> find(VwInWarehouseBill vwInWarehouseBill);

    @Override
    List<VwInWarehouseBill> findByCondition(Map<String, Object> condition);

    @Override
    List<VwInWarehouseBill> findByOrCondition(Map<String, Object> condition);

    @Override
    List<VwInWarehouseBill> findByOrQuery(VwInWarehouseBill vwInWarehouseBill);

    @Override
    List<VwInWarehouseBill> findByLikeQuery(VwInWarehouseBill vwInWarehouseBill, VwInWarehouseBill exclude);

    @Override
    List<VwInWarehouseBill> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<VwInWarehouseBill> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(VwInWarehouseBill vwInWarehouseBill);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<VwInWarehouseBill> findLikeInfo(VwInWarehouseBill vwInWarehouseBill);
}
