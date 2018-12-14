package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.SuitcaseFunccategory;

import java.util.List;

public interface ISuitcaseFunccategoryService {
    List<SuitcaseFunccategory> findAll();

    int deleteById(String id);

    SuitcaseFunccategory findById(String id);

    int update(SuitcaseFunccategory suitcaseFunccategory);

    int save(SuitcaseFunccategory suitcaseFunccategory);

    List<SuitcaseFunccategory> find(SuitcaseFunccategory suitcaseFunccategory);

    long countAll();

    List<SuitcaseFunccategory> findLikeInfo(SuitcaseFunccategory suitcaseFunccategory);
}
