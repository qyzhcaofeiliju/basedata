package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/6 23:49
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface IOutWarehouseDetailBillService {
    List<OutWarehouseDetailBill> findAll();

    int deleteById(String outwId);

    OutWarehouseDetailBill findById(String outwId);

    int update(OutWarehouseDetailBill outWarehouseDetailBill);

    int save(OutWarehouseDetailBill outWarehouseDetailBill);

    List<OutWarehouseDetailBill> find(OutWarehouseDetailBill outWarehouseDetailBill);

    List<OutWarehouseDetailBill> findLikeInfo(OutWarehouseDetailBill outWarehouseDetailBill);

    long countAll();
}
