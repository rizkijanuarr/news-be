package com.kiki.newsbe.annotations.swagger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SwaggerTypeGroup {
    DEFAULT("Default Endpoint", "Endpoint terbuka"),
    APPS_WEB("App Web", "Hanya Untuk apps Web"),
    APPS_MOBILE("App Mobile", "Hanya Untuk apps Mobile");

    private String value;
    private String description;

    SwaggerTypeGroup(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public static Map<SwaggerTypeGroup, List<String>> swaggerTypeGroupListMapGetDefault() {
        Map<SwaggerTypeGroup, List<String>> swaggerTypeGroupListMap = new HashMap<>();
        for (SwaggerTypeGroup swaggerTypeGroup : SwaggerTypeGroup.values()) {
            swaggerTypeGroupListMap
                    .computeIfAbsent(swaggerTypeGroup, k -> new ArrayList<>());
        }

        return swaggerTypeGroupListMap;
    }

    public String getDescription() {
        return description;
    }
}
