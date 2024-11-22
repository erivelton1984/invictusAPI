package br.com.invictus.data.vo;

import java.util.Objects;

public class PasswordRecoveryRequestVO {

    private String userName;
    private String email;

    // Getters e Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordRecoveryRequestVO that)) return false;
        return Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getEmail());
    }
}
