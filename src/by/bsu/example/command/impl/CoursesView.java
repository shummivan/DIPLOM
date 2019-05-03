package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;


public class CoursesView extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {
        CourseLogic cl = CourseLogic.getInstance();
        try {
            request.setAttribute("courses", cl.getAll());
        }catch (LogicException e){
            logger.error(e.getMessage());
        }
        return JspPagesManager.getProperty(JspPageParamName.COURSES_VIEW_PAGE);
    }
}
