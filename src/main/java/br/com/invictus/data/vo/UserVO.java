
package br.com.invictus.data.vo;


import br.com.invictus.model.PermissionModel;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private String email;
    private List<PermissionModel> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PermissionModel> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionModel> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserVO userVO)) return false;
        return Objects.equals(getId(), userVO.getId()) && Objects.equals(getUserName(), userVO.getUserName()) && Objects.equals(getFullName(), userVO.getFullName()) && Objects.equals(getPassword(), userVO.getPassword()) && Objects.equals(getAccountNonExpired(), userVO.getAccountNonExpired()) && Objects.equals(getAccountNonLocked(), userVO.getAccountNonLocked()) && Objects.equals(getCredentialsNonExpired(), userVO.getCredentialsNonExpired()) && Objects.equals(getEnabled(), userVO.getEnabled()) && Objects.equals(getEmail(), userVO.getEmail()) && Objects.equals(getPermissions(), userVO.getPermissions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getFullName(), getPassword(), getAccountNonExpired(), getAccountNonLocked(), getCredentialsNonExpired(), getEnabled(), getEmail(), getPermissions());
    }
}
