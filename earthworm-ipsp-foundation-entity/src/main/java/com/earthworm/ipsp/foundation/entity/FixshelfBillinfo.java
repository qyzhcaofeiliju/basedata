package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 固定货架表单信息
 */
public class FixshelfBillinfo extends BaseEntity {

    private static final long serialVersionUID = 4419283846903019549L;

    private String billId;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String billNumber;

    @NotNull(message = EarthWormException.NOTNULL)
    private Date billArrivaltime;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short billStatus;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short billSite;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short billCategory;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId == null ? null : billId.trim();
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber == null ? null : billNumber.trim();
    }

    public Date getBillArrivaltime() {
        return billArrivaltime;
    }

    public void setBillArrivaltime(Date billArrivaltime) {
        this.billArrivaltime = billArrivaltime;
    }

    public Short getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Short billStatus) {
        this.billStatus = billStatus;
    }

    public Short getBillSite() {
        return billSite;
    }

    public void setBillSite(Short billSite) {
        this.billSite = billSite;
    }

    public Short getBillCategory() {
        return billCategory;
    }

    public void setBillCategory(Short billCategory) {
        this.billCategory = billCategory;
    }
}