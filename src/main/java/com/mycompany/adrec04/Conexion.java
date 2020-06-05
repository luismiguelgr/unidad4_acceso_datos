package com.mycompany.adrec04;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author miguel
 */
public class Conexion {
    static Connection conexion = null;
    private String nombre;
    
    public Conexion(String nombre) {
        String directorioHome = System.getProperty("user.home");
         String baseDatos = "jdbc:sqlite:"+directorioHome+"/" + nombre;
               
        try{
            conexion = DriverManager.getConnection(baseDatos);
            if(conexion != null){
                crearTabla();
                System.out.println("La base de datos fue creada");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
          
    public static void desconetarBaseDatos(){
      try{
          if(conexion != null){
              conexion.close();
              System.out.println("Desconectado de la base de datos");
          }
      }
      catch(SQLException e){
          System.out.println(e.getMessage());
      }
    }
      
    private static void crearTabla(){
       try{
          String sqlCountries = "CREATE TABLE IF NOT EXISTS countries (\n" +
                            "geo_id integer PRIMARY KEY,\n"+
                            "country_territory_code text ,\n"+
                            "name text ,\n"+
                            "pop_data_2018 integer ,\n"+
                            "continent_exp text);";
          
          String sqlDeaths = "CREATE TABLE IF NOT EXISTS cases_and_deaths (\n" +
                            "date date PRIMARY KEY,\n"+
                            "cases integer ,\n"+
                            "deaths integer ,\n"+
                            "geo_id integer);";

          Statement stmt = conexion.createStatement();
          stmt.execute(sqlCountries);
          stmt.execute(sqlDeaths);
          System.out.println("Tabla creada.");
      }
      catch(SQLException e){
          System.out.println(e.getMessage());
      }
        
    }
    
     public static boolean existeCampo(String tabla,  String columna, String campo){
        boolean contiene = false;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM "+ tabla +" WHERE " + columna + " LIKE ?;";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, campo);
            rs = pstmt.executeQuery();     
            if(rs.next() == false){
                contiene = false;
            }else{
                contiene = true;
            }            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return contiene;
    
    }
    
    public static void insertarValores1(String dateRep, int cases, int deaths, String countriesAndTerritories,
                                        String geoId, String countryterritoryCode, String popData2018, String  continentExp){
        String tablaCountries = "countries";
        String columnaCountries = "geo_id";
        try{
            
            if(!existeCampo(tablaCountries, columnaCountries, geoId)){                            
                String sqlCountries = "INSERT INTO countries (geo_id, country_territory_code, countries_and_territories, pop_data_2018, continent_exp) "
                                + "VALUES(?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conexion.prepareStatement(sqlCountries);
                pstmt.setString(1, geoId);
                pstmt.setString(2, countriesAndTerritories);
                pstmt.setString(3, countriesAndTerritories);
                pstmt.setString(4, popData2018);
                pstmt.setString(5, continentExp);
                pstmt.executeUpdate();
            }
                        
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void insertarValores2(String dateRep, int cases, int deaths, String countriesAndTerritories,
                                        String geoId, String countryterritoryCode, String popData2018, String  continentExp){
        String tablaCases = "cases_and_deaths";
        String columnaCases = "date";
        try{
                String sqlDeaths = "INSERT INTO cases_and_deaths (date, cases, deaths, geo_id) "
                                + "VALUES(?, ?, ?, ?)";
                PreparedStatement pstmt2 = conexion.prepareStatement(sqlDeaths);


                pstmt2.setString(1, dateRep);
                pstmt2.setInt(2, cases);
                pstmt2.setInt(3, deaths);
                pstmt2.setString(4, geoId);
                pstmt2.executeUpdate();
           
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void obtenerMayorNumMuertesPorPais(){
       /* ResultSet rs = null;
        try{
            String sql1 = "select c.countries_and_territories, (select MAX(cases) from cases_and_deaths where geo_id = c.geo_id) as casos \n" +
                            "from countries c INNER JOIN cases_and_deaths cd ON cd.geo_id = c.geo_id \n" +
                            " group by c.geo_id having casos > " + numCasos+ " order by casos asc";
            
            Statement stmt = conexion.createStatement();
            
            rs = stmt.executeQuery(sql1);
               
                 while(rs.next()){
                     System.out.println("Pais: "+rs.getString("countries_and_territories") + " - Casos: " + rs.getString("casos"));
                } 
               
                
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }*/
    }
   
     public static void obtenerPaisesPorNumCasos(int numCasos){
        Session session = HibernateUtil.getSession();
        String sql1 = "select c.countries_and_territories, (select MAX(cases) from cases_and_deaths where geo_id = c.geo_id) as casos " +
                                    "from countries c INNER JOIN cases_and_deaths cd ON cd.geo_id = c.geo_id " +
                                        "group by c.geo_id having casos > "+numCasos+ " order by casos asc";
        Query sql = session.createNativeQuery(sql1);
        //sql.setParameter("n", numCasos);
        List<Countrie> records = sql.list();
        for(Countrie r :records){
            System.out.println("Pais: " + r);
        }
    }
    
    public static void procesarXml(String nombreXml, String archivoJson){
         XMLReader procesadorXml = null;
         XMLReader procesadorXml2 = null;
        try{
            procesadorXml = XMLReaderFactory.createXMLReader();
            Countriexml countrieXml = new Countriexml();
            //CasesAndDeathsxml casesAndDeathsXml = new CasesAndDeathsxml();
            procesadorXml.setContentHandler(countrieXml);
            //procesadorXml.setContentHandler(casesAndDeathsXml);
            InputSource archivoXml = new InputSource(nombreXml);
            procesadorXml.parse(archivoXml);
            //ArrayList<CasesAndDeaths> casesAndDeaths = casesAndDeathsXml.getCasesAndDeaths();
            ArrayList<Countrie> countries = countrieXml.getCountries();
            Session session = null;
            Transaction tran = null;
            session = HibernateUtil.leerFicheroConfigurarMysql(archivoJson);
            session.beginTransaction();
            Countrie cAnt = null;
            for(Countrie c: countries){                
                if(cAnt == null){
                    session.save(c);
                    cAnt = c;
                }else{
                    if(!c.getGeoId().equals(cAnt.getGeoId())){
                    session.save(c);
                    cAnt = c;
                }  
                }
                                    
                
            }
            session.getTransaction().commit();
                session.close();
               
        } catch (SAXException e){
            System.out.println("Error al leer el XML");
        } catch (IOException e){
            System.out.println("Error al leer el archivo XML");
        }
    }
    
     public static void procesarXml2(String nombreXml, String archivoJson){
         
         XMLReader procesadorXml2 = null;
        try{
            
                //////
                Session session = null;
            Transaction tran = null;
                procesadorXml2 = XMLReaderFactory.createXMLReader();
                CasesAndDeathsxml casesAndDeathsXml = new CasesAndDeathsxml();
              procesadorXml2.setContentHandler(casesAndDeathsXml);
              InputSource archivoXml2 = new InputSource(nombreXml);
            procesadorXml2.parse(archivoXml2);
              ArrayList<CasesAndDeaths> casesAndDeaths = casesAndDeathsXml.getCasesAndDeaths();
           
            session = HibernateUtil.leerFicheroConfigurarMysql(archivoJson);
            session.beginTransaction();
                  
            for(CasesAndDeaths cad: casesAndDeaths){                
                session.save(cad);           
            }
              
            session.getTransaction().commit();
                session.close();
        } catch (SAXException e){
            System.out.println("Error al leer el XML");
        } catch (IOException e){
            System.out.println("Error al leer el archivo XML");
        }
    }
    
    public static boolean contieneDatos(){
        boolean contiene = false;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM countries;";
            Statement stmt = conexion.createStatement();
          
            rs = stmt.executeQuery(sql);     
            if(rs.next() == false){
                contiene = false;
            }else{
                contiene = true;
            }            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return contiene;
    }
   
    
}
