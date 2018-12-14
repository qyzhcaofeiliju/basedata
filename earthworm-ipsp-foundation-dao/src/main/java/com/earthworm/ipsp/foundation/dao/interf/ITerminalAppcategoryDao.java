package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.TerminalAppcategory;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 18:25
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface ITerminalAppcategoryDao extends BaseDao<TerminalAppcategory> {
    @Override
    int save(TerminalAppcategory terminalAppcategory);

    @Override
    long saveBatch(List<TerminalAppcategory> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(TerminalAppcategory terminalAppcategory);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(TerminalAppcategory terminalAppcategory);

    @Override
    int updateBatch(List<TerminalAppcategory> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    TerminalAppcategory findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<TerminalAppcategory> findAll();

    @Override
    List<TerminalAppcategory> find(TerminalAppcategory terminalAppcategory);

    @Override
    List<TerminalAppcategory> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(TerminalAppcategory terminalAppcategory);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<TerminalAppcategory> findLikeInfo(TerminalAppcategory terminalAppcategory);
}
