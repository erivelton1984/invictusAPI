package br.com.invictus.data.vo;

import java.util.Objects;

public class FighterVO {

    private String nameFighter;
    private String academyProjectFighter;
    private String weightFighter;
    private String beltOfFighter;
    private int ageOfFighter;
    private String genderOfFighter;

    public FighterVO() {
    }

    public String getNameFighter() {
        return nameFighter;
    }

    public void setNameFighter(String nameFighter) {
        this.nameFighter = nameFighter;
    }

    public String getAcademyProjectFighter() {
        return academyProjectFighter;
    }

    public void setAcademyProjectFighter(String academyProjectFighter) {
        this.academyProjectFighter = academyProjectFighter;
    }

    public String getWeightFighter() {
        return weightFighter;
    }

    public void setWeightFighter(String weightFighter) {
        this.weightFighter = weightFighter;
    }

    public String getBeltOfFighter() {
        return beltOfFighter;
    }

    public void setBeltOfFighter(String beltOfFighter) {
        this.beltOfFighter = beltOfFighter;
    }

    public int getAgeOfFighter() {
        return ageOfFighter;
    }

    public void setAgeOfFighter(int ageOfFighter) {
        this.ageOfFighter = ageOfFighter;
    }

    public String getGenderOfFighter() {
        return genderOfFighter;
    }

    public void setGenderOfFighter(String genderOfFighter) {
        this.genderOfFighter = genderOfFighter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FighterVO fighterVO = (FighterVO) o;
        return ageOfFighter == fighterVO.ageOfFighter && Objects.equals(nameFighter, fighterVO.nameFighter) && Objects.equals(academyProjectFighter, fighterVO.academyProjectFighter) && Objects.equals(weightFighter, fighterVO.weightFighter) && Objects.equals(beltOfFighter, fighterVO.beltOfFighter) && Objects.equals(genderOfFighter, fighterVO.genderOfFighter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameFighter, academyProjectFighter, weightFighter, beltOfFighter, ageOfFighter, genderOfFighter);
    }
}
