package com.kiki.newsbe.utils.enumaration;

import java.util.List;

public enum MitraTypeEnum {
    INDIVIDUAL("Perorangan"),
    GOVERMENT_AGENCIES("Instansi Pemerintah"),
    EDUCATION_AGENCIES("Instansi Pendidikan"),
    PRIVATE_COMPANY("Perusahaan Swasta");

    private String value;

    MitraTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<MitraTypeEnum> getAllEnum() {
        return List.of(INDIVIDUAL, GOVERMENT_AGENCIES, EDUCATION_AGENCIES, PRIVATE_COMPANY);
    }
}
