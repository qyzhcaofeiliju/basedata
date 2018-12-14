package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 14:04
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface IOutWarehouseDetailBillDao extends BaseDao<OutWarehouseDetailBill> {
    @Override
    int save(OutWarehouseDetailBill outWarehouseDetailBill);

    @Override
    long saveBatch(List<OutWarehouseDetailBill> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(OutWarehouseDetailBill outWarehouseDetailBill);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(OutWarehouseDetailBill outWarehouseDetailBill);

    @Override
    int updateBatch(List<OutWarehouseDetailBill> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    OutWarehouseDetailBill findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<OutWarehouseDetailBill> findAll();

    @Override
    List<OutWarehouseDetailBill> find(OutWarehouseDetailBill outWarehouseDetailBill);

    @Override
    List<OutWarehouseDetailBill> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(OutWarehouseDetailBill outWarehouseDetailBill);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<OutWarehouseDetailBill> findLikeInfo(OutWarehouseDetailBill outWarehouseDetailBill);
}
