package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static Properties getProperties(String filePropertiesName) {
        Properties properties = null;
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(filePropertiesName)) {

            properties = new Properties();
            properties.load(input);

            return properties;
        } catch (Exception ignored) {
        }
        return new Properties();
    }
}
