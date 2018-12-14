package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.GoodslocationAxisdictionary;
import com.earthworm.ipsp.foundation.entity.TerminalEquipment;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;

import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/6 23:49
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface ITerminalEquipmentService {
    List<TerminalEquipment> findAll();

    int deleteById(String equId);

    TerminalEquipment findById(String equId);

    int update(TerminalEquipment terminalEquipment);

    int update(TerminalEquipment terminalEquipment,GoodslocationAxisdictionary goodslocationAxisdictionary) throws IPSPFoundationServiceException;

    int save(TerminalEquipment terminalEquipment);

    int save(TerminalEquipment terminalEquipment, GoodslocationAxisdictionary goodslocationAxisdictionary) throws IPSPFoundationServiceException;

    List<TerminalEquipment> find(TerminalEquipment terminalEquipment);

    long countAll();
}
