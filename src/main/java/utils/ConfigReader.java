package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {


    public static String PropertyReaderMethod(String filepath, String key)
    {
        String value = null;
        //InputStream is required while loading into the properties file
        try (InputStream Input = new FileInputStream(filepath)) {
            //object creation for properties class
            Properties properties = new Properties();
            //Load the properties file
            properties.load(Input);
            //getproperty wil fetch the values accordingly to the key
            value = properties.getProperty(key);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return value;
    }
}