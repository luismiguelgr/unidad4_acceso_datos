
package com.mycompany.adrec04;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author miguel
 */
@Entity
@Table(name="record")
public class Records implements Serializable{
    
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name="id", unique= true)
    private int id;
    
    @Column(name="countries_and_territories")
    private String countriesAndTerritories;
    
    @Column(name="continent_exp")
    private String continentExp;
    
    @Column(name="cases")
    private int cases;
    
    @Column(name="deaths")
    private int deaths;
    
    @Column(name="day")
    private int day;

    public Records() {
    }
    
    public Records(String countriesAndTerritories, String continentExp, int cases, int deaths, int day) {
        this.countriesAndTerritories = countriesAndTerritories;
        this.continentExp = continentExp;
        this.cases = cases;
        this.deaths = deaths;
        this.day = day;
    }

    public String getCountriesAndTerritories() {
        return countriesAndTerritories;
    }

    public void setCountriesAndTerritories(String countriesAndTerritories) {
        this.countriesAndTerritories = countriesAndTerritories;
    }

    public String getContinentExp() {
        return continentExp;
    }

    public void setContinentExp(String continentExp) {
        this.continentExp = continentExp;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
