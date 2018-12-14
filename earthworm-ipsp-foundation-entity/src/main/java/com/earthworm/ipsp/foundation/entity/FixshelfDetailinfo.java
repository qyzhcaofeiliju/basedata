package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 生产区固定货架的物料信息，
 */
public class FixshelfDetailinfo extends BaseEntity {

    private static final long serialVersionUID = -3876868384643049869L;

    private String fixId;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixSuiid;

    @Size(max = 50, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixSuiuniquecode;

    @Size(max = 30, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixMaterialmodel;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixMaterialcode;

    @Size(max = 80, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixMaterialuniquecode;

    @Size(max = 50, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixMaterialmanufacturer;

    @Size(max = 10, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixMaterialunit;

    @Size(max = 20, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixMaterialbatch;

    @NotNull(message = EarthWormException.NOTNULL)
    private Date fixMaterialeffectivedate;

    @NotNull(message = EarthWormException.NOTNULL)
    private Date fixMaterialproduceddate;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixMaterialamount;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixIsreimbursement;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixCabinindex;

    @NotNull(message = EarthWormException.NOTNULL)
    private Date fixBindtime;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixBillscategory;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixBillsnumber;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixProcesscode;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixCurrentpath;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixCurrentsite;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixTargetsite;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixTargetpath;

    @Size(max = 50, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String fixBulksuiuniquecode;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixIsbulkmaterial;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short fixFromsource;

    @Size(max = 200, message = "BEYONDSIZE")
    private String fixDescription;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 0, message = "BEYONDMIN")
    private Short fixStatus;

    public String getFixId() {
        return fixId;
    }

    public void setFixId(String fixId) {
        this.fixId = fixId == null ? null : fixId.trim();
    }

    public String getFixSuiid() {
        return fixSuiid;
    }

    public void setFixSuiid(String fixSuiid) {
        this.fixSuiid = fixSuiid == null ? null : fixSuiid.trim();
    }

    public String getFixSuiuniquecode() {
        return fixSuiuniquecode;
    }

    public void setFixSuiuniquecode(String fixSuiuniquecode) {
        this.fixSuiuniquecode = fixSuiuniquecode == null ? null : fixSuiuniquecode.trim();
    }

    public String getFixMaterialmodel() {
        return fixMaterialmodel;
    }

    public void setFixMaterialmodel(String fixMaterialmodel) {
        this.fixMaterialmodel = fixMaterialmodel == null ? null : fixMaterialmodel.trim();
    }

    public String getFixMaterialcode() {
        return fixMaterialcode;
    }

    public void setFixMaterialcode(String fixMaterialcode) {
        this.fixMaterialcode = fixMaterialcode == null ? null : fixMaterialcode.trim();
    }

    public String getFixMaterialuniquecode() {
        return fixMaterialuniquecode;
    }

    public void setFixMaterialuniquecode(String fixMaterialuniquecode) {
        this.fixMaterialuniquecode = fixMaterialuniquecode == null ? null : fixMaterialuniquecode.trim();
    }

    public String getFixMaterialmanufacturer() {
        return fixMaterialmanufacturer;
    }

    public void setFixMaterialmanufacturer(String fixMaterialmanufacturer) {
        this.fixMaterialmanufacturer = fixMaterialmanufacturer == null ? null : fixMaterialmanufacturer.trim();
    }

    public String getFixMaterialunit() {
        return fixMaterialunit;
    }

    public void setFixMaterialunit(String fixMaterialunit) {
        this.fixMaterialunit = fixMaterialunit == null ? null : fixMaterialunit.trim();
    }

    public String getFixMaterialbatch() {
        return fixMaterialbatch;
    }

    public void setFixMaterialbatch(String fixMaterialbatch) {
        this.fixMaterialbatch = fixMaterialbatch == null ? null : fixMaterialbatch.trim();
    }

    public Date getFixMaterialeffectivedate() {
        return fixMaterialeffectivedate;
    }

    public void setFixMaterialeffectivedate(Date fixMaterialeffectivedate) {
        this.fixMaterialeffectivedate = fixMaterialeffectivedate;
    }

    public Date getFixMaterialproduceddate() {
        return fixMaterialproduceddate;
    }

    public void setFixMaterialproduceddate(Date fixMaterialproduceddate) {
        this.fixMaterialproduceddate = fixMaterialproduceddate;
    }

    public Short getFixMaterialamount() {
        return fixMaterialamount;
    }

    public void setFixMaterialamount(Short fixMaterialamount) {
        this.fixMaterialamount = fixMaterialamount;
    }

    public Short getFixIsreimbursement() {
        return fixIsreimbursement;
    }

    public void setFixIsreimbursement(Short fixIsreimbursement) {
        this.fixIsreimbursement = fixIsreimbursement;
    }

    public Short getFixCabinindex() {
        return fixCabinindex;
    }

    public void setFixCabinindex(Short fixCabinindex) {
        this.fixCabinindex = fixCabinindex;
    }

    public Date getFixBindtime() {
        return fixBindtime;
    }

    public void setFixBindtime(Date fixBindtime) {
        this.fixBindtime = fixBindtime;
    }

    public Short getFixBillscategory() {
        return fixBillscategory;
    }

    public void setFixBillscategory(Short fixBillscategory) {
        this.fixBillscategory = fixBillscategory;
    }

    public String getFixBillsnumber() {
        return fixBillsnumber;
    }

    public void setFixBillsnumber(String fixBillsnumber) {
        this.fixBillsnumber = fixBillsnumber == null ? null : fixBillsnumber.trim();
    }

    public String getFixProcesscode() {
        return fixProcesscode;
    }

    public void setFixProcesscode(String fixProcesscode) {
        this.fixProcesscode = fixProcesscode == null ? null : fixProcesscode.trim();
    }

    public Short getFixCurrentpath() {
        return fixCurrentpath;
    }

    public void setFixCurrentpath(Short fixCurrentpath) {
        this.fixCurrentpath = fixCurrentpath;
    }

    public Short getFixCurrentsite() {
        return fixCurrentsite;
    }

    public void setFixCurrentsite(Short fixCurrentsite) {
        this.fixCurrentsite = fixCurrentsite;
    }

    public Short getFixTargetsite() {
        return fixTargetsite;
    }

    public void setFixTargetsite(Short fixTargetsite) {
        this.fixTargetsite = fixTargetsite;
    }

    public Short getFixTargetpath() {
        return fixTargetpath;
    }

    public void setFixTargetpath(Short fixTargetpath) {
        this.fixTargetpath = fixTargetpath;
    }

    public String getFixBulksuiuniquecode() {
        return fixBulksuiuniquecode;
    }

    public void setFixBulksuiuniquecode(String fixBulksuiuniquecode) {
        this.fixBulksuiuniquecode = fixBulksuiuniquecode == null ? null : fixBulksuiuniquecode.trim();
    }

    public Short getFixIsbulkmaterial() {
        return fixIsbulkmaterial;
    }

    public void setFixIsbulkmaterial(Short fixIsbulkmaterial) {
        this.fixIsbulkmaterial = fixIsbulkmaterial;
    }

    public Short getFixFromsource() {
        return fixFromsource;
    }

    public void setFixFromsource(Short fixFromsource) {
        this.fixFromsource = fixFromsource;
    }

    public String getFixDescription() {
        return fixDescription;
    }

    public void setFixDescription(String fixDescription) {
        this.fixDescription = fixDescription == null ? null : fixDescription.trim();
    }

    public Short getFixStatus() {
        return fixStatus;
    }

    public void setFixStatus(Short fixStatus) {
        this.fixStatus = fixStatus;
    }
}