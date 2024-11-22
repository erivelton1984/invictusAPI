package br.com.invictus.controller;

import br.com.invictus.data.vo.PasswordRecoveryRequestVO;
import br.com.invictus.data.vo.UserVO;
import br.com.invictus.data.vo.security.AccountCredentialsVO;
import br.com.invictus.services.AuthServices;
import br.com.invictus.services.PasswordRecoveryService;
import br.com.invictus.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Endopint de autenticação.")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordRecoveryService passwordRecoveryService;


    @SuppressWarnings("rawtypes")
    @Operation(summary = "Autenticando usuário e retornando token.")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {

        if (checkIfParamsIsNotNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        var accessToken = authServices.signin(data);
        if (accessToken == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        return accessToken;
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return data == null || data.getUserName() == null || data.getUserName().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh do token para usuário autenticado e retornando um token.")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        var accessToken = authServices.refreshToken(username, refreshToken);
        if (accessToken == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        return accessToken;
    }

    @Operation(summary = "Preenchimento de informações para recebimento de e-mail com link para redefinir senha.")
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody PasswordRecoveryRequestVO request) {
        try {
            passwordRecoveryService.sendRecoveryEmail(request.getUserName(), request.getEmail());
            return ResponseEntity.ok("Instruções de recuperação de senha foram enviadas ao seu e-mail.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao solicitar recuperação de senha.");
        }
    }

    @Operation(summary = "Preenchimento dos campos para redefinir a senha o usuário.")
    @PostMapping("/new-password")
    public ResponseEntity<String> newPassword(@RequestBody UserVO userVO) {
        try {
            passwordRecoveryService.newPassword(userVO);
            return ResponseEntity.ok("Sua senha foi redefinida com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao redefinir senha!");
        }
    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }
}