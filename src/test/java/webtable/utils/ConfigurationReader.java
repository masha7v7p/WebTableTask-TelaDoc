package webtable.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties = new Properties();

    static {
        try {
            // Open the file using input stream
            FileInputStream inputStream = new FileInputStream("configuration.properties");
            // load to properties object
            properties.load(inputStream);
            // close the file after loading to free up memory
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occured while reading configuration file");
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
