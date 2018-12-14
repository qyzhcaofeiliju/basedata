package com.earthworm.ipsp.foundation.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 周转箱特征描述数据表
 */
public class SuitcaseProfile extends BaseEntity {

    private static final long serialVersionUID = -5252874128190732638L;

    private String proId;

    @Size(max = 50, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = "[\\u4E00-\\u9FA5\\w-#]*", message = "NOMATCHREGEXP")
    @Excel(name = "唯一码", orderNum = "0")
    private String proUniquecode;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "仓位总数", orderNum = "1")
    private Short proCabintotal;

    @Size(max = 200, message = "BEYONDSIZE")
    @Excel(name = "描述信息", orderNum = "2")
    private String proDescription;

    @Min(value = 0, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "当前状态", orderNum = "3")
    private Short proStatus;

    @NotNull(message = EarthWormException.NOTNULL)
    private Boolean proIspartition;

    @Min(value = 0, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short proVerticalamount;

    @Min(value = 0, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short proHorizontalamount;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    @Excel(name = "RFID码", orderNum = "4")
    private String proRfid;

    @Min(value = 0,message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "高", orderNum = "5")
    private Short proHeight;

    @Min(value = 0,message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "宽", orderNum = "6")
    private Short proWidth;

    @Min(value = 0,message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "长", orderNum = "7")
    private Short proLength;

    @Min(value = 0,message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "仓位厚度", orderNum = "8")
    private Short proCabinwidth;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "功能分类", orderNum = "9")
    private Short proFunccategory;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    @Excel(name = "应用分类", orderNum = "10")
    private Short proAppcategory;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getProUniquecode() {
        return proUniquecode;
    }

    public void setProUniquecode(String proUniquecode) {
        this.proUniquecode = proUniquecode == null ? null : proUniquecode.trim();
    }

    public Short getProCabintotal() {
        return proCabintotal;
    }

    public void setProCabintotal(Short proCabintotal) {
        this.proCabintotal = proCabintotal;
    }

    public Short getProFunccategory() {
        return proFunccategory;
    }

    public void setProFunccategory(Short proFunccategory) {
        this.proFunccategory = proFunccategory;
    }

    public Short getProAppcategory() {
        return proAppcategory;
    }

    public void setProAppcategory(Short proAppcategory) {
        this.proAppcategory = proAppcategory;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription == null ? null : proDescription.trim();
    }

    public Short getProStatus() {
        return proStatus;
    }

    public void setProStatus(Short proStatus) {
        this.proStatus = proStatus;
    }

    public Boolean getProIspartition() {
        return proIspartition;
    }

    public void setProIspartition(Boolean proIspartition) {
        this.proIspartition = proIspartition;
    }

    public Short getProVerticalamount() {
        return proVerticalamount;
    }

    public void setProVerticalamount(Short proVerticalamount) {
        this.proVerticalamount = proVerticalamount;
    }

    public Short getProHorizontalamount() {
        return proHorizontalamount;
    }

    public void setProHorizontalamount(Short proHorizontalamount) {
        this.proHorizontalamount = proHorizontalamount;
    }

    public String getProRfid() {
        return proRfid;
    }

    public void setProRfid(String proRfid) {
        this.proRfid = proRfid == null ? null : proRfid.trim();
    }

    public Short getProHeight() {
        return proHeight;
    }

    public void setProHeight(Short proHeight) {
        this.proHeight = proHeight;
    }

    public Short getProWidth() {
        return proWidth;
    }

    public void setProWidth(Short proWidth) {
        this.proWidth = proWidth;
    }

    public Short getProLength() {
        return proLength;
    }

    public void setProLength(Short proLength) {
        this.proLength = proLength;
    }

    public Short getProCabinwidth() {
        return proCabinwidth;
    }

    public void setProCabinwidth(Short proCabinwidth) {
        this.proCabinwidth = proCabinwidth;
    }
}