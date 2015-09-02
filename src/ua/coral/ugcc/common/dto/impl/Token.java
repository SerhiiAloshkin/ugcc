package ua.coral.ugcc.common.dto.impl;

import ua.coral.ugcc.common.annotation.ShortString;
import ua.coral.ugcc.common.dto.Dto;

import java.io.Serializable;

public class Token implements Dto, Serializable {

    private Long id = System.currentTimeMillis();
    @ShortString
    private String accessToken;
    @ShortString
    private String refreshToken;
    private Long expiredDate;

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(final Long expiredDate) {
        this.expiredDate = expiredDate;
    }
}
