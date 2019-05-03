package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.controller.*;
import by.bsu.example.dao.DaoException;
import by.bsu.example.dao.DaoFactory;
import by.bsu.example.dao.impl.CourseDao;
import by.bsu.example.entity.Course;
import by.bsu.example.entity.Login;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.command.Command;
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public class SelectCourseStudent extends AbstractCommand {


    private static final String ACTION_SUBSCRIBE="subscribe";
    private static final String ACTION_UNSUBSCRIBE="unsubscribe";

    @Override
    public String execute(HttpServletRequest request) {
        String page = JspPagesManager.getProperty(JspPageParamName.SELECT_COURSES_PAGE);

        try {
            CourseLogic cl = CourseLogic.getInstance();
            Login userData = (Login) request.getSession().getAttribute(SessionParamName.USER_DATA);
            List<Course> courses = cl.findAllBySubscribeStudent(userData.getId());

            request.setAttribute("courses", courses);
        }catch(LogicException e){
            logger.error(e.getMessage());
        }

        return page;
    }

}
