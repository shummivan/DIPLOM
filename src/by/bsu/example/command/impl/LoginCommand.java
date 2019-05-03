package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.command.Command;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.controller.SessionParamName;
import by.bsu.example.entity.Login;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.logic.LogicException;
import by.bsu.example.logic.UserLogic;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class LoginCommand extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(CommandParameterName.PARAM_NAME_LOGIN);
        String pass = request.getParameter(CommandParameterName.PARAM_NAME_PASSWORD);

        UserLogic logic = UserLogic.getInstance();
        try {

            if (login != null && pass != null) {
                Login user = logic.userLogin(login, pass);
                if (user == null) {
                    request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_LOGIN_OR_PASSWORD_MESSAGE));
                    page = JspPagesManager.getProperty(JspPageParamName.LOGIN_PAGE);
                } else {

                    user.setLastLogin(new Date());
                    logic.updateUser(user);
                    request.getSession().setAttribute(SessionParamName.USER_DATA, user);
                    request.getSession().setAttribute(SessionParamName.USER_ACCESS,
                            logic.getUserAccessByRoleId(user.getRoleId()));
                }
            } else {
                page = JspPagesManager.getProperty(JspPageParamName.LOGIN_PAGE);
            }
        }catch(LogicException e){
            logger.error(e.getMessage());
        }
        return page;
    }
}
