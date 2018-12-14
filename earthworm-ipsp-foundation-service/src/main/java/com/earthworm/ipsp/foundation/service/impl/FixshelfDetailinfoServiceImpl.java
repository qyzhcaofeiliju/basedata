package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IFixshelfDetailinfoDao;
import com.earthworm.ipsp.foundation.entity.FixshelfDetailinfo;
import com.earthworm.ipsp.foundation.service.interf.IFixshelfDetailinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/28 10:22
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class FixshelfDetailinfoServiceImpl implements IFixshelfDetailinfoService {

    @Autowired
    private IFixshelfDetailinfoDao fixshelfDetailinfoDao;

    @Override
    public List<FixshelfDetailinfo> findAll() {
        return fixshelfDetailinfoDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String appId) {
        return fixshelfDetailinfoDao.deleteById(appId);
    }

    @Override
    public FixshelfDetailinfo findById(String appId) {
        return fixshelfDetailinfoDao.findById(appId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(FixshelfDetailinfo fixshelfDetailinfo) {
        return fixshelfDetailinfoDao.update(fixshelfDetailinfo);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(FixshelfDetailinfo fixshelfDetailinfo) {
        fixshelfDetailinfo.setFixId(UUID.randomUUID().toString());
        return fixshelfDetailinfoDao.save(fixshelfDetailinfo);
    }

    @Override
    public List<FixshelfDetailinfo> find(FixshelfDetailinfo fixshelfDetailinfo) {
        return fixshelfDetailinfoDao.find(fixshelfDetailinfo);
    }

    @Override
    public long countAll() {
        return fixshelfDetailinfoDao.countAll();
    }

    public List<FixshelfDetailinfo> findLikeInfo(FixshelfDetailinfo fixshelfDetailinfo){
        return fixshelfDetailinfoDao.findLikeInfo(fixshelfDetailinfo);
    };
}
