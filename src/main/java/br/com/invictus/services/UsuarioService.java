package br.com.invictus.services;

import br.com.invictus.data.vo.UserVO;
import br.com.invictus.data.vo.security.AccountCredentialsVO;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.PermissionModel;
import br.com.invictus.model.UserModel;
import br.com.invictus.repositories.PermissionRepository;
import br.com.invictus.repositories.UrlRepository;
import br.com.invictus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.*;


import java.util.logging.Logger;

@Service
public class UsuarioService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    UserRepository usuarioRepository;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    PermissionRepository permissionRepository;

    public List<UserVO> findAll(){

        logger.info("Find all users");

        var usuarios = usuarioRepository.findAll();
        var usuariosVO = DozerMapper.parseListObjects(usuarios,UserVO.class);

        return usuariosVO;
    }

    public UserVO findById(Long id) {
        logger.info("Finding one user.");

        var url = usuarioRepository.findById(id);

        if (url.isPresent()) {
            return DozerMapper.parseObject(url.get(), UserVO.class);
        } else {
            logger.warning("User not found for ID: " + id);
            throw  new ResourceNotFoundException("URL not found");
        }
    }

    public UserVO findUsuarioByName(String usuario){

        logger.info("Finding by name");
        logger.info("Finding by name: {}" + usuario);

        var user = usuarioRepository.findByUserName("%" + usuario.trim().toLowerCase() + "%");

        if (user == null) {
            logger.warning("No user found with name: {}" + usuario);
        }

        var usuarioVO = DozerMapper.parseObject(user, UserVO.class);

        return usuarioVO;
    }

    public ResponseEntity<?>  create (UserVO userVO) {

        logger.info("Creating a user");

        AccountCredentialsVO accountCredentialsVO = new AccountCredentialsVO(userVO.getUserName(), userVO.getPassword());

        try {
            accountCredentialsVO.setUsername(userVO.getUserName());
            accountCredentialsVO.setPassword(userVO.getPassword());

            String username = accountCredentialsVO.getUserName();
            String password = accountCredentialsVO.getPassword();

            // Verifica se o usuário já existe
            if (usuarioRepository.findByUserName(userVO.getUserName()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Usuário já existe!");
            }

            // Validação de permissões
            if (userVO.getPermissions() == null || userVO.getPermissions().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Permissões não podem ser nulas ou vazias!");
            }

            // Verifica se a permissão é válida
            List<PermissionModel> permissions = new ArrayList<>();
            for (PermissionModel permissionModel : userVO.getPermissions()) {
                Optional<PermissionModel> permission = permissionRepository.findById(permissionModel.getId());
                if (permission.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Permissão inválida com ID: " + permissionModel.getId());
                }
                permissions.add(permission.get());
            }

           Map<String, PasswordEncoder> encoders = new HashMap<>();

            Pbkdf2PasswordEncoder pbkdf2Encoder =
                    new Pbkdf2PasswordEncoder(
                            "", 8, 185000,
                            Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

            encoders.put("pbkdf2", pbkdf2Encoder);
            DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
            passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

            String passwordEncrypted = passwordEncoder.encode(password);

            if (passwordEncrypted.startsWith("{pbkdf2}")) {
                passwordEncrypted = passwordEncrypted.substring("{pbkdf2}".length());
            }

            // Cria o objeto UserModel com os dados do novo usuário
            UserModel newUser = new UserModel();
            newUser.setUserName(userVO.getUserName());
            newUser.setFullName(userVO.getFullName());
            newUser.setPassword(passwordEncrypted);
            newUser.setEmail(userVO.getEmail());
            newUser.setPermissions(permissions);
            newUser.setAccountNonExpired(true);
            newUser.setAccountNonLocked(true);
            newUser.setCredentialsNonExpired(true);
            newUser.setEnabled(true);

            // Salva o usuário no banco e converte para VO
            UserModel savedUser = usuarioRepository.save(newUser);
            UserVO savedUserVO = DozerMapper.parseObject(savedUser, UserVO.class);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuário criado com sucesso");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            logger.warning("Erro ao criar usuário");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha ao criar o usuário: " + e.getMessage());
        }
    }

    public UserVO update (UserVO userVO){

        logger.info("Update in user");

        var entity = usuarioRepository.findById(userVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFullName(userVO.getFullName());
        entity.setUserName(userVO.getUserName());
        entity.setEmail(userVO.getEmail());

        // Mantém a senha atual se o password no UserVO for nulo ou em branco
        if (userVO.getPassword() == null || userVO.getPassword().isBlank()) {
            entity.setPassword(entity.getPassword());
        }

        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setEnabled(true);

        var vo =  DozerMapper.parseObject(usuarioRepository.save(entity), UserVO.class);
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one user!");

        var entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var urls = urlRepository.findUrlByIdUser(id);

        // Verifique se existem URLs associadas
        if (urls == null || urls.isEmpty()) {
            logger.warning("No URLs found for user ID: " + id);
        } else {
            // Deletar todas as URLs associadas
            urlRepository.deleteAll(urls);
        }

        usuarioRepository.delete(entity);
    }
}