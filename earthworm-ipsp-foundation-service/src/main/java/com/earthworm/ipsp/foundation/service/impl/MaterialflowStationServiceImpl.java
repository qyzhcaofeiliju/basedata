package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IMaterialflowStationDao;
import com.earthworm.ipsp.foundation.entity.MaterialflowStation;
import com.earthworm.ipsp.foundation.service.interf.IMaterialflowStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 16:34
 * @version: v1.0.0
 * @Type: "implements" 实现类
 * @Desc: “物流站点”的Service 层的实现类
 */
@Service("materialflowStationService")
public class MaterialflowStationServiceImpl implements IMaterialflowStationService {

    /**
     * 注入"物料站点"的Dao 层接口类
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private IMaterialflowStationDao materialflowStationDao;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<MaterialflowStation> findAll() {
        return materialflowStationDao.findAll();
    }

    /**
     * 通过主键“站点id”进行删除操作
     *
     * @param staId：站点id
     * @return
     */
    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String staId) {
        return materialflowStationDao.deleteById(staId);
    }

    /**
     * 通过主键“站点id”进行查询操作
     *
     * @param staId：站点id
     * @return
     */
    @Override
    public MaterialflowStation findById(String staId) {
        return materialflowStationDao.findById(staId);
    }

    /**
     * 更新一条记录
     *
     * @param materialflowStation：“物流站点”实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(MaterialflowStation materialflowStation) {
        return materialflowStationDao.update(materialflowStation);
    }

    /**
     * 执行保存一条记录
     *
     * @param materialflowStation：“物流站点”实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(MaterialflowStation materialflowStation) {
        materialflowStation.setStaId(UUID.randomUUID().toString());
        return materialflowStationDao.save(materialflowStation);
    }

    /**
     * 根据“物流站点”信息实体进行查询
     *
     * @param materialflowStation：“物流站点”实体
     * @return
     */
    @Override
    public List<MaterialflowStation> find(MaterialflowStation materialflowStation) {
        return materialflowStationDao.find(materialflowStation);
    }

    /**
     * 统计所有记录数
     *
     * @return
     */
    @Override
    public long countAll() {
        return materialflowStationDao.countAll();
    }

    public List<MaterialflowStation> findLikeInfo(MaterialflowStation materialflowStation){
        return materialflowStationDao.findLikeInfo(materialflowStation);
    };
}
