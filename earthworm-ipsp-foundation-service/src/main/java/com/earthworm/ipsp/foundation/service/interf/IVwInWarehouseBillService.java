package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.VwInWarehouseBill;

import java.util.List;

public interface IVwInWarehouseBillService {

    List<VwInWarehouseBill> findAll();

    int deleteById(String id);

    VwInWarehouseBill findById(String id);

    int update(VwInWarehouseBill VwInWarehouseBill);

    List<VwInWarehouseBill> find(VwInWarehouseBill VwInWarehouseBill);

    long countAll();

    List<VwInWarehouseBill> findLikeInfo(VwInWarehouseBill vwInWarehouseBill);
}
