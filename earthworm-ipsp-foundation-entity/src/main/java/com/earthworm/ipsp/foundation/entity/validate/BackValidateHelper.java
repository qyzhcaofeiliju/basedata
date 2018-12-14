package com.earthworm.ipsp.foundation.entity.validate;

import com.earthworm.exception.EWValidationException;
import com.earthworm.exception.EarthWormException;
import com.earthworm.postgres.validate.ValidateHelper;

import java.util.Map;

/**
 * @Author: yangh@i-earthworm.com
 * @Date: 2018/11/24 11:22
 * @Version: v1.0.0
 * @Type: validate 验证类
 * @Desc: 产线功能模块中的后台验证相关类
 */
public class BackValidateHelper {

    /**
     * “保存”操作时的后台验证
     *
     * @param object
     */
    public static void backValidateForSave(Object object) {
        Map<String, String> validate = ValidateHelper.validate(object);
        if (validate != null && !validate.isEmpty()) {
            throw new EWValidationException(validate);
        }
    }

    /**
     * “更新/修改”操作时的后台验证
     * @param object
     */
    public static void backValidateForUpdate(Object object) {
        Map<String, String> validate = ValidateHelper.validate(object);
        if (validate != null && !validate.isEmpty()) {
            // 如果为NOTBLANK的话，就不用抛出EWValidationException验证
            if (validate.values().stream().anyMatch(code -> !EarthWormException.NOTNULL.equals(code)))
                throw new EWValidationException(validate);
        }
    }
}
