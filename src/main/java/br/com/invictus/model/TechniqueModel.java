package br.com.invictus.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "techniques")
public class TechniqueModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="technique_name")
    private String techniqueName;

    @Column(name = "technique_address")
    private String techniqueAddress;

    public TechniqueModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechniqueName() {
        return techniqueName;
    }

    public void setTechniqueName(String techniqueName) {
        this.techniqueName = techniqueName;
    }

    public String getTechniqueAddress() {
        return techniqueAddress;
    }

    public void setTechniqueAddress(String techniqueAddress) {
        this.techniqueAddress = techniqueAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TechniqueModel that = (TechniqueModel) o;
        return Objects.equals(id, that.id) && Objects.equals(techniqueName, that.techniqueName) && Objects.equals(techniqueAddress, that.techniqueAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, techniqueName, techniqueAddress);
    }
}
