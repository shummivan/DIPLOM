package by.bsu.example.locales;

import by.bsu.example.controller.SessionParamName;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


public class LocaleController {

    private volatile static LocaleController instance=null;

    private LocaleController(){}

    public static LocaleController getInstance(){
        if(instance==null){
            synchronized (LocaleController.class){
                if(instance == null) {
                    instance = new LocaleController();
                }
            }
        }
        return instance;
    }


    public Locale getLocaleByLanguage(String language){
        Locale localeObj=null;
        for(SupportedLocale locale: SupportedLocale.values()) {
            if (locale.getLanguage().equals(language)) {
                localeObj=new Locale(locale.getLanguage(), locale.getCountry());
                break;
            }
        }
        return localeObj;
    }

    public Locale getLocaleByLocaleStr(String localeSre){
        Locale localeObj=null;

        for(SupportedLocale locale: SupportedLocale.values()) {
            String currStr=locale.getLanguage()+"_"+locale.getCountry();
            if (currStr.equals(localeSre)) {
                localeObj=new Locale(locale.getLanguage(), locale.getCountry());
                break;
            }
        }
        return localeObj;
    }


    public String getLocaleStringByLanguage(String language){
        String localeStr=null;
        for(SupportedLocale locale: SupportedLocale.values()) {
            if (locale.getLanguage().equals(language)) {
                localeStr=locale.getLanguage()+"_"+locale.getCountry();
                break;
            }
        }
        return localeStr;
    }


    public String getCurrentLocale(HttpServletRequest request){

        return (String)request.getSession().getAttribute(SessionParamName.CURRENT_LOCALE);
    }


}
