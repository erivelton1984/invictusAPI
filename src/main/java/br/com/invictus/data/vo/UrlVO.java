package br.com.invictus.data.vo;

import java.io.Serializable;
import java.util.Objects;

public class UrlVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeUrl;
    private String url;
    private Long idUsuario;

    public UrlVO() {
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
        if (!(o instanceof UrlVO urlVO)) return false;
        return Objects.equals(getId(), urlVO.getId()) && Objects.equals(getNomeUrl(), urlVO.getNomeUrl()) && Objects.equals(getUrl(), urlVO.getUrl()) && Objects.equals(getIdUsuario(), urlVO.getIdUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNomeUrl(), getUrl(), getIdUsuario());
    }
}