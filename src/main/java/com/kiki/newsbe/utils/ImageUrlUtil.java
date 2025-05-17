package com.kiki.newsbe.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUrlUtil {

    @Value("${app.base-url}")
    private String baseUrl;

    public String getImageUrl(String filename) {
        if (filename == null || filename.isEmpty()) {
            return null;
        }
        return baseUrl + "/storage/" + filename;
    }
} 