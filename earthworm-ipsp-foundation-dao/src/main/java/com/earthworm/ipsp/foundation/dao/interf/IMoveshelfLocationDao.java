package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.MoveshelfLocation;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 10:36
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface IMoveshelfLocationDao extends BaseDao<MoveshelfLocation> {
    @Override
    int save(MoveshelfLocation moveshelfLocation);

    @Override
    long saveBatch(List<MoveshelfLocation> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(MoveshelfLocation moveshelfLocation);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(MoveshelfLocation moveshelfLocation);

    @Override
    int updateBatch(List<MoveshelfLocation> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    MoveshelfLocation findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<MoveshelfLocation> findAll();

    @Override
    List<MoveshelfLocation> find(MoveshelfLocation moveshelfLocation);

    @Override
    List<MoveshelfLocation> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(MoveshelfLocation moveshelfLocation);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<MoveshelfLocation> findLikeInfo(MoveshelfLocation moveshelfLocation);
}
