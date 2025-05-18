package br.com.invictus.data.vo;

import java.io.Serializable;
import java.util.Objects;

public class TechniqueVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String techniqueName;
    private String techniqueAddress;

    public TechniqueVO() {
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
        TechniqueVO that = (TechniqueVO) o;
        return Objects.equals(id, that.id) && Objects.equals(techniqueName, that.techniqueName) && Objects.equals(techniqueAddress, that.techniqueAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, techniqueName, techniqueAddress);
    }
}
