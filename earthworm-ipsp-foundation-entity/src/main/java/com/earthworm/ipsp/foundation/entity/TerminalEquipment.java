package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 站点设备
 */
public class TerminalEquipment extends BaseEntity {

    private static final long serialVersionUID = 3012304343887012725L;

    private String equId;// 设备ID

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 40, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String equCatId;// 设备分类ID

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 40, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String equStaId;// 设备站点ID

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 50, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String equUniquecode;// 设备唯一码

    @NotNull(message = EarthWormException.NOTNULL)
    private Integer equNumber;// 终端设备分类编号用四位数据表示（前两位表示产品分类、后两位表示具体的设备数量编号）

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(0)
    @Max(3)
    private Short equCurrentstatus;// 设备当前状态

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 20, message = "OUTLENGTH")
    @BlockSQLInject
    /*@Pattern(regexp = "[/^(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5])$/]+", message = "IP地址不合法")*/
    private String equIp;// 设备的ip地址

    @NotNull(message = EarthWormException.NOTNULL)
    private Integer equPort;// 设备的端口

    @Size(max = 100, message = "OUTLENGTH")
    private String equDescription;// 设备描述

    public String getEquId() {
        return equId;
    }

    public void setEquId(String equId) {
        this.equId = equId == null ? null : equId.trim();
    }

    public String getEquCatId() {
        return equCatId;
    }

    public void setEquCatId(String equCatId) {
        this.equCatId = equCatId == null ? null : equCatId.trim();
    }

    public String getEquStaId() {
        return equStaId;
    }

    public void setEquStaId(String equStaId) {
        this.equStaId = equStaId == null ? null : equStaId.trim();
    }

    public String getEquUniquecode() {
        return equUniquecode;
    }

    public void setEquUniquecode(String equUniquecode) {
        this.equUniquecode = equUniquecode == null ? null : equUniquecode.trim();
    }

    public Integer getEquNumber() {
        return equNumber;
    }

    public void setEquNumber(Integer equNumber) {
        this.equNumber = equNumber;
    }

    public Short getEquCurrentstatus() {
        return equCurrentstatus;
    }

    public void setEquCurrentstatus(Short equCurrentstatus) {
        this.equCurrentstatus = equCurrentstatus;
    }

    public String getEquIp() {
        return equIp;
    }

    public void setEquIp(String equIp) {
        this.equIp = equIp == null ? null : equIp.trim();
    }

    public Integer getEquPort() {
        return equPort;
    }

    public void setEquPort(Integer equPort) {
        this.equPort = equPort;
    }

    public String getEquDescription() {
        return equDescription;
    }

    public void setEquDescription(String equDescription) {
        this.equDescription = equDescription == null ? null : equDescription.trim();
    }
}