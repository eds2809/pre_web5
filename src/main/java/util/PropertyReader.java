package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static Properties getProperties(String filePropertiesname) {
        Properties properties = null;
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(filePropertiesname)) {

            properties = new Properties();
            properties.load(input);

            return properties;
        } catch (Exception ignored) {
        }
        return new Properties();
    }
}
