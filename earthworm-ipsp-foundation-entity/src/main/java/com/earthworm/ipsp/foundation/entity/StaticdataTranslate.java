package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 静态数据翻译表
 */
public class StaticdataTranslate extends BaseEntity {

    private static final long serialVersionUID = 6007620411719920993L;

    private String traId;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1,message = "BEYONDMIN")
    private Short traAppcategory;

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(value = 1,message = "BEYONDMIN")
    private Short traRawvalue;

    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Size(max = 50,message = "BEYONDSIZE")
    @Pattern(regexp = RegexHelper.NAME_PATTERN,message = "NOMATCHREGEXP")
    private String traTranslatevalue;

    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Size(max = 40,message = "BEYONDSIZE")
    @Pattern(regexp = RegexHelper.NAME_PATTERN,message = "NOMATCHREGEXP")
    private String traOwnerid;

    @NotBlank(message = EarthWormException.NOTNULL)
    @BlockSQLInject
    @Size(max = 10,message = "BEYONDSIZE")
    @Pattern(regexp = RegexHelper.NAME_PATTERN,message = "NOMATCHREGEXP")
    private String traOwnername;
    
    @Size(max = 200,message = "BEYONDSIZE")
    private String traAppdescription;

    public String getTraId() {
        return traId;
    }

    public void setTraId(String traId) {
        this.traId = traId == null ? null : traId.trim();
    }

    public Short getTraAppcategory() {
        return traAppcategory;
    }

    public void setTraAppcategory(Short traAppcategory) {
        this.traAppcategory = traAppcategory;
    }

    public Short getTraRawvalue() {
        return traRawvalue;
    }

    public void setTraRawvalue(Short traRawvalue) {
        this.traRawvalue = traRawvalue;
    }

    public String getTraTranslatevalue() {
        return traTranslatevalue;
    }

    public void setTraTranslatevalue(String traTranslatevalue) {
        this.traTranslatevalue = traTranslatevalue == null ? null : traTranslatevalue.trim();
    }

    public String getTraOwnerid() {
        return traOwnerid;
    }

    public void setTraOwnerid(String traOwnerid) {
        this.traOwnerid = traOwnerid == null ? null : traOwnerid.trim();
    }

    public String getTraOwnername() {
        return traOwnername;
    }

    public void setTraOwnername(String traOwnername) {
        this.traOwnername = traOwnername == null ? null : traOwnername.trim();
    }

    public String getTraAppdescription() {
        return traAppdescription;
    }

    public void setTraAppdescription(String traAppdescription) {
        this.traAppdescription = traAppdescription == null ? null : traAppdescription.trim();
    }
}