package com.lijiawei.lottery.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SexEnum {

    NAN(1,"nan"),WOMAN(2,"nv");
    private int id;
    private String name;

    public static SexEnum getById(int id) {
        SexEnum[] values = values();
        for (SexEnum value : values) {
            if (value.getId() == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("illegal id : " + id);
    }
}
