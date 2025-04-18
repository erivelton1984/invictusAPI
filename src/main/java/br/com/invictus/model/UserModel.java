package br.com.invictus.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="users")
public class UserModel implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn (name = "id_user")},
            inverseJoinColumns = {@JoinColumn (name = "id_permission")}
    )
    private List<PermissionModel> permissions;

    public UserModel() {}

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (PermissionModel permission : permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

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

    public List<PermissionModel> getPermissions() {
       return permissions;
    }

    public void setPermissions(List<PermissionModel> permissions) {
       this.permissions = permissions;
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
        if (!(o instanceof UserModel userModel)) return false;
        return Objects.equals(getId(), userModel.getId()) && Objects.equals(getUserName(), userModel.getUserName()) && Objects.equals(getFullName(), userModel.getFullName()) && Objects.equals(getPassword(), userModel.getPassword()) && Objects.equals(isAccountNonExpired(), userModel.isAccountNonExpired()) && Objects.equals(isAccountNonLocked(), userModel.isAccountNonLocked()) && Objects.equals(isCredentialsNonExpired(), userModel.isCredentialsNonExpired()) && Objects.equals(isEnabled(), userModel.isEnabled()) && Objects.equals(getEmail(), userModel.getEmail()) && Objects.equals(getPermissions(), userModel.getPermissions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getFullName(), getPassword(), isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled(), getEmail(), getPermissions());
    }
}
