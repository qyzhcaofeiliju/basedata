package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.TerminalEquipment;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/6 23:46
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@MapperScanner
public interface ITerminalEquipmentDao extends BaseDao<TerminalEquipment> {

    @Override
    int save(TerminalEquipment terminalEquipment);

    @Override
    long saveBatch(List<TerminalEquipment> list);

    @Override
    int deleteById(String equId);

    @Override
    long deleteAll();

    @Override
    long delete(TerminalEquipment terminalEquipment);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(TerminalEquipment terminalEquipment);

    @Override
    int updateBatch(List<TerminalEquipment> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    TerminalEquipment findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<TerminalEquipment> findAll();

    @Override
    List<TerminalEquipment> find(TerminalEquipment terminalEquipment);

    @Override
    List<TerminalEquipment> findByCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(TerminalEquipment terminalEquipment);

    @Override
    long countByCondition(Map<String, Object> condition);
}
