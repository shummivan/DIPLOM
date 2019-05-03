package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.logic.UserLogic;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;


public class UserDelete extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = JspPagesManager.getProperty(JspPageParamName.USERS_VIEV_PAGE);
        UserLogic ul = UserLogic.getInstance();
        try {
            if (ul.delete(new Integer(request.getParameter(CommandParameterName.PARAM_NAME_ID)))) {
                request.setAttribute("actionresult", MessageParamName.ACTION_USER_DELETED);
                page = JspPagesManager.getProperty(JspPageParamName.USER_ACTION_FINISHED_PAGE);
            }
        }catch (LogicException e){
            logger.error(e.getMessage());
        }
        return page;
    }
}
