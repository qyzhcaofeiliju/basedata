package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 配码完成后生成的 仓位详情信息表。
 */
public class CabinDetailinfo extends BaseEntity {

    private static final long serialVersionUID = -3129141661815280952L;

    private String detId;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detSuiid;

    @Size(max = 50, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detSuiuniquecode;

    @Size(max = 30, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detMaterialmodel;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detMaterialcode;

    @Size(max = 80, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detMaterialuniquecode;

    @Size(max = 50, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detMaterialmanufacturer;

    @Size(max = 10, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detMaterialunit;

    @Size(max = 20, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detMaterialbatch;

    @NotNull(message = EarthWormException.NOTNULL)
    private Date detMaterialeffectivedate;

    @NotNull(message = EarthWormException.NOTNULL)
    private Date detMaterialproduceddate;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detMaterialamount;

    @Min(value = 0, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detIsreimbursement;

    @Min(value = 0, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detCabinindex;

    @NotNull(message = EarthWormException.NOTNULL)
    private Date detBindtime;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detBillscategory;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detBillsnumber;

    @Size(max = 40, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detProcesscode;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detCurrentpath;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detCurrentsite;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detTargetsite;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detTargetpath;

    @Size(max = 50, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String detBulksuiuniquecode;

    @Min(value = 0, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detIsbulkmaterial;

    @Min(value = 0, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short detFromsource;

    @Size(max = 200, message = "BEYONDSIZE")
    private String detDescription;

    @NotNull(message = EarthWormException.NOTNULL)
    private Boolean detIshalf;

    public String getDetId() {
        return detId;
    }

    public void setDetId(String detId) {
        this.detId = detId == null ? null : detId.trim();
    }

    public String getDetSuiid() {
        return detSuiid;
    }

    public void setDetSuiid(String detSuiid) {
        this.detSuiid = detSuiid == null ? null : detSuiid.trim();
    }

    public String getDetSuiuniquecode() {
        return detSuiuniquecode;
    }

    public void setDetSuiuniquecode(String detSuiuniquecode) {
        this.detSuiuniquecode = detSuiuniquecode == null ? null : detSuiuniquecode.trim();
    }

    public String getDetMaterialmodel() {
        return detMaterialmodel;
    }

    public void setDetMaterialmodel(String detMaterialmodel) {
        this.detMaterialmodel = detMaterialmodel == null ? null : detMaterialmodel.trim();
    }

    public String getDetMaterialcode() {
        return detMaterialcode;
    }

    public void setDetMaterialcode(String detMaterialcode) {
        this.detMaterialcode = detMaterialcode == null ? null : detMaterialcode.trim();
    }

    public String getDetMaterialuniquecode() {
        return detMaterialuniquecode;
    }

    public void setDetMaterialuniquecode(String detMaterialuniquecode) {
        this.detMaterialuniquecode = detMaterialuniquecode == null ? null : detMaterialuniquecode.trim();
    }

    public String getDetMaterialmanufacturer() {
        return detMaterialmanufacturer;
    }

    public void setDetMaterialmanufacturer(String detMaterialmanufacturer) {
        this.detMaterialmanufacturer = detMaterialmanufacturer == null ? null : detMaterialmanufacturer.trim();
    }

    public String getDetMaterialunit() {
        return detMaterialunit;
    }

    public void setDetMaterialunit(String detMaterialunit) {
        this.detMaterialunit = detMaterialunit == null ? null : detMaterialunit.trim();
    }

    public String getDetMaterialbatch() {
        return detMaterialbatch;
    }

    public void setDetMaterialbatch(String detMaterialbatch) {
        this.detMaterialbatch = detMaterialbatch == null ? null : detMaterialbatch.trim();
    }

    public Date getDetMaterialeffectivedate() {
        return detMaterialeffectivedate;
    }

    public void setDetMaterialeffectivedate(Date detMaterialeffectivedate) {
        this.detMaterialeffectivedate = detMaterialeffectivedate;
    }

    public Date getDetMaterialproduceddate() {
        return detMaterialproduceddate;
    }

    public void setDetMaterialproduceddate(Date detMaterialproduceddate) {
        this.detMaterialproduceddate = detMaterialproduceddate;
    }

    public Short getDetMaterialamount() {
        return detMaterialamount;
    }

    public void setDetMaterialamount(Short detMaterialamount) {
        this.detMaterialamount = detMaterialamount;
    }

    public Short getDetIsreimbursement() {
        return detIsreimbursement;
    }

    public void setDetIsreimbursement(Short detIsreimbursement) {
        this.detIsreimbursement = detIsreimbursement;
    }

    public Short getDetCabinindex() {
        return detCabinindex;
    }

    public void setDetCabinindex(Short detCabinindex) {
        this.detCabinindex = detCabinindex;
    }

    public Date getDetBindtime() {
        return detBindtime;
    }

    public void setDetBindtime(Date detBindtime) {
        this.detBindtime = detBindtime;
    }

    public Short getDetBillscategory() {
        return detBillscategory;
    }

    public void setDetBillscategory(Short detBillscategory) {
        this.detBillscategory = detBillscategory;
    }

    public String getDetBillsnumber() {
        return detBillsnumber;
    }

    public void setDetBillsnumber(String detBillsnumber) {
        this.detBillsnumber = detBillsnumber == null ? null : detBillsnumber.trim();
    }

    public String getDetProcesscode() {
        return detProcesscode;
    }

    public void setDetProcesscode(String detProcesscode) {
        this.detProcesscode = detProcesscode == null ? null : detProcesscode.trim();
    }

    public Short getDetCurrentpath() {
        return detCurrentpath;
    }

    public void setDetCurrentpath(Short detCurrentpath) {
        this.detCurrentpath = detCurrentpath;
    }

    public Short getDetCurrentsite() {
        return detCurrentsite;
    }

    public void setDetCurrentsite(Short detCurrentsite) {
        this.detCurrentsite = detCurrentsite;
    }

    public Short getDetTargetsite() {
        return detTargetsite;
    }

    public void setDetTargetsite(Short detTargetsite) {
        this.detTargetsite = detTargetsite;
    }

    public Short getDetTargetpath() {
        return detTargetpath;
    }

    public void setDetTargetpath(Short detTargetpath) {
        this.detTargetpath = detTargetpath;
    }

    public String getDetBulksuiuniquecode() {
        return detBulksuiuniquecode;
    }

    public void setDetBulksuiuniquecode(String detBulksuiuniquecode) {
        this.detBulksuiuniquecode = detBulksuiuniquecode == null ? null : detBulksuiuniquecode.trim();
    }

    public Short getDetIsbulkmaterial() {
        return detIsbulkmaterial;
    }

    public void setDetIsbulkmaterial(Short detIsbulkmaterial) {
        this.detIsbulkmaterial = detIsbulkmaterial;
    }

    public Short getDetFromsource() {
        return detFromsource;
    }

    public void setDetFromsource(Short detFromsource) {
        this.detFromsource = detFromsource;
    }

    public String getDetDescription() {
        return detDescription;
    }

    public void setDetDescription(String detDescription) {
        this.detDescription = detDescription == null ? null : detDescription.trim();
    }

    public Boolean getDetIshalf() {
        return detIshalf;
    }

    public void setDetIshalf(Boolean detIshalf) {
        this.detIshalf = detIshalf;
    }
}