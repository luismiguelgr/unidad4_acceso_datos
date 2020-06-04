
package com.mycompany.adrec04;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author miguel
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
  
        String nombreXml = "coronavirus.xml";
        String archivoJson = "config.json";
        
        Session session = HibernateUtil.leerFicheroConfigurarMysql(archivoJson);

        //Query query = session.createQuery("SELECT COUNT(*) FROM countries");
        //Long numFilas = (Long) query.uniqueResult();
        //if(numFilas <= 0){
            Conexion.procesarXml(nombreXml, archivoJson);
        //}
        menu();
     
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
