package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.VwOutWarehouseBill;

import java.util.List;

public interface IVwOutWarehouseBillService {

    List<VwOutWarehouseBill> findAll();

    int deleteById(String id);

    VwOutWarehouseBill findById(String id);

    int update(VwOutWarehouseBill vwOutWarehouseBill);

    List<VwOutWarehouseBill> find(VwOutWarehouseBill vwOutWarehouseBill);

    long countAll();

    List<VwOutWarehouseBill> findLikeInfo(VwOutWarehouseBill vwOutWarehouseBill);
}
