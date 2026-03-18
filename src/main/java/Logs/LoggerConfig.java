/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
        

/**
 *
 * @author M2200478
 */
public class LoggerConfig {
   private static final String CONFIG_FILE  = "log.properties";
   private static final boolean initialised = false;
   
   private LoggerConfig(){  }
   
   public static void setup(){
       if(initialised) {//to avoid overwritting files
           return;
       }
       try (FileInputStream fis = new FileInputStream(CONFIG_FILE)){
           LogManager.getLogManager().readConfiguration(fis);
       } catch (IOException e) {
           System.err.println("Could not load logger config: " + e.getMessage());
       }
   }
   
   public static Logger getLogger(Class<?> cls) {
       setup();
       Logger log = Logger.getLogger(cls.getName());
       log.setUseParentHandlers(false);
       return log;
   }
   
   }
