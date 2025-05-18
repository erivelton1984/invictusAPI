package br.com.invictus.data.vo;

import java.util.Objects;

public class FightVO {

    private FighterVO fighter1;
    private FighterVO fighter2;
    private String category;

    public FightVO(FighterVO fighter1, FighterVO fighter2, String category) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.category = category;
    }

    public FighterVO getFighter1() {
        return fighter1;
    }

    public void setFighter1(FighterVO fighter1) {
        this.fighter1 = fighter1;
    }

    public FighterVO getFighter2() {
        return fighter2;
    }

    public void setFighter2(FighterVO fighter2) {
        this.fighter2 = fighter2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FightVO fightVO = (FightVO) o;
        return Objects.equals(fighter1, fightVO.fighter1) && Objects.equals(fighter2, fightVO.fighter2) && Objects.equals(category, fightVO.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fighter1, fighter2, category);
    }
}
