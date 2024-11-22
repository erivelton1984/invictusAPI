package br.com.invictus.data.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ProjectVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String projectName;
    private String projectAddress;
    private String projectPhone;
    private String projectPhoneTwo;
    private String emailProject;
    private Boolean enabled;
    private List<String> classSchedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectPhone() {
        return projectPhone;
    }

    public void setProjectPhone(String projectPhone) {
        this.projectPhone = projectPhone;
    }

    public String getProjectPhoneTwo() {
        return projectPhoneTwo;
    }

    public void setProjectPhoneTwo(String projectPhoneTwo) {
        this.projectPhoneTwo = projectPhoneTwo;
    }

    public String getEmailProject() {
        return emailProject;
    }

    public void setEmailProject(String emailProject) {
        this.emailProject = emailProject;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(List<String> classSchedule) {
        this.classSchedule = classSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectVO projectVO)) return false;
        return Objects.equals(getId(), projectVO.getId()) && Objects.equals(getProjectName(), projectVO.getProjectName()) && Objects.equals(getProjectAddress(), projectVO.getProjectAddress()) && Objects.equals(getProjectPhone(), projectVO.getProjectPhone()) && Objects.equals(getProjectPhoneTwo(), projectVO.getProjectPhoneTwo()) && Objects.equals(getEmailProject(), projectVO.getEmailProject()) && Objects.equals(getEnabled(), projectVO.getEnabled()) && Objects.equals(getClassSchedule(), projectVO.getClassSchedule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProjectName(), getProjectAddress(), getProjectPhone(), getProjectPhoneTwo(), getEmailProject(), getEnabled(), getClassSchedule());
    }
}
