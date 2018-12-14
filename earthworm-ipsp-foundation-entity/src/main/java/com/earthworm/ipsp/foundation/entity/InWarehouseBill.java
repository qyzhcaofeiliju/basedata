package com.earthworm.ipsp.foundation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 入库单
 */
public class InWarehouseBill extends BaseEntity {

    private static final long serialVersionUID = -8251095250279087950L;

    private String inwId;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "编号", orderNum = "0")
    private String inwBillsnumber;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    @Excel(name = "分类", orderNum = "1")
    private Short inwCategory;

    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "入库时间", orderNum = "2")
    private Date inwIntime;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 0, message = "BEYONDMIN")
    @Excel(name = "状态", orderNum = "3")
    private Short inwStatus;

    public String getInwId() {
        return inwId;
    }

    public void setInwId(String inwId) {
        this.inwId = inwId == null ? null : inwId.trim();
    }

    public String getInwBillsnumber() {
        return inwBillsnumber;
    }

    public void setInwBillsnumber(String inwBillsnumber) {
        this.inwBillsnumber = inwBillsnumber == null ? null : inwBillsnumber.trim();
    }

    public Short getInwCategory() {
        return inwCategory;
    }

    public void setInwCategory(Short inwCategory) {
        this.inwCategory = inwCategory;
    }

    public Date getInwIntime() {
        return inwIntime;
    }

    public void setInwIntime(Date inwIntime) {
        this.inwIntime = inwIntime;
    }

    public Short getInwStatus() {
        return inwStatus;
    }

    public void setInwStatus(Short inwStatus) {
        this.inwStatus = inwStatus;
    }
}