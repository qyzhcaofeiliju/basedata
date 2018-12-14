package com.earthworm.ipsp.foundation.dao.interf;

import com.earthworm.ipsp.foundation.entity.VwAppSysMenu;
import com.earthworm.ipsp.foundation.entity.VwTerminalCategory;
import com.earthworm.postgres.annotation.MapperScanner;
import com.earthworm.postgres.mapper.BaseDao;

import java.util.List;
import java.util.Map;
import java.util.Set;

@MapperScanner
public interface IVwAppSysMenuDao extends BaseDao<VwAppSysMenu> {
    /**
     * Find by VwAppSysMenu
     * @param vwAppSysMenu
     * @return
     */
    List<VwAppSysMenu> find(VwAppSysMenu vwAppSysMenu);

    /**
     * Find by app codes
     * @param sysCodes
     * @return
     */
    List<VwAppSysMenu> findBySysCodes(Set<String> sysCodes);

    /**
     * Find by map condition
     * @param condition
     * @return
     */
    List<VwAppSysMenu> findByCondition(Map<String, Object> condition);
}
