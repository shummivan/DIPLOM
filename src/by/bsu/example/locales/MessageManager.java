package by.bsu.example.locales;

import sun.plugin2.message.Message;

import java.util.Locale;
import java.util.ResourceBundle;


public class MessageManager {
    private static final String RESOURCE_MESSAGE_BUNDLE="resources.messages";
    private ResourceBundle resourceBundle;
    private volatile static MessageManager instance=null;
    public static MessageManager getInstance(){
        if(instance==null){
            synchronized (MessageManager.class){
                if(instance == null){
            instance=new MessageManager();}
            }
        }
        return instance;
    }
    private MessageManager() {

    }
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
    public String getMessage(String key){
        return resourceBundle.getString(key);
    }
    public void setLanguage(String language){
        Locale locale = LocaleController.getInstance().getLocaleByLanguage(language);
        resourceBundle = resourceBundle.getBundle(RESOURCE_MESSAGE_BUNDLE, locale);
    }
}
