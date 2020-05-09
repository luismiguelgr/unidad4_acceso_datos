
package com.mycompany.adrec04;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

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
        String fichero = "";
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader("config.json"))) {
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
        System.out.println(conf.dbConnection.getAddress());
        System.out.println(conf.dbConnection.getPort());
        System.out.println(conf.dbConnection.getName());
        System.out.println(conf.dbConnection.getUser());
        System.out.println(conf.dbConnection.getPassword());
    }
    
}
