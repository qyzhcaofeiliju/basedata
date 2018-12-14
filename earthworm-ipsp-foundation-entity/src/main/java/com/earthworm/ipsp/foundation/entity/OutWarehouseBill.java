package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 出库单
 */
public class OutWarehouseBill extends BaseEntity {

    private static final long serialVersionUID = -1301642345211170756L;

    private String outwId;//出库单ID

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 40, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String outwBillsnumber;//出库单编号

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(1)
    private Short outwCategory;// 出库单分类

    @NotNull(message = EarthWormException.NOTNULL)
    private Date outwOuttime;// 出库时间

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(0)
    private Short outwStatus;// 出库状态：未完成、完成 两种状态，默认为未完成。

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

    public Short getOutwCategory() {
        return outwCategory;
    }

    public void setOutwCategory(Short outwCategory) {
        this.outwCategory = outwCategory;
    }

    public Date getOutwOuttime() {
        return outwOuttime;
    }

    public void setOutwOuttime(Date outwOuttime) {
        this.outwOuttime = outwOuttime;
    }

    public Short getOutwStatus() {
        return outwStatus;
    }

    public void setOutwStatus(Short outwStatus) {
        this.outwStatus = outwStatus;
    }

}