package com.wj.vegetablebackend.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/**
 * 情侣角色枚举
 */
@Getter
public enum CoupleRoleEnum {
    FEEDER("饲养员", "feeder"),
    FOODIE("吃货", "foodie");

    private final String text;

    private final String value;

    CoupleRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值的value
     * @return 枚举值
     */
    public static CoupleRoleEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (CoupleRoleEnum anEnum : CoupleRoleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
