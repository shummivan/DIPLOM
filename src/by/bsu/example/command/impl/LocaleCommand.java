package by.bsu.example.command.impl;

import by.bsu.example.command.Command;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.SessionParamName;
import by.bsu.example.locales.LocaleController;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.locales.SupportedLocale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String newLocale = (String)request.getParameter(CommandParameterName.PARAM_LOCALE_LANGUAGE);

        if(newLocale!=null){
            for(SupportedLocale locale: SupportedLocale.values()){
                if(locale.getLanguage().equals(newLocale)){

                    Locale.setDefault(LocaleController.getInstance().getLocaleByLanguage(newLocale));

                    request.getSession().setAttribute(SessionParamName.CURRENT_LOCALE,newLocale);
                    MessageManager.getInstance().setLanguage(newLocale);
                    break;
                }
            }
        }
        return null;
    }
}
