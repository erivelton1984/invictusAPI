package br.com.invictus.services;

import br.com.invictus.data.vo.StudentVO;
import br.com.invictus.data.vo.TeatcherVO;
import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.StudentModel;
import br.com.invictus.model.TeatcherModel;

import br.com.invictus.repositories.StudentRepository;
import br.com.invictus.repositories.TeatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        // Atualiza os campos recebidos do frontend
        existing.setStudentName(vo.getStudentName());
        existing.setRg(vo.getRg());
        existing.setCpf(vo.getCpf());
        existing.setStudentAddress(vo.getStudentAddress());
        existing.setStudentPhone(vo.getStudentPhone());
        existing.setStudentCellPhone(vo.getStudentCellPhone());
        existing.setStudentEmail(vo.getStudentEmail());
        existing.setObservation(vo.getObservation());
        existing.setBeltENUM(vo.getBeltENUM());
        existing.setDegreeENUM(vo.getDegreeENUM());
        existing.setProjectId(vo.getProjectId());

        StudentModel saved = studentRepository.save(existing);

        return DozerMapper.parseObject(saved, StudentVO.class);
    }
}
