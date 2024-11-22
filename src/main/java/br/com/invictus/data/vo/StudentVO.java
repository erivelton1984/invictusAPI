package br.com.invictus.data.vo;


import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import jakarta.persistence.Column;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class StudentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String studentName;
    private String rg;
    private String cpf;
    private String studentAddress;
    private String studentPhone;
    private String studentCellPhone;
    private String studentEmail;
    private LocalDate birthDate;
    private String observation;
    private BeltENUM beltENUM;
    private DegreeENUM degreeENUM;
    private Long projectId;
    private Long teatcherId;

    public StudentVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentCellPhone() {
        return studentCellPhone;
    }

    public void setStudentCellPhone(String studentCellPhone) {
        this.studentCellPhone = studentCellPhone;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public BeltENUM getBeltENUM() {
        return beltENUM;
    }

    public void setBeltENUM(BeltENUM beltENUM) {
        this.beltENUM = beltENUM;
    }

    public DegreeENUM getDegreeENUM() {
        return degreeENUM;
    }

    public void setDegreeENUM(DegreeENUM degreeENUM) {
        this.degreeENUM = degreeENUM;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTeatcherId() {
        return teatcherId;
    }

    public void setTeatcherId(Long teatcherId) {
        this.teatcherId = teatcherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentVO studentVO)) return false;
        return Objects.equals(getId(), studentVO.getId()) && Objects.equals(getStudentName(), studentVO.getStudentName()) && Objects.equals(getRg(), studentVO.getRg()) && Objects.equals(getCpf(), studentVO.getCpf()) && Objects.equals(getStudentAddress(), studentVO.getStudentAddress()) && Objects.equals(getStudentPhone(), studentVO.getStudentPhone()) && Objects.equals(getStudentCellPhone(), studentVO.getStudentCellPhone()) && Objects.equals(getStudentEmail(), studentVO.getStudentEmail()) && Objects.equals(getBirthDate(), studentVO.getBirthDate()) && Objects.equals(getObservation(), studentVO.getObservation()) && getBeltENUM() == studentVO.getBeltENUM() && getDegreeENUM() == studentVO.getDegreeENUM() && Objects.equals(getProjectId(), studentVO.getProjectId()) && Objects.equals(getTeatcherId(), studentVO.getTeatcherId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudentName(), getRg(), getCpf(), getStudentAddress(), getStudentPhone(), getStudentCellPhone(), getStudentEmail(), getBirthDate(), getObservation(), getBeltENUM(), getDegreeENUM(), getProjectId(), getTeatcherId());
    }
}
