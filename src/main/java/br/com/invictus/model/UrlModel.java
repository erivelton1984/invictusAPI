package br.com.invictus.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "url")
public class UrlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_url")
    private String nomeUrl;

    @Column(name = "url")
    private String url;

    @Column(name = "usuario_id")
    private Long idUsuario;

    public UrlModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUrl() {
        return nomeUrl;
    }

    public void setNomeUrl(String nomeUrl) {
        this.nomeUrl = nomeUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UrlModel urlModel)) return false;
        return Objects.equals(getId(), urlModel.getId()) && Objects.equals(getNomeUrl(), urlModel.getNomeUrl()) && Objects.equals(getUrl(), urlModel.getUrl()) && Objects.equals(getIdUsuario(), urlModel.getIdUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNomeUrl(), getUrl(), getIdUsuario());
    }
}