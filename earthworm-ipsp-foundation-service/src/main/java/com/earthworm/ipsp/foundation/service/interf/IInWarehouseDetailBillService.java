package com.earthworm.ipsp.foundation.service.interf;


import com.earthworm.ipsp.foundation.entity.InWarehouseDetailBill;

import java.util.List;

public interface IInWarehouseDetailBillService {
    List<InWarehouseDetailBill> findAll();

    int deleteById(String id);

    InWarehouseDetailBill findById(String id);

    int update(InWarehouseDetailBill inWarehouseDetailBill);

    int save(InWarehouseDetailBill inWarehouseDetailBill);

    List<InWarehouseDetailBill> find(InWarehouseDetailBill inWarehouseDetailBill);

    long countAll();

    List<InWarehouseDetailBill> findLikeInfo(InWarehouseDetailBill inWarehouseDetailBill);
}
