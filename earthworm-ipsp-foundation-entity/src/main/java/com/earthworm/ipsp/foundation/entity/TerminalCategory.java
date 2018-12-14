package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * 设备表
 */
public class TerminalCategory extends BaseEntity {

    private static final long serialVersionUID = -7904392969675358513L;

    private String catId;// 设备分类ID

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 30, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String catModel;// 设备型号

    @NotBlank(message = EarthWormException.NOTNULL)
    @Size(max = 100, message = "OUTLENGTH")
    @BlockSQLInject
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NoMatchRegexp")
    private String catName;// 设备名称

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(1)
    private Short catFunccategory;// 设备功能分类

    @NotNull(message = EarthWormException.NOTNULL)
    @Min(1)
    private Short catAppcategory;// 设备应用分类

    @Size(max = 100, message = "OUTLENGTH")
    private String catDescription;// 设备描述信息

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId == null ? null : catId.trim();
    }

    public String getCatModel() {
        return catModel;
    }

    public void setCatModel(String catModel) {
        this.catModel = catModel == null ? null : catModel.trim();
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public Short getCatFunccategory() {
        return catFunccategory;
    }

    public void setCatFunccategory(Short catFunccategory) {
        this.catFunccategory = catFunccategory;
    }

    public Short getCatAppcategory() {
        return catAppcategory;
    }

    public void setCatAppcategory(Short catAppcategory) {
        this.catAppcategory = catAppcategory;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription == null ? null : catDescription.trim();
    }
}