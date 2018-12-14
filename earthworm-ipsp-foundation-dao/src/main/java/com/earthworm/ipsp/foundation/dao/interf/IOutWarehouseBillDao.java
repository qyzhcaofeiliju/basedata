package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.OutWarehouseBill;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 11:26
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface IOutWarehouseBillDao extends BaseDao<OutWarehouseBill> {
    @Override
    int save(OutWarehouseBill outWarehouseBill);

    @Override
    long saveBatch(List<OutWarehouseBill> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(OutWarehouseBill outWarehouseBill);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(OutWarehouseBill outWarehouseBill);

    @Override
    int updateBatch(List<OutWarehouseBill> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    OutWarehouseBill findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<OutWarehouseBill> findAll();

    @Override
    List<OutWarehouseBill> find(OutWarehouseBill outWarehouseBill);

    @Override
    List<OutWarehouseBill> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(OutWarehouseBill outWarehouseBill);

    @Override
    long countByCondition(Map<String, Object> condition);
}
