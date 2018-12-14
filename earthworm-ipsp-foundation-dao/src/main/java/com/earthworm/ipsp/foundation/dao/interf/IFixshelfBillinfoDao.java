package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.FixshelfBillinfo;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 13:20
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface IFixshelfBillinfoDao extends BaseDao<FixshelfBillinfo> {

    @Override
    int save(FixshelfBillinfo fixshelfBillinfo);

    @Override
    long saveBatch(List<FixshelfBillinfo> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(FixshelfBillinfo fixshelfBillinfo);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(FixshelfBillinfo fixshelfBillinfo);

    @Override
    int updateBatch(List<FixshelfBillinfo> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    FixshelfBillinfo findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<FixshelfBillinfo> findAll();

    @Override
    List<FixshelfBillinfo> find(FixshelfBillinfo fixshelfBillinfo);

    @Override
    List<FixshelfBillinfo> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(FixshelfBillinfo fixshelfBillinfo);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<FixshelfBillinfo> findLikeInfo(FixshelfBillinfo fixshelfBillinfo);
}
