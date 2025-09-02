package br.com.invictus.controller;

import br.com.invictus.data.vo.StudentVO;
import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.repositories.StudentRepository;
import br.com.invictus.repositories.TeatcherRepository;
import br.com.invictus.services.StudentService;
import br.com.invictus.services.TeatcherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Tag(name = "Endpoint for listing, creating, updating and deleting of students.")
@RestController
@RequestMapping("/api/invictus/student/v1")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeatcherRepository teatcherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeatcherService teatcherService;

    @Operation(summary = "Get a list of all student.")
    @GetMapping()
    public List<StudentVO> findAll(){
        return ResponseEntity.ok(studentService.findAll()).getBody();
    }

    @Operation(summary = "Get a student with id.")
    @GetMapping("/{id}")
    public StudentVO findById(@PathVariable Long id){
        var vo = studentService.findById(id);
        return vo;
    }

    @Operation(summary = "Get student with student name.")
    @GetMapping("/name/{studentName}")
    public List<StudentVO> findByUserName(@PathVariable String studentName){
        var vo = studentService.findByStudentName(studentName);
        return vo;
    }

    @Operation(summary = "Update em student.")
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody StudentVO studentVO) {
        try {
            StudentVO updated = studentService.update(studentVO);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar aluno");
        }
    }

    @GetMapping("/project/{projectId}")
    public List<StudentVO> findStudentByProject(@PathVariable Long projectId) {
        return studentService.findStudentByProjectId(projectId);
    }

    @Operation(summary = "Delete a student.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Method to create one student with photo.")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(
            @RequestParam("studentName") String studentName,
            @RequestParam("rg") String rg,
            @RequestParam("cpf") String cpf,
            @RequestParam("studentAddress") String studentAddress,
            @RequestParam("studentPhone") String studentPhone,
            @RequestParam("studentCellPhone") String studentCellPhone,
            @RequestParam("studentEmail") String studentEmail,
            @RequestParam("genderStudent") String genderStudent,
            @RequestParam("birthDate") String birthDate,
            @RequestParam("studentAge") Integer studentAge,
            @RequestParam("studentWeight") String studentWeight,
            @RequestParam("enabled") Boolean enabled,
            @RequestParam("observation") String observation,
            @RequestParam("beltENUM") String beltENUM,
            @RequestParam("degreeENUM") String degreeENUM,
            @RequestParam("projectId") Long projectId,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        try {
            StudentVO studentVO = new StudentVO();
            studentVO.setStudentName(studentName);
            studentVO.setRg(rg);
            studentVO.setCpf(cpf);
            studentVO.setStudentAddress(studentAddress);
            studentVO.setStudentPhone(studentPhone);
            studentVO.setStudentCellPhone(studentCellPhone);
            studentVO.setStudentEmail(studentEmail);
            studentVO.setGenderStudent(genderStudent);
            studentVO.setStudentWeight(studentWeight);
            studentVO.setBirthDate(LocalDate.parse(birthDate));
            studentVO.setStudentAge(studentAge);
            studentVO.setObservation(observation);
            studentVO.setBeltENUM(BeltENUM.fromDescription(beltENUM));
            studentVO.setDegreeENUM(DegreeENUM.fromValue(degreeENUM));
            studentVO.setProjectId(projectId);

            if (photo != null && !photo.isEmpty()) {
                String base64Image = Base64.getEncoder().encodeToString(photo.getBytes());
                studentVO.setPhotoBase64(base64Image);
            }

            return studentService.create(studentVO);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem.");
        }
    }
}
