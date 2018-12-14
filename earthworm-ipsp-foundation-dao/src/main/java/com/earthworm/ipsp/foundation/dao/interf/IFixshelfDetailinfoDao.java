package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.FixshelfDetailinfo;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/28 9:27
 * @version: v1.0.0
 * @Type:
 * @Desc: FixshelfDetailinfoDao interface
 */
@MapperScanner
public interface IFixshelfDetailinfoDao extends BaseDao<FixshelfDetailinfo> {
    @Override
    int save(FixshelfDetailinfo fixshelfDetailinfo);

    @Override
    long saveBatch(List<FixshelfDetailinfo> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(FixshelfDetailinfo fixshelfDetailinfo);

    @Override
    long cascadeDelete(FixshelfDetailinfo fixshelfDetailinfo);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(FixshelfDetailinfo fixshelfDetailinfo, FixshelfDetailinfo exclude);

    @Override
    int update(FixshelfDetailinfo fixshelfDetailinfo);

    @Override
    int updateBatch(List<FixshelfDetailinfo> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    FixshelfDetailinfo findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<FixshelfDetailinfo> findAll();

    @Override
    List<FixshelfDetailinfo> find(FixshelfDetailinfo fixshelfDetailinfo);

    @Override
    List<FixshelfDetailinfo> findByCondition(Map<String, Object> condition);

    @Override
    List<FixshelfDetailinfo> findByOrCondition(Map<String, Object> condition);

    @Override
    List<FixshelfDetailinfo> findByOrQuery(FixshelfDetailinfo fixshelfDetailinfo);

    @Override
    List<FixshelfDetailinfo> findByLikeQuery(FixshelfDetailinfo fixshelfDetailinfo, FixshelfDetailinfo exclude);

    @Override
    List<FixshelfDetailinfo> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<FixshelfDetailinfo> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(FixshelfDetailinfo fixshelfDetailinfo);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<FixshelfDetailinfo> findLikeInfo(FixshelfDetailinfo fixshelfDetailinfo);
}
