package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.MoveshelfLocation;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/6 23:49
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface IMoveshelfLocationService {
    List<MoveshelfLocation> findAll();

    int deleteById(String mslId);

    MoveshelfLocation findById(String mslId);

    int update(MoveshelfLocation moveshelfLocation);

    int save(MoveshelfLocation moveshelfLocation);

    List<MoveshelfLocation> find(MoveshelfLocation moveshelfLocation);

    long countAll();

    List<MoveshelfLocation> findLikeInfo(MoveshelfLocation moveshelfLocation);
}
