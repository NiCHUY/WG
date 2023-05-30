package WG.by.fpmibsu.entity;

import java.awt.*;
import java.util.Objects;

public class Country implements Comparable {
    int countyID;
    String name;
    float area;
    int population;
    String continent;
    String fact;
    String flag;

    String territory;

    public Country() {
    }

    public int getCountyID() {
        return countyID;
    }

    public void setCountyID(int countyID) {
        this.countyID = countyID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public Country(int countyID, String name, float area, int population, String continent, String fact, String flag, String territory) {
        this.countyID = countyID;
        this.name = name;
        this.area = area;
        this.population = population;
        this.continent = continent;
        this.fact = fact;
        this.flag = flag;
        this.territory = territory;
    }

    public int getID() {
        return this.countyID;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Object o) {
        if (this.getID() == ((Country)o).getID()) {
            return 1;
        }
        else return -1;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countyID=" + countyID +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", population=" + population +
                ", continent='" + continent + '\'' +
                ", fact='" + fact + '\'' +
                ", flag='" + flag + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return countyID == country.countyID && Float.compare(country.area, area) == 0 && population == country.population && Objects.equals(name, country.name) && Objects.equals(continent, country.continent) && Objects.equals(fact, country.fact) && Objects.equals(flag, country.flag) && Objects.equals(territory, country.territory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countyID, name, area, population, continent, fact, flag, territory);
    }
}
