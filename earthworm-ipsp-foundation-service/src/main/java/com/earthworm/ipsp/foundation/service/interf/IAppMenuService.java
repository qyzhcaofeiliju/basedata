package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.AppMenu;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;

import javax.validation.Valid;
import java.util.List;

public interface IAppMenuService {
    /**
     * Get all menus
     * @return List
     */
    public List<AppMenu> findAll();

    /**
     * Add menu
     * @return boolean
     */
    public boolean addMenu(@Valid AppMenu menu) throws IPSPFoundationServiceException;

    /**
     * Update menu
     * @param menu
     * @return boolean
     */
    public boolean updateMenu(AppMenu menu) throws IPSPFoundationServiceException;

    /**
     * Query all submenu
     * @param menu
     * @param includeSelf
     * @return List
     */
    public List<AppMenu> findAllChildren(AppMenu menu, boolean includeSelf);

    /**
     * Delete menu and submenus
     * @param menu
     * @param includeSelf
     * @return boolean
     */
    public boolean cascadeDelete(AppMenu menu, boolean includeSelf) throws IPSPFoundationServiceException;
}
