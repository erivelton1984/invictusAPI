package br.com.invictus.controller;

import br.com.invictus.data.vo.TeatcherVO;
import br.com.invictus.enums.BeltENUM;
import br.com.invictus.enums.DegreeENUM;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.services.TeatcherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
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

    @Operation(summary = "Method to create one teatcher with photo.")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(
            @RequestParam("firstNameTeatcher") String firstNameTeatcher,
            @RequestParam("lastNameTeatcher") String lastNameTeatcher,
            @RequestParam("birthDateTeatcher") String birthDateTeatcher,
            @RequestParam("weightTeatcher") Double weightTeatcher,
            @RequestParam("addressTeatcher") String addressTeatcher,
            @RequestParam("genderTeatcher") String genderTeatcher,
            @RequestParam("emailTeatcher") String emailTeatcher,
            @RequestParam("phoneTeatcher") String phoneTeatcher,
            @RequestParam("phoneTeatcherTwo") String phoneTeatcherTwo,
            @RequestParam("enabled") Boolean enabled,
            @RequestParam("belt") String belt,
            @RequestParam("degree") String degree,
            @RequestParam("projectId") Long projectId,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        try {
            TeatcherVO teatcherVO = new TeatcherVO();
            teatcherVO.setFirstNameTeatcher(firstNameTeatcher);
            teatcherVO.setLastNameTeatcher(lastNameTeatcher);
            teatcherVO.setBirthDateTeatcher(LocalDate.parse(birthDateTeatcher));
            teatcherVO.setWeightTeatcher(weightTeatcher);
            teatcherVO.setAddressTeatcher(addressTeatcher);
            teatcherVO.setGenderTeatcher(genderTeatcher);
            teatcherVO.setEmailTeatcher(emailTeatcher);
            teatcherVO.setPhoneTeatcher(phoneTeatcher);
            teatcherVO.setPhoneTeatcherTwo(phoneTeatcherTwo);
            teatcherVO.setEnabled(enabled);

            teatcherVO.setBelt(BeltENUM.fromDescription(belt));
            teatcherVO.setDegree(DegreeENUM.fromDescription(degree));

            teatcherVO.setProjectId(projectId);



            if (photo != null && !photo.isEmpty()) {
                String base64Image = Base64.getEncoder().encodeToString(photo.getBytes());
                teatcherVO.setPhotoBase64(base64Image);
            }

            return teatcherService.create(teatcherVO);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem.");
        }
    }

    @Operation(summary = "Update em professor.")
    @PutMapping
    public ResponseEntity<?> updateTeatcher(@RequestBody TeatcherVO teatcherVO) {
        try {
            TeatcherVO updated = teatcherService.update(teatcherVO);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar professor");
        }
    }


}
