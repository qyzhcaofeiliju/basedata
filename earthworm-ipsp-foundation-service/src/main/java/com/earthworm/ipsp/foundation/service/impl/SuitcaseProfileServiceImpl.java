package com.earthworm.ipsp.foundation.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import com.earthworm.exception.EarthWormException;
import com.earthworm.ipsp.foundation.dao.interf.ISuitcaseProfileDao;
import com.earthworm.ipsp.foundation.entity.SuitcaseProfile;
import com.earthworm.ipsp.foundation.service.SuitcaseProfileHandlerService;
import com.earthworm.ipsp.foundation.service.interf.ISuitcaseProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SuitcaseProfileServiceImpl implements ISuitcaseProfileService {

    @Autowired
    private ISuitcaseProfileDao suitcaseProfileDao;

    @Override

    public List<SuitcaseProfile> findAll() {
        return suitcaseProfileDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int deleteById(String id) {
        return suitcaseProfileDao.deleteById(id);
    }

    @Override

    public SuitcaseProfile findById(String id) {
        return suitcaseProfileDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int update(SuitcaseProfile suitcaseProfile) {
        return suitcaseProfileDao.update(suitcaseProfile);
    }

    @Override
    @Transactional(rollbackFor = EarthWormException.class)
    public int save(SuitcaseProfile suitcaseProfile) {
        suitcaseProfile.setProId(UUID.randomUUID().toString());
        return suitcaseProfileDao.save(suitcaseProfile);
    }

    @Override

    public List<SuitcaseProfile> find(SuitcaseProfile suitcaseProfile) {
        return suitcaseProfileDao.find(suitcaseProfile);
    }

    @Override

    public long countAll() {
        return suitcaseProfileDao.countAll();
    }

    //解析Excel方法
    @Override
    @Transactional
    public List<SuitcaseProfile> prseExcelMethod(MultipartFile file, ServletResponse response){
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        List<SuitcaseProfile> suitcaseProfiles = new ArrayList<>();
        ImportParams params = new ImportParams();
        IExcelDataHandler<SuitcaseProfile> handler = new SuitcaseProfileHandlerService<>();
        handler.setNeedHandlerFields(new String[]{"仓位总数", "当前状态", "高", "宽", "长", "仓位厚度"});
        params.setDataHanlder(handler);
        try {
            suitcaseProfiles = ExcelImportUtil.importExcel(file.getInputStream(), SuitcaseProfile.class, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suitcaseProfiles;
    };
}
