package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.AppMenu;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

@MapperScanner
public interface IAppMenuDao extends BaseDao<AppMenu> {
    @Override
    int save(AppMenu appMenu);

    @Override
    long saveBatch(List<AppMenu> list);

    @Override
    int deleteById(String id);

    @Override
    long deleteAll();

    @Override
    long delete(AppMenu appMenu);

    @Override
    long deleteByLikeQuery(@Param("menu") AppMenu appMenu, @Param("excl") AppMenu exclude);

    /**
     * Delete by ownerid
     * @param ownerIdSet
     * @return
     */
    long deleteByOwnerId(Set<String> ownerIdSet);

    @Override
    long deleteByCondition(Map<String, Object> condition);

    @Override
    int update(AppMenu appMenu);

    @Override
    int updateBatch(List<AppMenu> list);

    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    @Override
    AppMenu findById(String id);

    @Override
    List<AppMenu> findAll();

    @Override
    List<AppMenu> find(AppMenu appMenu);

    @Override
    List<AppMenu> findByCondition(Map<String, Object> condition);

    @Override
    List<AppMenu> findByOrQuery(AppMenu appMenu);

    @Override
    List<AppMenu> findByLikeQuery(@Param("menu") AppMenu appMenu, @Param("excl") AppMenu exclude);

    @Override
    List<AppMenu> findByLikeCondition(Map<String, Object> condition);

    @Override
    long countAll();

    @Override
    long count(AppMenu appMenu);

    @Override
    long countByCondition(Map<String, Object> condition);
}
