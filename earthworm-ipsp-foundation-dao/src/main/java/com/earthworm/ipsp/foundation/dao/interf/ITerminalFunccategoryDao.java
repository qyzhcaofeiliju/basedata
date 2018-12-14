package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.TerminalFunccategory;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 18:26
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface ITerminalFunccategoryDao extends BaseDao<TerminalFunccategory> {
    @Override
    int save(TerminalFunccategory terminalFunccategory);

    @Override
    long saveBatch(List<TerminalFunccategory> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(TerminalFunccategory terminalFunccategory);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(TerminalFunccategory terminalFunccategory);

    @Override
    int updateBatch(List<TerminalFunccategory> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    TerminalFunccategory findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<TerminalFunccategory> findAll();

    @Override
    List<TerminalFunccategory> find(TerminalFunccategory terminalFunccategory);

    @Override
    List<TerminalFunccategory> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(TerminalFunccategory terminalFunccategory);

    @Override
    long countByCondition(Map<String, Object> condition);

    List<TerminalFunccategory> findLikeInfo(TerminalFunccategory terminalFunccategory);
}
