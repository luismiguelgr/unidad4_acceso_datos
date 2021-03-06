/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adrec04;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author miguel
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;
    static String ip;
     static String puerto ;
       static  String baseDatos;
        static String usuario ;
        static String pass;
        static String driver;
        static String dialect;
       static  String hbm2ddl_auto;
        static boolean show_sql;
  
    public static Session getSession(){
        session = HibernateUtil.setSessionFactory(ip, puerto, baseDatos, usuario, pass, driver, dialect, hbm2ddl_auto, show_sql).openSession();
        return session;
    }
    
    public static SessionFactory setSessionFactory(String ip, String puerto, String baseDatos, String usuario, String pass, 
                                        String driver, String dialect,  String hbm2ddl_auto, boolean show_sql) {
        
        String url = "jdbc:mysql://"+ip+":"+puerto+"/"+baseDatos;
        if (sessionFactory == null) {
            try {
                
                Configuration configuration = new Configuration();
                
                // Configuracion del archivo hibernate.cfg.xml
                Properties settings = new Properties();

                //settings.put(Environment.URL, url+"?serverTimezone="+TimeZone.getDefault().getID());
                //settings.put(Environment.URL, url+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                settings.put(Environment.URL, url+"?createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8&serverTimezone="+TimeZone.getDefault().getID());
                settings.put(Environment.USER, usuario);
                
                settings.put(Environment.PASS, pass);
                
                settings.put(Environment.DRIVER, driver);
                
                settings.put(Environment.DIALECT, dialect);
                
                settings.put(Environment.HBM2DDL_AUTO, hbm2ddl_auto);
                
                settings.put(Environment.SHOW_SQL, show_sql);

                //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Countrie.class);
                configuration.addAnnotatedClass(CasesAndDeaths.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()

                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                //sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
    
    public static Session leerFicheroConfigurarMysql(String archivoConfig){
        String fichero = "";
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoConfig))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
                System.out.println(linea);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Configuracion conf = gson.fromJson(fichero, Configuracion.class);
        
        String ip = conf.dbConnection.getAddress();
        String puerto = conf.dbConnection.getPort();
        String baseDatos = conf.dbConnection.getName();
        String usuario = conf.dbConnection.getUser();
        String pass = conf.dbConnection.getPassword();
        String driver = conf.hibernate.getDriver();
        String dialect = conf.hibernate.getDialect();
        String hbm2ddl_auto = conf.hibernate.getHBM2DDL_AUTO();
        boolean show_sql = conf.hibernate.isSHOW_SQL();
        //HibernateUtil.setSessionFactory(ip, puerto, baseDatos, usuario, pass, driver, dialect, hbm2ddl_auto, show_sql);
        session = HibernateUtil.setSessionFactory(ip, puerto, baseDatos, usuario, pass, driver, dialect, hbm2ddl_auto, show_sql).openSession();
        return session;
    }
    
}
