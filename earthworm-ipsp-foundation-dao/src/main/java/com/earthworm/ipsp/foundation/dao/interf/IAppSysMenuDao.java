package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.AppSysMenu;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Auther: chenrh@i-earthworm.com
 * @Date: 2018/10/11 16:33
 * @Description:
 */
@MapperScanner
public interface IAppSysMenuDao extends BaseDao<AppSysMenu>{
    @Override
    int save(AppSysMenu appSysMenu);

    @Override
    long saveBatch(List<AppSysMenu> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(AppSysMenu appSysMenu);

    @Override
    long cascadeDelete(AppSysMenu appSysMenu);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    long deleteByLikeQuery(AppSysMenu appSysMenu, AppSysMenu exclude);

    @Override
    int update(AppSysMenu appSysMenu);

    @Override
    int updateBatch(List<AppSysMenu> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    AppSysMenu findById(String id);

    @Override
    long findVersion(String id);

    @Override
    List<AppSysMenu> findAll();

    @Override
    List<AppSysMenu> find(AppSysMenu appSysMenu);

    @Override
    List<AppSysMenu> findByCondition(Map<String, Object> condition);

    @Override
    List<AppSysMenu> findByOrCondition(Map<String, Object> condition);

    @Override
    List<AppSysMenu> findByOrQuery(AppSysMenu appSysMenu);

    @Override
    List<AppSysMenu> findByLikeQuery(AppSysMenu appSysMenu, AppSysMenu exclude);

    @Override
    List<AppSysMenu> findByLikeCondition(Map<String, Object> condition);

    @Override
    List<AppSysMenu> findByInCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(AppSysMenu appSysMenu);

    @Override
    long countByCondition(Map<String, Object> condition);
}
