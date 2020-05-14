/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adrec04;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author miguel
 */
public class GsonFichero {
    public static ConfiguracionHibernate configuracion;
    
     public static void leerFichero(String nombre_fichero) throws FileNotFoundException{
        String strCurrentLine = "";
         Path path_fichero = Paths.get(nombre_fichero);
        BufferedReader bufferedReader = null;
        if(Files.exists(path_fichero)){
            FileReader fr = new FileReader(nombre_fichero);       
            bufferedReader = new BufferedReader(fr);            
        }else{
            escribirFichero(nombre_fichero);
            FileReader fr = new FileReader(nombre_fichero);       
            bufferedReader = new BufferedReader(fr);    
        }       
        Gson gson = new Gson();
        try {
            if(null == bufferedReader.readLine()){
                    escribirFichero(nombre_fichero);                
            }            
            
            JsonReader jReader = new JsonReader(bufferedReader);
            while ((strCurrentLine = bufferedReader.readLine()) != null) {    
                System.out.println(strCurrentLine);  
            } 
            
             
            System.out.println(jReader);
            configuracion = gson.fromJson(new FileReader(nombre_fichero), ConfiguracionHibernate.class);
            
        } catch (IOException ex) {
            System.out.println("Error");
        }
        
    }  
    
    public static void escribirFichero(String nombre_fichero){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String cadena = gson.toJson(configuracion);
        
        File fichero = new File(nombre_fichero); 
        if(fichero.exists()){
            try{
                    FileWriter datos = new FileWriter(fichero);
                    BufferedWriter fichero_buffer = new BufferedWriter(datos);
                    fichero_buffer.write(cadena);
                    fichero_buffer.close();
            }catch(IOException e){
                System.out.println("\nError con el fichero\nError: " + e);
            }
        }else{  
            try{
            File fichero_nuevo = new File(nombre_fichero);
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero_nuevo));
            }catch(IOException e){
                System.out.println("error");
            }
        }
    }
}
