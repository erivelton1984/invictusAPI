package br.com.invictus.data.vo;


import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

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
    private String genderStudent;
    private LocalDate birthDate;
    private Integer studentAge;
    private String studentWeight;
    private String observation;
    private BeltENUM beltENUM;
    private DegreeENUM degreeENUM;
    private Long projectId;
    private Long teatcherId;
    private String photoBase64;

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

    public String getGenderStudent() {
        return genderStudent;
    }

    public void setGenderStudent(String genderStudent) {
        this.genderStudent = genderStudent;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentWeight() {
        return studentWeight;
    }

    public void setStudentWeight(String studentWeight) {
        this.studentWeight = studentWeight;
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

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentVO studentVO = (StudentVO) o;
        return Objects.equals(id, studentVO.id) && Objects.equals(studentName, studentVO.studentName) && Objects.equals(rg, studentVO.rg) && Objects.equals(cpf, studentVO.cpf) && Objects.equals(studentAddress, studentVO.studentAddress) && Objects.equals(studentPhone, studentVO.studentPhone) && Objects.equals(studentCellPhone, studentVO.studentCellPhone) && Objects.equals(studentEmail, studentVO.studentEmail) && Objects.equals(genderStudent, studentVO.genderStudent) && Objects.equals(birthDate, studentVO.birthDate) && Objects.equals(studentAge, studentVO.studentAge) && Objects.equals(studentWeight, studentVO.studentWeight) && Objects.equals(observation, studentVO.observation) && beltENUM == studentVO.beltENUM && degreeENUM == studentVO.degreeENUM && Objects.equals(projectId, studentVO.projectId) && Objects.equals(teatcherId, studentVO.teatcherId) && Objects.equals(photoBase64, studentVO.photoBase64);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, rg, cpf, studentAddress, studentPhone, studentCellPhone, studentEmail, genderStudent, birthDate, studentAge, studentWeight, observation, beltENUM, degreeENUM, projectId, teatcherId, photoBase64);
    }
}