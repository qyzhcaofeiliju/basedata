package com.earthworm.ipsp.foundation.service;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import com.earthworm.ipsp.foundation.entity.OutWarehouseDetailBill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutWHandlerService extends ExcelDataHandlerDefaultImpl<OutWarehouseDetailBill> {
    private static final Logger log = LoggerFactory.getLogger(OutWarehouseDetailBill.class);
    private static Set<String> shortFields = new HashSet<>();

    static {
        shortFields = Stream.of("outwTotal", "outwAmount", "outwActualcount", "outwStatus", "outwWarehousenumber", "outwIspreout",
                "Bom单实际总数量", "Bom单需求量", "Bom单实际出库量", "出库状态", "厂库编号", "是否预出库")
                .collect(Collectors.toSet());
    }

    @Override
    public Object importHandler(OutWarehouseDetailBill obj, String name, Object value) {
        return value != null && shortFields.contains(name) ? Short.valueOf(String.valueOf(value)) : value;
    }
}
