package by.bsu.example.manager;

import java.util.ResourceBundle;


public class JspPagesManager {
    private static final String RESOURCE_JSPPAGES_BUNDLE="resources.jsppages";
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle(RESOURCE_JSPPAGES_BUNDLE);

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
