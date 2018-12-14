package com.earthworm.ipsp.foundation.service.interf;


import com.earthworm.ipsp.foundation.entity.BillsCategory;

import java.util.List;

public interface IBillsCategoryService {
    List<BillsCategory> findAll();

    int deleteById(String id);

    BillsCategory findById(String id);

    int update(BillsCategory billsCategory);

    int save(BillsCategory billsCategory);

    List<BillsCategory> find(BillsCategory billsCategory);

    List<BillsCategory> findLikeInfo(BillsCategory billsCategory);

    long countAll();

}
