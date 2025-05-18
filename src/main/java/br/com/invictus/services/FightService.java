package br.com.invictus.services;

import br.com.invictus.data.vo.FightVO;
import br.com.invictus.data.vo.FighterVO;

import br.com.invictus.util.CategoryUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FightService {

    private List<FighterVO> fightPairs = new ArrayList<>();
    private List<FightVO> fightPair = new ArrayList<>();

    public List<FighterVO> generateKeys(List<FighterVO> fighters) {
        fightPairs.clear(); // Limpa casamentos anteriores
        fightPair.clear();

        // Separa por gênero
        List<FighterVO> maleFighters = fighters.stream()
                .filter(f -> "MASCULINO".equalsIgnoreCase(f.getGenderOfFighter()))
                .collect(Collectors.toList());

        List<FighterVO> femaleFighters = fighters.stream()
                .filter(f -> "FEMININO".equalsIgnoreCase(f.getGenderOfFighter()))
                .collect(Collectors.toList());

        generateKeysByGender(maleFighters);
        generateKeysByGender(femaleFighters);

        return fightPairs;
    }

    public List<FightVO> getFightPairs() {
        return fightPair;
    }

    private void generateKeysByGender(List<FighterVO> fighters) {
        Map<String, List<FighterVO>> groupedFighters = fighters.stream()
                .collect(Collectors.groupingBy(this::generateGroupKey));

        for (List<FighterVO> group : groupedFighters.values()) {
            for (int i = 0; i < group.size(); i += 2) {
                FighterVO fighter1 = group.get(i);
                FighterVO fighter2 = (i + 1 < group.size()) ? group.get(i + 1) : null;

                String category = CategoryUtils.determineAgeCategory(fighter1.getAgeOfFighter());
                FightVO fight = new FightVO(fighter1, fighter2, category);
                fightPair.add(fight);
            }
        }
    }

    private String generateGroupKey(FighterVO fighter) {
        String ageCategory = CategoryUtils.determineAgeCategory(fighter.getAgeOfFighter());
        String weightCategory = CategoryUtils.determineWeightCategory(
                fighter.getWeightFighter(),
                fighter.getGenderOfFighter(),
                fighter.getAgeOfFighter()
        );
        String belt = fighter.getBeltOfFighter().toUpperCase();

        return ageCategory + "-" + weightCategory + "-" + belt;
    }
}
