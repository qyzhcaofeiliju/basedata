package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.IFixshelfBillinfoDao;
import com.earthworm.ipsp.foundation.dao.interf.IFixshelfDetailinfoDao;
import com.earthworm.ipsp.foundation.entity.FixshelfBillinfo;
import com.earthworm.ipsp.foundation.entity.FixshelfDetailinfo;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import com.earthworm.ipsp.foundation.service.interf.IFixshelfBillinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/8 13:30
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
@Service
public class FixshelfBillinfoServiceImpl implements IFixshelfBillinfoService {

    private static Logger logger = LoggerFactory.getLogger(FixshelfBillinfoServiceImpl.class);

    @Autowired
    private IFixshelfBillinfoDao fixshelfBillinfoDao;

    @Autowired
    private IFixshelfDetailinfoDao fixshelfDetailinfoDao;

    @Override
    public List<FixshelfBillinfo> findAll() {
        return fixshelfBillinfoDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) throws IPSPFoundationServiceException {
        FixshelfBillinfo fixshelfBillinfo = fixshelfBillinfoDao.findById(id);
        String billNumber = fixshelfBillinfo.getBillNumber();
        FixshelfDetailinfo fixshelfDetailinfo = new FixshelfDetailinfo();
        fixshelfDetailinfo.setFixBillsnumber(billNumber);
        List<FixshelfDetailinfo> fixshelfDetailinfos = fixshelfDetailinfoDao.find(fixshelfDetailinfo);
        int detailFixwIdTemp = 0;

        for (int i = 0; i < fixshelfDetailinfos.size(); i++) {
            detailFixwIdTemp = fixshelfDetailinfoDao.deleteById(fixshelfDetailinfos.get(i).getFixId());
        }
        int fixTemp = fixshelfBillinfoDao.deleteById(id);
        if (detailFixwIdTemp == 0 || fixTemp == 0) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("DELETE fixshelfBillinfo failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_DEL_ERR);
            logger.error("DELETE fixshelfBillinfo failed!", ex);
            throw ex;
        } else {
            return 1;
        }

    }

    @Override
    public FixshelfBillinfo findById(String id) {
        return fixshelfBillinfoDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(FixshelfBillinfo fixshelfBillinfo) {
        return fixshelfBillinfoDao.update(fixshelfBillinfo);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(FixshelfBillinfo fixshelfBillinfo, FixshelfDetailinfo fixshelfDetailinfo) throws IPSPFoundationServiceException {
        int update = fixshelfBillinfoDao.update(fixshelfBillinfo);
        int update1 = fixshelfDetailinfoDao.update(fixshelfDetailinfo);
        if (update != 1 || update1 != 1) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("update inWarehouse failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_UPT_ERR);
            logger.error("Upt inWarehouse failed!", ex);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(FixshelfBillinfo fixshelfBillinfo) {
        fixshelfBillinfo.setBillId(UUID.randomUUID().toString());
        return fixshelfBillinfoDao.save(fixshelfBillinfo);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(FixshelfBillinfo fixshelfBillinfo, FixshelfDetailinfo fixshelfDetailinfo) throws IPSPFoundationServiceException {
        fixshelfBillinfo.setBillId(UUID.randomUUID().toString());
        int save = fixshelfBillinfoDao.save(fixshelfBillinfo);
        fixshelfDetailinfo.setFixId(UUID.randomUUID().toString());
        int save1 = fixshelfDetailinfoDao.save(fixshelfDetailinfo);
        if (save != 1 || save1 != 1) {
            IPSPFoundationServiceException ex = new IPSPFoundationServiceException("Add inWarehouse failed!", IPSPFoundationServiceException.FoundationCode.INWAREHOUSE_ADD_ERR);
            logger.error("Add inWarehouse failed!", ex);
            throw ex;
        } else {
            return 1;
        }
    }

    @Override
    public List<FixshelfBillinfo> find(FixshelfBillinfo fixshelfBillinfo) {
        return fixshelfBillinfoDao.find(fixshelfBillinfo);
    }

    @Override
    public long countAll() {
        return fixshelfBillinfoDao.countAll();
    }

    public List<FixshelfBillinfo> findLikeInfo(FixshelfBillinfo fixshelfBillinfo){
        return  fixshelfBillinfoDao.findLikeInfo(fixshelfBillinfo);
    };
}