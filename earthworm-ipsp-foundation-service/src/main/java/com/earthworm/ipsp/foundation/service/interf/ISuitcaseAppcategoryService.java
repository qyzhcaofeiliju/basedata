package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.SuitcaseAppcategory;

import java.util.List;

public interface ISuitcaseAppcategoryService {
    List<SuitcaseAppcategory> findAll();

    int deleteById(String id);

    SuitcaseAppcategory findById(String id);

    int update(SuitcaseAppcategory suitcaseAppcategory);

    int save(SuitcaseAppcategory suitcaseAppcategory);

    List<SuitcaseAppcategory> find(SuitcaseAppcategory suitcaseAppcategory);

    List<SuitcaseAppcategory> findLikeInfo(SuitcaseAppcategory suitcaseAppcategory);

    long countAll();
}
