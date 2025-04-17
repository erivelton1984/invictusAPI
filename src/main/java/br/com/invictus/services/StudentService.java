package br.com.invictus.services;

import br.com.invictus.data.vo.StudentVO;
import br.com.invictus.data.vo.TeatcherVO;
import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.StudentModel;
import br.com.invictus.model.TeatcherModel;

import br.com.invictus.model.UserModel;
import br.com.invictus.repositories.StudentRepository;
import br.com.invictus.repositories.TeatcherRepository;
import br.com.invictus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    public List<StudentVO> findAll(){
        logger.info("Finding all students.");

        var student = studentRepository.findAll();

        var studentVO = DozerMapper.parseListObjects(student, StudentVO.class);
        return studentVO;
    }

    public StudentVO findById(Long id) {
        logger.info("Finding one student.");

        var student = studentRepository.findById(id);

        if (student.isPresent()) {
            return DozerMapper.parseObject(student.get(), StudentVO.class);
        } else {
            logger.warning("Student not found for ID: " + id);
            throw  new ResourceNotFoundException("Student not found");
        }
    }

    public List<StudentVO> findByStudentName(String student){

        logger.info("Finding by name");
        logger.info("Finding by name: {}" + student);

        var studentName = studentRepository.findByStudentName("%" + student.trim().toLowerCase() + "%");

        if (studentName == null) {
            logger.warning("No user found with name: {}" + studentName);
        }

        var studentVO = DozerMapper.parseObject(studentName, StudentVO.class);

        return Collections.singletonList(studentVO);
    }

    public ResponseEntity<?> create(StudentVO studentVO) {

        var student = studentRepository.findByStudentName(studentVO.getStudentName());

        try{
            // Verifica se o usuário já existe
            if (student == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Aluno já existe!");
            }

            var studentModel = DozerMapper.parseObject(studentVO, StudentModel.class);
            var savedStudent = studentRepository.save(studentModel);
            StudentVO savedStudentVO = DozerMapper.parseObject(savedStudent, StudentVO.class);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Aluno criado com sucesso");
        } catch (Exception e) {
            logger.warning("Erro ao criar aluno");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha ao criar o aluno: " + e.getMessage());
        }
    }

    public StudentVO update(StudentVO vo) {

        Optional<StudentModel> existingOptional = studentRepository.findById(vo.getId());

        if (!existingOptional.isPresent()) {
            throw new ResourceNotFoundException("Aluno não encontrado com ID: " + vo.getId());
        }

        StudentModel existing = existingOptional.get();

        // Atualiza os campos recebidos do frontend, preservando os campos não enviados
        if (vo.getStudentName() != null) existing.setStudentName(vo.getStudentName());
        if (vo.getRg() != null) existing.setRg(vo.getRg());
        if (vo.getCpf() != null) existing.setCpf(vo.getCpf());
        if (vo.getStudentAddress() != null) existing.setStudentAddress(vo.getStudentAddress());
        if (vo.getStudentPhone() != null) existing.setStudentPhone(vo.getStudentPhone());
        if (vo.getStudentCellPhone() != null) existing.setStudentCellPhone(vo.getStudentCellPhone());
        if (vo.getStudentEmail() != null) existing.setStudentEmail(vo.getStudentEmail());
        if (vo.getObservation() != null) existing.setObservation(vo.getObservation());
        if (vo.getBeltENUM() != null) existing.setBeltENUM(vo.getBeltENUM());
        if (vo.getDegreeENUM() != null) existing.setDegreeENUM(vo.getDegreeENUM());
        if (vo.getProjectId() != null) existing.setProjectId(vo.getProjectId());

        StudentModel saved = studentRepository.save(existing);

        return DozerMapper.parseObject(saved, StudentVO.class);
    }

    @Transactional
    public List<StudentVO> findStudentByProjectId(Long projectId) {

        logger.info("Finding students by project id: {}" + projectId);

        var students = studentRepository.findStudentByProjectId(projectId);

        if (students == null || students.isEmpty()) {
            logger.warning("No students found for project id: {}" + projectId);
            return Collections.emptyList();
        }

        return DozerMapper.parseListObjects(students, StudentVO.class);
    }
}
