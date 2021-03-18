package OrangeHRM.Utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * reads the properties file configuration.properties
 */
public class ConfigurationReader {

    private static Properties properties;
    private static Map<String,String>PROPERTIESMAP = new HashMap<>();

    //static block is a way to initialise the static variables
    //eager initialization
    static {

        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            for(Object key : properties.keySet()){
                PROPERTIESMAP.put(String.valueOf(key),PROPERTIESMAP.get(key));
            }
           properties.forEach((key, value) -> PROPERTIESMAP.put(String.valueOf(key), String.valueOf(value)));

            input.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }

    public static String getFromMAP(String key){
        return PROPERTIESMAP.get(key);
    }
}