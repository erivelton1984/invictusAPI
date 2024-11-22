package br.com.invictus.data.vo;

import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TeatcherVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstNameTeatcher;
    private String lastNameTeatcher;
    private LocalDate birthDateTeatcher;
    private Double weightTeatcher;
    private String addressTeatcher;
    private String genderTeatcher;
    private String emailTeatcher;
    private String phoneTeatcher;
    private String phoneTeatcherTwo;
    private Boolean enabled;
    private BeltENUM belt;
    private DegreeENUM degree;
    private Long projectId;
    private String photoBase64;

    public TeatcherVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameTeatcher() {
        return firstNameTeatcher;
    }

    public void setFirstNameTeatcher(String firstNameTeatcher) {
        this.firstNameTeatcher = firstNameTeatcher;
    }

    public String getLastNameTeatcher() {
        return lastNameTeatcher;
    }

    public void setLastNameTeatcher(String lastNameTeatcher) {
        this.lastNameTeatcher = lastNameTeatcher;
    }

    public LocalDate getBirthDateTeatcher() {
        return birthDateTeatcher;
    }

    public void setBirthDateTeatcher(LocalDate birthDateTeatcher) {
        this.birthDateTeatcher = birthDateTeatcher;
    }

    public Double getWeightTeatcher() {
        return weightTeatcher;
    }

    public void setWeightTeatcher(Double weightTeatcher) {
        this.weightTeatcher = weightTeatcher;
    }

    public String getAddressTeatcher() {
        return addressTeatcher;
    }

    public void setAddressTeatcher(String addressTeatcher) {
        this.addressTeatcher = addressTeatcher;
    }

    public String getGenderTeatcher() {
        return genderTeatcher;
    }

    public void setGenderTeatcher(String genderTeatcher) {
        this.genderTeatcher = genderTeatcher;
    }

    public String getEmailTeatcher() {
        return emailTeatcher;
    }

    public void setEmailTeatcher(String emailTeatcher) {
        this.emailTeatcher = emailTeatcher;
    }

    public String getPhoneTeatcher() {
        return phoneTeatcher;
    }

    public void setPhoneTeatcher(String phoneTeatcher) {
        this.phoneTeatcher = phoneTeatcher;
    }

    public String getPhoneTeatcherTwo() {
        return phoneTeatcherTwo;
    }

    public void setPhoneTeatcherTwo(String phoneTeatcherTwo) {
        this.phoneTeatcherTwo = phoneTeatcherTwo;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public BeltENUM getBelt() {
        return belt;
    }

    public void setBelt(BeltENUM belt) {
        this.belt = belt;
    }

    public DegreeENUM getDegree() {
        return degree;
    }

    public void setDegree(DegreeENUM degree) {
        this.degree = degree;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeatcherVO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFirstNameTeatcher(), that.getFirstNameTeatcher()) && Objects.equals(getLastNameTeatcher(), that.getLastNameTeatcher()) && Objects.equals(getBirthDateTeatcher(), that.getBirthDateTeatcher()) && Objects.equals(getWeightTeatcher(), that.getWeightTeatcher()) && Objects.equals(getAddressTeatcher(), that.getAddressTeatcher()) && Objects.equals(getGenderTeatcher(), that.getGenderTeatcher()) && Objects.equals(getEmailTeatcher(), that.getEmailTeatcher()) && Objects.equals(getPhoneTeatcher(), that.getPhoneTeatcher()) && Objects.equals(getPhoneTeatcherTwo(), that.getPhoneTeatcherTwo()) && Objects.equals(getEnabled(), that.getEnabled()) && getBelt() == that.getBelt() && getDegree() == that.getDegree() && Objects.equals(getProjectId(), that.getProjectId()) && Objects.equals(getPhotoBase64(), that.getPhotoBase64());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstNameTeatcher(), getLastNameTeatcher(), getBirthDateTeatcher(), getWeightTeatcher(), getAddressTeatcher(), getGenderTeatcher(), getEmailTeatcher(), getPhoneTeatcher(), getPhoneTeatcherTwo(), getEnabled(), getBelt(), getDegree(), getProjectId(), getPhotoBase64());
    }
}