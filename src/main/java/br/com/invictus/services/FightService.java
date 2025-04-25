package br.com.invictus.services;

import br.com.invictus.data.vo.FighterVO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FightService {

    public List<List<FighterVO>> generateKeys(List<FighterVO> fighters) {

        List<FighterVO> maleFighters = fighters.stream()
                .filter(fighter -> "MASCULINO".equalsIgnoreCase(fighter.getGenderOfFighter()))
                .collect(Collectors.toList());

        List<FighterVO> femaleFighters = fighters.stream()
                .filter(fighter -> "FEMININO".equalsIgnoreCase(fighter.getGenderOfFighter()))
                .collect(Collectors.toList());

        List<List<FighterVO>> maleKeys = gerarChavesPorGenero(maleFighters);
        List<List<FighterVO>> femaleKeys = gerarChavesPorGenero(femaleFighters);

        List<List<FighterVO>> allKeys = new ArrayList<>();
        allKeys.addAll(maleKeys);
        allKeys.addAll(femaleKeys);

        return allKeys;
    }

    private List<List<FighterVO>> gerarChavesPorGenero(List<FighterVO> fighters) {
        List<List<FighterVO>> keys = new ArrayList<>();

        for (int i = 0; i < fighters.size(); i++) {
            for (int j = i + 1; j < fighters.size(); j++) {
                FighterVO fighter1 = fighters.get(i);
                FighterVO fighter2 = fighters.get(j);

                try {
                    double weight1 = Double.parseDouble(fighter1.getWeightFighter());
                    double weight2 = Double.parseDouble(fighter2.getWeightFighter());

                    if (Math.abs(weight1 - weight2) <= 10 &&
                            Math.abs(fighter1.getAgeOfFighter() - fighter2.getAgeOfFighter()) <= 5 &&
                            fighter1.getBeltOfFighter().equalsIgnoreCase(fighter2.getBeltOfFighter())) {
                        List<FighterVO> key = new ArrayList<>();
                        key.add(fighter1);
                        key.add(fighter2);
                        keys.add(key);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter peso de um dos lutadores: " + e.getMessage());
                    // Pode adicionar log ou tratamento adicional se quiser
                }
            }
        }

        return keys;
    }

}
//List<List<FighterVO>> chaves = new ArrayList<>();
// Implementar a lógica para casar os FighterVOes
        /*for (int i = 0; i < FighterVOes.size(); i++) {
            for (int j = i + 1; j < FighterVOes.size(); j++) {
                FighterVO FighterVO1 = FighterVOes.get(i);
                FighterVO FighterVO2 = FighterVOes.get(j);
                if (Math.abs(FighterVO1.getPeso() - FighterVO2.getPeso()) <= 5 &&
                    Math.abs(FighterVO1.getKeyade() - FighterVO2.getKeyade()) <= 5){
                    List<FighterVO> chave = new ArrayList<>();
                    chave.add(FighterVO1);
                    chave.add(FighterVO2);
                    chaves.add(chave);
                }
            }
        }
        return chaves;*/