package br.com.invictus.services;

import br.com.invictus.data.vo.ProjectVO;
import br.com.invictus.data.vo.TeatcherVO;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.ProjectModel;
import br.com.invictus.model.TeatcherModel;
import br.com.invictus.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProjectService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    ProjectRepository projectRepository;


    public List<ProjectVO> findAll(){
        logger.info("Finding all projects.");

        var project = projectRepository.findAll();

        var projectVO = DozerMapper.parseListObjects(project, ProjectVO.class);
        return projectVO;
    }

    public ProjectVO findById(Long id) {
        logger.info("Finding one project.");

        var project = projectRepository.findById(id);

        if (project.isPresent()) {
            return DozerMapper.parseObject(project.get(), ProjectVO.class);
        } else {
            logger.warning("Project not found for ID: " + id);
            throw  new ResourceNotFoundException("Project not found");
        }
    }

    public ProjectVO findByProjectName(String project){

        logger.info("Finding by name");
        logger.info("Finding by name: " + project);

        var projectName = projectRepository.findByProjectName("%" + project.trim().toLowerCase() + "%");

        if (projectName == null) {
            logger.warning("No user found with name: " + projectName);
        }

        var projectVO = DozerMapper.parseObject(projectName, ProjectVO.class);

        return projectVO;
    }

    public ResponseEntity<?> create(ProjectVO projectVO) {

        var project = projectRepository.findByProjectName(projectVO.getProjectName());

        try{
            if (project != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Projeto já existe!");
            }

            var projectModel = DozerMapper.parseObject(projectVO, ProjectModel.class);
            var savedProject = projectRepository.save(projectModel);
            ProjectVO savedProjectVO = DozerMapper.parseObject(savedProject, ProjectVO.class);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Projeto criado com sucesso");
        } catch (Exception e) {
            logger.warning("Erro ao criar projeto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha ao criar o projeto: " + e.getMessage());
        }

    }

    public ProjectVO update(ProjectVO vo) {

       Optional<ProjectModel> existingOptional = projectRepository.findById(vo.getId());

        if (!existingOptional.isPresent()) {
            throw new ResourceNotFoundException("Projeto não encontrado com ID: " + vo.getId());
        }

        ProjectModel existing = existingOptional.get();

        // Atualiza os campos recebidos do frontend
        existing.setProjectName(vo.getProjectName());
        existing.setProjectAddress(vo.getProjectAddress());
        existing.setEmailProject(vo.getEmailProject());
        existing.setProjectPhone(vo.getProjectPhone());
        existing.setProjectPhoneTwo(vo.getProjectPhoneTwo());
        existing.setEmailProject(vo.getEmailProject());
        existing.setClassSchedule(vo.getClassSchedule());


        ProjectModel saved = projectRepository.save(existing);

        return DozerMapper.parseObject(saved, ProjectVO.class);
    }
}
