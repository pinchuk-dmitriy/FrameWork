package service;

import java.util.ResourceBundle;

public class TestDataReader {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key) {
        System.out.println(key);
        String resource = resourceBundle.getString(key);

        return resource.equals("NONE") ? "" : resource;
    }
}