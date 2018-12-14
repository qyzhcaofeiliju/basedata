package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.SuitcaseQrrule;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Auther: chenrh@i-earthworm.com
 * @Date: 2018/11/1 17:52
 * @Description:周转箱唯一码规则
 */
@MapperScanner
public interface ISuitcaseQrruleDao extends BaseDao<SuitcaseQrrule>{
    /**
     * 增加记录
     * @param suitcaseQrrule
     * @return
     */
    @Override
    int save(SuitcaseQrrule suitcaseQrrule);

    /**
     * 增加多条记录
     * @param list
     * @return
     */
    @Override
    long saveBatch(List<SuitcaseQrrule> list);

    /**
     * 根据id删除记录
     * @param id
     * @return
     */
    @Override
    int deleteById(String id);

    /**
     * 删除全部记录
     * @return
     */
    @Override
    long deleteAll();

    /**
     * 根据条件删除
     * @param suitcaseQrrule
     * @return
     */
    @Override
    long delete(SuitcaseQrrule suitcaseQrrule);

    /**
     * 级联删除
     * @param suitcaseQrrule
     * @return
     */
    @Override
    long cascadeDelete(SuitcaseQrrule suitcaseQrrule);

    /**
     * 根据map条件删除
     * @param condition
     * @return
     */
    @Override
    long deleteByCondition(Map<String, Object> condition);

    /**
     * 查询删除排查删除
     * @param suitcaseQrrule
     * @param exclude
     * @return
     */
    @Override
    long deleteByLikeQuery(SuitcaseQrrule suitcaseQrrule, SuitcaseQrrule exclude);

    /**
     * 修改记录
     * @param suitcaseQrrule
     * @return
     */
    @Override
    int update(SuitcaseQrrule suitcaseQrrule);

    /**
     * 修改多条记录
     * @param list
     * @return
     */
    @Override
    int updateBatch(List<SuitcaseQrrule> list);

    /**
     * 根据map条件修改记录
     * @param updateParam
     * @param conditions
     * @return
     */
    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    /**
     * 根据Id查询记录
     * @param id
     * @return
     */
    @Override
    SuitcaseQrrule findById(String id);

    /**
     * 查询全部记录
     * @return
     */
    @Override
    List<SuitcaseQrrule> findAll();

    /**
     * 条件查询记录
     * @param suitcaseQrrule
     * @return
     */
    @Override
    List<SuitcaseQrrule> find(SuitcaseQrrule suitcaseQrrule);

    /**
     * 根据map条件查询记录
     * @param condition
     * @return
     */
    @Override
    List<SuitcaseQrrule> findByCondition(Map<String, Object> condition);

    /**
     * 根据map条件查询记录，查询方式or
     * @param condition
     * @return
     */
    @Override
    List<SuitcaseQrrule> findByOrCondition(Map<String, Object> condition);

    /**
     * 条件查询，查询方式or
     * @param suitcaseQrrule
     * @return
     */
    @Override
    List<SuitcaseQrrule> findByOrQuery(SuitcaseQrrule suitcaseQrrule);

    /**
     * 条件查询排查查询
     * @param suitcaseQrrule
     * @param exclude
     * @return
     */
    @Override
    List<SuitcaseQrrule> findByLikeQuery(SuitcaseQrrule suitcaseQrrule, SuitcaseQrrule exclude);

    /**
     * 根据map条件查询记录，查询方式like
     * @param condition
     * @return
     */
    @Override
    List<SuitcaseQrrule> findByLikeCondition(Map<String, Object> condition);

    /**
     * 根据map条件查询记录，查询方式in
     * @param condition
     * @return
     */
    @Override
    List<SuitcaseQrrule> findByInCondition(Map<String, Object> condition);

    /**
     * 查询全部记录数
     * @return
     */
    @Override
    long countAll();

    /**
     * 根据条件查询记录数
     * @param suitcaseQrrule
     * @return
     */
    @Override
    long count(SuitcaseQrrule suitcaseQrrule);

    /**
     * 根据map条件查询记录数
     * @param condition
     * @return
     */
    @Override
    long countByCondition(Map<String, Object> condition);

    /**
     * 模糊查询
     * @param suitcaseQrrule
     * @return
     */
    List<SuitcaseQrrule> findLikeInfo(SuitcaseQrrule suitcaseQrrule);

    /**
     * 根据条件查询序号的最大值
     * @param string
     * @return
     */
    String findSqrrNumberMaxByFunName(String string);

    /**
     * 查询序号的最大值
     * @return
     */
    String findSqrrNumberMax();
}
