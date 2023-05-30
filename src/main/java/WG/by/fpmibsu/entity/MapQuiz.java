package WG.by.fpmibsu.entity;

import java.util.Objects;

public class MapQuiz extends ModePrototype {
    int mapQuizID;
    Country country;
    String inputTxt;
    String clueFact;
    int clueArea;
    int cluePopulation;
    String clueContinent;

    public MapQuiz(int mapQuizID, Country country, String inputTxt, String clueFact, int clueArea, int cluePopulation, String clueContinent) {
        this.mapQuizID = mapQuizID;
        this.country = country;
        this.inputTxt = inputTxt;

        this.clueFact = clueFact;
        this.clueArea = clueArea;
        this.cluePopulation = cluePopulation;
        this.clueContinent = clueContinent;

        this.nameOfRegime = "MAP QUIZ";
    }

    public boolean complete() {
        if (country.getName().compareTo(inputTxt) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getMapQuizID() {
        return mapQuizID;
    }

    public void setMapQuizID(int mapQuizID) {
        this.mapQuizID = mapQuizID;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getInputTxt() {
        return inputTxt;
    }

    public void setInputTxt(String inputTxt) {
        this.inputTxt = inputTxt;
    }

    public String getClueFact() {
        return clueFact;
    }

    public void setClueFact(String clueFact) {
        this.clueFact = clueFact;
    }

    public int getClueArea() {
        return clueArea;
    }

    public void setClueArea(int clueArea) {
        this.clueArea = clueArea;
    }

    public int getCluePopulation() {
        return cluePopulation;
    }

    public void setCluePopulation(int cluePopulation) {
        this.cluePopulation = cluePopulation;
    }

    public String getClueContinent() {
        return clueContinent;
    }

    public void setClueContinent(String clueContinent) {
        this.clueContinent = clueContinent;
    }

    @Override
    public String toString() {
        return "MapQuiz{" +
                "mapQuizID=" + mapQuizID +
                ", country=" + country +
                ", inputTxt='" + inputTxt + '\'' +
                ", clueFact='" + clueFact + '\'' +
                ", clueArea=" + clueArea +
                ", cluePopulation=" + cluePopulation +
                ", clueContinent='" + clueContinent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapQuiz mapQuiz = (MapQuiz) o;
        return mapQuizID == mapQuiz.mapQuizID && clueArea == mapQuiz.clueArea && cluePopulation == mapQuiz.cluePopulation && Objects.equals(country, mapQuiz.country) && Objects.equals(inputTxt, mapQuiz.inputTxt) && Objects.equals(clueFact, mapQuiz.clueFact) && Objects.equals(clueContinent, mapQuiz.clueContinent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapQuizID, country, inputTxt, clueFact, clueArea, cluePopulation, clueContinent);
    }
}
