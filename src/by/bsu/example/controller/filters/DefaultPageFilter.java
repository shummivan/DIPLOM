package by.bsu.example.controller.filters;


import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.SessionParamName;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DefaultPageFilter  implements  Filter {

    private static String defaultCommand;

    public void destroy(){};

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        if(session.getAttribute(SessionParamName.USER_DATA)!=null){
            request.setAttribute(CommandParameterName.PARAM_NAME_DEFAULT_PAGE, JspPagesManager.getProperty(JspPageParamName.MAIN_PAGE));
        }else{

            request.setAttribute(CommandParameterName.PARAM_NAME_DEFAULT_PAGE, JspPagesManager.getProperty(JspPageParamName.COURSES_VIEW_PAGE));
            if(this.defaultCommand!=null && !this.defaultCommand.equals("")){

                String comm=(String)request.getParameter(CommandParameterName.COMMAND_NAME);
                if(comm==null){
                    request.setAttribute(CommandParameterName.COMMAND_NAME,this.defaultCommand);
                }
            }

        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig)throws ServletException{
        this.defaultCommand = filterConfig.getInitParameter("defaultCommand");
    }
}
