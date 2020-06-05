package com.mycompany.adrec04;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author miguel
 */
public class CasesAndDeathsxml extends DefaultHandler{

    private ArrayList<CasesAndDeaths> casesAndDeaths;
    private CasesAndDeaths casesAndDeathsAux;
    private String dateRep;
    private boolean dateRepEncontrado = false;
    private String cases;
    private boolean casesEncontrado = false;
    private String deaths;
    private boolean deathsEncontrado = false;
    private String geoId;
    private boolean geoIdEncontrado = false;
    
    public CasesAndDeathsxml() {
        super();
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("records")){
            this.casesAndDeaths = new ArrayList<CasesAndDeaths>();
        }else if(qName.equals("record")){
            this.casesAndDeathsAux = new CasesAndDeaths();
            dateRepEncontrado = true;
            casesEncontrado = true;
            deathsEncontrado = true;
            geoIdEncontrado = true;
        }
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException{
        if(qName.equals("geoId")){
            if(geoIdEncontrado){
                this.casesAndDeathsAux.setGeoId(geoId);
                geoIdEncontrado = false;
            }
        }else if(qName.equals("dateRep")){
            if(dateRepEncontrado){
                this.casesAndDeathsAux.setDate(dateRep);
                dateRepEncontrado = false;
            }
        }else if(qName.equals("cases")){
            if(casesEncontrado){
                this.casesAndDeathsAux.setCases(Integer.parseInt(cases));
                casesEncontrado = false;
            }
        }else if(qName.equals("deaths")){
            if(deathsEncontrado){
                this.casesAndDeathsAux.setDeaths(Integer.parseInt(deaths));
                deathsEncontrado = false;
            }
        }else if(qName.equals("geoId")){
           if(geoIdEncontrado){
                this.casesAndDeathsAux.setGeoId(geoId);
                geoIdEncontrado = false;
            }
        }else if(qName.equals("record")){
            this.casesAndDeaths.add(this.casesAndDeathsAux);
        }
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(dateRepEncontrado){
            this.dateRep = new String(ch, start, length);
        }
        if(casesEncontrado){
            this.cases = new String(ch, start, length);
        }
        if(deathsEncontrado){
            this.deaths = new String(ch, start, length);
        }
        if(geoIdEncontrado){
            this.geoId = new String(ch, start, length);
        }
        
    }
        
    public ArrayList<CasesAndDeaths> getCasesAndDeaths(){
        return this.casesAndDeaths;
    }
   
}
