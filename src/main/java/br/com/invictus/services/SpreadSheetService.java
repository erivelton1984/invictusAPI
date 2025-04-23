package br.com.invictus.services;

import br.com.invictus.data.vo.FighterVO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SpreadSheetService {

    public List<FighterVO> readSpreadSheet(MultipartFile file) throws IOException {

        List<FighterVO> fighters = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // Ignorar a primeira linha se for o cabeçalho
            if (currentRow.getRowNum() == 0) {
                continue;
            }

            FighterVO fighter = new FighterVO();

            Cell nomeCell = currentRow.getCell(0);
            Cell academiaCell = currentRow.getCell(1);
            Cell pesoCell = currentRow.getCell(2);
            Cell faixaCell = currentRow.getCell(3);
            Cell idadeCell = currentRow.getCell(4);
            Cell generoCell = currentRow.getCell(5);

            fighter.setNameFighter(getCellValueAsString(nomeCell));
            fighter.setAcademyProjectFighter(getCellValueAsString(academiaCell));
            fighter.setWeightFighter(getCellValueAsString(pesoCell)); // Isso lida com números e strings
            fighter.setBeltOfFighter(getCellValueAsString(faixaCell));
            fighter.setAgeOfFighter(getCellValueAsInt(idadeCell)); // Agora trata como inteiro
            fighter.setGenderOfFighter(getCellValueAsString(generoCell));

            fighters.add(fighter);
        }

        workbook.close();
        return fighters;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Formata data, se for o caso
                } else {
                    return String.valueOf(cell.getNumericCellValue()); // Tratar como número
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) return 0;
        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue(); // Retorna como inteiro
            case STRING:
                try {
                    return Integer.parseInt(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0; // Retorna 0 se não for numérico
                }
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1 : 0; // Pode tratar booleano como 1 ou 0, se necessário
            default:
                return 0;
        }
    }
}