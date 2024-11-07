package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class will be used to retrieve values we do not want to hardcode (usernames, passwords, etc..) from the config.properties file.
 */
public class Config {
    private final Properties properties = new Properties();

    public Config(){
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")){
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("IO exception trying to access config properties", e);
        }
    }

    public String getDBUsername(){
        return properties.getProperty("DB.username");
    }

    public String getDBPassword(){
        return properties.getProperty("DB.password");
    }

    public String getDBUrl(){
        return properties.getProperty("DB.url");
    }

    public String getEmailUsername(){return properties.getProperty("email.username");}

    public String getEmailPassword(){return properties.getProperty("email.password");}

    public String getEmailHost(){return properties.getProperty("smtp.host");}

    public int getEmailPort(){
        String port = properties.getProperty("smtp.port");
        return Integer.parseInt(port);
    }
}





