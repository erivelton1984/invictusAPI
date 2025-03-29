package br.com.invictus.services;

import br.com.invictus.data.vo.TeatcherVO;
import br.com.invictus.data.vo.UserVO;
import br.com.invictus.enums.DegreeENUM;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.TeatcherModel;
import br.com.invictus.repositories.TeatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TeatcherService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    TeatcherRepository teatcherRepository;

    public List<TeatcherVO> findAll(){
        logger.info("Finding all teatchers.");

        var teatcher = teatcherRepository.findAll();

        var teathcersVO = DozerMapper.parseListObjects(teatcher, TeatcherVO.class);
        return teathcersVO;
    }

    public TeatcherVO findById(Long id) {
        logger.info("Finding one teatcher.");

        var teatcher = teatcherRepository.findById(id);

        if (teatcher.isPresent()) {
            return DozerMapper.parseObject(teatcher.get(), TeatcherVO.class);
        } else {
            logger.warning("Teatcher not found for ID: " + id);
            throw  new ResourceNotFoundException("Teatcher not found");
        }
    }

    public List<TeatcherVO> findByTeatcherName(String teatcher){

        logger.info("Finding by name");
        logger.info("Finding by name: {}" + teatcher);

        var teatcherName = teatcherRepository.findByTeatcherName("%" + teatcher.trim().toLowerCase() + "%");

        if (teatcherName == null) {
            logger.warning("No user found with name: {}" + teatcherName);
        }

        var teatcherVO = DozerMapper.parseObject(teatcherName, TeatcherVO.class);

        return Collections.singletonList(teatcherVO);
    }

    public ResponseEntity<?> create(TeatcherVO teatcherVO) {
        var existingTeatchers = teatcherRepository.findByTeatcherName(teatcherVO.getFirstNameTeatcher());

        // Verifica se já existe um professor com o mesmo nome
        if (!existingTeatchers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Professor já existe!");
        }

        try {
            var teatcherModel = DozerMapper.parseObject(teatcherVO, TeatcherModel.class);
            var savedTeatcher = teatcherRepository.save(teatcherModel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Professor criado com sucesso");
        } catch (Exception e) {
            logger.warning("Erro ao criar professor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar o professor");
        }
    }
}
