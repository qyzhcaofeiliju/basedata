package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.ITerminalFunccategoryDao;
import com.earthworm.ipsp.foundation.entity.TerminalFunccategory;
import com.earthworm.ipsp.foundation.service.interf.ITerminalFunccategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 19:57
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class TerminalFunccategoryServiceImpl implements ITerminalFunccategoryService {

    @Autowired
    private ITerminalFunccategoryDao terminalFunccategoryDao;


    @Override
    public List<TerminalFunccategory> findAll() {
        return terminalFunccategoryDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String funId) {
        return terminalFunccategoryDao.deleteById(funId);
    }

    @Override
    public TerminalFunccategory findById(String funId) {
        return terminalFunccategoryDao.findById(funId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(TerminalFunccategory terminalFunccategory) {
        return terminalFunccategoryDao.update(terminalFunccategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(TerminalFunccategory terminalFunccategory) {
        terminalFunccategory.setFunId(UUID.randomUUID().toString());
        return terminalFunccategoryDao.save(terminalFunccategory);
    }

    @Override
    public List<TerminalFunccategory> find(TerminalFunccategory terminalFunccategory) {
        return terminalFunccategoryDao.find(terminalFunccategory);
    }

    @Override
    /**
     * 统计所有行
     */
    public long countAll() {
        return terminalFunccategoryDao.countAll();
    }

    public List<TerminalFunccategory> findLikeInfo(TerminalFunccategory terminalFunccategory){
        return terminalFunccategoryDao.findLikeInfo(terminalFunccategory);
    };
}
