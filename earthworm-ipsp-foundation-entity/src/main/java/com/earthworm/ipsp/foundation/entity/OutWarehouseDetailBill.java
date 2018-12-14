package com.earthworm.ipsp.foundation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 物料出库信息明细表
 */
public class OutWarehouseDetailBill extends BaseEntity {

    private static final long serialVersionUID = 3180493154741682238L;

    private String outwId;// 出库单详情表ID

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 40, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    @Excel(name = "出库单编号", orderNum = "0")
    private String outwBillsnumber;// 出库单编号

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 30, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    @Excel(name = "物料型号", orderNum = "1")
    private String outwModel;// 物料型号

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 40, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    @Excel(name = "物料码", orderNum = "2")
    private String outwCode;// 物料码

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 80, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = "[\\u4E00-\\u9FA5\\w-#]*", message = "NoMatchRegexp")
    @Excel(name = "物料唯一码", orderNum = "3")
    private String outwUniquecode;// 物料唯一码

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 50, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    @Excel(name = "物料生产厂家", orderNum = "4")
    private String outwManufacturer;// 物料生产厂家

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 10, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    @Excel(name = "物料计量单位", orderNum = "5")
    private String outwUnit;// 物料计量单位

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 20, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    @Excel(name = "物料批次", orderNum = "6")
    private String outwBatch;// 物料批次

    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "物料有效日期", orderNum = "7", importFormat = "yyyy-MM-dd",exportFormat ="yyyy-MM-dd")
    private Date outwEffectivedate;// 物料有效日期

    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "物料生产日期", orderNum = "8", importFormat = "yyyy-MM-dd",exportFormat ="yyyy-MM-dd")
    private Date outwProduceddate;// 物料生产日期

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(0)
    @Excel(name = "Bom单实际总数量", orderNum = "9")
    private Short outwTotal;// Bom单实际总数量

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(0)
    @Excel(name = "Bom单需求量", orderNum = "10")
    private Short outwAmount;// Bom单需求量

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(0)
    @Excel(name = "Bom单实际出库量", orderNum = "11")
    private Short outwActualcount;// Bom单实际出库量

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(0)
    @Excel(name = "出库状态", orderNum = "12")
    private Short outwStatus;// 出库状态：0：出库未完成，1：已完成

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(1)
    @Excel(name = "厂库编号", orderNum = "13")
    private Short outwWarehousenumber;// 厂库编号

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(0)
    @Max(1)
    @Excel(name = "是否预出库", orderNum = "14")
    private Short outwIspreout;// 是否预出库：0-否， 1-是，如果是预出库，则数据返回生产准备区

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 50, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    @Excel(name = "预出库单编号", orderNum = "15")
    private String outwPrebillnumber;// 预出库单编号

    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "出库时间", orderNum = "16", importFormat = "yyyy-MM-dd",exportFormat ="yyyy-MM-dd")
    private Date outwOutdatetime;// 出库时间

    public String getOutwId() {
        return outwId;
    }

    public void setOutwId(String outwId) {
        this.outwId = outwId == null ? null : outwId.trim();
    }

    public String getOutwBillsnumber() {
        return outwBillsnumber;
    }

    public void setOutwBillsnumber(String outwBillsnumber) {
        this.outwBillsnumber = outwBillsnumber == null ? null : outwBillsnumber.trim();
    }

    public String getOutwModel() {
        return outwModel;
    }

    public void setOutwModel(String outwModel) {
        this.outwModel = outwModel == null ? null : outwModel.trim();
    }

    public String getOutwCode() {
        return outwCode;
    }

    public void setOutwCode(String outwCode) {
        this.outwCode = outwCode == null ? null : outwCode.trim();
    }

    public String getOutwUniquecode() {
        return outwUniquecode;
    }

    public void setOutwUniquecode(String outwUniquecode) {
        this.outwUniquecode = outwUniquecode == null ? null : outwUniquecode.trim();
    }

    public String getOutwManufacturer() {
        return outwManufacturer;
    }

    public void setOutwManufacturer(String outwManufacturer) {
        this.outwManufacturer = outwManufacturer == null ? null : outwManufacturer.trim();
    }

    public String getOutwUnit() {
        return outwUnit;
    }

    public void setOutwUnit(String outwUnit) {
        this.outwUnit = outwUnit == null ? null : outwUnit.trim();
    }

    public String getOutwBatch() {
        return outwBatch;
    }

    public void setOutwBatch(String outwBatch) {
        this.outwBatch = outwBatch == null ? null : outwBatch.trim();
    }

    public Date getOutwEffectivedate() {
        return outwEffectivedate;
    }

    public void setOutwEffectivedate(Date outwEffectivedate) {
        this.outwEffectivedate = outwEffectivedate;
    }

    public Date getOutwProduceddate() {
        return outwProduceddate;
    }

    public void setOutwProduceddate(Date outwProduceddate) {
        this.outwProduceddate = outwProduceddate;
    }

    public Short getOutwTotal() {
        return outwTotal;
    }

    public void setOutwTotal(Short outwTotal) {
        this.outwTotal = outwTotal;
    }

    public Short getOutwAmount() {
        return outwAmount;
    }

    public void setOutwAmount(Short outwAmount) {
        this.outwAmount = outwAmount;
    }

    public Short getOutwActualcount() {
        return outwActualcount;
    }

    public void setOutwActualcount(Short outwActualcount) {
        this.outwActualcount = outwActualcount;
    }

    public Short getOutwStatus() {
        return outwStatus;
    }

    public void setOutwStatus(Short outwStatus) {
        this.outwStatus = outwStatus;
    }

    public Short getOutwWarehousenumber() {
        return outwWarehousenumber;
    }

    public void setOutwWarehousenumber(Short outwWarehousenumber) {
        this.outwWarehousenumber = outwWarehousenumber;
    }

    public Short getOutwIspreout() {
        return outwIspreout;
    }

    public void setOutwIspreout(Short outwIspreout) {
        this.outwIspreout = outwIspreout;
    }

    public String getOutwPrebillnumber() {
        return outwPrebillnumber;
    }

    public void setOutwPrebillnumber(String outwPrebillnumber) {
        this.outwPrebillnumber = outwPrebillnumber == null ? null : outwPrebillnumber.trim();
    }

    public Date getOutwOutdatetime() {
        return outwOutdatetime;
    }

    public void setOutwOutdatetime(Date outwOutdatetime) {
        this.outwOutdatetime = outwOutdatetime;
    }
}