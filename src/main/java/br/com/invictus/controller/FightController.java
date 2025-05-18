package br.com.invictus.controller;

import br.com.invictus.data.vo.FightVO;
import br.com.invictus.data.vo.FighterVO;
import br.com.invictus.services.ExcelService;
import br.com.invictus.services.FightService;
import br.com.invictus.services.SpreadSheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Tag(name = "Endpoint for uploading spreadsheet and creating fighter keys")
@RestController
@RequestMapping("/api/invictus/fight/v1")
public class FightController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private FightService fightService;

    @Autowired
    private SpreadSheetService spreadSheetService;


    @Operation(summary = "Read spreadsheet uploaded and generate fight keys")
    @PostMapping("/upload")
    public ResponseEntity<List<FighterVO>> uploadSpreadSheet(@RequestParam("file") MultipartFile file) throws IOException {
        List<FighterVO> fighters = spreadSheetService.readSpreadSheet(file);

        return ResponseEntity.ok(fightService.generateKeys(fighters));
    }

    @Operation(summary = "Generate an Excel file with the fights keys already created")
    @PostMapping("/generateExcel")
    public ResponseEntity<byte[]> generateFightExcel() throws IOException {
        List<FightVO> fightPairs = fightService.getFightPairs();

        ByteArrayOutputStream excelFile = excelService.generateFightExcel(fightPairs);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=lutas_casadas.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelFile.toByteArray());
    }

    @Operation(summary = "Get the generated fight keys")
    @GetMapping("/fightKeys")
    public ResponseEntity<List<FightVO>> getFightKeys() {
        return ResponseEntity.ok(fightService.getFightPairs());
    }
}
