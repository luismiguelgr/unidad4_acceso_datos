
package com.mycompany.adrec04;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author miguel
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
    
//GsonFichero.leerFichero("config.json");
        //GsonFichero.escribirFichero("conf.json",true);
       
        /*String json = "{'dbConnection':{'address':'192.168.56.101','port':'3306','name':'hibernate','user':'userhibernate','password':'abc123.'},'hibernate':"
                + "{'driver':'com.mysql.cj.jdbc.Driver','dialect':'org.hibernate.dialect.MySQL5Dialect','HBM2DDL_AUTO':'create','SHOW_SQL': true}}";
        
        Gson gson = new Gson();
        Configuracion conf = gson.fromJson(json, Configuracion.class);
        Configuracion cf = new Configuracion();
        System.out.println(cf.dbConnection.getName());*/
        String nombreXml = "coronavirus.xml";
        String archivoJson = "config.json";
        
        Session session = HibernateUtil.leerFicheroConfigurarMysql(archivoJson);
        //Conexion.procesarXml(nombreXml, archivoJson);
        Query query = session.createQuery("SELECT COUNT(*) FROM Records");
        Long numFilas = (Long) query.uniqueResult();
        if(numFilas <= 0){
            //Conexion.procesarXml(nombreXml, archivoJson);
        }
        menu();
            
        //}
        /*Session session = null;
        Records record = new Records("España", "Europa", 10, 5, 6);
        Records record1 = new Records("España", "Europa", 10, 5, 6);
         Transaction tran = null;
        try{
            //Session session = HibernateUtil.setSessionFactory(ip, puerto, baseDatos, usuario, pass, driver, dialect, hbm2ddl_auto, show_sql).openSession();
            session = HibernateUtil.leerFicheroConfigurarMysql(archivo);
            tran = session.beginTransaction();
            
            session.save(record);
            
            tran.commit();
        }catch(HibernateException e){
            e.printStackTrace();
        }*/
    }
    
     public static void menu(){     
       Scanner sc = new Scanner(System.in);
       boolean salir =  false;
       int opcion;
       
       while(!salir){
           try{
                System.out.println("\nMenu\n");  
                System.out.println("1. Paises por número de casos / 2. Día con mayores muertes / 3. Sair");
                System.out.println("Escolla opcion");
                opcion = sc.nextInt();

                switch(opcion){
                    case 1:
                        System.out.println("Introduzca número de casos\n");
                        int numCasos = sc.nextInt();
                        System.out.println("Listado: \n");
                        Conexion.obtenerPaisesPorNumCasos(numCasos);   
                        break;
                    case 2:
                        System.out.println("Listado: \n");
                        Conexion.obtenerMayorNumMuertesPorPais();  
                        break;
                    case 3:
                        Conexion.desconetarBaseDatos();
                        System.out.println("Saliendo...");
                        salir=true;
                        break;
                }
           } catch (InputMismatchException e){
               System.out.println("Solo se admiten numeros");
               sc.next();
           }
       }
    }
    
    
}
