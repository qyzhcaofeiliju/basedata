package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IGoodslocationAxisdictionaryDao;
import com.earthworm.ipsp.foundation.dao.interf.ITerminalEquipmentDao;
import com.earthworm.ipsp.foundation.entity.GoodslocationAxisdictionary;
import com.earthworm.ipsp.foundation.entity.TerminalEquipment;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.ITerminalEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 9:13
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class TerminalEquipmentServiceImpl implements ITerminalEquipmentService {

    // sl4j 日志
    private Logger logger = LoggerFactory.getLogger(TerminalEquipmentServiceImpl.class);

    @Autowired
    private ITerminalEquipmentDao terminalEquipmentDao;

    @Autowired
    private IGoodslocationAxisdictionaryDao goodslocationAxisdictionaryDao;

    @Override
    public List<TerminalEquipment> findAll() {
        return terminalEquipmentDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String equId) {
        return terminalEquipmentDao.deleteById(equId);
    }

    @Override
    public TerminalEquipment findById(String equId) {
        return terminalEquipmentDao.findById(equId);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(TerminalEquipment terminalEquipment) {
        return terminalEquipmentDao.update(terminalEquipment);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(TerminalEquipment terminalEquipment, GoodslocationAxisdictionary goodslocationAxisdictionary) throws IPSPFoundationServiceException {
        int update = terminalEquipmentDao.update(terminalEquipment);
        int update1 = goodslocationAxisdictionaryDao.update(goodslocationAxisdictionary);
        if (update != 1 || update1 != 1) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("update terminalEquipment failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_UPT_ERR);
            logger.error("Upt terminalEquipment failed!", ex);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(TerminalEquipment terminalEquipment) {
        terminalEquipment.setEquId(UUID.randomUUID().toString());
        return terminalEquipmentDao.save(terminalEquipment);
    }

    /**
     * 同时保存站点设备和货架货位信息表
     *
     * @param terminalEquipment
     * @param goodslocationAxisdictionary
     * @return
     * @throws IPSPFoundationServiceException
     */
    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(TerminalEquipment terminalEquipment, GoodslocationAxisdictionary goodslocationAxisdictionary) throws IPSPFoundationServiceException {
        terminalEquipment.setEquId(UUID.randomUUID().toString());
        int save = terminalEquipmentDao.save(terminalEquipment);
        goodslocationAxisdictionary.setAxisdId(UUID.randomUUID().toString());
        goodslocationAxisdictionary.setAxisdEquId(terminalEquipment.getEquId());
        int save1 = goodslocationAxisdictionaryDao.save(goodslocationAxisdictionary);
        if (save != 1 || save1 != 1) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("Add terminalEquipment failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_ADD_ERR);
            logger.error("Add terminalEquipment failed!", ex);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    public List<TerminalEquipment> find(TerminalEquipment terminalEquipment) {
        return terminalEquipmentDao.find(terminalEquipment);
    }

    @Override
    public long countAll() {
        return terminalEquipmentDao.countAll();
    }
}
