package com.earthworm.ipsp.foundation.service.interf;


import com.earthworm.ipsp.foundation.entity.StaticdataTranslate;

import java.util.List;

public interface IStaticdataTranslateService {
    List<StaticdataTranslate> findAll();

    int deleteById(String id);

    StaticdataTranslate findById(String id);

    int update(StaticdataTranslate staticdataTranslate);

    int save(StaticdataTranslate staticdataTranslate);

    List<StaticdataTranslate> find(StaticdataTranslate staticdataTranslate);

    long countAll();

    List<StaticdataTranslate> findLikeInfo(StaticdataTranslate staticdataTranslate);
}
