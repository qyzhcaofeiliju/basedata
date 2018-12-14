package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.TerminalCategory;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 15:10
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface ITerminalCategoryService {

    List<TerminalCategory> findAll();

    int deleteById(String catId);

    TerminalCategory findById(String catId);

    int update(TerminalCategory terminalCategory);

    int save(TerminalCategory terminalCategory);

    List<TerminalCategory> find(TerminalCategory terminalCategory);

    long countAll();
}
