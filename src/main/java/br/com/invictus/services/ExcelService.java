package br.com.invictus.services;

import br.com.invictus.data.vo.FightVO;
import br.com.invictus.data.vo.FighterVO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public ByteArrayInputStream exportLutasToExcel(List<FightVO> fightPairs) throws IOException {
        String[] columns = {"Lutador 1", "Academia 1", "Peso 1", "Faixa 1", "Idade 1", "Gênero 1",
                "Lutador 2", "Academia 2", "Peso 2", "Faixa 2", "Idade 2", "Gênero 2", "Categoria"};

        try (Workbook workbook = new XSSFWorkbook();ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Lutas");

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                headerRow.createCell(i).setCellValue(columns[i]);
            }

            int rowIdx = 1;
            for (FightVO pair : fightPairs) {
                FighterVO fighter1 = pair.getFighter1();
                FighterVO fighter2 = pair.getFighter2();

                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(fighter1 != null ? fighter1.getNameFighter() : "");
                row.createCell(1).setCellValue(fighter1 != null ? fighter1.getAcademyProjectFighter() : "");
                row.createCell(2).setCellValue(fighter1 != null ? fighter1.getWeightFighter() : "");
                row.createCell(3).setCellValue(fighter1 != null ? fighter1.getBeltOfFighter() : "");
                row.createCell(4).setCellValue(fighter1 != null ? fighter1.getAgeOfFighter() : 0);
                row.createCell(5).setCellValue(fighter1 != null ? fighter1.getGenderOfFighter() : "");

                row.createCell(6).setCellValue(fighter2 != null ? fighter2.getNameFighter() : "");
                row.createCell(7).setCellValue(fighter2 != null ? fighter2.getAcademyProjectFighter() : "");
                row.createCell(8).setCellValue(fighter2 != null ? fighter2.getWeightFighter() : "");
                row.createCell(9).setCellValue(fighter2 != null ? fighter2.getBeltOfFighter() : "");
                row.createCell(10).setCellValue(fighter2 != null ? fighter2.getAgeOfFighter() : 0);
                row.createCell(11).setCellValue(fighter2 != null ? fighter2.getGenderOfFighter() : "");

                row.createCell(12).setCellValue(pair.getCategory());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayOutputStream generateFightExcel(List<FightVO> fightPairs) throws IOException {
        ByteArrayInputStream byteArrayInputStream = exportLutasToExcel(fightPairs);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = byteArrayInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        return outputStream;
    }
}