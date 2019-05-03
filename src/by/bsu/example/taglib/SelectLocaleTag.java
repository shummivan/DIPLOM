package by.bsu.example.taglib;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

import by.bsu.example.controller.SessionParamName;
import by.bsu.example.locales.SupportedLocale;

public class SelectLocaleTag extends TagSupport{

    public int doStartTag() throws JspException {

        String str="";

        String current_locale=(String)pageContext.getSession().getAttribute(SessionParamName.CURRENT_LOCALE);


        for(SupportedLocale locale: SupportedLocale.values() ){
            if(locale.getLanguage().equals(current_locale)){
                str+= "[<b>"+locale.getLanguageName()+"</b>]";
            }else {
                str += "[<a href=\"?command=locale&v=" + locale.getLanguage() + "\">" + locale.getLanguageName() + "</a>]";
            }
        }

        try {
            JspWriter out = pageContext.getOut();
            out.write(str);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
