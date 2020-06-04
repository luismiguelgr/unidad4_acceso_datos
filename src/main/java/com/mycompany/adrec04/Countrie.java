/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name="countries")
public class Countrie implements Serializable {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name="id", unique= true)
    private int id;
    
     @Column(name="geo_id")
    private String geoId;
    
    @Column(name="country_territory_code")
    private String countryterritoryCode;
    
    @Column(name="countries_and_territories")
    private String countriesAndTerritories;
    
    @Column(name="pop_data_2018")
    private int popData2018;
    
    @Column(name="continent_exp")
    private String continentExp;

    public Countrie() {
    }

    public Countrie(int id, String geoId, String countryterritoryCode, String countriesAndTerritories, int popData2018, String continentExp) {
        this.id = id;
        this.geoId = geoId;
        this.countryterritoryCode = countryterritoryCode;
        this.countriesAndTerritories = countriesAndTerritories;
        this.popData2018 = popData2018;
        this.continentExp = continentExp;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public String getCountryterritoryCode() {
        return countryterritoryCode;
    }

    public void setCountryterritoryCode(String countryterritoryCode) {
        this.countryterritoryCode = countryterritoryCode;
    }

    public String getCountriesAndTerritories() {
        return countriesAndTerritories;
    }

    public void setCountriesAndTerritories(String countriesAndTerritories) {
        this.countriesAndTerritories = countriesAndTerritories;
    }

    public int getPopData2018() {
        return popData2018;
    }

    public void setPopData2018(int popData2018) {
        this.popData2018 = popData2018;
    }

    public String getContinentExp() {
        return continentExp;
    }

    public void setContinentExp(String continentExp) {
        this.continentExp = continentExp;
    }
    
    
}
