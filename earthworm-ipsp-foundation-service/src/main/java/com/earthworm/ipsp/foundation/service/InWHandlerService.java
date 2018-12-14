package com.earthworm.ipsp.foundation.service;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InWHandlerService<InWarehouseDetailBill> extends ExcelDataHandlerDefaultImpl<InWarehouseDetailBill> {
    private static final Logger log = LoggerFactory.getLogger(Object.class);

    private static Set<String> shortFields = new HashSet<>();

    static {
        shortFields = Stream.of("inwAmount", "inwActualcount", "inwStatus", "inwRank", "inwTier", "inwColumn", "inwRangeindex",
                "入库数量", "实际入库数量", "入库状态", "货位排", "货位层", "货位列", "仓库索引")
                .collect(Collectors.toSet());
    }

    @Override
    public Object importHandler(InWarehouseDetailBill obj, String name, Object value) {
        return value != null && shortFields.contains(name) ? Short.valueOf(String.valueOf(value)) : value;
    }

}