package by.bsu.example.command.impl;

import by.bsu.example.command.*;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;


public class CoursesDelete extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = JspPagesManager.getProperty(JspPageParamName.COURSE_EDIT_PAGE);
        CourseLogic cl = CourseLogic.getInstance();
        try {
            if (cl.deleteCourse(new Integer(request.getParameter(CommandParameterName.PARAM_NAME_ID)))) {
                request.setAttribute("actionresult", MessageParamName.ACTION_COURSE_DELETED);
                page = JspPagesManager.getProperty(JspPageParamName.COURSES_ACTION_FINISHED_PAGE);
            }
        }catch (LogicException e){
            logger.error(e.getMessage());
        }
        return page;
    }
}
