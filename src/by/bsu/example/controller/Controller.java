package by.bsu.example.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.bsu.example.command.*;
import by.bsu.example.manager.JspPagesManager;


public class Controller extends HttpServlet {
    private  int counter=0;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String page;
        page = (String)request.getAttribute(CommandParameterName.PARAM_NAME_DEFAULT_PAGE);
        request.setAttribute("contextPath", getServletContext().getContextPath());


        Command command = CommandFactory.getInstance().defineCommand(request);


        if(command != null) {
            page = command.execute(request);
        }




        if (page != null & request.getParameter("isRedirect")==null) {
            //page = getServletContext().getContextPath() + page;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {

            if(request.getParameter("isRedirect")!=null){

                response.sendRedirect(getServletContext().getContextPath()
                        + request.getParameter("redirectUrl"));
            }
            else {

                response.sendRedirect(getServletContext().getContextPath()
                        + JspPageParamName.APPLICATION_CONTEXT);
            }
        }
    }
}

