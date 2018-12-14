package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.ipsp.foundation.dao.interf.IVwAppSysMenuDao;
import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;
import com.earthworm.ipsp.foundation.service.interf.IVwAppSysMenuService;
import com.earthworm.utils.regex.RegexHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("vwAppSysMenuService")
@Transactional
public class VwAppSysMenuServiceImpl implements IVwAppSysMenuService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IVwAppSysMenuDao vwAppSysMenuDao;

    @Transactional(readOnly = true)
    @Override
    public List<VwAppSysMenu> findSysMenu(VwAppSysMenu appSysMenu) {
        return vwAppSysMenuDao.find(appSysMenu);
    }

    @Transactional(readOnly = true)
    @Override
    public List<VwAppSysMenu> findSysMenu(String sysCode) {
        return findSysMenu(new String[]{sysCode});
    }

    @Transactional(readOnly = true)
    @Override
    public List<VwAppSysMenu> findSysMenu(String... sysCode) {
        List<VwAppSysMenu> list = null;

        if(sysCode!=null&&sysCode.length>0) {
            Set<String> sysCodes = Stream.of(sysCode).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
            if(sysCodes!=null&&!sysCodes.isEmpty()){
                list = vwAppSysMenuDao.findBySysCodes(sysCodes);
            }
        }

        return list;
    }
}
