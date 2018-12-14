package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AppSys extends BaseEntity {
    private String sysId;

    @Size(max = 20, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    private String sysCode;

    private String sysName;

    @Size(max = 64, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    private String sysAddress;

    @NotNull(message = EarthWormException.NOTNULL)
    private LocalDateTime sysCreateTime;

    @NotNull(message = EarthWormException.NOTNULL)
    private LocalDateTime sysModifyTime;

    private String sysModifyAuthor;

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    public String getSysAddress() {
        return sysAddress;
    }

    public void setSysAddress(String sysAddress) {
        this.sysAddress = sysAddress == null ? null : sysAddress.trim();
    }

    public LocalDateTime getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(LocalDateTime sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public LocalDateTime getSysModifyTime() {
        return sysModifyTime;
    }

    public void setSysModifyTime(LocalDateTime sysModifyTime) {
        this.sysModifyTime = sysModifyTime;
    }

    public String getSysModifyAuthor() {
        return sysModifyAuthor;
    }

    public void setSysModifyAuthor(String sysModifyAuthor) {
        this.sysModifyAuthor = sysModifyAuthor == null ? null : sysModifyAuthor.trim();
    }
}