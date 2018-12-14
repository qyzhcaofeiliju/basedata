package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.GoodslocationAxisdictionary;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 9:27
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface IGoodslocationAxisdictionaryService {
    List<GoodslocationAxisdictionary> findAll();

    int deleteById(String axisdId);

    GoodslocationAxisdictionary findById(String axisdId);

    int update(GoodslocationAxisdictionary materialflowStation);

    int save(GoodslocationAxisdictionary materialflowStation);

    List<GoodslocationAxisdictionary> find(GoodslocationAxisdictionary materialflowStation);

    long countAll();
}
