package br.com.invictus.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "permission")
public class PermissionModel implements GrantedAuthority, Serializable {

    private static final long serialVersioUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    public PermissionModel() {
    }

    public PermissionModel(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return this.description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionModel that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription());
    }
}
