package br.com.invictus.model;

import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "student")
public class StudentModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "rg")
    private String rg;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "student_address")
    private String studentAddress;

    @Column(name = "student_phone")
    private String studentPhone;

    @Column(name = "student_cellphone")
    private String studentCellPhone;

    @Column(name = "student_email")
    private String studentEmail;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "observation")
    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(name = "belt")
    private BeltENUM beltENUM;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree")
    private DegreeENUM degreeENUM;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "teatcher_id")
    private Long teatcherId;

    public StudentModel() {
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
        if (!(o instanceof StudentModel that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStudentName(), that.getStudentName()) && Objects.equals(getRg(), that.getRg()) && Objects.equals(getCpf(), that.getCpf()) && Objects.equals(getStudentAddress(), that.getStudentAddress()) && Objects.equals(getStudentPhone(), that.getStudentPhone()) && Objects.equals(getStudentCellPhone(), that.getStudentCellPhone()) && Objects.equals(getStudentEmail(), that.getStudentEmail()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getObservation(), that.getObservation()) && getBeltENUM() == that.getBeltENUM() && getDegreeENUM() == that.getDegreeENUM() && Objects.equals(getProjectId(), that.getProjectId()) && Objects.equals(getTeatcherId(), that.getTeatcherId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudentName(), getRg(), getCpf(), getStudentAddress(), getStudentPhone(), getStudentCellPhone(), getStudentEmail(), getBirthDate(), getObservation(), getBeltENUM(), getDegreeENUM(), getProjectId(), getTeatcherId());
    }
}