package by.bsu.example.manager;

import java.util.ResourceBundle;


public class DBResourceManager {
    private final static String RESOURCE_DB_PARAMS_BUNDLE="resources.db";
    private final static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_DB_PARAMS_BUNDLE);

    private DBResourceManager() { }
    public String getValue(String key) {
        return resourceBundle.getString(key);
    }
    public static DBResourceManager getInstance(){
        return instance;
    }
}
