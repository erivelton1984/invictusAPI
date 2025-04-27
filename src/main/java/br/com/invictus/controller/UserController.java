package br.com.invictus.controller;

import br.com.invictus.data.vo.UserVO;
import br.com.invictus.data.vo.security.AccountCredentialsVO;
import br.com.invictus.model.PermissionModel;
import br.com.invictus.repositories.UserRepository;
import br.com.invictus.services.PermissionService;
import br.com.invictus.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endopint for listing, creating, updating and deleting of users..")
@RestController
@RequestMapping("/api/invictus/user/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private PermissionService permissionService;

    @Operation(summary = "Get a list of all users.")
    @GetMapping()
    public List<UserVO> findAll(){
        return ResponseEntity.ok(usuarioService.findAll()).getBody();
    }

    @Operation(summary = "Get users with ID.")
    @GetMapping("/{id}")
    public UserVO findById(@PathVariable Long id){
        var vo = usuarioService.findById(id);
        return vo;
    }

    @Operation(summary = "Get user with name.")
    @GetMapping("/name/{userName}")
    public UserVO findByUserName(@PathVariable String userName){
        var vo = usuarioService.findUsuarioByName(userName);
        return vo;
    }

    @Operation(summary = "Create a new user.")
    @PostMapping(value = "/signup")
    public ResponseEntity<?> create(@RequestBody UserVO userVO) {

        if (checkIfParamsIsNotNull(userVO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Dados inválidos!");
        }

        ResponseEntity<?> response = usuarioService.create(userVO);

        return response;
    }

    @Operation(summary = "Update a user.")
    @PutMapping
    public UserVO update(@RequestBody UserVO userVO) {
        return usuarioService.update(userVO);
    }

    @Operation(summary = "Delete a user.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cheking if the parameters id nul or not.")
    private boolean checkIfParamsIsNotNull(UserVO userVO) {
        AccountCredentialsVO accountCredentialsVO = new AccountCredentialsVO(userVO.getUserName(), userVO.getPassword());
         return accountCredentialsVO == null || accountCredentialsVO.getUserName() == null || accountCredentialsVO.getUserName().isBlank()
                || accountCredentialsVO.getPassword() == null || accountCredentialsVO.getPassword().isBlank();
    }

    @Operation(summary = "Get levels.")
    @GetMapping("/levels")
    public ResponseEntity<List<PermissionModel>> getUserLevels() {
        List<PermissionModel> levels = permissionService.getUserLevels();
        return ResponseEntity.ok(levels);
    }
}
