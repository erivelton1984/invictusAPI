package br.com.invictus.controller;

import br.com.invictus.data.vo.ProjectVO;
import br.com.invictus.data.vo.TechniqueVO;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.services.TechniqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endopint for listing and creating techniques")
@RestController
@RequestMapping("/api/invictus/technique/v1")
public class TechniqueController {

    @Autowired
    TechniqueService techniqueService;

    @Operation(summary = "Get a list of all techniques.")
    @GetMapping()
    public List<TechniqueVO> findAll(){
        return ResponseEntity.ok(techniqueService.findAll()).getBody();
    }

    @Operation(summary = "Method for a create one technique.")
    @PostMapping
    public ResponseEntity<?> create (@RequestBody TechniqueVO techniqueVO){
        ResponseEntity<?> response = techniqueService.create(techniqueVO);
        return response;
    }

    @Operation(summary = "Update in técnica.")
    @PutMapping
    public ResponseEntity<?> updatTechnique(@RequestBody TechniqueVO techniqueVO) {
        try {
            TechniqueVO updated = techniqueService.update(techniqueVO);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Técnica não encontrada");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar técnica");
        }
    }

    @Operation(summary = "Delete a project.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        techniqueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
