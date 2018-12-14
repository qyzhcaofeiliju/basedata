package com.earthworm.ipsp.foundation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 入库单详情表
 */
public class InWarehouseDetailBill extends BaseEntity {

    private static final long serialVersionUID = -9200644817960542672L;

    private String inwId;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "编号", orderNum = "0")
    private String inwBillsnumber;

    @Size(max = 30, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "规格型号", orderNum = "1")
    private String inwModel;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "编码", orderNum = "2")
    private String inwCode;

    @Size(max = 80, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject(regex = false,specialChar = "!")
    @Pattern(regexp = "[\\u4E00-\\u9FA5\\w-#]*", message = "NOMATCHREGEXP")
    @Excel(name = "唯一码", orderNum = "3")
    private String inwUniquecode;

    @Size(max = 50, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "厂家", orderNum = "4")
    private String inwManufacturer;

    @Size(max = 20, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "计量单位", orderNum = "5")
    private String inwUnit;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "批次", orderNum = "6")
    private String inwBatch;

    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "有效日期", orderNum = "7", importFormat = "yyyy-MM-dd",exportFormat ="yyyy-MM-dd")
    private Date inwEffectivedate;

    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "生产日期", orderNum = "8", importFormat = "yyyy-MM-dd",exportFormat ="yyyy-MM-dd")
    private Date inwProduceddate;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    @Excel(name = "入库数量", orderNum = "9")
    private Short inwAmount;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    @Excel(name = "实际入库数量", orderNum = "10", importFormat = "Short")
    private Short inwActualcount;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 0, message = "BEYONDMIN")
    @Excel(name = "入库状态", orderNum = "11")
    private Short inwStatus;

    @Size(max = 30, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "仓库编码", orderNum = "12")
    private String inwWarehousenumber;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    @Excel(name = "货位排", orderNum = "13")
    private Short inwRank;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    @Excel(name = "货位层", orderNum = "14")
    private Short inwTier;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    @Excel(name = "货位列", orderNum = "15")
    private Short inwColumn;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    @Excel(name = "仓库索引", orderNum = "16")
    private Short inwRangeindex;

    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "入库日期", orderNum = "17", importFormat = "yyyy-MM-dd",exportFormat ="yyyy-MM-dd")
    private Date inwIndatetime;

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

    public String getInwModel() {
        return inwModel;
    }

    public void setInwModel(String inwModel) {
        this.inwModel = inwModel == null ? null : inwModel.trim();
    }

    public String getInwCode() {
        return inwCode;
    }

    public void setInwCode(String inwCode) {
        this.inwCode = inwCode == null ? null : inwCode.trim();
    }

    public String getInwUniquecode() {
        return inwUniquecode;
    }

    public void setInwUniquecode(String inwUniquecode) {
        this.inwUniquecode = inwUniquecode == null ? null : inwUniquecode.trim();
    }

    public String getInwManufacturer() {
        return inwManufacturer;
    }

    public void setInwManufacturer(String inwManufacturer) {
        this.inwManufacturer = inwManufacturer == null ? null : inwManufacturer.trim();
    }

    public String getInwUnit() {
        return inwUnit;
    }

    public void setInwUnit(String inwUnit) {
        this.inwUnit = inwUnit == null ? null : inwUnit.trim();
    }

    public String getInwBatch() {
        return inwBatch;
    }

    public void setInwBatch(String inwBatch) {
        this.inwBatch = inwBatch == null ? null : inwBatch.trim();
    }

    public Date getInwEffectivedate() {
        return inwEffectivedate;
    }

    public void setInwEffectivedate(Date inwEffectivedate) {
        this.inwEffectivedate = inwEffectivedate;
    }

    public Date getInwProduceddate() {
        return inwProduceddate;
    }

    public void setInwProduceddate(Date inwProduceddate) {
        this.inwProduceddate = inwProduceddate;
    }

    public Short getInwAmount() {
        return inwAmount;
    }

    public void setInwAmount(Short inwAmount) {
        this.inwAmount = inwAmount;
    }

    public Short getInwActualcount() {
        return inwActualcount;
    }

    public void setInwActualcount(Short inwActualcount) {
        this.inwActualcount = inwActualcount;
    }

    public Short getInwStatus() {
        return inwStatus;
    }

    public void setInwStatus(Short inwStatus) {
        this.inwStatus = inwStatus;
    }

    public String getInwWarehousenumber() {
        return inwWarehousenumber;
    }

    public void setInwWarehousenumber(String inwWarehousenumber) {
        this.inwWarehousenumber = inwWarehousenumber == null ? null : inwWarehousenumber.trim();
    }

    public Short getInwRank() {
        return inwRank;
    }

    public void setInwRank(Short inwRank) {
        this.inwRank = inwRank;
    }

    public Short getInwTier() {
        return inwTier;
    }

    public void setInwTier(Short inwTier) {
        this.inwTier = inwTier;
    }

    public Short getInwColumn() {
        return inwColumn;
    }

    public void setInwColumn(Short inwColumn) {
        this.inwColumn = inwColumn;
    }

    public Short getInwRangeindex() {
        return inwRangeindex;
    }

    public void setInwRangeindex(Short inwRangeindex) {
        this.inwRangeindex = inwRangeindex;
    }

    public Date getInwIndatetime() {
        return inwIndatetime;
    }

    public void setInwIndatetime(Date inwIndatetime) {
        this.inwIndatetime = inwIndatetime;
    }
}