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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class JournalCommand extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = JspPagesManager.getProperty(JspPageParamName.JOURNAL_PAGE);

        String requestId=request.getParameter(CommandParameterName.PARAM_NAME_ID);
        if(requestId==null){
            request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_REQUEST_MESSAGE));
            page = JspPagesManager.getProperty(JspPageParamName.ERROR_PAGE);
        }else{

            Integer itemId=new Integer(requestId);

            JournalLogic jl = JournalLogic.getInstance();
            CourseLogic cl = CourseLogic.getInstance();


            try {
                Course course = cl.getForEdit(itemId);
                request.setAttribute("course", course);

                Login teaacher = (Login) request.getSession().getAttribute(SessionParamName.USER_DATA);
                if (course == null || course.getTeacherId() != teaacher.getId()) {
                    request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_REQUEST_MESSAGE));
                    page = JspPagesManager.getProperty(JspPageParamName.ERROR_PAGE);
                } else {
                    List<Journal> students = jl.getStudentsList(course.getId());
                    List<Journal> marks = jl.getStudentsMarks(course.getId());
                    List<Journal> days = jl.getDays(course.getId());
                    
                    request.setAttribute("students", students);
                    request.setAttribute("marks", marks);
                    request.setAttribute("days", days);
                }
            }catch(LogicException e){
                logger.error(e.getMessage());
            }

        }
        return page;
    }
}