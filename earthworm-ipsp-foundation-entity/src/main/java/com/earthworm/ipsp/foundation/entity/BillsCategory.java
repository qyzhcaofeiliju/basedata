package com.earthworm.ipsp.foundation.entity;

import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.entity.BaseEntity;
import com.earthworm.postgres.validate.BlockSQLInject;
import com.earthworm.utils.regex.RegexHelper;

import javax.validation.constraints.*;

/**
 * Bom表单分类
 */
public class BillsCategory extends BaseEntity {

    private static final long serialVersionUID = 2918368038632744754L;

    private String catId;

    @Size(max = 64, message = "BEYONDSIZE")
    @BlockSQLInject
    @NotBlank(message = EarthWormException.NOTNULL)
    @Pattern(regexp = RegexHelper.NAME_PATTERN, message = "NOMATCHREGEXP")
    private String catName;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short catCategory;

    @Min(value = 1, message = "BEYONDMIN")
    @NotNull(message = EarthWormException.NOTNULL)
    private Short catOwnercategory;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId == null ? null : catId.trim();
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public Short getCatCategory() {
        return catCategory;
    }

    public void setCatCategory(Short catCategory) {
        this.catCategory = catCategory;
    }

    public Short getCatOwnercategory() {
        return catOwnercategory;
    }

    public void setCatOwnercategory(Short catOwnercategory) {
        this.catOwnercategory = catOwnercategory;
    }
}