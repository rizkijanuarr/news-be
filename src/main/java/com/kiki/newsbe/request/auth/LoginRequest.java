package com.kiki.newsbe.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String user_email;
    private String user_password;
}
