package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.MaterialflowStation;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 11:28
 * @version: v1.0.0
 * @Type: "interface" 接口类
 * @Desc: “物流站点”的Dao 接口类
 */
@MapperScanner
public interface IMaterialflowStationDao extends BaseDao<MaterialflowStation> {
    /**
     * 保存一条实体对象操作
     *
     * @param materialflowStation：“物流站点”实体对象
     * @return
     */
    @Override
    int save(MaterialflowStation materialflowStation);

    /**
     * 批量保存多条实体对象
     *
     * @param list：包含“物流站点”实体对象的List集合
     * @return
     */
    @Override
    long saveBatch(List<MaterialflowStation> list);

    /**
     * 通过“主键id”进行删除操作
     *
     * @param id：主键id
     * @return
     */
    @Override
    int deleteById(String id);

    /**
     * 删除所有记录
     *
     * @return
     */
    @Override
    long deleteAll();

    /**
     * 根据“物流站点”实体进行删除操作
     *
     * @param materialflowStation
     * @return
     */
    @Override
    long delete(MaterialflowStation materialflowStation);

    /**
     * 根据指定的条件进行删除
     *
     * @param condition：指定的map集合条件
     * @return
     */
    @Override
    long deleteByCondition(Map<String, Object> condition);

    /**
     * 根据指定的“物流站点”实体进行更新操作
     *
     * @param materialflowStation：物流站点实体
     * @return
     */
    @Override
    int update(MaterialflowStation materialflowStation);

    /**
     * 批量进行更新操作
     *
     * @param list：带有“物流站点”实体的List集合
     * @return
     */
    @Override
    int updateBatch(List<MaterialflowStation> list);

    /**
     * 根据指定的条件进行更新相应的参数
     *
     * @param updateParam：更新参数的Map集合
     * @param conditions：指定条件的Map集合
     * @return
     */
    @Override
    int updateByCondition(Map<String, Object> updateParam, Map<String, Object> conditions);

    /**
     * 根据主键“站点id”进行查询操作
     *
     * @param id：主键“站点id”
     * @return
     */
    @Override
    MaterialflowStation findById(String id);

    /**
     * 根据“版本号id”进行查询
     *
     * @param id：版本号id
     * @return
     */
    @Override
    long findVersion(String id);

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    List<MaterialflowStation> findAll();

    /**
     * 根据指定的“物流站点”实体对象进行查询
     *
     * @param materialflowStation：物流站点实体对象
     * @return
     */
    @Override
    List<MaterialflowStation> find(MaterialflowStation materialflowStation);

    /**
     * 根据指定的条件进行查询
     *
     * @param condition：指定一个或者多个条件的Map集合
     * @return
     */
    @Override
    List<MaterialflowStation> findByCondition(Map<String, Object> condition);

    /**
     * 统计所有记录数
     *
     * @return
     */
    @Override
    long countAll();

    /**
     * 根据指定的“物流站点”实体对象进行统计操作
     *
     * @param materialflowStation：物流站点实体对象
     * @return
     */
    @Override
    long count(MaterialflowStation materialflowStation);

    /**
     * 根据指定的条件进行统计操作
     *
     * @param condition：指定条件的map集合
     * @return
     */
    @Override
    long countByCondition(Map<String, Object> condition);

    List<MaterialflowStation> findLikeInfo(MaterialflowStation materialflowStation);
}
