package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.MaterialflowStation;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 16:32
 * @version: v1.0.0
 * @Type: “interface”Service 接口类
 * @Desc: “物流站点信息”的Service 层接口类
 */
public interface IMaterialflowStationService {
    /**
     * 查询所有
     *
     * @return
     */
    List<MaterialflowStation> findAll();

    /**
     * 通过主键“站点id”进行删除操作
     *
     * @param staId：站点id
     * @return
     */
    int deleteById(String staId);

    /**
     * 通过主键“站点id”进行查询操作
     *
     * @param staId：站点id
     * @return
     */
    MaterialflowStation findById(String staId);

    /**
     * 更新一条记录
     *
     * @param materialflowStation：“物流站点”实体
     * @return
     */
    int update(MaterialflowStation materialflowStation);

    /**
     * 执行保存一条记录
     *
     * @param materialflowStation：“物流站点”实体
     * @return
     */
    int save(MaterialflowStation materialflowStation);

    /**
     * 根据“物流站点”信息实体进行查询
     *
     * @param materialflowStation：“物流站点”实体
     * @return
     */
    List<MaterialflowStation> find(MaterialflowStation materialflowStation);

    /**
     * 统计所有记录数
     *
     * @return
     */
    long countAll();

    List<MaterialflowStation> findLikeInfo(MaterialflowStation materialflowStation);
}
