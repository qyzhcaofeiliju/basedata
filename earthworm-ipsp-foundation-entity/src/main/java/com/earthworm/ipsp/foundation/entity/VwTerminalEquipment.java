package com.earthworm.ipsp.foundation.entity;

import com.earthworm.postgres.entity.BaseEntity;

public class VwTerminalEquipment extends BaseEntity {

    private static final long serialVersionUID = -5903653682189085359L;

    private String equId;

    private String equCatId;

    private String equStaId;

    private String equUniquecode;

    private Integer equNumber;

    private Short equCurrentstatus;

    private String equIp;

    private Integer equPort;

    private String equDescription;

    private String catName;

    private String staName;

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

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName == null ? null : staName.trim();
    }
}