package br.com.invictus.controller;

import br.com.invictus.data.vo.UrlVO;
import br.com.invictus.services.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Endopint de Listagem, Criação, Update, Delete de URLs.")
@RestController
@RequestMapping("/api/bi/url/v1")
public class UrlController {

    @Autowired
    UrlService urlService;

    @Operation(summary = "Obtém uma lita de todas as URLs.")
    @GetMapping()
    public List<UrlVO> findAll(){
        return ResponseEntity.ok(urlService.findAll()).getBody();
    }

    @Operation(summary = "Obtém uma URL através do ID")
    @GetMapping("/{id}")
    public UrlVO findById(@PathVariable Long id){
        var vo = urlService.findById(id);
        return vo;
    }

    @Operation(summary = "Obtém uma URL através do id do usuário.")
    @GetMapping("/user/{idUsuario}")
    public List<UrlVO> findUrlByIdUser(@PathVariable Long idUsuario){
        List<UrlVO> urlVOList = new ArrayList<>();
        urlVOList = urlService.findUrlByIdUser(idUsuario);
        return urlVOList;
    }

    @Operation(summary = "Obtém uma URL através do nome.")
    @GetMapping("/name/{nomeUrl}")
    public List<UrlVO> findUrlByName(@PathVariable String nomeUrl){
        List<UrlVO> urlVOList = new ArrayList<>();
        urlVOList = urlService.findUrlByName(nomeUrl);
        return urlVOList;
    }

    @Operation(summary = "Cria uma nova URL para o usuário.")
    @PostMapping("/{id}")
    public UrlVO create(@RequestBody UrlVO urlVO, @PathVariable Long id) {
        var vo = urlService.create(urlVO, id);
        return vo;
    }

    @Operation(summary = "Atualiza uma URL para o usuário.")
    @PutMapping
    public UrlVO update (@RequestBody UrlVO urlVO){
        var vo = urlService.update(urlVO);
        return vo;
    }

    @Operation(summary = "Exclui uma url.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        urlService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*@PatchMapping
    public UrlVO disableUrl(@PathVariable(value = "id") Long id) {
        return urlService.disableUrl(id);
    }*/
}
