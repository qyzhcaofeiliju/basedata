package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IMoveshelfLocationDao;
import com.earthworm.ipsp.foundation.entity.MoveshelfLocation;
import com.earthworm.ipsp.foundation.service.interf.IMoveshelfLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 10:46
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class MoveshelfLocationServiceImpl implements IMoveshelfLocationService {

    @Autowired
    private IMoveshelfLocationDao moveshelfLocationDao;

    @Override
    public List<MoveshelfLocation> findAll() {
        return moveshelfLocationDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String mslId) {
        return moveshelfLocationDao.deleteById(mslId);
    }

    @Override
    public MoveshelfLocation findById(String mslId) {
        return moveshelfLocationDao.findById(mslId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(MoveshelfLocation moveshelfLocation) {
        return moveshelfLocationDao.update(moveshelfLocation);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(MoveshelfLocation moveshelfLocation) {
        moveshelfLocation.setMslId(UUID.randomUUID().toString());
        return moveshelfLocationDao.save(moveshelfLocation);
    }

    @Override
    public List<MoveshelfLocation> find(MoveshelfLocation moveshelfLocation) {
        return moveshelfLocationDao.find(moveshelfLocation);
    }

    @Override
    public long countAll() {
        return moveshelfLocationDao.countAll();
    }

    public List<MoveshelfLocation> findLikeInfo(MoveshelfLocation moveshelfLocation){
        return moveshelfLocationDao.findLikeInfo(moveshelfLocation);
    };

}
