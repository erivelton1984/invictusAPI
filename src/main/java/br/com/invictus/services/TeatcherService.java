package br.com.invictus.services;

import br.com.invictus.data.vo.TeatcherVO;


import br.com.invictus.data.vo.UserVO;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.TeatcherModel;
import br.com.invictus.model.UserModel;
import br.com.invictus.repositories.TeatcherRepository;
import br.com.invictus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public List<TeatcherVO> findByTeatcherName(String firstNameTeatcher) {
        logger.info("Finding by name: " + firstNameTeatcher);

        var teatchers = teatcherRepository.findByTeatcherName(firstNameTeatcher.trim());

        if (teatchers == null || teatchers.isEmpty()) {
            logger.warning("No teatchers found with name: " + firstNameTeatcher);
            return Collections.emptyList();
        }

        return DozerMapper.parseListObjects(teatchers, TeatcherVO.class);
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

    public TeatcherVO update(TeatcherVO vo) {

        Optional<TeatcherModel> existingOptional = teatcherRepository.findById(vo.getId());

        if (!existingOptional.isPresent()) {
            throw new ResourceNotFoundException("Professor não encontrado com ID: " + vo.getId());
        }

        TeatcherModel existing = existingOptional.get();

        // Atualiza os campos recebidos do frontend
        existing.setEmailTeatcher(vo.getEmailTeatcher());
        existing.setWeightTeatcher(vo.getWeightTeatcher());
        existing.setAddressTeatcher(vo.getAddressTeatcher());
        existing.setPhoneTeatcher(vo.getPhoneTeatcher());
        existing.setPhoneTeatcherTwo(vo.getPhoneTeatcherTwo());
        existing.setGenderTeatcher(vo.getGenderTeatcher());
        existing.setPhotoBase64(vo.getPhotoBase64());
        existing.setBelt(vo.getBelt());
        existing.setDegree(vo.getDegree());
        existing.setProjectId(vo.getProjectId());

        TeatcherModel saved = teatcherRepository.save(existing);

        return DozerMapper.parseObject(saved, TeatcherVO.class);
    }
    @Transactional
    public List<TeatcherVO> findByEmail(String emailTeatcher) {
        logger.info("Finding by name: " + emailTeatcher);

        var teatchers = teatcherRepository.findByEmail(emailTeatcher.trim());

        if (teatchers == null || teatchers.isEmpty()) {
            logger.warning("No teatchers found with name: " + emailTeatcher);
            return Collections.emptyList();
        }

        return DozerMapper.parseListObjects(teatchers, TeatcherVO.class);
    }
}