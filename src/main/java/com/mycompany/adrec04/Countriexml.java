package com.mycompany.adrec04;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author miguel
 */
public class Countriexml extends DefaultHandler{

    private ArrayList<Countrie> countries;
    private Countrie countrieAux;
    private String continentExp;
    private boolean continentExpEncontrado = false;
    private String countriesAndTerritories;
    private boolean countriesAndTerritoriesEncontrado = false;
    private String geoId;
    private boolean geoIdEncontrado = false;
    private String countryterritoryCode;
    private boolean countryterritoryCodeEncontrado = false;
    private String popData2018;
    private boolean popData2018Encontrado = false;
    
    public Countriexml() {
        super();
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("records")){
            this.countries = new ArrayList<Countrie>();
        }else if(qName.equals("record")){
            this.countrieAux = new Countrie();

            continentExpEncontrado = true;
            countriesAndTerritoriesEncontrado = true;
            geoIdEncontrado = true;
            countryterritoryCodeEncontrado = true;
            popData2018Encontrado = true;
        }
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException{
        if(qName.equals("geoId")){
            if(geoIdEncontrado){
                this.countrieAux.setGeoId(geoId);
                geoIdEncontrado = false;
            }
        }else if(qName.equals("continentExp")){
            if(continentExpEncontrado){
                this.countrieAux.setContinentExp(continentExp);
                continentExpEncontrado = false;
            }
        }else if(qName.equals("countriesAndTerritories")){
            if(countriesAndTerritoriesEncontrado){
                this.countrieAux.setCountriesAndTerritories(countriesAndTerritories);
                countriesAndTerritoriesEncontrado = false;
            }
        }else if(qName.equals("countryterritoryCode")){
            if(countryterritoryCodeEncontrado){
                this.countrieAux.setCountryterritoryCode(countryterritoryCode);
                countryterritoryCodeEncontrado = false;
            }
        }else if(qName.equals("popData2018")){
           if(popData2018Encontrado){
                this.countrieAux.setPopData2018(popData2018);
                popData2018Encontrado = false;
            }
        }else if(qName.equals("record")){
            this.countries.add(this.countrieAux);
        }
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {
       
        if(continentExpEncontrado){
            this.continentExp = new String(ch, start, length);
        }
        if(countriesAndTerritoriesEncontrado){
            this.countriesAndTerritories = new String(ch, start, length);
        }
        if(geoIdEncontrado){
            this.geoId = new String(ch, start, length);
        }
        if(countryterritoryCodeEncontrado){
            this.countryterritoryCode = new String(ch, start, length);
        }
        if(popData2018Encontrado){
            this.popData2018 = new String(ch, start, length);
        }
    }
        
    public ArrayList<Countrie> getCountries(){
        return this.countries;
    }
   
}
