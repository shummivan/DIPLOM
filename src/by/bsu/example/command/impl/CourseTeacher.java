package by.bsu.example.command.impl;

import by.bsu.example.command.*;
import by.bsu.example.controller.*;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;
import javax.servlet.http.HttpServletRequest;
import by.bsu.example.entity.Login;

public class CourseTeacher extends AbstractCommand  {
    public String execute(HttpServletRequest request) {

        Login userData = (Login) request.getSession().getAttribute(SessionParamName.USER_DATA);
        CourseLogic cl= CourseLogic.getInstance();

        try {
            request.setAttribute("courses", cl.getAllByIdTeacher(userData.getId()));
        }catch (LogicException e){
            logger.error(e.getMessage());
        }
        return JspPagesManager.getProperty(JspPageParamName.COURSES_PAGE);
    }
}
