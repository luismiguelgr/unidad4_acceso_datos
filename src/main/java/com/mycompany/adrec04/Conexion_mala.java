/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adrec04;

import javax.imageio.spi.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author miguel
 */
public class Conexion_mala {
    
    public static void SessionFactory(String ip, String puerto, String baseDatos, String usuario, String pass){ 
                                        //String driver, String dialect,  String hbm2ddl_auto, boolean show_sql) {
    try {
        SessionFactory sessionFactory;
        Configuration configuracion = new Configuration();
        configuracion.configure();
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
        configuracion.getProperties()).build();

    //sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);
         //conf = new AnnotationConfiguration().configure();
        // conf = new Configuration().configure("/hib.cfg.xml").buildSessionFactory();
      // <!-- Database connection settings -->
        String url = "jdbc:mysql://"+ip+":"+puerto+"/"+baseDatos;
        System.out.println(url);
        configuracion.setProperty("hibernate.connection.url", url);
        configuracion.setProperty("hibernate.connection.username", usuario);
        configuracion.setProperty("hibernate.connection.password", pass);
        sessionFactory = configuracion.buildSessionFactory();

    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
    
  }
}
