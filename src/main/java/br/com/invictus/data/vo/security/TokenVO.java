package br.com.invictus.data.vo.security;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private Boolean authenticated;
    private Date create;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    private String resetPasswordToken;
    private Date resetPasswordExpiration;

    public TokenVO() {}

    public TokenVO(String userName, Boolean authenticated, Date create, Date expiration, String accessToken, String refreshToken) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.create = create;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Date getResetPasswordExpiration() {
        return resetPasswordExpiration;
    }

    public void setResetPasswordExpiration(Date resetPasswordExpiration) {
        this.resetPasswordExpiration = resetPasswordExpiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenVO tokenVO)) return false;
        return Objects.equals(getUserName(), tokenVO.getUserName()) && Objects.equals(getAuthenticated(), tokenVO.getAuthenticated()) && Objects.equals(getCreate(), tokenVO.getCreate()) && Objects.equals(getExpiration(), tokenVO.getExpiration()) && Objects.equals(getAccessToken(), tokenVO.getAccessToken()) && Objects.equals(getRefreshToken(), tokenVO.getRefreshToken()) && Objects.equals(getResetPasswordToken(), tokenVO.getResetPasswordToken()) && Objects.equals(getResetPasswordExpiration(), tokenVO.getResetPasswordExpiration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getAuthenticated(), getCreate(), getExpiration(), getAccessToken(), getRefreshToken(), getResetPasswordToken(), getResetPasswordExpiration());
    }
}
