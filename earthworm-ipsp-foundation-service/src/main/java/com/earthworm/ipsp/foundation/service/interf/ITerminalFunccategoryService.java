package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.TerminalFunccategory;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/7 19:46
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface ITerminalFunccategoryService {

    List<TerminalFunccategory> findAll();

    int deleteById(String funId);

    TerminalFunccategory findById(String funId);

    int update(TerminalFunccategory terminalFunccategory);

    int save(TerminalFunccategory terminalFunccategory);

    List<TerminalFunccategory> find(TerminalFunccategory terminalFunccategory);

    List<TerminalFunccategory> findLikeInfo(TerminalFunccategory terminalFunccategory);

    long countAll();


}
