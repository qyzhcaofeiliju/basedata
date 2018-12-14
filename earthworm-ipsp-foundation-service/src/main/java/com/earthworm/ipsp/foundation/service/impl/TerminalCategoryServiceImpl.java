package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.ITerminalCategoryDao;
import com.earthworm.ipsp.foundation.entity.TerminalCategory;
import com.earthworm.ipsp.foundation.service.interf.ITerminalCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 15:12
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class TerminalCategoryServiceImpl implements ITerminalCategoryService {

    @Autowired
    private ITerminalCategoryDao terminalCategoryDao;

    @Override
    public List<TerminalCategory> findAll() {
        return terminalCategoryDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String catId) {
        return terminalCategoryDao.deleteById(catId);
    }

    @Override
    public TerminalCategory findById(String catId) {
        return terminalCategoryDao.findById(catId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(TerminalCategory terminalCategory) {
        return terminalCategoryDao.update(terminalCategory);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(TerminalCategory terminalCategory) {
        terminalCategory.setCatId(UUID.randomUUID().toString());
        return terminalCategoryDao.save(terminalCategory);
    }

    @Override
    public List<TerminalCategory> find(TerminalCategory terminalCategory) {
        return terminalCategoryDao.find(terminalCategory);
    }

    @Override
    public long countAll() {
        return terminalCategoryDao.countAll();
    }
}
