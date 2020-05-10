package com.mycompany.adrec04;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
         String baseDatos = "jdbc:sqlite:/home/miguel/" + nombre;
        
        try{
            conexion = DriverManager.getConnection(baseDatos);
            if(conexion != null){
                //DatabaseMetaData meta = connection.getMetaData();
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
          String sql = "CREATE TABLE IF NOT EXISTS record (\n" +
                            "id int PRIMARY KEY,\n"+
                            "dateRep varchar(50) ,\n"+
                            "day int ,\n"+
                            "month int ,\n"+
                            "year int ,\n"+
                            "cases int ,\n"+
                            "deaths int ,\n"+
                            "countriesAndTerritories varchar(50),\n"+
                            "geoId varchar(50) ,\n"+
                            "countryterritoryCode varchar(50),\n"+
                            "popData2018 varchar(50),\n"+
                            "continentExp varchar(50)\n"+
                            ");";

          Statement stmt = conexion.createStatement();
          stmt.execute(sql);
          System.out.println("Tabla creada.");
      }
      catch(SQLException e){
          System.out.println(e.getMessage());
      }
        
    }
    
    public static void insertarValores(String tabla, String dateRep, int day, int month,
                                        int year, int cases, int deaths, String countriesAndTerritories,
                                        String geoId, String countryterritoryCode, String popData2018, String  continentExp){
        try{
            String sql = "INSERT INTO "+tabla+"(dateRep, day, month, year, cases, deaths, countriesAndTerritories, "
                                             + "geoId, countryterritoryCode, popData2018, continentExp) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);

            pstmt.setString(1, dateRep);
            pstmt.setInt(2, day);
            pstmt.setInt(3, month);
            pstmt.setInt(4, year);
            pstmt.setInt(5, cases);
            pstmt.setInt(6, deaths);
            pstmt.setString(7, countriesAndTerritories);
            pstmt.setString(8, geoId);
            pstmt.setString(9, countryterritoryCode);
            pstmt.setString(10, popData2018);
            pstmt.setString(11, continentExp);
            pstmt.executeUpdate();
            System.out.println(tabla + " se añadió correctamente.");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void obtenerPaisesPorNumCasos(int numCasos){
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM record WHERE cases > ? GROUP BY countriesAndTerritories ORDER BY cases;";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            
            pstmt.setInt(1, numCasos);
            rs = pstmt.executeQuery();     
            if(rs.next() == false){
                System.out.println("No se han encontrado paises");
            }else{
                while(rs.next()){
                System.out.println("Pais: "+rs.getString("countriesAndTerritories") + " - Casos: " + rs.getString("cases"));
                }
            }            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
     public static void obtenerMayorNumMuertesPorPais(){
        ResultSet rs = null;
        try{
            String sql = "SELECT MAX(deaths) AS deaths, countriesAndTerritories, day FROM record GROUP BY countriesAndTerritories ORDER BY deaths;";
            Statement stmt = conexion.createStatement();
          
            rs = stmt.executeQuery(sql);     
            if(rs.next() == false){
                System.out.println("No se han encontrado paises");
            }else{
                while(rs.next()){
                    System.out.println("Pais: "+rs.getString("countriesAndTerritories") + " - Muertes: " + rs.getInt("deaths") + " - Día: " + rs.getInt("day"));
                }
            }            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void procesarXml(String nombreXml, String archivoJson){
         XMLReader procesadorXml = null;
        try{
            procesadorXml = XMLReaderFactory.createXMLReader();
            Recordxml recordXml = new Recordxml();
            procesadorXml.setContentHandler(recordXml);
            InputSource archivoXml = new InputSource(nombreXml);
            procesadorXml.parse(archivoXml);
            ArrayList<Records> records = recordXml.getRecords();
            Session session = null;
            Transaction tran = null;
            session = HibernateUtil.leerFicheroConfigurarMysql(archivoJson);
         // tran = session.beginTransaction();
          session.beginTransaction();
            for(Records r: records){                                 
                 
               //record = new Records(r.getCountriesAndTerritories(), r.getContinentExp(), r.getCases(), r.getDeaths(), r.getDay());
                            
                session.save(r);
           
            }
            //session.getTransaction().commit();
             //tran.commit();
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
            String sql = "SELECT * FROM record;";
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
    /*
     public static void crearBaseDatos(String nombreBaseDatos){

        String directorio = "/home/miguel/" + nombreBaseDatos;
        String url = "jdbc:sqlite:"+directorio;

        File ficheroBaseDatos = new File(directorio);
        if (!ficheroBaseDatos.exists()) {
            
            
            System.out.println("Base de datos no existe.");
        }
              
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String schema = conn.getSchema();
                System.out.println("Prueba");
                System.out.println(schema);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
}