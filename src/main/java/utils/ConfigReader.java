package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    public ConfigReader() {
        try {
            String path=System.getProperty("user.dir") + "/src/test/resources/config.properties";

            FileInputStream fis=new FileInputStream(path);
            prop=new Properties();
            prop.load(fis);
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public String getBaseUrl() {
        return getValue("baseUrl");
    }

    public String getBrowser() {
        return getValue("browser");
    }

    public int getTimeout() {
        return Integer.parseInt(getValue("timeout"));
    }

    public String getUsername() {
        return getValue("username");
    }

    public String getPassword() {
        return getValue("password");
    }

    public String getTestDataPath() {
        return System.getProperty("user.dir") + "/" + getValue("testDataPath");
    }

    // 🔹 Common method (avoids duplication)
    private String getValue(String key) {
        String value = prop.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Key not found in config.properties: " + key);
        }

        return value.trim();
    }
}