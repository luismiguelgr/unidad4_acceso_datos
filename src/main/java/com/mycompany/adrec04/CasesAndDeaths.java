/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adrec04;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author miguel
 */
@Entity
@Table(name="cases_and_deaths")
public class CasesAndDeaths implements Serializable{
    
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name="id", unique= true)
    private int id;
    
     @Column(name="date")
    private String date;
    
    @Column(name="cases")
    private int cases;
    
    @Column(name="deaths")
    private int deaths;
    
    @Column(name="geo_id")
    private String geoId;
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countrie_id")
    private Countrie countrie;
*/
    public CasesAndDeaths() {
    }

    /*
    public CasesAndDeaths(int id, String date, int cases, int deaths, String geoId) {
        this.id = id;
        this.date = date;
        this.cases = cases;
        this.deaths = deaths;
        this.geoId = geoId;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }
    
    
}
