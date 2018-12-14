package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.TerminalCategory;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 11:24
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface ITerminalCategoryDao extends BaseDao<TerminalCategory> {

    @Override
    int save(TerminalCategory terminalCategory);

    @Override
    long saveBatch(List<TerminalCategory> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(TerminalCategory terminalCategory);

    @Override
    long cascadeDelete(TerminalCategory terminalCategory);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(TerminalCategory terminalCategory, TerminalCategory exclude);

    @Override
    int update(TerminalCategory terminalCategory);

    @Override
    int updateBatch(List<TerminalCategory> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    TerminalCategory findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<TerminalCategory> findAll();

    @Override
    List<TerminalCategory> find(TerminalCategory terminalCategory);

    @Override
    List<TerminalCategory> findByCondition(Map<String, Object> condition);

    @Override
    List<TerminalCategory> findByOrCondition(Map<String, Object> condition);

    @Override
    List<TerminalCategory> findByOrQuery(TerminalCategory terminalCategory);

    @Override
    List<TerminalCategory> findByLikeQuery(TerminalCategory terminalCategory, TerminalCategory exclude);

    @Override
    List<TerminalCategory> findByLikeCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(TerminalCategory terminalCategory);

    @Override
    long countByCondition(Map<String, Object> condition);
}
