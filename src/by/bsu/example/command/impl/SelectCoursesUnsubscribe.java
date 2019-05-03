package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.command.Command;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.controller.SessionParamName;
import by.bsu.example.entity.Course;
import by.bsu.example.entity.Login;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class SelectCoursesUnsubscribe extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = JspPagesManager.getProperty(JspPageParamName.SELECT_COURSES_PAGE);

        String id=request.getParameter(CommandParameterName.PARAM_NAME_ID);
        Integer courseId=new Integer(id);
        if(id!=null) {
            CourseLogic cl=CourseLogic.getInstance();
            Login login = (Login)request.getSession().getAttribute(SessionParamName.USER_DATA);
            try {
                cl.unSubscribe(courseId, login.getId());
                request.setAttribute("actionresult", MessageManager.getInstance().getMessage(MessageParamName.UNSUBSCRIBED_OK_MESSAGE));
                page = JspPagesManager.getProperty(JspPageParamName.SUBSCRIBE_ACTION_FINISHED_PAGE);
            }catch(LogicException e){
                logger.error(e.getMessage());
            }
        }else{
            request.setAttribute("errorMsg",MessageManager.getInstance().getMessage(MessageParamName.WRONG_REQUEST_MESSAGE));
        }

        return page;
    }

}
