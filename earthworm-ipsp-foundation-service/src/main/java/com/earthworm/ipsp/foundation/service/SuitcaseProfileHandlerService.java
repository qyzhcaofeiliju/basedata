package com.earthworm.ipsp.foundation.service;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuitcaseProfileHandlerService<SuitcaseProfile> extends ExcelDataHandlerDefaultImpl<SuitcaseProfile> {
    private static final Logger log = LoggerFactory.getLogger(Object.class);

    private static Set<String> shortFields = new HashSet<>();

    static {
        shortFields = Stream.of("proCabintotal", "proStatus", "proHeight", "proWidth", "proLength", "proCabinwidth",
                "仓位总数", "当前状态", "高", "宽", "长", "仓位厚度")
                .collect(Collectors.toSet());
    }

    @Override
    public Object importHandler(SuitcaseProfile obj, String name, Object value) {
        return value != null && shortFields.contains(name) ? Short.valueOf(String.valueOf(value)) : value;
    }

}