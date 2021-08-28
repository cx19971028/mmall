package com.njtech.mmall.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum GenderEnums {

    MAN(1,"男"),
    WOMAN(0, "女");

    @EnumValue// save to db
    private Integer code;
    private String value;

    GenderEnums(Integer code, String gender) {
        this.code = code;
        this.value = gender;
    }
}
