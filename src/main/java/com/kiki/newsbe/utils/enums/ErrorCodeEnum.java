package com.kiki.newsbe.utils.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    EMPTY(0, "Value Null or Empty");

    private Integer code;
    private String description;

    ErrorCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
