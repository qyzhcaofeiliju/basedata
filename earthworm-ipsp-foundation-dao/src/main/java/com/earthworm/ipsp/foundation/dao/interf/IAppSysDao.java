package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.AppSys;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Auther: chenrh@i-earthworm.com
 * @Date: 2018/10/11 16:32
 * @Description:
 */
@MapperScanner
public interface IAppSysDao extends BaseDao<AppSys>{
    @Override
    int save(AppSys appSys);

    @Override
    long saveBatch(List<AppSys> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(AppSys appSys);

    @Override
    long cascadeDelete(AppSys appSys);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(AppSys appSys, AppSys exclude);

    @Override
    int update(AppSys appSys);

    @Override
    int updateBatch(List<AppSys> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    AppSys findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<AppSys> findAll();

    @Override
    List<AppSys> find(AppSys appSys);

    @Override
    List<AppSys> findByCondition(Map<String, Object> condition);

    @Override
    List<AppSys> findByOrCondition(Map<String, Object> condition);

    @Override
    List<AppSys> findByOrQuery(AppSys appSys);

    @Override
    List<AppSys> findByLikeQuery(AppSys appSys, AppSys exclude);

    @Override
    List<AppSys> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<AppSys> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(AppSys appSys);

    @Override
    long countByCondition(Map<String, Object> condition);
}
