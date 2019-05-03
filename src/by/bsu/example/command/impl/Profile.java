package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.controller.SessionParamName;
import by.bsu.example.entity.Command;
import by.bsu.example.entity.Login;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.logic.LogicException;
import by.bsu.example.logic.UserLogic;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;


public class Profile extends AbstractCommand {
    public String execute(HttpServletRequest request) {

        String page = JspPagesManager.getProperty(JspPageParamName.PROFILE_PAGE);


        String pass = request.getParameter(CommandParameterName.PARAM_NAME_PASSWORD);
        String passConfirm = request.getParameter(CommandParameterName.PARAM_NAME_PASSWORD_CONFIRMATION);
        String passOld = request.getParameter(CommandParameterName.PARAM_NAME_PASSWORD_OLD);
        String firstName = request.getParameter(CommandParameterName.PARAM_FIRST_NAME);
        String lastName = request.getParameter(CommandParameterName.PARAM_LAST_NAME);
        String email = request.getParameter(CommandParameterName.PARAM_NAME_EMAIL);
        String tmp=request.getParameter(CommandParameterName.PARAM_NAME_NOT_CHANGE_PASSWORD);
        Boolean changePassNotRequired = new Boolean(tmp);

        if(passOld!=null){
            Login currUser = (Login)request.getSession().getAttribute(SessionParamName.USER_DATA);
            try {
                Login checkUser = UserLogic.getInstance().userLogin(currUser.getLogin(), passOld);
                if(currUser.equals(checkUser)){
                    if(!changePassNotRequired && !pass.equals(passConfirm)){
                        request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_PASSWORD_CONFIRMATION_MESSAGE));
                    }else { //
                        if (!changePassNotRequired) {
                            currUser.setMd5Password(pass);
                        }
                        currUser.setFirstName(firstName);
                        currUser.setLastName(lastName);
                        currUser.setEmail(email);
                        UserLogic.getInstance().updateUser(currUser);
                        request.setAttribute("actionresult", MessageParamName.ACTION_PROFILE_UPDATED);

                        page = JspPagesManager.getProperty(JspPageParamName.PROFILE_UPDATED_PAGE);
                    }
                }else{
                    request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_OLD_PASSWORD_MESSAGE));
                }
            }catch (LogicException e){
                logger.error(e.getMessage());
            }
        }

        return page;
    }
}