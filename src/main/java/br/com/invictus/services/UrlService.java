package br.com.invictus.services;

import br.com.invictus.data.vo.UrlVO;
import br.com.invictus.exceptions.RequiredObjectIsNullException;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.UrlModel;
import br.com.invictus.repositories.UrlRepository;
import br.com.invictus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class UrlService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    UserRepository userRepository;

    public List<UrlVO> findAll(){

        logger.info("Find all users");

        var urls = urlRepository.findAll();
        var urlVO = DozerMapper.parseListObjects(urls,UrlVO.class);

        return urlVO;
    }

    public UrlVO findById(Long id) {

        logger.info("Finding one url.");

        var url = urlRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Url não encontrada com esse ID!"));
        var vo = DozerMapper.parseObject(url, UrlVO.class);

        return vo;
    }

    public List<UrlVO> findUrlByName(String name){

        logger.info("Finding url by name.");
        var urls = urlRepository.findUrlByName(name);

        var urlVO = DozerMapper.parseListObjects(urls, UrlVO.class);

        return urlVO;
    }

    public List<UrlVO> findUrlByIdUser(Long idUsuario){
        logger.info("Finding url with idUsuario");

        List<UrlModel> urlModelList = new ArrayList<>();
        urlModelList = urlRepository.findUrlByIdUser(idUsuario);
        List<UrlVO> urlVoList = DozerMapper.parseListObjects(urlModelList, UrlVO.class);

        return urlVoList;
    }


    public UrlVO create (UrlVO urlVO, Long id ){

        logger.info("Creating a url");

        UrlModel urlModel = new UrlModel();

        if (urlVO == null) throw new RequiredObjectIsNullException();

        var usuarioModel = userRepository.findById(id);

        var entity = DozerMapper.parseObject(urlVO, UrlModel.class);
        entity.setIdUsuario(usuarioModel.get().getId());

        var vo = DozerMapper.parseObject(urlRepository.save(entity), UrlVO.class);

        return vo;
    }

    public UrlVO update(UrlVO urlVO) {

        if (urlVO == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one url!");

        var entity = urlRepository.findById(urlVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setNomeUrl(urlVO.getNomeUrl());
        entity.setUrl(urlVO.getUrl());
        entity.setIdUsuario(urlVO.getIdUsuario());

        var vo =  DozerMapper.parseObject(urlRepository.save(entity), UrlVO.class);

        return vo;
    }

    /*public UrlVO disableUrl(Long id) {

        logger.info("Disabling one url!");

        urlRepository.disableUrl(id);

        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, UrlVO.class);

        return vo;
    }*/

    public void delete(Long id) {

        logger.info("Deleting one url!");

        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        urlRepository.delete(entity);
    }
}