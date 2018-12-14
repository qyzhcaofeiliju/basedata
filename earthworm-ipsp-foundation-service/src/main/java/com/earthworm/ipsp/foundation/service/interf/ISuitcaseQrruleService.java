package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.SuitcaseQrrule;

import java.util.List;
import java.util.Map;

/**
 * @Auther: chenrh@i-earthworm.com
 * @Date: 2018/11/1 18:29
 * @Description:周转箱唯一码规则
 */
public interface ISuitcaseQrruleService {
    int save(SuitcaseQrrule suitcaseQrrule);


    long saveBatch(List<SuitcaseQrrule> list);


    int deleteById(String id);


    long deleteAll();


    long delete(SuitcaseQrrule suitcaseQrrule);


    long cascadeDelete(SuitcaseQrrule suitcaseQrrule);


    long deleteByCondition(Map<String, Object> condition);


    long deleteByLikeQuery(SuitcaseQrrule suitcaseQrrule, SuitcaseQrrule exclude);


    int update(SuitcaseQrrule suitcaseQrrule);


    int updateBatch(List<SuitcaseQrrule> list);


    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);


    SuitcaseQrrule findById(String id);


    List<SuitcaseQrrule> findAll();


    List<SuitcaseQrrule> find(SuitcaseQrrule suitcaseQrrule);


    List<SuitcaseQrrule> findByCondition(Map<String, Object> condition);


    List<SuitcaseQrrule> findByOrCondition(Map<String, Object> condition);


    List<SuitcaseQrrule> findByOrQuery(SuitcaseQrrule suitcaseQrrule);


    List<SuitcaseQrrule> findByLikeQuery(SuitcaseQrrule suitcaseQrrule, SuitcaseQrrule exclude);


    List<SuitcaseQrrule> findByLikeCondition(Map<String, Object> condition);


    List<SuitcaseQrrule> findByInCondition(Map<String, Object> condition);


    long countAll();


    long count(SuitcaseQrrule suitcaseQrrule);


    long countByCondition(Map<String, Object> condition);

    List<SuitcaseQrrule> findLikeInfo(SuitcaseQrrule suitcaseQrrule);

}
