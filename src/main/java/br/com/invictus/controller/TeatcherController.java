package br.com.invictus.controller;

import br.com.invictus.data.vo.TeatcherVO;
import br.com.invictus.data.vo.UserVO;
import br.com.invictus.services.TeatcherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endopint de Listagem, Criação, Update, Delete of Teatchers.")
@RestController
@RequestMapping("/api/invictus/teatcher/v1")
public class TeatcherController {

    @Autowired
    TeatcherService teatcherService;

    @Operation(summary = "Get a list of all teatchers.")
    @GetMapping()
    public List<TeatcherVO> findAll(){
        return ResponseEntity.ok(teatcherService.findAll()).getBody();
    }

    @Operation(summary = "Get a teatcher with id.")
    @GetMapping("/{id}")
    public TeatcherVO findById(@PathVariable Long id){
        var vo = teatcherService.findById(id);
        return vo;
    }

    @Operation(summary = "Get teatcher with teatcher name.")
    @GetMapping("/name/{teatcherName}")
    public List<TeatcherVO> findByUserName(@PathVariable String firstNameTeatcher){
        var vo = teatcherService.findByTeatcherName(firstNameTeatcher);
        return vo;
    }

    @Operation(summary = "Tethod for a create one teatcher.")
    @PostMapping
    public ResponseEntity<?> create (@RequestBody TeatcherVO teatcherVO){
        ResponseEntity<?> response = teatcherService.create(teatcherVO);
        return response;
    }


}
