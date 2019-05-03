package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.logic.LogicException;
import by.bsu.example.logic.UserLogic;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;


public class UsersView extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {
        UserLogic cl=UserLogic.getInstance();
        try {
            request.setAttribute("items", cl.getAll());
        }catch (LogicException e){
            logger.error(e.getMessage());
        }
        return JspPagesManager.getProperty(JspPageParamName.USERS_VIEV_PAGE);
    }
}
