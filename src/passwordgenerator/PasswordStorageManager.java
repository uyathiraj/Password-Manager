/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uyat
 */
public class PasswordStorageManager {
    public PasswordStorageManager()
    {
        new File("C:\\ProgramData\\Password Manager").mkdir();
    }
    public  void storePassword(String userName,String environment,String password)
    {
        Properties props = new Properties();
        props.setProperty(userName+","+environment, password);
        
        File f = new File("C:\\ProgramData\\Password Manager\\credentials.properties");
        OutputStream out=null;
        try {
            out = new FileOutputStream(f,true);
             props.save(out, environment); 
             out.close();
        } catch (FileNotFoundException ex) {
          System.out.println(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }
    public String getConfigValue(String key)
    {
        Properties props = new Properties();
        InputStream is = null;
        try 
        {
            File f = new File("C:\\ProgramData\\Password Manager\\config.properties");
            if(!f.exists())
                return null;
            is = new FileInputStream(f);
        } catch (Exception e) {
            is = null;
        }
        try {
            if (is == null) 
            {
                // Try loading from classpath
                is = getClass().getResourceAsStream("C:\\ProgramData\\Password Manager\\credentials.properties");
            }
            // Try loading properties from the file (if found)
            props.load(is);
            is.close();
        } catch (Exception e) {
        }
        return props.getProperty(key,null);
    }
    public void setConfigValue(String key,String value) throws IOException
    {
        Properties props = new Properties();
        
        
        File f = new File("C:\\ProgramData\\Password Manager\\config.properties");
        OutputStream out=null;
        InputStream is = null;
         
        try {
            is = new FileInputStream(f);
            props.load(is);
            props.setProperty(key, value);
            out = new FileOutputStream(f);
             props.save(out,"Configuration "); 
             
        } catch (FileNotFoundException ex) {
          System.out.println(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            out.close();
            is.close();
        }
       
    }
    public void setConfigValue(Properties props) throws IOException
    {
        OutputStream out=null;
        try {
            File f = new File("C:\\ProgramData\\Password Manager\\config.properties");
            
            out = new FileOutputStream(f); 
            props.save(out,"Configuration ");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PasswordStorageManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            out.close();
           
        }
    }
    public String getPassword(String  userName,String environment)
    {
             
        Properties props = new Properties();
        InputStream is = null;
        try 
        {
            File f = new File("C:\\ProgramData\\Password Manager\\credentials.properties");
            is = new FileInputStream(f);
        } catch (Exception e) {
            is = null;
        }
        try {
            if (is == null) 
            {
                // Try loading from classpath
                is = getClass().getResourceAsStream("C:\\ProgramData\\Password Manager\\credentials.propertiess");
            }
            // Try loading properties from the file (if found)
            
            props.load(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Propppp : "+props.isEmpty());
        String value = props.getProperty(userName+","+environment);
        System.out.println("Stored pass : " +value);
        return value;
    }

}
