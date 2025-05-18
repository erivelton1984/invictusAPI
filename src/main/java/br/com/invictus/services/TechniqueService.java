package br.com.invictus.services;

import br.com.invictus.data.vo.ProjectVO;
import br.com.invictus.data.vo.TeatcherVO;
import br.com.invictus.data.vo.TechniqueVO;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.ProjectModel;
import br.com.invictus.model.TechniqueModel;
import br.com.invictus.repositories.TechniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TechniqueService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    TechniqueRepository techniqueRepository;

    public List<TechniqueVO> findAll(){
        logger.info("Finding all techniques.");

        var techniques = techniqueRepository.findAll();

        var techniquesVO = DozerMapper.parseListObjects(techniques, TechniqueVO.class);
        return techniquesVO;
    }

    public ResponseEntity<?> create(TechniqueVO techniqueVO) {

        var technique = techniqueRepository.findByProjectName(techniqueVO.getTechniqueName());

        try{
            if (technique != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Técnica cadastrada já existe!");
            }

            var techniqueModel = DozerMapper.parseObject(techniqueVO, TechniqueModel.class);
            var savedTechnique = techniqueRepository.save(techniqueModel);
            TechniqueVO savedTechniqueVO = DozerMapper.parseObject(savedTechnique, TechniqueVO.class);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Técnica criada com sucesso");
        } catch (Exception e) {
            logger.warning("Erro ao criar técnica");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha ao criar o técnica: " + e.getMessage());
        }
    }

    public TechniqueVO update(TechniqueVO vo) {

        Optional<TechniqueModel> existingOptional = techniqueRepository.findById(vo.getId());

        if (!existingOptional.isPresent()) {
            throw new ResourceNotFoundException("Técnica não encontrada com ID: " + vo.getId());
        }

        TechniqueModel existing = existingOptional.get();

        // Atualiza os campos recebidos do frontend
        existing.setTechniqueName(vo.getTechniqueName());
        existing.setTechniqueAddress(vo.getTechniqueAddress());

        TechniqueModel saved = techniqueRepository.save(existing);

        return DozerMapper.parseObject(saved, TechniqueVO.class);
    }

    public void delete(Long id) {

        logger.info("Deleting one user!");

        var entity = techniqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        techniqueRepository.delete(entity);
    }
}
