package br.com.invictus.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class ProjectModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_address")
    private String projectAddress;

    @Column(name = "project_phone")
    private String projectPhone;

    @Column(name = "project_phone_two")
    private String projectPhoneTwo;

    @Column(name = "email_project")
    private String emailProject;

    @Column(name = "enabled")
    private Boolean enabled;


    @Column(name = "schedule")
    private List<String> classSchedule;

    // Getters e Setters
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
        if (!(o instanceof ProjectModel)) return false;
        ProjectModel that = (ProjectModel) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getProjectName(), that.getProjectName()) &&
                Objects.equals(getProjectAddress(), that.getProjectAddress()) &&
                Objects.equals(getProjectPhone(), that.getProjectPhone()) &&
                Objects.equals(getProjectPhoneTwo(), that.getProjectPhoneTwo()) &&
                Objects.equals(getEmailProject(), that.getEmailProject()) &&
                Objects.equals(getEnabled(), that.getEnabled()) &&
                Objects.equals(getClassSchedule(), that.getClassSchedule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProjectName(), getProjectAddress(), getProjectPhone(),
                getProjectPhoneTwo(), getEmailProject(), getEnabled(), getClassSchedule());
    }
}