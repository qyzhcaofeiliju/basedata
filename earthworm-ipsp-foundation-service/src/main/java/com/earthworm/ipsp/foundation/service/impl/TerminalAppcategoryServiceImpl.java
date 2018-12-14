package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.ITerminalAppcategoryDao;
import com.earthworm.ipsp.foundation.entity.TerminalAppcategory;
import com.earthworm.ipsp.foundation.service.interf.ITerminalAppcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 19:53
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class TerminalAppcategoryServiceImpl implements ITerminalAppcategoryService {

    @Autowired
    private ITerminalAppcategoryDao terminalAppcategoryDao;

    @Override
    public List<TerminalAppcategory> findAll() {
        return terminalAppcategoryDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String appId) {
        return terminalAppcategoryDao.deleteById(appId);
    }

    @Override
    public TerminalAppcategory findById(String appId) {
        return terminalAppcategoryDao.findById(appId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(TerminalAppcategory terminalAppcategory) {
        return terminalAppcategoryDao.update(terminalAppcategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(TerminalAppcategory terminalAppcategory) {
        terminalAppcategory.setAppId(UUID.randomUUID().toString());
        return terminalAppcategoryDao.save(terminalAppcategory);
    }

    @Override
    public List<TerminalAppcategory> find(TerminalAppcategory terminalAppcategory) {
        return terminalAppcategoryDao.find(terminalAppcategory);
    }

    @Override
    public long countAll() {
        return terminalAppcategoryDao.countAll();
    }

    public List<TerminalAppcategory> findLikeInfo(TerminalAppcategory terminalAppcategory){
        return terminalAppcategoryDao.findLikeInfo(terminalAppcategory);
    };
}
