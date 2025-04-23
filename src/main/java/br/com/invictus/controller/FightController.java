package br.com.invictus.controller;

import br.com.invictus.data.vo.FighterVO;
import br.com.invictus.services.ExcelService;
import br.com.invictus.services.FightService;
import br.com.invictus.services.SpreadSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/invictus/fight")
public class FightController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private FightService fightService;

    @Autowired
    private SpreadSheetService spreadSheetService;

    @PostMapping("/upload")
    public List<List<FighterVO>> uploadSpreadSheet(@RequestParam("file") MultipartFile file) throws IOException {
        List<FighterVO> fighters = spreadSheetService.readSpreadSheet(file);
        return fightService.generateKeys(fighters);
    }

    @PostMapping("/generateExcel")
    public ResponseEntity<byte[]> generateFightExcel(@RequestParam("file") MultipartFile file) throws IOException {
        // Ler os lutadores da planilha
        List<FighterVO> fighters = spreadSheetService.readSpreadSheet(file);

        // Gerar as chaves das lutas
        List<List<FighterVO>> fightKeys = fightService.generateKeys(fighters);

        // Gerar o arquivo Excel
        ByteArrayOutputStream excelFile = excelService.generateFightExcel(fightKeys);

        // Definir o cabeçalho para download
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=lutas_casadas.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Retornar a resposta com o arquivo
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelFile.toByteArray());
    }

}