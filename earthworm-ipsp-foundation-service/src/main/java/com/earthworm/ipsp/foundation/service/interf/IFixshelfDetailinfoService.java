package com.earthworm.ipsp.foundation.service.interf;


import com.earthworm.ipsp.foundation.entity.FixshelfDetailinfo;

import java.util.List;

public interface IFixshelfDetailinfoService {
    List<FixshelfDetailinfo> findAll();

    int deleteById(String id);

    FixshelfDetailinfo findById(String id);

    int update(FixshelfDetailinfo inWarehouseBill);

    int save(FixshelfDetailinfo inWarehouseBill);

    List<FixshelfDetailinfo> find(FixshelfDetailinfo inWarehouseBill);

    long countAll();

    List<FixshelfDetailinfo> findLikeInfo(FixshelfDetailinfo fixshelfDetailinfo);
}
