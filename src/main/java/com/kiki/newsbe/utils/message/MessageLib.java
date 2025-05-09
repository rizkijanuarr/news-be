package com.kiki.newsbe.utils.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageLib {

    private final MessageConfig messageConfig;

    @Autowired
    public MessageLib(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    public String getUserIdNotFound() {
        return messageConfig.get("user.id.not.found");
    }

    public String getUserNameCantNull() {
        return messageConfig.get("user.name.cannot.be.null");
    }

    public String getUserEmailCantNull() {
        return messageConfig.get("user.email.cannot.be.null");
    }

    public String getUserPasswordCantNull() {
        return messageConfig.get("user.password.cannot.be.null");
    }

    public String getCategoryNotFound() {
        return messageConfig.get("category.not.found");
    }
}
