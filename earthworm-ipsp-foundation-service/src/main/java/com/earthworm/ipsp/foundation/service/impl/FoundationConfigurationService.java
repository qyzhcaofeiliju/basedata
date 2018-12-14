package com.earthworm.ipsp.foundation.service.impl;

import com.earthworm.ipsp.foundation.dao.interf.IAppSysDao;
import com.earthworm.ipsp.foundation.entity.AppSys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("foundationConfiguration")
public class FoundationConfigurationService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IAppSysDao appSysDao;
    private static String sysCode;
    @Value("${ew.ipsp.foundation.sys.code}")
    String foundationSysCode;
    private static Map<String,String> sysAddress = new HashMap<>();
    private static FoundationConfigurationService cfg = new FoundationConfigurationService();

    private FoundationConfigurationService(){
    }

    /**
     * Init app address
     */
    @PostConstruct
    public void initSysAddress() {
        if(StringUtils.isNotBlank(foundationSysCode)) {
            // Get app
            AppSys appSys = new AppSys();
            appSys.setSysCode(foundationSysCode);
            List<AppSys> appSysList = appSysDao.find(appSys);
            // Get default app address
            if(appSysList!=null&&!appSysList.isEmpty()) {
                appSys=appSysList.get(0);
                if(appSys!=null)
                    sysAddress.put(foundationSysCode,appSys.getSysAddress());
            }
            sysCode = foundationSysCode;
        }
    }

    /**
     * FoundationConfigurationService instance
     * @return
     */
    public static FoundationConfigurationService getInstance() {
        return cfg;
    }

    /**
     * Get app address
     * @return
     */
    public String getSysAddress() {
        return sysAddress.get(sysCode);
    }
}
