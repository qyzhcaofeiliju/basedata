package com.earthworm.ipsp.foundation.service.interf;

import com.earthworm.ipsp.foundation.entity.OutWarehouseBill;
import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import java.util.List;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/6/6 23:49
 * @version: v1.0.0
 * @Type:
 * @Desc:
 */
public interface IOutWarehouseBillService {
    List<OutWarehouseBill> findAll();

    int deleteById(String outwId) throws IPSPFoundationServiceException;

    OutWarehouseBill findById(String outwId);

    int update(OutWarehouseBill outWarehouseBill);

    int update(OutWarehouseBill outWarehouseBill, OutWarehouseDetailBill outWarehouseDetailBill) throws IPSPFoundationServiceException;

    int save(OutWarehouseBill outWarehouseBill);

    int save(OutWarehouseBill outWarehouseBill, OutWarehouseDetailBill outWarehouseDetailBill) throws IPSPFoundationServiceException;

    List<OutWarehouseBill> find(OutWarehouseBill outWarehouseBill);

    long countAll();

    List prseExcelMethod(MultipartFile file, ServletResponse response);




}
