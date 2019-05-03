package by.bsu.example.command.impl;

import by.bsu.example.controller.*;
import by.bsu.example.entity.Course;
import by.bsu.example.entity.Journal;
import by.bsu.example.entity.Login;
import by.bsu.example.command.*;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.JournalLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

public class JournalAdmin extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = JspPagesManager.getProperty(JspPageParamName.JOURNAL_ADMIN_PAGE);

        //String subunit="106";
        String subunit=request.getParameter(CommandParameterName.PARAM_NAME_SUBUNIT);
        if(subunit==null){
            request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_REQUEST_MESSAGE));
            page = JspPagesManager.getProperty(JspPageParamName.ERROR_PAGE);
        }else{


            JournalLogic jl = JournalLogic.getInstance();
            CourseLogic cl = CourseLogic.getInstance();


            try {


                    List<Journal> students = jl.getStudentsListBySubunit(subunit);
                    request.setAttribute("students", students);

            }catch(LogicException e){
                logger.error(e.getMessage());
            }

        }
        return page;
    }
}