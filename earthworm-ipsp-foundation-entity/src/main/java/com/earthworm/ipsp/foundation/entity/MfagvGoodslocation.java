package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 多功能AGV货位
 */
public class MfagvGoodslocation extends BaseEntity {

    private static final long serialVersionUID = 3607753696700499108L;

    private String gslId;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String gslAxisid;

    @Size(max = 50, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String gslSuitcaseuniquecode;

    @Size(max = 40, message = "BEYONDSIZE")
    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String gslBillsnumber;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1, message = "BEYONDMIN")
    private Short gslLocationnumber;

    public String getGslId() {
        return gslId;
    }

    public void setGslId(String gslId) {
        this.gslId = gslId == null ? null : gslId.trim();
    }

    public String getGslAxisid() {
        return gslAxisid;
    }

    public void setGslAxisid(String gslAxisid) {
        this.gslAxisid = gslAxisid == null ? null : gslAxisid.trim();
    }

    public String getGslSuitcaseuniquecode() {
        return gslSuitcaseuniquecode;
    }

    public void setGslSuitcaseuniquecode(String gslSuitcaseuniquecode) {
        this.gslSuitcaseuniquecode = gslSuitcaseuniquecode == null ? null : gslSuitcaseuniquecode.trim();
    }

    public String getGslBillsnumber() {
        return gslBillsnumber;
    }

    public void setGslBillsnumber(String gslBillsnumber) {
        this.gslBillsnumber = gslBillsnumber == null ? null : gslBillsnumber.trim();
    }

    public Short getGslLocationnumber() {
        return gslLocationnumber;
    }

    public void setGslLocationnumber(Short gslLocationnumber) {
        this.gslLocationnumber = gslLocationnumber;
    }
}