package by.bsu.example.controller.filters;

import by.bsu.example.controller.SessionParamName;
import by.bsu.example.locales.LocaleController;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.locales.PageContentManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;


public class LocaleFilter implements Filter {
    private static String defaultLocale;

    public void destroy(){};


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();


        String locale=(String)session.getAttribute(SessionParamName.CURRENT_LOCALE);
        if(locale==null){
            locale=this.defaultLocale;
            session.setAttribute(SessionParamName.CURRENT_LOCALE, locale);
        }
        Locale.setDefault(LocaleController.getInstance().getLocaleByLanguage(locale));

        MessageManager.getInstance().setLanguage(locale);
        PageContentManager.getInstance().setLanguage(locale);

        chain.doFilter(req, res);
    }

       public void init(FilterConfig filterConfig)throws ServletException{
        defaultLocale = filterConfig.getInitParameter("defaultLocale"); // ru
    }

}



