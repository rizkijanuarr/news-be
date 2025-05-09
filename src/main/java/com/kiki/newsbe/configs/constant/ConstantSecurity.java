package com.kiki.newsbe.configs.constant;

public class ConstantSecurity {
    public static final String SECRET = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static final long EXPIRATION_TIME = 1000 * 60 * 15; // 15 minutes
    public static final long REFRESH_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7 days
    public static final long EXPIRATION_TIME_GOOGLE = 86400000L * 7; // Expired OAuth Google
    public static final String BEARER_TOKEN_PREFIX = "Bearer "; // Prefix di Authorization header
    public static final String BASIC_TOKEN_PREFIX = "Basic "; // Prefix basic auth
    public static final String SIGN_UP_URL = "/users/sign-up"; // Endpoint signup
    public static final String NAME_APP = "CRUD Spring Boot"; // Nama aplikasi
}
