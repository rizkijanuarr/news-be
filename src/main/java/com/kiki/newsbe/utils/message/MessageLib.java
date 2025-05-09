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

    public String getMitraNameCantNull() {
        return messageConfig.get("mitra.name.cannot.be.null");
    }

    public String getMitraPhoneCantNull() {
        return messageConfig.get("mitra.phone.cannot.be.null");
    }

    public String getMitraAddressCantNull() {
        return messageConfig.get("mitra.address.cannot.be.null");
    }

    public String getMitraTypeCantNull() {
        return messageConfig.get("mitra.type.cannot.be.null");
    }

    public String getMitraNotFound() {
        return messageConfig.get("mitra.not.found");
    }

    public String getFarmerIdNotFound() {
        return messageConfig.get("farmer.id.not.found");
    }

    public String getFarmerNameNotFound() {
        return messageConfig.get("farmer.name.not.found");
    }

    public String getFarmerPhoneNotFound() {
        return messageConfig.get("farmer.phone.not.found");
    }

    public String getFarmerAddressNotFound() {
        return messageConfig.get("farmer.address.not.found");
    }

    public String getAreaNotFound() {
        return messageConfig.get("area.not.found");
    }

    public String getAreaNameCantNull() {
        return messageConfig.get("area.name.not.found");
    }

    public String getAreaLandCantNull() {
        return messageConfig.get("area.land.not.found");
    }

    public String getAreaCoordinatesCantNull() {
        return messageConfig.get("area.coordinates.not.found");
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
}
