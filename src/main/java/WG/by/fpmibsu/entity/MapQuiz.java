package WG.by.fpmibsu.entity;

import java.util.Objects;

public class MapQuiz extends ModePrototype {
    int mapQuizID;
    Country country;

    public MapQuiz(int mapQuizID, Country country) {
        this.mapQuizID = mapQuizID;
        this.country = country;
        this.nameOfRegime = "MAP QUIZ";
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

    @Override
    public String toString() {
        return "MapQuiz{" +
                "mapQuizID=" + mapQuizID +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapQuiz mapQuiz = (MapQuiz) o;
        return mapQuizID == mapQuiz.mapQuizID  && Objects.equals(country, mapQuiz.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapQuizID, country);
    }

    @Override
    public boolean complete() {
        return false;
    }
}
