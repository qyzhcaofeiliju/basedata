package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.TerminalAppcategory;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 19:46
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface ITerminalAppcategoryService {

    List<TerminalAppcategory> findAll();

    int deleteById(String appId);

    TerminalAppcategory findById(String appId);

    int update(TerminalAppcategory terminalAppcategory);

    int save(TerminalAppcategory terminalAppcategory);

    List<TerminalAppcategory> find(TerminalAppcategory terminalAppcategory);

    List<TerminalAppcategory> findLikeInfo(TerminalAppcategory terminalAppcategory);

    long countAll();
}
