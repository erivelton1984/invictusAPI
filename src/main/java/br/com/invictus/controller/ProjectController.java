package br.com.invictus.controller;

import br.com.invictus.data.vo.ProjectVO;
import br.com.invictus.repositories.ProjectRepository;
import br.com.invictus.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endopint of List, Create, Update, Delete of Project.")
@RestController
@RequestMapping("/api/invictus/project/v1")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @Operation(summary = "Get a list of all projects.")
    @GetMapping()
    public List<ProjectVO> findAll(){
        return ResponseEntity.ok(projectService.findAll()).getBody();
    }

    @Operation(summary = "Get a project with id.")
    @GetMapping("/{id}")
    public ProjectVO findById(@PathVariable Long id){
        var vo = projectService.findById(id);
        return vo;
    }

    @Operation(summary = "Get project with name.")
    @GetMapping("/name/{projectName}")
    public ProjectVO findByProjectName(@PathVariable String projectName){
        var vo = projectService.findByProjectName(projectName);
        return vo;
    }

    @Operation(summary = "Method for a create one project.")
    @PostMapping
    public ResponseEntity<?> create (@RequestBody ProjectVO projectVO){
        ResponseEntity<?> response = projectService.create(projectVO);
        return response;
    }
}
