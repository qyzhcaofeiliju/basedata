package com.earthworm.ipsp.foundation.service.interf;


import com.earthworm.ipsp.foundation.entity.InWarehouseBill;
import com.earthworm.ipsp.foundation.entity.InWarehouseDetailBill;
import com.earthworm.ipsp.foundation.service.exception.IPSPFoundationServiceException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import java.util.List;

public interface IInWarehouseBillService {
    List<InWarehouseBill> findAll();

    int deleteById(String id) throws IPSPFoundationServiceException;

    InWarehouseBill findById(String id);

    int update(InWarehouseBill inWarehouseBill);

    int update(InWarehouseBill inWarehouseBill,InWarehouseDetailBill inWarehouseDetailBill) throws  IPSPFoundationServiceException;

    int save(InWarehouseBill inWarehouseBill);

    int save(InWarehouseBill inWarehouseBill,InWarehouseDetailBill inWarehouseDetailBill) throws IPSPFoundationServiceException;

    List<InWarehouseBill> find(InWarehouseBill inWarehouseBill);

    long countAll();

    List prseExcelMethod(MultipartFile file, ServletResponse response);
}
